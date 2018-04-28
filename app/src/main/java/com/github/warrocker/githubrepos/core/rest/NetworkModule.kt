package com.github.warrocker.githubrepos.core.rest

import com.github.warrocker.githubrepos.BuildConfig
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Warrocker on 10.12.2017.
 */
class NetworkModule private constructor(){
    lateinit var serverApi: ApiService
    private object Holder { val INSTANCE = NetworkModule() }
    companion object {
        val instance: NetworkModule by lazy { Holder.INSTANCE }
    }

    fun setUpNetworkModule() {
        serverApi = setUpRetrofit()
    }
    private fun setUpRetrofit() : ApiService{
        val gson = GsonBuilder().setLenient().create()
        val builder = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .validateEagerly(true)
                .client(provideHttpClient())
        val build = builder.build()
        return build.create(ApiService::class.java)
    }

    private fun provideHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level =
                if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY
                else
                    HttpLoggingInterceptor.Level.NONE

        return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .followRedirects(false)
                .build()
    }
}