package com.abhi165.moviemania.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal class IosDispatcher(override val io: CoroutineDispatcher = Dispatchers.Default) : Dispatcher

internal actual fun provideDispatcher(): Dispatcher = IosDispatcher()