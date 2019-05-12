package io.osav.stat.tools

import android.util.Log
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.LogStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import io.osav.stat.BuildConfig

/**
 * Wrapper for {@link com.orhanobut.logger.Logger}
 * can be easily replaced with Timber or any alternative
 */
object Looog {

    private const val DEFAULT_TAG = "APP"

    fun init() {
        val strategy = PrettyFormatStrategy.newBuilder()
            .methodCount(0)
            .tag("STAT")
            .logStrategy(object : LogStrategy {
                override fun log(priority: Int, tag: String?, message: String) {
                    Log.println(priority, randomKey() + tag, message)
                }

                var last: Int = 0
                private fun randomKey(): String {
                    var random: Int = (Math.random() * 10).toInt()
                    if (random == last) random = (random + 1) % 10
                    last = random
                    return random.toString()
                }
            })
            .build()
        Logger.addLogAdapter(object : AndroidLogAdapter(strategy) {
            override fun isLoggable(priority: Int, tag: String?) = BuildConfig.DEBUG || priority >= Log.ERROR

            override fun log(priority: Int, tag: String?, message: String) {
                if (BuildConfig.DEBUG) {
                    super.log(priority, tag, message)
                    return
                }
                if (priority >= Log.ERROR) {
//                    Crashlytics.log(priority, tag, message)
                }
            }
        })
    }


    fun d(tag: String) = { message: String ->
        Logger.t(tag).d(message)
    }

    fun i(tag: String) = { message: String ->
        Logger.t(tag).i(message)
    }

    fun w(tag: String) = { message: String ->
        Logger.t(tag).w(message)
    }

    fun e(tag: String) = { message: String ->
        Logger.t(tag).e(message)
    }

    fun ex(tag: String) = { message: String, throwable: Throwable ->
        Logger.t(tag).e(throwable, message)
    }
}