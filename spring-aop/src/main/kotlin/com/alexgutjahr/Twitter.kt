package com.alexgutjahr

import com.twitter.clientlib.api.TwitterApi
import com.twitter.clientlib.model.User
import org.springframework.stereotype.Service

@Service
class Twitter(private val api: TwitterApi) {

    @RateLimit
    fun user(username: String): User? {
        return api.users().findUserByUsername(username).execute().data
    }

}
