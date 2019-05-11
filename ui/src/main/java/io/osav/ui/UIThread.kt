package io.osav.ui

import io.osav.domain.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class UIThread: PostExecutionThread {
    override fun getScheduler(): Scheduler = AndroidSchedulers.mainThread()
}