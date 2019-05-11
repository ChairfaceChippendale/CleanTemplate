package io.osav.domain.usecase

import io.osav.domain.executor.PostExecutionThread
import io.osav.domain.executor.ThreadExecutor
import io.reactivex.Maybe
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableMaybeObserver
import io.reactivex.schedulers.Schedulers


abstract class UseCaseMaybe<T, in Params>(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread,
    disposable: CompositeDisposable
): Disposable(disposable) {

    abstract fun buildUseCase(@NonNull params: Params): Maybe<T>

    fun execute(@NonNull observer: DisposableMaybeObserver<T>, @NonNull params: Params) {
        val observable = this.buildUseCase(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.getScheduler())
        addDisposable(observable.subscribeWith(observer))
    }
}