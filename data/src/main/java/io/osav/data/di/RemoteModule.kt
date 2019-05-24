package io.osav.data.di

import com.google.gson.GsonBuilder
import io.osav.data.ApiConst
import io.osav.data.dog.remote.DogApi
import io.osav.data.interceptor.LoggingInterceptor
import io.osav.data.serializer.StringDeserializer
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val remoteModule = module {

    factory {
        get<Retrofit>().create(DogApi::class.java)
    }

    factory {
        Retrofit.Builder()
            .baseUrl(getProperty<String>("base_url"))
            .client(get())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(get(named("gson_okhttp"))))
            .build()
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get(named("logging_interceptor")))
            .connectTimeout(ApiConst.CONNECTION_TIMEOUT_SEC, TimeUnit.SECONDS)
            .readTimeout(ApiConst.READ_TIMEOUT_SEC, TimeUnit.SECONDS)
            .writeTimeout(ApiConst.WRITE_TIMEOUT_SEC, TimeUnit.SECONDS)
            .build()
    }

    single(named("gson_okhttp")) {
        GsonBuilder()
//            .serializeNulls()
//            .setLenient()
//            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
//            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
//            .registerTypeAdapter(String::class.java, StringDeserializer())
            .create()
    }

    single<Interceptor>(named("logging_interceptor")) {
        val level = if (getProperty("debug")) LoggingInterceptor.Level.BODY else LoggingInterceptor.Level.NONE
        LoggingInterceptor(level, get(named("log_net")))
    }
}