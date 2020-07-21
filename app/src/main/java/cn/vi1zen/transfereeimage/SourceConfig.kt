package cn.vi1zen.transfereeimage

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Pair
import kotlin.collections.ArrayList

/**
 * Created by Vans Z on 2018/9/26.
 */

object SourceConfig {

    @JvmStatic
    val thumbSourceGroup: List<String>
        get() {
            val thumbnailImageList: MutableList<String> = ArrayList()
            thumbnailImageList.add("http://static.fdc.com.cn/avatar/sns/1486263782969.png@233w_160h_20q")
            thumbnailImageList.add("http://static.fdc.com.cn/avatar/sns/1485055822651.png@233w_160h_20q")
            thumbnailImageList.add("http://static.fdc.com.cn/avatar/sns/1486194909983.png@233w_160h_20q")
            thumbnailImageList.add("http://static.fdc.com.cn/avatar/sns/1486194996586.png@233w_160h_20q")
            thumbnailImageList.add("http://static.fdc.com.cn/avatar/sns/1486195059137.png@233w_160h_20q")
            thumbnailImageList.add("http://static.fdc.com.cn/avatar/sns/1486173497249.png@233w_160h_20q")
            thumbnailImageList.add("http://static.fdc.com.cn/avatar/sns/1486173526402.png@233w_160h_20q")
            thumbnailImageList.add("http://static.fdc.com.cn/avatar/sns/1486173639603.png@233w_160h_20q")
            thumbnailImageList.add("http://static.fdc.com.cn/avatar/sns/1486172566083.png@233w_160h_20q")
            return thumbnailImageList
        }
    @JvmStatic
    val originalSourceGroup: List<String>
        get() {
            val sourceImageList: MutableList<String> = ArrayList()
            sourceImageList.add("http://static.fdc.com.cn/avatar/sns/1486263782969.png")
            sourceImageList.add("http://static.fdc.com.cn/avatar/sns/1485055822651.png")
            sourceImageList.add("http://static.fdc.com.cn/avatar/sns/1486194909983.png")
            sourceImageList.add("http://static.fdc.com.cn/avatar/sns/1486194996586.png")
            sourceImageList.add("http://static.fdc.com.cn/avatar/sns/1486195059137.png")
            sourceImageList.add("http://static.fdc.com.cn/avatar/sns/1486173497249.png")
            sourceImageList.add("http://static.fdc.com.cn/avatar/sns/1486173526402.png")
            sourceImageList.add("http://static.fdc.com.cn/avatar/sns/1486173639603.png")
            sourceImageList.add("http://static.fdc.com.cn/avatar/sns/1486172566083.png")
            return sourceImageList
        }

    @JvmStatic
    fun getMixingSourceGroup() : ArrayList<String> {
            val sourceList = ArrayList<String>()
            sourceList.add("http://img2.woyaogexing.com/2018/01/25/f5d815584c61d376!500x500.jpg")
            sourceList.add("http://img3.duitang.com/uploads/item/201605/13/20160513144041_Ze3a4.gif")
            sourceList.add("https://pic4.zhimg.com/80/v2-ab305465594807042787fb0dc06c423b_hd.jpg")
            sourceList.add("http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4")
            sourceList.add("http://static.fdc.com.cn/avatar/sns/1486194996586.png")
            sourceList.add("http://static.fdc.com.cn/avatar/sns/1486195059137.png")
            sourceList.add("http://static.fdc.com.cn/avatar/sns/1486173497249.png")
            sourceList.add("http://static.fdc.com.cn/avatar/sns/1486173639603.png")
            sourceList.add("http://jzvd.nathen.cn/c494b340ff704015bb6682ffde3cd302/64929c369124497593205a4190d7d128-5287d2089db37e62345123a1be272f8b.mp4")
            return sourceList
    }

