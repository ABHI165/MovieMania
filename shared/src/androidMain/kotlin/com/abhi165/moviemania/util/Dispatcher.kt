package com.abhi165.moviemania.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal class AndroidDispatcher(override val io: CoroutineDispatcher = Dispatchers.IO) : Dispatcher

internal actual fun provideDispatcher(): Dispatcher = AndroidDispatcher()