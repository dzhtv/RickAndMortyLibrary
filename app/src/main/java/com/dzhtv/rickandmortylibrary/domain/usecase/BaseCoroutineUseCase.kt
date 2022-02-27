package com.dzhtv.rickandmortylibrary.domain.usecase

abstract class BaseCoroutineUseCase<I: BaseCoroutineUseCase.RequestValues, O> {

    abstract class RequestValues

    class EmptyValues : RequestValues()

    abstract suspend fun execute(params: I): O
}