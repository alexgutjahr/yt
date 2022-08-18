package com.alexgutjahr

import com.twitter.clientlib.TwitterCredentialsBearer
import com.twitter.clientlib.api.TwitterApi
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(TwitterProperties::class)
class TwitterConfig {

    @Bean
    fun api(props: TwitterProperties): TwitterApi {
        return TwitterApi(TwitterCredentialsBearer(props.bearer))
    }

}
