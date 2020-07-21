package cn.vi1zen.transferee.transfer

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.AbsListView
import android.widget.ImageView
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import cn.vi1zen.transferee.R
import cn.vi1zen.transferee.indicator.index.NumberIndexIndicator
import cn.vi1zen.transferee.indicator.interfaces.IIndexIndicator
import cn.vi1zen.transferee.indicator.interfaces.IProgressIndicator
import cn.vi1zen.transferee.indicator.progress.ProgressBarIndicator
import cn.vi1zen.transferee.loader.interfaces.ImageLoader
import com.blankj.utilcode.util.PathUtils
import java.io.File
import java.util.regex.Pattern

/**
 * Transferee Attributes
 *
 *
 * Created by Vans Z on 2017/1/19.
 *
 *
 * email: 196425254@qq.com
 */
class TransferConfig {
    var nowThumbnailIndex = 0
    var offscreenPageLimit = 0
    var missPlaceHolder = 0
    var errorPlaceHolder = 0
    var backgroundColor = 0
        get() = if (field == 0) Color.BLACK else field
    var duration: Long = 0
    var isJustLoadHitPage = false
        private set
    var isEnableDragClose = false
        private set
    var isEnableDragHide = false
        private set
    var isEnableDragPause = false
        private set
    var isEnableHideThumb = false
        private set
    var isEnableScrollingWithPageChange = false
        private set

    private var imageLoader:ImageLoader = object : ImageLoader{}

    private var missDrawable: Drawable? = null
    private var errorDrawable: Drawable? = null
    var originImageList: List<ImageView>? = null
        get() = if (field == null) ArrayList() else field
    private var sourceModelList: List<Any> = arrayListOf()
    private val sourceKeyList = arrayListOf<String>()
    var progressIndicator: IProgressIndicator = ProgressBarIndicator()
    var indexIndicator: IIndexIndicator = NumberIndexIndicator()

    @IdRes
    var imageId = 0
    var imageView: ImageView? = null
    var listView: AbsListView? = null
    var recyclerView: RecyclerView? = null
    var customView: View? = null
    var headerSize = 0
    var footerSize = 0
    var longClickListener: Transferee.OnTransfereeLongClickListener? = null

    fun enableJustLoadHitPage(justLoadHitPage: Boolean) {
        isJustLoadHitPage = justLoadHitPage
    }

    fun enableDragClose(enableDragClose: Boolean) {
        isEnableDragClose = enableDragClose
    }

    fun enableDragHide(enableDragHide: Boolean) {
        isEnableDragHide = enableDragHide
    }

    fun enableDragPause(enableDragPause: Boolean) {
        isEnableDragPause = enableDragPause
    }

    fun enableHideThumb(enableHideThumb: Boolean) {
        isEnableHideThumb = enableHideThumb
    }

    fun enableScrollingWithPageChange(enableScrollingWithPageChange: Boolean) {
        isEnableScrollingWithPageChange = enableScrollingWithPageChange
    }

    fun getMissDrawable(context: Context): Drawable? {
        return if (missDrawable != null) missDrawable else if (missPlaceHolder != 0) context.getDrawable(missPlaceHolder) else context.getDrawable(
            R.drawable.ic_empty_photo)
    }

    fun setMissDrawable(missDrawable: Drawable?) {
        this.missDrawable = missDrawable
    }

    fun getErrorDrawable(context: Context): Drawable? {
        return if (errorDrawable != null) errorDrawable else if (errorPlaceHolder != 0) context.getDrawable(errorPlaceHolder) else context.getDrawable(
            R.drawable.ic_empty_photo)
    }

    fun setErrorDrawable(errorDrawable: Drawable?) {
        this.errorDrawable = errorDrawable
    }

    fun getSourceModelList(): List<Any> {
        return sourceModelList
    }

    fun getImageLoader() = imageLoader

