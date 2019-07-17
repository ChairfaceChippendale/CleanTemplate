package io.osav.domain.usecase

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class Disposable(private val disposables: CompositeDisposable) {

    fun dispose() = disposables.dispose()
    fun clear() = disposables.clear()
    protected fun addDisposable(disposable: Disposable) = disposables.add(disposable)
}