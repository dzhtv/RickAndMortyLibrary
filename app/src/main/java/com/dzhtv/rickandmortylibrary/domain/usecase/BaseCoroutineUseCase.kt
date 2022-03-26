package com.dzhtv.rickandmortylibrary.domain.usecase

abstract class BaseCoroutineUseCase<in I: BaseCoroutineUseCase.RequestValues, out O> {

    abstract class RequestValues

    class EmptyValues : RequestValues()

    abstract suspend fun execute(params: I): O
}