package com.bixlabs.bkotlin.utils

object ObjectValidation {

    /**
     * Verifies if the object is not null and returns it or throws a [NullPointerException]
     * with the given message.
     *
     * @param obj the object to verify
     * @param message the message to use with the NullPointerException
     *
     * @return the object itself
     *
     * @throws NullPointerException if object is null
     */
    fun <T> requireNotNull(obj: T, message: String): T {
        if (obj == null) throw NullPointerException(message)
        return obj
    }

    /**
     * Validate that the given value is positive or report an IllegalArgumentException with
     * the parameter name.
     *
     * @param value the value to validate
     * @param paramName the parameter name of the value
     *
     * @return value
     *
     * @throws IllegalArgumentException if the value isn't positive
     */
    fun verifyPositive(value: Long, paramName: String): Long {
        if (value < 0) throw IllegalArgumentException("$paramName > 0 required but it was $value")
        return value
    }

    /**
     * Validate that the given value is positive or report an IllegalArgumentException with
     * the parameter name.
     *
     * @param value the value to validate
     * @param paramName the parameter name of the value
     *
     * @return value
     *
     * @throws IllegalArgumentException if the value isn't positive
     */
    fun verifyPositive(value: Int, paramName: String): Int {
        if (value < 0) throw IllegalArgumentException("$paramName > 0 required but it was $value")
        return value
    }

    /**
     * Validate that the given value is positive or report an IllegalArgumentException with
     * the parameter name.
     *
     * @param value the value to validate
     * @param paramName the parameter name of the value
     *
     * @return value
     *
     * @throws IllegalArgumentException if the value isn't positive
     */
    fun verifyPositive(value: Float, paramName: String): Float {
        if (value < 0) throw IllegalArgumentException("$paramName > 0 required but it was $value")
        return value
    }

    /**
     * Validate that the given value is positive or report an IllegalArgumentException with
     * the parameter name.
     *
     * @param value the value to validate
     * @param paramName the parameter name of the value
     *
     * @return value
     *
     * @throws IllegalArgumentException if the value isn't positive
     */
    fun verifyPositive(value: Double, paramName: String): Double {
        if (value < 0) throw IllegalArgumentException("$paramName > 0 required but it was $value")
        return value
    }
}