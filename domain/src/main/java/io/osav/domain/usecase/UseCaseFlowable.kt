package io.osav.domain.usecase

import io.osav.domain.executor.PostExecutionThread
import io.osav.domain.executor.ThreadExecutor
import io.reactivex.Flowable
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber


abstract class UseCaseFlowable<T, in Params>(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread,
    disposable: CompositeDisposable
): Disposable(disposable) {

    abstract fun buildUseCase(@NonNull params: Params): Flowable<T>

    fun execute(@NonNull observer: DisposableSubscriber<T>, @NonNull params: Params) {
        val observable = this.buildUseCase(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.getScheduler())
        addDisposable(observable.subscribeWith(observer))
    }
}