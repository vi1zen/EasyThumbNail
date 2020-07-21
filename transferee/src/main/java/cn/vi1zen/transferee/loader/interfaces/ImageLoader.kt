package cn.vi1zen.transferee.loader.interfaces

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import java.io.File

/**
 * @author <a href="mailto:wenzhen@snqu.com">vi1zen</a>
 * @version 1.0
 * @description
 * @time 2020/7/21 15:14
 * @update
 */
interface ImageLoader {

    fun load(context:Context,sourceModel:Any,onSuccess:(File) -> Unit,onFailure:() -> Unit){
        Glide.with(context).download(sourceModel)
            .listener(object : RequestListener<File>{
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<File>?,
                    isFirstResource: Boolean
                ): Boolean {
                    onFailure.invoke()
                    return false
                }

                override fun onResourceReady(
                    resource: File?,
                    model: Any?,
                    target: Target<File>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    resource?.let {
                        onSuccess.invoke(resource)
                    } ?: onFailure.invoke()
                    return false
                }
            })
            .preload()
    }
}