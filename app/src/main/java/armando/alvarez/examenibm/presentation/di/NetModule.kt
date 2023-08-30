package armando.alvarez.examenibm.presentation.di

import armando.alvarez.examenibm.data.api.BooksApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {
    @Provides
    @Singleton
    fun getGson(): Gson {
        return GsonBuilder().serializeNulls()
            .setLenient().create()
    }

    @Provides
    @Singleton
    fun getInterceptor(): Interceptor {
        return Interceptor {
            var request: Request = it.request()
            val builder = request.newBuilder()
                .header("Cache-Control", "no-cache")
            request = builder.build()
            it.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun getOkHttpClient(
        interceptor: Interceptor
    ): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .cache(null)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS)

        return httpBuilder
            .protocols(mutableListOf(Protocol.HTTP_1_1))
            .build()
    }

    @Provides
    @Singleton
    fun getRetrofit(
        client: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideBooksApiService(retrofit: Retrofit): BooksApiService {
        return retrofit.create(BooksApiService::class.java)
    }

}