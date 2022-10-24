package com.dzhtv.rickandmortylibrary.domain.usecase

import com.dzhtv.rickandmortylibrary.domain.model.ErrorResponse
import com.dzhtv.rickandmortylibrary.domain.model.NetworkException
import com.dzhtv.rickandmortylibrary.domain.model.NullResultException
import com.dzhtv.rickandmortylibrary.domain.model.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseCoroutineUseCase<in I : BaseCoroutineUseCase.RequestValues, out O> {

    abstract class RequestValues

    class EmptyValues : RequestValues()

    abstract suspend fun run(params: I): O

    open suspend fun execute(params: I): ResultWrapper<O> {
        return withContext(Dispatchers.IO) {
            try {
                val result = run(params)
                if (result != null) {
                    ResultWrapper.Success(result)
                } else {
                    throw NullResultException()
                }
            } catch (er: NetworkException) {
                ResultWrapper.Error(
                    ErrorResponse(er.errorMsg)
                )
            } catch (ex: Exception) {
                ResultWrapper.Error(
                    ErrorResponse(ex.message ?: ex.toString())
                )
            }
        }
    }
}