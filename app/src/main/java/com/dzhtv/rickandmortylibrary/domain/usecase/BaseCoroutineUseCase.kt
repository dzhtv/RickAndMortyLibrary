package com.dzhtv.rickandmortylibrary.domain.usecase

abstract class BaseCoroutineUseCase<I: BaseCoroutineUseCase.InputValues, O> {

    abstract class InputValues

    abstract suspend fun execute(params: I): O
}