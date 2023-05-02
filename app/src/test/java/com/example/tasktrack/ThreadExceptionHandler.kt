package com.example.tasktrack

import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * A custom test watcher that sets the default uncaught exception handler so that any exceptions
 * inside of a view-model scope will actually fail a test.
 *
 * @author[https://github.com/Kotlin/kotlinx.coroutines/issues/1205#issuecomment-880411987]
 */

class ThreadExceptionHandler : TestWatcher() {

    private var previousHandler: Thread.UncaughtExceptionHandler? = null

    override fun starting(description: Description) {
        previousHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler { _, throwable ->
            throw throwable
        }
    }

    override fun finished(description: Description) {
        Thread.setDefaultUncaughtExceptionHandler(previousHandler)
    }
}
