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

    /**
     * Validate that the given value is more than another given value,
     * or report an IllegalArgumentException with the parameter name.
     *
     * @param value the value to validate
     * @param moreThan the value which the provided value to validate should be more than
     * @param paramName the parameter name of the value
     *
     * @return value
     *
     * @throws IllegalArgumentException if the value isn't more than the second value
     */
    fun verifyMoreThan(value: Long, moreThan: Long, paramName: String): Long {
        if (value <= moreThan) throw IllegalArgumentException("$paramName >= $moreThan required but it was $value")
        return value
    }


    /**
     * Validate that the given value is more than another given value,
     * or report an IllegalArgumentException with the parameter name.
     *
     * @param value the value to validate
     * @param moreThan the value which the provided value to validate should be more than
     * @param paramName the parameter name of the value
     *
     * @return value
     *
     * @throws IllegalArgumentException if the value isn't more than the second value
     */
    fun verifyMoreThan(value: Int, moreThan: Int, paramName: String): Int {
        if (value <= moreThan) throw IllegalArgumentException("$paramName >= $moreThan required but it was $value")
        return value
    }

    /**
     * Validate that the given value is more than another given value,
     * or report an IllegalArgumentException with the parameter name.
     *
     * @param value the value to validate
     * @param moreThan the value which the provided value to validate should be more than
     * @param paramName the parameter name of the value
     *
     * @return value
     *
     * @throws IllegalArgumentException if the value isn't more than the second value
     */
    fun verifyMoreThan(value: Float, moreThan: Float, paramName: String): Float {
        if (value <= moreThan) throw IllegalArgumentException("$paramName >= $moreThan required but it was $value")
        return value
    }

    /**
     * Validate that the given value is more than another given value,
     * or report an IllegalArgumentException with the parameter name.
     *
     * @param value the value to validate
     * @param moreThan the value which the provided value to validate should be more than
     * @param paramName the parameter name of the value
     *
     * @return value
     *
     * @throws IllegalArgumentException if the value isn't more than the second value
     */
    fun verifyMoreThan(value: Double, moreThan: Double, paramName: String): Double {
        if (value <= moreThan) throw IllegalArgumentException("$paramName >= $moreThan required but it was $value")
        return value
    }

    /**
     * Validate that the given value is les than another given value,
     * or report an IllegalArgumentException with the parameter name.
     *
     * @param value the value to validate
     * @param lessThan the value which the provided value to validate should be less than
     * @param paramName the parameter name of the value
     *
     * @return value
     *
     * @throws IllegalArgumentException if the value isn't less than the second value
     */
    fun verifyLessThan(value: Long, lessThan: Long, paramName: String): Long {
        if (value >= lessThan) throw IllegalArgumentException("$paramName <= $lessThan required but it was $value")
        return value
    }

    /**
     * Validate that the given value is les than another given value,
     * or report an IllegalArgumentException with the parameter name.
     *
     * @param value the value to validate
     * @param lessThan the value which the provided value to validate should be less than
     * @param paramName the parameter name of the value
     *
     * @return value
     *
     * @throws IllegalArgumentException if the value isn't less than the second value
     */
    fun verifyLessThan(value: Int, lessThan: Int, paramName: String): Int {
        if (value >= lessThan) throw IllegalArgumentException("$paramName <= $lessThan required but it was $value")
        return value
    }


    /**
     * Validate that the given value is les than another given value,
     * or report an IllegalArgumentException with the parameter name.
     *
     * @param value the value to validate
     * @param lessThan the value which the provided value to validate should be less than
     * @param paramName the parameter name of the value
     *
     * @return value
     *
     * @throws IllegalArgumentException if the value isn't less than the second value
     */
    fun verifyLessThan(value: Float, lessThan: Float, paramName: String): Float {
        if (value >= lessThan) throw IllegalArgumentException("$paramName <= $lessThan required but it was $value")
        return value
    }

    /**
     * Validate that the given value is les than another given value,
     * or report an IllegalArgumentException with the parameter name.
     *
     * @param value the value to validate
     * @param lessThan the value which the provided value to validate should be less than
     * @param paramName the parameter name of the value
     *
     * @return value
     *
     * @throws IllegalArgumentException if the value isn't less than the second value
     */
    fun verifyLessThan(value: Double, lessThan: Double, paramName: String): Double {
        if (value >= lessThan) throw IllegalArgumentException("$paramName <= $lessThan required but it was $value")
        return value
    }
}