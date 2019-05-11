package io.osav.data

import io.osav.domain.executor.ThreadExecutor
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
 * Decorated {@link java.util.concurrent.ThreadPoolExecutor}
 */
class JobExecutor: ThreadExecutor {

    private val threadPoolExecutor by lazy { ThreadPoolExecutor(3, 5, 10, TimeUnit.SECONDS, LinkedBlockingQueue<Runnable>(), JobThreadFactory()) }

    override fun execute(command: Runnable?) = threadPoolExecutor.execute(command)

    private class JobThreadFactory : ThreadFactory{
        var counter = 0
        override fun newThread(r: Runnable) = Thread(r, "android_${counter++}")
    }
}