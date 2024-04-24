package com.ayoub.jpmcodingexercise.domain.base

interface BaseUseCase<In, Out> {
    suspend operator fun invoke(input: In): Out
}