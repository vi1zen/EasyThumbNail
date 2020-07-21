package cn.vi1zen.transferee.transfer

import android.graphics.BitmapFactory
import cn.vi1zen.transferee.indicator.interfaces.IProgressIndicator
import cn.vi1zen.transferee.view.image.TransferImage
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import java.io.File

/**
 * 没有缩略图 ImageView, 直接在 transferee 中展示图片
 *
 *
 * Created by Vans Z on 2020/4/16.
 *
 *
 * email: 196425254@qq.com
 */
class NoneThumbState internal constructor(transfer: TransferLayout) : TransferState(transfer) {
    override fun prepareTransfer(transImage: TransferImage, position: Int) {
        // 在此种状态下无需处理 prepareTransfer
    }

    override fun transferIn(position: Int): TransferImage? {
        transfer.displayTransfer()
        return null
    }

    override fun transferLoad(position: Int) {
        val adapter = transfer.transAdapter
        val transConfig = transfer.transConfig
        val sourceModel = transConfig.getSourceModelList()[position]
        val targetImage = adapter.getImageItem(position)
        val cache =
            transConfig.getCache(transConfig.getSourceKeyList()[position])
        if (cache == null) {
            val placeholder =
                transConfig.getMissDrawable(transfer.context)
            // 按缺省的 drawable 大小裁剪初始显示的图片区域
            val clipSize =
                intArrayOf(placeholder!!.intrinsicWidth, placeholder.intrinsicHeight)
            clipTargetImage(targetImage, placeholder, clipSize)
            val progressIndicator = transConfig.progressIndicator
            progressIndicator.attach(position, adapter.getParentItem(position))
            targetImage.setImageDrawable(placeholder)
            loadSourceImage(targetImage, progressIndicator, sourceModel, position)
        } else {
            val bitmap =
                BitmapFactory.decodeFile(cache.absolutePath)
            if (bitmap == null) {
                targetImage.setImageDrawable(transConfig.getMissDrawable(transfer.context))
            } else {
                targetImage.setImageBitmap(bitmap)
            }
            loadSourceImage(targetImage, null, sourceModel, position)
        }
    }

    private fun loadSourceImage(
        targetImage: TransferImage, progressIndicator: IProgressIndicator?,
        sourceModel: Any, position: Int
    ) {
        progressIndicator?.onStart(position)
        transfer.transConfig.getImageLoader().load(targetImage.context,sourceModel,onSuccess = {
            startPreview(
                targetImage,
                it,
                sourceModel.hashCode().toString()
            ) {
                progressIndicator?.onFinish(position)
                targetImage.transformIn()
            }
        },onFailure = {
            loadFailedDrawable(targetImage, position)
        })
    }

    override fun transferOut(position: Int): TransferImage? {
        // 因为没有缩略图 ImageView ，直接返回null,执行扩散动画
        return null
    }
}