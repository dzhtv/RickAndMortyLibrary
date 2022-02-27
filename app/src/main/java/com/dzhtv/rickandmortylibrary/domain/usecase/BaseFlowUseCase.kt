package com.dzhtv.rickandmortylibrary.domain.usecase

import kotlinx.coroutines.flow.Flow

abstract class BaseFlowUseCase<I: BaseFlowUseCase.RequestValues, O> {

    abstract class RequestValues

    class EmptyValues : RequestValues()

    abstract suspend fun execute(params: I): Flow<O>
}