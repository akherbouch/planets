package com.ayoub.jpmcodingexercise.domain.base

interface BaseUseCase<In, Out> {
    suspend fun invoke(input: In): Out
}