package my.booking.bookhotel.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import my.booking.bookhotel.BuildConfig
import my.booking.bookhotel.local.AppPreference
import my.booking.bookhotel.remote.BaseUrl.DEV_URL
import my.booking.bookhotel.remote.interceptors.JsonParseInterceptor
import my.booking.bookhotel.remote.services.HomeService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val TIME_OUT = 20L
    private const val API_VERSION = 1

    @Provides
    fun provideHomeService(retrofit: Retrofit): HomeService{
        return retrofit.create(HomeService::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        interceptor: JsonParseInterceptor
    ): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
            .connectTimeout(TIME_OUT * 3, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(interceptor)

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(logging)
            clientBuilder.addInterceptor(ChuckerInterceptor(context))
        }
        return clientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshiFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(DEV_URL)
            .client(okHttpClient)
            .addConverterFactory(moshiFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideMoshiConverter(): MoshiConverterFactory{
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        return MoshiConverterFactory.create(moshi).asLenient()
    }

    @Singleton
    @Provides
    fun provideJsonMainInterceptor(
        preference: AppPreference,
//        listener: JsonParseInterceptor.Listener
    ): JsonParseInterceptor {
        return JsonParseInterceptor(preference
//            , listener
        )
    }
}