package com.xinto.opencord.di

import android.content.Context
import com.hcaptcha.sdk.HCaptcha
import com.hcaptcha.sdk.HCaptchaConfig
import com.xinto.opencord.BuildConfig
import org.koin.dsl.module

val hcaptchaModule = module {
    single { context ->
        val config = HCaptchaConfig.builder()
            .siteKey(BuildConfig.CAPTCHA_KEY)
            .resetOnTimeout(true)
            .tokenExpiration(TOKEN_EXPIRATION)
            .build()
        
        HCaptcha.getClient(context as Context).setup(config)
    }

    companion object {
        private const val TOKEN_EXPIRATION = 1 * 60 * 1000L // 1 minute
    }
}