package com.dzhtv.rickandmortylibrary.data.source

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

open class BaseCacheDataSource {

    suspend fun <T> execute(
        coroutineDispatcher: CoroutineDispatcher,
        block: () -> T
    ): T {
        return withContext(coroutineDispatcher) {
            block.invoke()
        }
    }
}