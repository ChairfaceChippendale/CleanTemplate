package io.osav.domain.usecase

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class Disposable {

    private val disposables: CompositeDisposable by lazy { CompositeDisposable() }

    fun dispose() = disposables.dispose()
    fun clear() = disposables.clear()
    fun addDisposable(disposable: Disposable) = disposables.add(disposable)
}