    @JvmStatic
    val sourcePicUrlList: List<String>
        get() {
            val sourceImageList: MutableList<String> = ArrayList()
            sourceImageList.add("http://static.fdc.com.cn/avatar/sns/1486263782969.png")
            sourceImageList.add("http://static.fdc.com.cn/avatar/sns/1485055822651.png")
            sourceImageList.add("http://static.fdc.com.cn/avatar/sns/1486194909983.png")
            sourceImageList.add("http://static.fdc.com.cn/avatar/sns/1486194996586.png")
            sourceImageList.add("http://static.fdc.com.cn/avatar/sns/1486195059137.png")
            sourceImageList.add("http://static.fdc.com.cn/avatar/sns/1486173497249.png")
            sourceImageList.add("http://static.fdc.com.cn/avatar/sns/1486173526402.png")
            sourceImageList.add("http://static.fdc.com.cn/avatar/sns/1486173639603.png")
            sourceImageList.add("http://static.fdc.com.cn/avatar/sns/1486172566083.png")
            sourceImageList.add("http://img3.duitang.com/uploads/item/201605/13/20160513144041_Ze3a4.gif")
            sourceImageList.add("http://img2.woyaogexing.com/2018/01/25/f5d815584c61d376!500x500.jpg")
            sourceImageList.add("http://img2.woyaogexing.com/2018/01/25/f39e625574dd6169!500x500.jpg")
            sourceImageList.add("http://img2.woyaogexing.com/2018/01/25/4771243daf1c4e38!500x500.jpg")
            sourceImageList.add("http://img2.woyaogexing.com/2018/01/25/991349aa8c98c502!500x500.jpg")
            sourceImageList.add("http://img2.woyaogexing.com/2018/01/25/090cf5fd769351a7!500x500.jpg")
            sourceImageList.add("http://ww2.sinaimg.cn/large/85cc5ccbgy1ffng1sbcbdg20dc0m87wh.gif")
            sourceImageList.add("http://img2.woyaogexing.com/2018/02/02/be4ffaa3df84a9fd!500x500.jpg")
            sourceImageList.add("http://img2.woyaogexing.com/2018/01/16/ebb71389722b2bc4!500x500.jpg")
            sourceImageList.add("http://img2.woyaogexing.com/2018/01/16/56adca0f49dde198!500x500.jpg")
            sourceImageList.add("http://img2.woyaogexing.com/2018/01/16/78b37fd847279e8c!500x500.jpg")
            sourceImageList.add("https://pic4.zhimg.com/80/v2-ab305465594807042787fb0dc06c423b_hd.jpg")
            return sourceImageList
        }

