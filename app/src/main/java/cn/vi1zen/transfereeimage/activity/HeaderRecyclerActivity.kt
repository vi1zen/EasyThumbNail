package cn.vi1zen.transfereeimage.activity

import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.vi1zen.transferee.indicator.index.CircleIndexIndicator
import cn.vi1zen.transferee.indicator.progress.ProgressBarIndicator
import cn.vi1zen.transferee.transfer.TransferConfig.Companion.build
import cn.vi1zen.transfereeimage.R
import cn.vi1zen.transfereeimage.SourceConfig.sourcePicUrlList
import com.bumptech.glide.Glide
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper

/**
 * 带 header 、footer 的 RecyclerView 示例
 */
class HeaderRecyclerActivity : BaseActivity() {
    private var rvImages: RecyclerView? = null
    private var headerAndFooterWrapper: HeaderAndFooterWrapper<Any>? = null
    private val linLayManager = LinearLayoutManager(this)
    private val gridLayManager = GridLayoutManager(this, 3)
    override fun getContentView(): Int {
        return R.layout.activity_recycler_view
    }

    override fun initView() {
        rvImages = findViewById(R.id.rv_images)
        rvImages?.setLayoutManager(gridLayManager)
        val adapter = NineGridAdapter()
        adapter.setOnItemClickListener(object : MultiItemTypeAdapter.OnItemClickListener {
            override fun onItemClick(view: View, viewHolder: RecyclerView.ViewHolder, position: Int) {
                // position 减去 header 的数量后，才是当前图片正确的索引
                config.nowThumbnailIndex = position - headerAndFooterWrapper!!.headersCount
                transferee.apply(config).show()
            }

            override fun onItemLongClick(view: View, viewHolder: RecyclerView.ViewHolder, i: Int): Boolean {
                return false
            }
        })
        headerAndFooterWrapper = HeaderAndFooterWrapper(adapter)
        val t1 = TextView(this)
        t1.gravity = Gravity.CENTER
        t1.text = "我是 RecyclerView 的 Header1"
        val t2 = TextView(this)
        t2.gravity = Gravity.CENTER
        t2.text = "我是 RecyclerView 的 Header2"
        val t3 = TextView(this)
        t3.text = "我是 RecyclerView 的 footer1"
        t3.gravity = Gravity.CENTER
        headerAndFooterWrapper?.addHeaderView(t1)
        headerAndFooterWrapper?.addHeaderView(t2)
        headerAndFooterWrapper?.addFootView(t3)
        rvImages?.setAdapter(headerAndFooterWrapper)
        headerAndFooterWrapper?.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.action_bar_options, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_toggle) {
            if (rvImages!!.layoutManager === linLayManager) {
                rvImages!!.layoutManager = gridLayManager
            } else {
                rvImages!!.layoutManager = linLayManager
            }
            headerAndFooterWrapper!!.notifyDataSetChanged()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun testTransferee() {
        config = build()
                .setSourceModelList(sourcePicUrlList)
                .setProgressIndicator(ProgressBarIndicator())
                .setIndexIndicator(CircleIndexIndicator())
                .enableScrollingWithPageChange(true)
                .bindRecyclerView(rvImages, headerAndFooterWrapper!!.headersCount,
                        headerAndFooterWrapper!!.footersCount, R.id.iv_thum)
    }

    private inner class NineGridAdapter : CommonAdapter<String?>(this@HeaderRecyclerActivity, R.layout.item_image, sourcePicUrlList) {
        override fun convert(viewHolder: ViewHolder, item: String?, position: Int) {
            val imageView = viewHolder.getView<ImageView>(R.id.iv_thum)
            Glide.with(imageView)
                    .load(item)
                    .placeholder(R.drawable.ic_empty_photo)
                    .into(imageView)
        }
    }
}