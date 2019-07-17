package io.osav.domain.usecase

import io.osav.domain.executor.PostExecutionThread
import io.osav.domain.executor.ThreadExecutor
import io.reactivex.Single
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


abstract class UseCaseSingle<T, in Params>(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread,
    disposable: CompositeDisposable
): Disposable(disposable) {

    abstract fun buildUseCase(@NonNull params: Params): Single<T>

    fun execute(@NonNull observer: DisposableSingleObserver<T>, @NonNull params: Params) {
        val observable = this.buildUseCase(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.getScheduler())
        addDisposable(observable.subscribeWith(observer))
    }

    fun executeBy(@NonNull params: Params,
                  onSuccess: (t: T) -> Unit,
                  onError: (e: Throwable) -> Unit) {

        val observable = this.buildUseCase(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.getScheduler())

        addDisposable(observable.subscribeWith(object : DisposableSingleObserver<T>(){
            override fun onSuccess(t: T) = onSuccess(t)
            override fun onError(e: Throwable) = onError(e)
        }))
    }
}