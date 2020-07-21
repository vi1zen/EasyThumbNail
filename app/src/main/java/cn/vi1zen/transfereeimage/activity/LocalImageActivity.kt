package cn.vi1zen.transfereeimage.activity

import android.Manifest
import android.content.pm.PackageManager
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import cn.vi1zen.transferee.indicator.index.NumberIndexIndicator
import cn.vi1zen.transferee.indicator.progress.ProgressBarIndicator
import cn.vi1zen.transferee.transfer.TransferConfig.Companion.build
import cn.vi1zen.transfereeimage.R
import cn.vi1zen.transfereeimage.SourceConfig.getLatestPhotoPaths
import com.bumptech.glide.Glide
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder

/**
 * 使用 GlideImageLoader 演示
 */
class LocalImageActivity : BaseActivity() {
    private var images: List<String>? = null
    override fun getContentView(): Int {
        return R.layout.activity_grid_view
    }

    override fun initView() {
        gvImages = findViewById(R.id.gv_images)
    }

    override fun testTransferee() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE),
                    READ_EXTERNAL_STORAGE)
        } else {
            images = getLatestPhotoPaths(this, 99)
            if (images != null && !images!!.isEmpty()) {
                initTransfereeConfig()
                gvImages.adapter = NineGridAdapter()
            }
        }
    }

    private fun initTransfereeConfig() {
        config = build()
                .setSourceModelList(images ?: emptyList())
                .setMissPlaceHolder(R.drawable.ic_empty_photo)
                .setErrorPlaceHolder(R.drawable.ic_empty_photo)
                .setProgressIndicator(ProgressBarIndicator())
                .setIndexIndicator(NumberIndexIndicator())
                .enableJustLoadHitPage(true)
                .bindRecyclerView(gvImages, R.id.iv_thum)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == READ_EXTERNAL_STORAGE) {
            images = getLatestPhotoPaths(this, 99)
            if (images != null && !images!!.isEmpty()) {
                initTransfereeConfig()
                gvImages.adapter = NineGridAdapter()
            }
        } else {
            Toast.makeText(this, "请允许获取相册图片文件访问权限", Toast.LENGTH_SHORT).show()
        }
    }

    private inner class NineGridAdapter : CommonAdapter<String?>(this@LocalImageActivity, R.layout.item_image, images) {
        override fun convert(viewHolder: ViewHolder, item: String?, position: Int) {
            val imageView = viewHolder.getView<ImageView>(R.id.iv_thum)
            Glide.with(imageView)
                    .load(item)
                    .placeholder(R.drawable.ic_empty_photo)
                    .into(imageView)
            imageView.setOnClickListener {
                config.nowThumbnailIndex = position
                transferee.apply(config).show()
            }
        }
    }
}