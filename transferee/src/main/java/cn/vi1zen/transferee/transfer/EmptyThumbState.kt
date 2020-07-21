package cn.vi1zen.transferee.transfer

import android.graphics.drawable.Drawable
import android.widget.ImageView
import cn.vi1zen.transferee.view.image.TransferImage
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import java.io.File

/**
 * 高清图尚未加载，使用原 ImageView 中显示的图片作为缩略图。
 * 同时使用 [TransferImage.CATE_ANIMA_APART] 动画类型展示图片
 *
 *
 * Created by Vans Z on 2017/5/4.
 *
 *
 * email: 196425254@qq.com
 */
internal class EmptyThumbState(transfer: TransferLayout?) : TransferState(transfer) {
    override fun prepareTransfer(transImage: TransferImage, position: Int) {
        transImage.setImageDrawable(clipAndGetPlaceHolder(transImage, position))
    }

    override fun transferIn(position: Int): TransferImage {
        val originImage = transfer.transConfig
            .originImageList!![position]
        val transImage = createTransferImage(originImage, true)
        transImage.setImageDrawable(originImage.drawable)
        transImage.transformIn(TransferImage.STAGE_TRANSLATE)
        transfer.addView(transImage, 1)
        return transImage
    }

    override fun transferLoad(position: Int) {
        val adapter = transfer.transAdapter
        val config = transfer.transConfig
        val sourceModel = config.getSourceModelList()[position]
        val targetImage = adapter.getImageItem(position)
        val placeHolder: Drawable?
        placeHolder = if (config.isJustLoadHitPage) {
            // 如果用户设置了 JustLoadHitImage 属性，说明在 prepareTransfer 中已经
            // 对 TransferImage 裁剪过了， 所以只需要获取 Drawable 作为占位图即可
            getPlaceHolder(position)
        } else {
            clipAndGetPlaceHolder(targetImage, position)
        }
        targetImage.setImageDrawable(placeHolder)
        val progressIndicator = config.progressIndicator
        progressIndicator.attach(position, adapter.getParentItem(position))
        progressIndicator.onStart(position)

        config.getImageLoader().load(targetImage.context,sourceModel,onSuccess = {
            startPreview(
                targetImage,
                it,
                sourceModel.hashCode().toString()
            ) {
                progressIndicator.onFinish(position)
                targetImage.transformIn(TransferImage.STAGE_SCALE)
            }
        },onFailure = {
            loadFailedDrawable(targetImage, position)
        })
    }

    override fun transferOut(position: Int): TransferImage {
        var transImage: TransferImage? = null
        val config = transfer.transConfig
        val originImageList: List<ImageView?>? =
            config.originImageList
        if (position <= originImageList!!.size - 1 && originImageList[position] != null) {
            transImage = createTransferImage(originImageList[position], true)
            val thumbnailDrawable =
                transfer.transAdapter.getImageItem(
                    config.nowThumbnailIndex
                ).drawable
            transImage.setImageDrawable(thumbnailDrawable)
            transImage.transformOut(TransferImage.STAGE_TRANSLATE)
            transfer.addView(transImage, 1)
        }
        return transImage!!
    }

    /**
     * 获取 position 位置处的 占位图，如果 position 超出下标，获取 MissDrawable
     *
     * @param position 图片索引
     * @return 占位图
     */
    private fun getPlaceHolder(position: Int): Drawable? {
        var placeHolder: Drawable? = null
        val config = transfer.transConfig
        val originImage = config.originImageList!![position]
        if (originImage != null) {
            placeHolder = originImage.drawable
        }
        if (placeHolder == null) {
            placeHolder = config.getMissDrawable(transfer.context)
        }
        return placeHolder
    }

    /**
     * 裁剪用于显示 PlaceHolder 的 TransferImage
     *
     * @param targetImage 被裁剪的 TransferImage
     * @param position    图片索引
     * @return 被裁减的 TransferImage 中显示的 Drawable
     */
    private fun clipAndGetPlaceHolder(
        targetImage: TransferImage,
        position: Int
    ): Drawable? {
        val placeHolder = getPlaceHolder(position)
        clipTargetImage(
            targetImage, placeHolder,
            getPlaceholderClipSize(position, TYPE_PLACEHOLDER_MISS)
        )
        return placeHolder
    }
}