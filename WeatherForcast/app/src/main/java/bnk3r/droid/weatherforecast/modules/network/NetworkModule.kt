package bnk3r.droid.weatherforecast.modules.network

import android.app.Application
import com.android.volley.Cache
import com.android.volley.Network
import com.android.volley.RequestQueue
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.HurlStack
import dagger.Module
import dagger.Provides

/**
 * Dagger2 module: Network
 * Created by YomanHD on 01/11/2016.
 */
@Module
class NetworkModule(private val context: Application) {

    @Provides @NetworkScope
    fun providesCache(): Cache {
        return DiskBasedCache(context.cacheDir, 1024 * 1024) //1Mo per response
    }

    @Provides @NetworkScope
    fun providesNetwork(): Network {
        return BasicNetwork(HurlStack())
    }

    @Provides @NetworkScope
    fun providesVolleyRequestQueue(cache: Cache, network: Network): RequestQueue {
        val queue = RequestQueue(cache, network)
        queue.start()
        return queue
    }
}