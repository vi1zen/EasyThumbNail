package cn.vi1zen.transfereeimage.activity

import android.content.Context
import android.graphics.Color
import android.util.Pair
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.vi1zen.transferee.indicator.index.NumberIndexIndicator
import cn.vi1zen.transferee.indicator.progress.ProgressBarIndicator
import cn.vi1zen.transferee.transfer.TransferConfig
import cn.vi1zen.transferee.transfer.TransferConfig.Companion.build
import cn.vi1zen.transfereeimage.DividerGridItemDecoration
import cn.vi1zen.transfereeimage.R
import cn.vi1zen.transfereeimage.SourceConfig
import com.bumptech.glide.Glide
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder

/**
 * 仿朋友圈
 *
 *
 * Created by Vans Z on 2020/4/16.
 */
class FriendsCircleActivity : BaseActivity() {
    override fun getContentView(): Int {
        return R.layout.activity_recycler_view
    }

    override fun initView() {
        val rvImages = findViewById<RecyclerView>(R.id.rv_images)
        rvImages.layoutManager = LinearLayoutManager(this)
        rvImages.adapter = FriendsCircleAdapter()
    }

    override fun testTransferee() {}
    private fun getBuilder(pos: Int): TransferConfig.Builder {
        val builder = build()
                .setProgressIndicator(ProgressBarIndicator())
                .setIndexIndicator(NumberIndexIndicator())
        if (pos == 4) {
            builder.enableHideThumb(false)
        } else if (pos == 5) {
            builder.enableJustLoadHitPage(true)
        } else if (pos == 6) {
            builder.enableDragPause(true)
        }
        return builder
    }

    /**
     * 朋友圈列表数据适配器
     */
    private inner class FriendsCircleAdapter internal constructor() : CommonAdapter<Pair<String, List<String>>>(this@FriendsCircleActivity, R.layout.item_friends_circle, SourceConfig.getFriendsCircleList(this@FriendsCircleActivity)) {
        private val divider = DividerGridItemDecoration(
                Color.TRANSPARENT,
                10,
                10
        )

        override fun convert(viewHolder: ViewHolder, item: Pair<String, List<String>>, position: Int) {
            viewHolder.setText(R.id.tv_content, item.first)
            val rvPhotos = viewHolder.getView<RecyclerView>(R.id.rv_photos)
            // 重置 divider
            rvPhotos.removeItemDecoration(divider)
            rvPhotos.addItemDecoration(divider)
            if (rvPhotos.layoutManager == null) rvPhotos.layoutManager = GridLayoutManager(this@FriendsCircleActivity, 3)
            val photosAdapter = PhotosAdapter(
                    this@FriendsCircleActivity,
                    R.layout.item_image,
                    item.second
            )
            photosAdapter.setOnItemClickListener(object : OnItemClickListener {
                override fun onItemClick(view: View, holder: RecyclerView.ViewHolder, pos: Int) {
                    transferee.apply(getBuilder(position)
                            .setNowThumbnailIndex(pos)
                            .setSourceModelList(item.second?.map { it.toString() } ?: emptyList<String>())
                            .bindRecyclerView(view.parent as RecyclerView, R.id.iv_thum)
                    ).show()
                }

                override fun onItemLongClick(view: View, viewHolder: RecyclerView.ViewHolder, i: Int): Boolean {
                    return false
                }
            })
            rvPhotos.adapter = photosAdapter
        }
    }

    /**
     * 单个 item 中照片数据适配器
     */
    private class PhotosAdapter internal constructor(context: Context?, layoutId: Int, datas: List<String?>?) : CommonAdapter<String?>(context, layoutId, datas) {
        override fun convert(holder: ViewHolder, url: String?, position: Int) {
            val imageView = holder.getView<ImageView>(R.id.iv_thum)
            Glide.with(imageView)
                    .load(url)
                    .placeholder(R.drawable.ic_empty_photo)
                    .into(imageView)
        }
    }
}