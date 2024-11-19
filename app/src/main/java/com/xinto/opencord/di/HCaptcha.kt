package com.xinto.opencord.di

import android.content.Context
import com.hcaptcha.sdk.HCaptcha
import com.hcaptcha.sdk.HCaptchaConfig
import com.xinto.opencord.BuildConfig
import org.koin.core.parameter.ParametersHolder
import org.koin.dsl.module

val hcaptchaModule = module {
    single { context ->
        HCaptchaConfig.builder().apply {
            siteKey(BuildConfig.CAPTCHA_KEY)
            resetOnTimeout(true)
            tokenExpiration(TOKEN_EXPIRATION)
        }.build().let { config ->
            HCaptcha.getClient(context as Context).apply { 
                setup(config)
            }
        }
    }

    companion object {
        // Configurable constant for token expiration
        private const val TOKEN_EXPIRATION = 5 * 60 * 1000L // 5 minutes
    }
}