package io.osav.domain.usecase.cat

import io.osav.domain.executor.PostExecutionThread
import io.osav.domain.executor.ThreadExecutor
import io.osav.domain.gateway.CatGateway
import io.osav.domain.usecase.UseCaseSingle
import io.osav.domain.usecase.cat.model.Cat
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class GetAllCatsUseCase(
    private val catGateway: CatGateway,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    disposables: CompositeDisposable
): UseCaseSingle<List<Cat>, GetAllCatsUseCase.Params>(threadExecutor, postExecutionThread, disposables) {

    override fun buildUseCase(params: Params): Single<List<Cat>> = catGateway.getCats()

    class Params {
        companion object {
            fun get() = Params()
        }
    }
}