    fun getResUriList(context: Context): List<String> {
        val resList: MutableList<Int> = ArrayList()
        resList.add(R.drawable.one)
        resList.add(R.drawable.two)
        resList.add(R.drawable.three)
        resList.add(R.drawable.four)
        resList.add(R.drawable.five)
        resList.add(R.drawable.six)
        resList.add(R.drawable.seven)
        resList.add(R.drawable.eight)
        resList.add(R.drawable.nine)
        val resUrlList: MutableList<String> = ArrayList()
        val r = context.resources
        for (res in resList) {
            val uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                    + r.getResourcePackageName(res) + "/"
                    + r.getResourceTypeName(res) + "/"
                    + r.getResourceEntryName(res))
            resUrlList.add(uri.toString())
        }
        return resUrlList
    }

    @JvmStatic
    fun getFriendsCircleList(context: Context): List<Pair<String, List<String>>> {
        val friendsCircleList: ArrayList<Pair<String, List<String>>> = ArrayList()
        friendsCircleList.add(Pair("app包内资源图预览演示", getResUriList(context)))
        val videoList: ArrayList<String> = ArrayList()
        videoList.add("http://jzvd.nathen.cn/video/2a101070-170bad88892-0007-1823-c86-de200.mp4")
        videoList.add("http://jzvd.nathen.cn/video/1137e480-170bac9c523-0007-1823-c86-de200.mp4")
        videoList.add("http://jzvd.nathen.cn/22b4de0e2b1245959c5baa77fe0bf14e/896a137559084b7eb879f5441faff20d-5287d2089db37e62345123a1be272f8b.mp4")
        friendsCircleList.add(Pair("视频播放演示", videoList))
        val gifList: ArrayList<String> = ArrayList()
        gifList.add("http://img.soogif.com/AF0GgQmFKggXX9KloAQZQCqw7iVBiTdo.gif")
        gifList.add("http://img.soogif.com/NKGXmtmwk44996y8zI1rhJUcoOYhsEv4.gif")
        gifList.add("http://img.soogif.com/QYm5j1dq7nBGw5R0QJ8SNMTzDP25Glnf.gif")
        friendsCircleList.add(Pair("gif 图片预览演示", gifList))
        val largeList: ArrayList<String> = ArrayList()
        largeList.add("https://ww4.sinaimg.cn/bmiddle/a716fd45ly1gf5nskmynvj20ku2q37wh.jpg")
        largeList.add("https://ww3.sinaimg.cn/bmiddle/a716fd45ly1gf5nskwbduj20ku2ao1kx.jpg")
        largeList.add("https://ww3.sinaimg.cn/bmiddle/a716fd45ly1gf5nsl2fvkj20ku3g3x6p.jpg")
        largeList.add("https://ww2.sinaimg.cn/bmiddle/a716fd45ly1gf5nsl75taj20ku2pyb29.jpg")
        largeList.add("https://ww3.sinaimg.cn/bmiddle/a716fd45ly1gf5nskqvnuj20ku2gn4qp.jpg")
        largeList.add("https://ww2.sinaimg.cn/bmiddle/a716fd45ly1gf5nsl3d0nj20ku2yt4qp.jpg")
        largeList.add("https://ww2.sinaimg.cn/bmiddle/a716fd45ly1gf5nslgftdj20ku2ay1kx.jpg")
        largeList.add("https://ww1.sinaimg.cn/bmiddle/a716fd45ly1gf5nslorclj20ku2igx6p.jpg")
        largeList.add("https://ww2.sinaimg.cn/bmiddle/a716fd45ly1gf5nslgqtsj20ku16eat4.jpg")
        friendsCircleList.add(Pair("长图预览演示", largeList))
        val hideThumbList: ArrayList<String> = ArrayList()
        hideThumbList.add("https://c-ssl.duitang.com/uploads/item/201702/25/20170225081804_XhUeL.thumb.1000_0.jpeg")
        hideThumbList.add("https://c-ssl.duitang.com/uploads/item/201706/29/20170629011755_LTEce.thumb.1000_0.jpeg")
        hideThumbList.add("https://c-ssl.duitang.com/uploads/item/201706/26/20170626173433_PkTKV.thumb.700_0.jpeg")
        hideThumbList.add("https://c-ssl.duitang.com/uploads/item/201703/25/20170325113119_EBM3n.thumb.1000_0.jpeg")
        hideThumbList.add("https://c-ssl.duitang.com/uploads/item/201703/25/20170325113114_JZasz.thumb.700_0.jpeg")
        hideThumbList.add("https://c-ssl.duitang.com/uploads/item/201707/16/20170716205424_ZcnXN.thumb.1000_0.jpeg")
        friendsCircleList.add(Pair("当 enableHideThumb = false 时，表示 transferee 打开或关闭不会干扰原缩略图的显示隐藏，默认开启", hideThumbList))
        val justLoadHitList: ArrayList<String> = ArrayList()
        justLoadHitList.add("https://c-ssl.duitang.com/uploads/item/201411/10/20141110213800_Arhdr.thumb.1000_0.gif")
        justLoadHitList.add("https://c-ssl.duitang.com/uploads/item/201411/10/20141110213256_UmSPw.thumb.1000_0.gif")
        justLoadHitList.add("https://c-ssl.duitang.com/uploads/item/201411/10/20141110213207_VEucm.thumb.700_0.gif")
        justLoadHitList.add("https://c-ssl.duitang.com/uploads/item/201411/14/20141114223145_Si2kF.thumb.700_0.gif")
        justLoadHitList.add("https://c-ssl.duitang.com/uploads/item/201411/14/20141114223121_t2UcU.thumb.700_0.gif")
        justLoadHitList.add("https://c-ssl.duitang.com/uploads/item/201411/14/20141114223053_mRzvc.thumb.700_0.gif")
        justLoadHitList.add("https://c-ssl.duitang.com/uploads/item/201411/22/20141122002215_i48Bj.thumb.1000_0.gif")
        justLoadHitList.add("https://c-ssl.duitang.com/uploads/item/201411/22/20141122002528_US2hX.thumb.700_0.gif")
        justLoadHitList.add("https://c-ssl.duitang.com/uploads/item/201411/22/20141122002255_yhWxC.thumb.700_0.gif")
        friendsCircleList.add(Pair("当 justLoadHitPage = true 时，只有当前页面会进行资源加载，默认关闭", justLoadHitList))
        val dragPauseList: ArrayList<String> = ArrayList()
        dragPauseList.add("http://jzvd.nathen.cn/video/7bf938c-170bac9c18a-0007-1823-c86-de200.mp4")
        friendsCircleList.add(Pair("当 enableDragPause = true 时，下拉拖拽关闭视频时，视频会暂停播放，默认关闭", dragPauseList))
        return friendsCircleList
    }

    /**
     * 使用ContentProvider读取SD卡最近图片
     *
     * @param maxCount 读取的最大张数
     */
    @JvmStatic
    fun getLatestPhotoPaths(context: Context, maxCount: Int): List<String>? {
        val mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val key_MIME_TYPE = MediaStore.Images.Media.MIME_TYPE
        val key_DATA = MediaStore.Images.Media.DATA
        val mContentResolver = context.contentResolver

        // 只查询jpg和png的图片,按最新修改排序
        val cursor = mContentResolver.query(mImageUri, arrayOf(key_DATA),
                "$key_MIME_TYPE=? or $key_MIME_TYPE=? or $key_MIME_TYPE=?", arrayOf("image/jpeg", "image/jpg", "image/gif"),
                MediaStore.Images.Media.DATE_MODIFIED)
        var latestImagePaths: List<String>? = null
        if (cursor != null) {
            //从最新的图片开始读取.
            //当cursor中没有数据时，cursor.moveToLast()将返回false
            if (cursor.moveToLast()) {
                latestImagePaths = ArrayList()
                do {
                    // 获取图片的路径
                    val path = cursor.getString(0)
                    if (!latestImagePaths.contains(path)) latestImagePaths.add(path)
                } while (latestImagePaths.size < maxCount && cursor.moveToPrevious())
            }
            cursor.close()
        }
        return latestImagePaths
    }
}