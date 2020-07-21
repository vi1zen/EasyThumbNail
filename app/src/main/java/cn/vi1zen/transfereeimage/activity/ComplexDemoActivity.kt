package cn.vi1zen.transfereeimage.activity

import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.GridView
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.vi1zen.transferee.indicator.index.CircleIndexIndicator
import cn.vi1zen.transferee.indicator.index.NumberIndexIndicator
import cn.vi1zen.transferee.indicator.progress.ProgressBarIndicator
import cn.vi1zen.transferee.transfer.TransferConfig
import cn.vi1zen.transfereeimage.R
import cn.vi1zen.transfereeimage.SourceConfig
import cn.vi1zen.transfereeimage.SourceConfig.getMixingSourceGroup
import cn.vi1zen.transfereeimage.SourceConfig.originalSourceGroup
import cn.vi1zen.transfereeimage.SourceConfig.thumbSourceGroup
import com.bumptech.glide.Glide
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder

class ComplexDemoActivity : BaseActivity() {
    private var recyclerView: RecyclerView? = null
    private var gridView: RecyclerView? = null
    private var imageView: ImageView? = null
    private var button: Button? = null

    val gridTransConfig = TransferConfig.build()
        .setSourceModelList(getMixingSourceGroup())
        .setProgressIndicator(ProgressBarIndicator())
        .setIndexIndicator(CircleIndexIndicator())
        .enableScrollingWithPageChange(true)
        .bindRecyclerView(gridView, R.id.iv_thum)
    public override fun getContentView(): Int {
        return R.layout.activity_complex_demo
    }

    override fun initView() {
        recyclerView = findViewById(R.id.rv_transferee)
        gridView = findViewById(R.id.gv_transferee)
        imageView = findViewById(R.id.iv_single_view)
        button = findViewById(R.id.btn_none_view)
    }

    override fun testTransferee() {
        recyclerDemo()
        gridDemo()
        singleViewDemo()
        noneViewDemo()
    }

    private fun noneViewDemo() {
        button!!.setOnClickListener { v: View? ->
            transferee.apply(
                TransferConfig.build()
                    .setSourceModelList(SourceConfig.getMixingSourceGroup())
                    .setNowThumbnailIndex(3)
                    .create()
            ).show()
        }
    }

    private fun singleViewDemo() {
        Glide.with(imageView!!).load(getMixingSourceGroup()[0]).into(imageView!!)
        imageView!!.setOnClickListener { v: View? ->
            transferee.apply(TransferConfig.build()
                    .setSourceModelList(SourceConfig.getMixingSourceGroup())
                    .enableJustLoadHitPage(true)
                    .setCustomView(View.inflate(baseContext, R.layout.layout_custom, null))
                    .bindImageView(imageView)
            ).show()
        }
    }

    private fun gridDemo() {
        gridView?.layoutManager = GridLayoutManager(this,3)
        gridView?.adapter = GridAdapter()
    }

    private fun recyclerDemo() {
        val recyclerTransConfig = TransferConfig.build()
                .setSourceModelList(originalSourceGroup)
                .setProgressIndicator(ProgressBarIndicator())
                .setIndexIndicator(NumberIndexIndicator())
                .enableHideThumb(false)
                .bindRecyclerView(recyclerView, R.id.iv_thum)
        val recyclerAdapter = RecyclerAdapter()
        recyclerAdapter.setOnItemClickListener(object : MultiItemTypeAdapter.OnItemClickListener {
            override fun onItemClick(view: View, viewHolder: RecyclerView.ViewHolder, pos: Int) {
                recyclerTransConfig.nowThumbnailIndex = pos
                transferee.apply(recyclerTransConfig).show()
            }

            override fun onItemLongClick(view: View, viewHolder: RecyclerView.ViewHolder, i: Int): Boolean {
                return false
            }
        })
        recyclerView!!.layoutManager = GridLayoutManager(this, 3)
        recyclerView!!.adapter = recyclerAdapter
    }

    private inner class RecyclerAdapter internal constructor() : CommonAdapter<String?>(this@ComplexDemoActivity, R.layout.item_image, thumbSourceGroup) {
        override fun convert(viewHolder: ViewHolder, item: String?, position: Int) {
            val imageView = viewHolder.getView<ImageView>(R.id.iv_thum)
            Glide.with(imageView)
                    .load(item)
                    .placeholder(R.drawable.ic_empty_photo)
                    .into(imageView)
        }
    }

    private inner class GridAdapter internal constructor() : CommonAdapter<String>(this@ComplexDemoActivity, R.layout.item_image, getMixingSourceGroup()) {
        override fun convert(viewHolder: ViewHolder, item: String, position: Int) {
            val imageView = viewHolder.getView<ImageView>(R.id.iv_thum)
            if (item.endsWith(".mp4")) {
                Glide.with(imageView)
                        .load(item)
                        .frame(1000000)
                        .placeholder(R.drawable.ic_empty_photo)
                        .into(imageView)
            } else {
                Glide.with(imageView)
                        .load(item)
                        .placeholder(R.drawable.ic_empty_photo)
                        .into(imageView)
            }
            viewHolder.itemView.setOnClickListener {
                gridTransConfig.nowThumbnailIndex = position
                transferee.apply(gridTransConfig).show()
            }
        }
    }
}