package com.bixlabs.bkotlin

/**
 * Result of the [attempt] function.
 * Either [value] or [error] is not null.
 *
 * @property value the return value if code execution was finished without an exception, null otherwise.
 * @property error a caught [Throwable] or null if nothing was caught.
 */
@Suppress("MemberVisibilityCanPrivate")
data class AttemptResult<out T> @PublishedApi internal constructor(val value: T?, val error: Throwable?) {
    @Suppress("UNCHECKED_CAST")
    inline fun <R> then(f: (T) -> R): AttemptResult<R> {
        if (isError) {
            @Suppress("UNCHECKED_CAST")
            return this as AttemptResult<R>
        }

        return attempt { f(value as T) }
    }

    inline val isError: Boolean
        get() = error != null

    inline val hasValue: Boolean
        get() = error == null
}

/**
 * Execute [f] and return the result or an exception, if an exception was occurred.
 */
inline fun <T> attempt(f: () -> T): AttemptResult<T> {
    var value: T? = null
    var error: Throwable? = null
    try {
        value = f()
    } catch(t: Throwable) {
        error = t
    }
    return AttemptResult(value, error)
}
