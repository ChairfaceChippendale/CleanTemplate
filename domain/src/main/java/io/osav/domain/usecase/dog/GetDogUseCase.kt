package io.osav.domain.usecase.dog

import io.osav.domain.executor.PostExecutionThread
import io.osav.domain.executor.ThreadExecutor
import io.osav.domain.gateway.DogGateway
import io.osav.domain.usecase.UseCaseMaybe
import io.osav.domain.usecase.dog.model.Dog
import io.reactivex.Maybe
import io.reactivex.disposables.CompositeDisposable

class GetDogUseCase(
    private val dogGateway: DogGateway,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    disposable: CompositeDisposable
): UseCaseMaybe<Dog, GetDogUseCase.Params>(threadExecutor, postExecutionThread, disposable) {

    override fun buildUseCase(params: Params): Maybe<Dog> = dogGateway.getDog(params.query)

    class Params(val query: String) {
        companion object {
            fun get(query: String) = Params(query)
        }
    }
}