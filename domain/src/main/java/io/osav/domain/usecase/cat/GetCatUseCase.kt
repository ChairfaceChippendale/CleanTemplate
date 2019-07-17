package io.osav.domain.usecase.cat

import io.osav.domain.executor.PostExecutionThread
import io.osav.domain.executor.ThreadExecutor
import io.osav.domain.gateway.CatGateway
import io.osav.domain.usecase.UseCaseMaybe
import io.osav.domain.usecase.cat.model.Cat
import io.reactivex.Maybe
import io.reactivex.disposables.CompositeDisposable

class GetCatUseCase(
    private val catGateway: CatGateway,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    disposables: CompositeDisposable
): UseCaseMaybe<Cat, GetCatUseCase.Params>(threadExecutor, postExecutionThread, disposables) {

    override fun buildUseCase(params: Params): Maybe<Cat> = catGateway.getCat(params.id)

    class Params(val id: String) {
        companion object {
            fun get(id: String) = Params(id)
        }
    }
}