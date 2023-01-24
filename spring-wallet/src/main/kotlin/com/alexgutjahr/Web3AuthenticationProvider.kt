package com.alexgutjahr

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import org.web3j.crypto.Keys
import org.web3j.crypto.Sign
import org.web3j.utils.Numeric
import java.math.BigInteger

@Component
class Web3AuthenticationProvider(private val users: UserService): AuthenticationProvider {

    private fun valid(signature: String, address: String, nonce: String): Boolean {
        val r = signature.substring(0,66)
        val s = "0x${signature.substring(66,130)}"
        val v = "0x${signature.substring(130,132)}"

        val data = Sign.SignatureData(
            Numeric.hexStringToByteArray(v),
            Numeric.hexStringToByteArray(r),
            Numeric.hexStringToByteArray(s)
        )

        val key = Sign.signedPrefixedMessageToKey(nonce.toByteArray(), data)
        return matches(key, address)
    }

    private fun matches(key: BigInteger, address: String): Boolean {
        return "0x${Keys.getAddress(key).lowercase()}" == address.lowercase()
    }

    override fun authenticate(authentication: Authentication): Authentication {
        users.findByAddress(authentication.name)?.let {
            val signature = authentication.credentials.toString()
            if (valid(signature, it.address, it.nonce)) {
                return Web3Authentication(it.address, signature)
            }
        }

        throw BadCredentialsException("${authentication.name} is not allowed to log in.")
    }

    override fun supports(authentication: Class<*>?) = Web3Authentication::class.java == authentication
}