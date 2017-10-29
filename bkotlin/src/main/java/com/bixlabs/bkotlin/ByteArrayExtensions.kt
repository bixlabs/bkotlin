package com.bixlabs.bkotlin

import java.math.BigInteger

/**
 * Return a HEX representation of this [ByteArray]
 */
fun ByteArray.toHexString(): String =
        String.format("%0" + this.size * 2 + 'x', BigInteger(1, this))
