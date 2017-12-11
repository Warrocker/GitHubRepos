package com.github.warrocker.githubrepos.core.rest

import android.util.Log
import android.widget.Toast
import com.github.warrocker.githubrepos.BuildConfig
import com.github.warrocker.githubrepos.R
import com.github.warrocker.githubrepos.core.ActivityContextKeeper
import com.github.warrocker.githubrepos.core.database.dao.DatabaseModule
import com.github.warrocker.githubrepos.core.entity.reposentities.RepoItem
import com.github.warrocker.githubrepos.core.entity.reposentities.Repositories
import com.github.warrocker.githubrepos.interfaces.OnFinishLoadListener
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Warrocker on 10.12.2017.
 */
class NetworkModule {
    var iServerApi: IApiService
        private set

    init {
        val gson = GsonBuilder().setLenient().create()
        val builder = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .validateEagerly(true)
                .client(provideHttpClient())
        val build = builder.build()
        iServerApi = build.create(IApiService::class.java)
    }

    private fun provideHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.BASIC

        return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .followRedirects(false)
                .build()
    }

    fun showFailureProblem(t: Throwable, errorMessage: String) {
        val context = ActivityContextKeeper.instance?.context?.getMainContext()
        Toast.makeText(context, R.string.failure_error, Toast.LENGTH_LONG).show()
        Log.d("RETROFIT ERROR", errorMessage, t)
    }
    fun getRepoFromNetwork(searchString : String, responseListener: OnFinishLoadListener<List<RepoItem>>) : DisposableSingleObserver<Repositories>{
        val disposable: DisposableSingleObserver<Repositories> = object : DisposableSingleObserver<Repositories>() {
            override fun onSuccess(t: Repositories?) {
                DatabaseModule.lastSearch = searchString
                t?.items?.let { responseListener.onLoadFinished(it) }
                t?.items?.let { DatabaseModule.saveRepoToDB(it) }
            }

            override fun onError(e: Throwable?) {
                if (e is HttpException)
                    showFailureProblem(e, e.message())
                responseListener.onLoadInterrupted()
            }
        }
        iServerApi.getUserProjects(searchString)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(disposable)
        return disposable
    }
}