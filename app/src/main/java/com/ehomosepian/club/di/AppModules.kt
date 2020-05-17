package com.ehomosepian.club.di

import android.app.Application
import androidx.room.Room
import com.ehomosepian.club.api.APIService
import com.ehomosepian.club.api.ApiConstants
import com.ehomosepian.club.api.ApiConstants.Companion.TIMEOUT_IN_SEC
import com.ehomosepian.club.db.AppDb
import com.ehomosepian.club.db.dao.UserDao
import com.ehomosepian.club.repository.Repository
import com.ehomosepian.club.ui.dashboard.DashboardViewModel
import com.ehomosepian.club.ui.home.HomeViewModel
import com.ehomosepian.club.ui.login.LoginViewModel
import com.ehomosepian.club.util.AppExecutors
import com.ehomosepian.club.util.LiveDataCallAdapterFactory
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val viewModelModule = module {
    viewModel { DashboardViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { HomeViewModel(get()) }
}

val repositoryModule = module {
    single { Repository(get(),get(),get()) }
}

val databaseModule = module {

    fun provideDb(app: Application): AppDb {
        return Room
            .databaseBuilder(app, AppDb::class.java, "ehomosepian.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideUserDao(db: AppDb): UserDao {
        return db.userDao()
    }

    single { provideDb(androidApplication()) }
    single { provideUserDao(get()) }
}

val retrofitModule = module {

    fun provideHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.connectTimeout(15, TimeUnit.SECONDS)
        okHttpClient.readTimeout(15, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(interceptor)
        return okHttpClient.build()
    }

    fun provideRetrofit(okHttpClient: OkHttpClient): APIService {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(ApiConstants.ENDPOINT)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .serializeNulls()
                        .setPrettyPrinting()
                        .create()
                )
            )
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
           // .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
        return retrofit.create(APIService::class.java)
    }


    single { provideHttpClient() }
    single { provideRetrofit(get()) }
    single { AppExecutors() }
}