    /**
     * 高清图的地址集合
     */
    fun setSourceModelList(sourceModelList: List<Any>,convertKey:(Any) -> String = { it.toString() }) {
        this.sourceModelList = sourceModelList
        this.sourceKeyList.clear()
        this.sourceKeyList.addAll(this.sourceModelList.map(convertKey))
    }

    /**
     * 获取key集合
     */
    fun getSourceKeyList() = sourceKeyList

    /**
     * 获取缓存
     */
    fun getCache(key:String) : File?{
        val fileName = key.split("/").last()
        val cacheFile = File(getCacheDir(), fileName)
        return if(cacheFile.exists()) cacheFile else null
    }

    fun getCacheDir() : File {
        val cacheDir = File(PathUtils.getInternalAppCachePath(), CACHE_DIR)
        if (!cacheDir.exists()) cacheDir.mkdirs()
        return cacheDir
    }

    /**
     * 原图路径集合是否为空
     *
     * @return true : 空
     */
    val isSourceEmpty: Boolean
        get() = sourceModelList.isEmpty()

    /**
     * 判断当前 position 下的资源是不是视频
     *
     * @param position 为 -1 值，表示取 nowThumbnailIndex
     * @return true : 是视频资源
     */
    fun isVideoSource(position: Int): Boolean {
        val sourceUrl = sourceKeyList[if (position == -1) nowThumbnailIndex else position]
        return VIDEO_PATTERN.matcher(sourceUrl).matches()
    }

    fun destroy() {
        imageView = null
        customView = null
        listView = null
        recyclerView = null
        originImageList = null
        setMissDrawable(null)
        setErrorDrawable(null)
    }

    class Builder {
        private var imageLoader:ImageLoader? = null
        private var nowThumbnailIndex = 0
        private var offscreenPageLimit = 0
        private var missPlaceHolder = 0
        private var errorPlaceHolder = 0
        private var backgroundColor = 0
        private var duration: Long = 0
        private var justLoadHitPage = false
        private var enableDragClose = true
        private var enableDragHide = true
        private var enableDragPause = false
        private var enableHideThumb = true
        private var enableScrollingWithPageChange = false
        private var autoAdjustDirection = true
        private var missDrawable: Drawable? = null
        private var errorDrawable: Drawable? = null
        private var originImageList: List<ImageView>? = null
        private var sourceModelList: ArrayList<Any> = arrayListOf()
        private var progressIndicator: IProgressIndicator = ProgressBarIndicator()
        private var indexIndicator: IIndexIndicator = NumberIndexIndicator()
        private var customView: View? = null

        @IdRes
        private var imageId = 0
        private var imageView: ImageView? = null
        private var listView: AbsListView? = null
        private var recyclerView: RecyclerView? = null
        private var headerSize = 0
        private var footerSize = 0
        private var longClickListener: Transferee.OnTransfereeLongClickListener? = null

        fun setImageLoader(imageLoader: ImageLoader){
            this.imageLoader = imageLoader
        }

        /**
         * 当前缩略图在所有图片中的索引
         */
        fun setNowThumbnailIndex(nowThumbnailIndex: Int): Builder {
            this.nowThumbnailIndex = nowThumbnailIndex
            return this
        }

        /**
         *
         * ViewPager 中进行初始化并创建的页面 : 设置为当前页面加上当前页面两侧的页面
         *
         * 默认为1, 表示第一次加载3张(nowThumbnailIndex, nowThumbnailIndex
         * + 1, nowThumbnailIndex - 1);值为 2, 表示加载5张。依次类推
         *
         * 这个参数是为了优化而提供的，值越大，初始化创建的页面越多，保留的页面也
         * 越多，推荐使用默认值
         */
        fun setOffscreenPageLimit(offscreenPageLimit: Int): Builder {
            this.offscreenPageLimit = offscreenPageLimit
            return this
        }

        /**
         * 缺省的占位图(资源ID)
         */
        fun setMissPlaceHolder(missPlaceHolder: Int): Builder {
            this.missPlaceHolder = missPlaceHolder
            return this
        }

