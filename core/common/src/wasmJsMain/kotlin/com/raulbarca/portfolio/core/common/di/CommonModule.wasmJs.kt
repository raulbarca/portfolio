package com.raulbarca.portfolio.core.common.di

import kotlinx.coroutines.Dispatchers

actual fun provideIoDispatcher() = Dispatchers.Default