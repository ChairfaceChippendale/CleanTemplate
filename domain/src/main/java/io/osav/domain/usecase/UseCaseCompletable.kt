package io.osav.domain.usecase

import io.osav.domain.executor.PostExecutionThread
import io.osav.domain.executor.ThreadExecutor
import io.reactivex.Completable
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers


abstract class UseCaseCompletable<in Params>(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread,
    disposable: CompositeDisposable
): Disposable(disposable) {

    abstract fun buildUseCase(@NonNull params: Params): Completable

    fun execute(@NonNull observer: DisposableCompletableObserver, @NonNull params: Params) {

        val observable = this.buildUseCase(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.getScheduler())

        addDisposable(observable.subscribeWith(observer))
    }

    fun executeBy(@NonNull params: Params,
                  onComplete: () -> Unit,
                  onError: (e: Throwable) -> Unit) {

        val observable = this.buildUseCase(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.getScheduler())

        addDisposable(observable.subscribeWith(object : DisposableCompletableObserver(){
            override fun onComplete() = onComplete()
            override fun onError(e: Throwable) = onError(e)
        }))
    }
}