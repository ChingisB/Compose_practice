package com.example.compose.timer

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds


fun timer(duration: Duration): Flow<Duration> = flow{
    var newDuration = duration
    while (newDuration != Duration.ZERO){
        newDuration -= 1.seconds
        delay(1.seconds.inWholeMilliseconds)
        emit(newDuration)
    }
    emit(newDuration)
}