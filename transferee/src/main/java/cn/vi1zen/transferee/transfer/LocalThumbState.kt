package cn.vi1zen.transferee.transfer

import android.graphics.BitmapFactory
import android.widget.ImageView
import cn.vi1zen.transferee.view.image.TransferImage
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import java.io.File

/**
 * 高清图图片已经加载过了，使用高清图作为缩略图。
 * 同时使用 [TransferImage.CATE_ANIMA_TOGETHER] 动画类型展示图片
 *
 *
 * Created by Vans Z on 2017/5/4.
 *
 *
 * email: 196425254@qq.com
 */
internal class LocalThumbState(transfer: TransferLayout?) : TransferState(transfer) {
    /**
     * 加载 imageUrl 所关联的图片到 TransferImage 中
     *
     * @param imageUrl 图片路径
     * @param in       true: 表示从缩略图到 Transferee, false: 从 Transferee 到缩略图
     */
    private fun loadThumbnail(imageUrl: String, transImage: TransferImage, `in`: Boolean) {
        val config = transfer.transConfig
        val thumbFile = config.getCache(imageUrl)
        val thumbBitmap = if (thumbFile != null) BitmapFactory.decodeFile(thumbFile.absolutePath) else null
        if (thumbBitmap == null) transImage.setImageDrawable(config.getMissDrawable(transfer.context)) else transImage.setImageBitmap(thumbBitmap)
        if (`in`) transImage.transformIn() else transImage.transformOut()
    }

    override fun prepareTransfer(transImage: TransferImage, position: Int) {
        val config = transfer.transConfig
        val imgUrl = config.getSourceKeyList()[position]
        Glide.with(transImage).load(imgUrl).into(transImage)
        transImage.enableGesture()
    }

    override fun transferIn(position: Int): TransferImage {
        val config = transfer.transConfig
        val transImage = createTransferImage(
                config.originImageList!![position], true)
        loadThumbnail(config.getSourceModelList()[position] as String, transImage, true)
        transfer.addView(transImage, 1)
        return transImage
    }

    override fun transferLoad(position: Int) {
        val config = transfer.transConfig
        val sourceModel = config.getSourceModelList()[position]
        val targetImage = transfer.transAdapter.getImageItem(position)
        if (config.isJustLoadHitPage) {
            // 如果用户设置了 JustLoadHitImage 属性，说明在 prepareTransfer 中已经
            // 对 TransferImage 裁剪且设置了占位图， 所以这里直接加载原图即可
            loadSourceImage(sourceModel, targetImage, position)
        } else {
            val cacheFile = config.getCache(sourceModel.hashCode().toString())
            if (cacheFile == null ) {
                targetImage.setImageDrawable(config.getMissDrawable(transfer.context))
            } else {
                val bitmap = BitmapFactory.decodeFile(cacheFile.absolutePath)
                targetImage.setImageBitmap(bitmap)
            }
            loadSourceImage(sourceModel, targetImage, position)
        }
    }

    private fun loadSourceImage(sourceModel: Any, targetImage: TransferImage, position: Int) {
        transfer.transConfig.getImageLoader().load(targetImage.context,sourceModel,onSuccess = {
            startPreview(targetImage, it, sourceModel.hashCode().toString()) { if (TransferImage.STATE_TRANS_CLIP == targetImage.state) targetImage.transformIn(TransferImage.STAGE_SCALE) }
        },onFailure = {
            loadFailedDrawable(targetImage, position)
        })
    }

    override fun transferOut(position: Int): TransferImage {
        var transImage: TransferImage? = null
        val config = transfer.transConfig
        val originImageList: List<ImageView?>? = config.originImageList
        if (position <= originImageList!!.size - 1 && originImageList[position] != null) {
            transImage = createTransferImage(originImageList[position], true)
            loadThumbnail(config.getSourceModelList()[position] as String, transImage, false)
            transfer.addView(transImage, 1)
        }
        return transImage!!
    }
}