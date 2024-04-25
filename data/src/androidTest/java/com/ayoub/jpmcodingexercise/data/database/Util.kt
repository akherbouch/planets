package com.ayoub.jpmcodingexercise.data.database

import androidx.paging.PagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <Key: Any, Model: Any> PagingSource<Key, Model>.getData(): List<Model> {
    return withContext(Dispatchers.IO) {
        val data = mutableListOf<Model>()
        val loadResult = this@getData.load(
            PagingSource.LoadParams.Refresh(
                key = null, loadSize = Int.MAX_VALUE, placeholdersEnabled = false
            )
        )
        when (loadResult) {
            is PagingSource.LoadResult.Error -> throw loadResult.throwable
            is PagingSource.LoadResult.Page -> data.addAll(loadResult.data)
            is PagingSource.LoadResult.Invalid -> TODO()
        }
        return@withContext data
    }
}