        /**
         * 图片加载错误显示的图片(资源ID)
         */
        fun setErrorPlaceHolder(errorPlaceHolder: Int): Builder {
            this.errorPlaceHolder = errorPlaceHolder
            return this
        }

        /**
         * 为 transferee 组件设置背景颜色
         */
        fun setBackgroundColor(backgroundColor: Int): Builder {
            this.backgroundColor = backgroundColor
            return this
        }

        /**
         * 动画播放时长
         */
        fun setDuration(duration: Long): Builder {
            this.duration = duration
            return this
        }

        /**
         * 仅仅只加载当前显示的图片
         */
        fun enableJustLoadHitPage(justLoadHitPage: Boolean): Builder {
            this.justLoadHitPage = justLoadHitPage
            return this
        }

        /**
         * 是否可以拖拽关闭
         */
        fun enableDragClose(enableDragClose: Boolean): Builder {
            this.enableDragClose = enableDragClose
            return this
        }

        /**
         * 拖拽关闭时是否隐藏除主视图以外的其他 view
         */
        fun enableDragHide(enableDragHide: Boolean): Builder {
            this.enableDragHide = enableDragHide
            return this
        }

        /**
         * 拖拽关闭时是否暂停当前页面视频播放
         */
        fun enableDragPause(enableDragPause: Boolean): Builder {
            this.enableDragPause = enableDragPause
            return this
        }

        /**
         * 是否开启当 transferee 打开时，隐藏缩略图
         */
        fun enableHideThumb(enableHideThumb: Boolean): Builder {
            this.enableHideThumb = enableHideThumb
            return this
        }

        /**
         * 是否启动列表随着 page 的切换而置顶滚动，仅仅针对绑定
         * RecyclerView/GridView/ListView 有效, 启动之后
         * 因为列表会实时滚动，缩略图 view 将不会出现为空的
         * 现象，从而保证关闭 transferee 时为过渡关闭动画,
         * 而不会出现扩散消失动画
         */
        fun enableScrollingWithPageChange(enableScrollingWithPageChange: Boolean): Builder {
            this.enableScrollingWithPageChange = enableScrollingWithPageChange
            return this
        }

        /**
         * 是否启用图片的方向自动校正
         */
        fun autoAdjustDirection(autoAdjustDirection: Boolean): Builder {
            this.autoAdjustDirection = autoAdjustDirection
            return this
        }

        /**
         * 缺省的占位图(Drawable 格式)
         */
        fun setMissDrawable(missDrawable: Drawable?): Builder {
            this.missDrawable = missDrawable
            return this
        }

        /**
         * 图片加载错误显示的图片(Drawable 格式)
         */
        fun setErrorDrawable(errorDrawable: Drawable?): Builder {
            this.errorDrawable = errorDrawable
            return this
        }

        /**
         * 高清图的地址集合
         */
        fun setSourceModelList(sourceModelList: List<Any>): Builder {
            this.sourceModelList.clear()
            this.sourceModelList.addAll(sourceModelList)
            return this
        }

        /**
         * 过渡前原始的 ImageView 集合
         */
        fun setOriginImageList(originImageList: List<ImageView>?): Builder {
            this.originImageList = originImageList
            return this
        }

        /**
         * 加载高清图的进度条 (默认内置 ProgressPieIndicator), 可自实现
         * IProgressIndicator 接口定义自己的图片加载进度条
         */
        fun setProgressIndicator(progressIndicator: IProgressIndicator): Builder {
            this.progressIndicator = progressIndicator
            return this
        }

        /**
         * 图片索引指示器 (默认内置 IndexCircleIndicator), 可自实现
         * IIndexIndicator 接口定义自己的图片索引指示器
         */
        fun setIndexIndicator(indexIndicator: IIndexIndicator): Builder {
            this.indexIndicator = indexIndicator
            return this
        }

