package jose.com.bookworm.extensions

import timber.log.Timber

/*
* Log the error stack trace through Timber*/
fun Throwable.logStackTrace() {
  Timber.d(stackTraceToString())
}