package io.osav.ui.core

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class DisposablesObserver(
    private val lifecycle: Lifecycle
): LifecycleObserver {

    private val onCreateDisposables = CompositeDisposable()
    private val onStartDisposables = CompositeDisposable()
    private val onResumeDisposables = CompositeDisposable()

    fun add(disposable: Disposable) {
        when {
            lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED) -> {
                onResumeDisposables.add(disposable)
                return
            }

            lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED) -> {
                onStartDisposables.add(disposable)
                return
            }

            lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED) -> {
                onCreateDisposables.add(disposable)
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun pause() {
        onResumeDisposables.clear()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop() {
        onStartDisposables.clear()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroy() {
        onCreateDisposables.clear()
    }

}