        /**
         * 绑定 transferee 长按操作监听器
         */
        fun setOnLongClickListener(listener: Transferee.OnTransfereeLongClickListener?): Builder {
            longClickListener = listener
            return this
        }

        /**
         * 在 transferee 视图放置用户自定义的视图
         */
        fun setCustomView(customView: View?): Builder {
            this.customView = customView
            return this
        }

        /**
         * 绑定 ListView
         *
         * @param imageId item layout 中的 ImageView Resource ID
         */
        fun bindListView(listView: AbsListView?, imageId: Int): TransferConfig {
            this.listView = listView
            this.imageId = imageId
            return create()
        }

        /**
         * 绑定 ListView, 并指定 header 和 footer 的数量
         *
         * @param imageId item layout 中的 ImageView Resource ID
         */
        fun bindListView(listView: AbsListView?, headerSize: Int, footerSize: Int, imageId: Int): TransferConfig {
            this.listView = listView
            this.headerSize = headerSize
            this.footerSize = footerSize
            this.imageId = imageId
            return create()
        }

        /**
         * 绑定 RecyclerView
         *
         * @param imageId item layout 中的 ImageView Resource ID
         */
        fun bindRecyclerView(recyclerView: RecyclerView?, imageId: Int): TransferConfig {
            this.recyclerView = recyclerView
            this.imageId = imageId
            return create()
        }

        /**
         * 绑定 RecyclerView, 并指定 header 和 footer 的数量
         *
         * @param imageId item layout 中的 ImageView Resource ID
         */
        fun bindRecyclerView(recyclerView: RecyclerView?, headerSize: Int, footerSize: Int, imageId: Int): TransferConfig {
            this.recyclerView = recyclerView
            this.headerSize = headerSize
            this.footerSize = footerSize
            this.imageId = imageId
            return create()
        }

        /**
         * 绑定单个 ImageView
         */
        fun bindImageView(imageView: ImageView?): TransferConfig {
            this.imageView = imageView
            return create()
        }

        /**
         * 绑定单个 ImageView, 并指定图片的 url
         */
        fun bindImageView(imageView: ImageView?, sourceUrl: Any): TransferConfig {
            this.imageView = imageView
            sourceModelList = ArrayList()
            sourceModelList.add(sourceUrl)
            return create()
        }

        fun create(): TransferConfig {
            val config = TransferConfig()
            imageLoader?.let { config.imageLoader = it }
            config.nowThumbnailIndex = nowThumbnailIndex
            config.offscreenPageLimit = offscreenPageLimit
            config.missPlaceHolder = missPlaceHolder
            config.errorPlaceHolder = errorPlaceHolder
            config.backgroundColor = backgroundColor
            config.duration = duration
            config.enableJustLoadHitPage(justLoadHitPage)
            config.enableDragClose(enableDragClose)
            config.enableDragHide(enableDragHide)
            config.enableDragPause(enableDragPause)
            config.enableHideThumb(enableHideThumb)
            config.enableScrollingWithPageChange(enableScrollingWithPageChange)
            config.setMissDrawable(missDrawable)
            config.setErrorDrawable(errorDrawable)
            config.setSourceModelList(sourceModelList)
            config.originImageList = originImageList
            config.progressIndicator = progressIndicator
            config.indexIndicator = indexIndicator
            config.customView = customView
            config.imageId = imageId
            config.imageView = imageView
            config.listView = listView
            config.recyclerView = recyclerView
            config.headerSize = headerSize
            config.footerSize = footerSize
            config.longClickListener = longClickListener
            return config
        }
    }

    companion object {
        private const val CACHE_DIR = "TransGlide"
        private val VIDEO_PATTERN = Pattern.compile(".+(://).+\\.(mp4|wmv|avi|mpeg|rm|rmvb|flv|3gp|mov|mkv|mod|)")
        @JvmStatic
        fun build() : Builder = Builder()
    }
}