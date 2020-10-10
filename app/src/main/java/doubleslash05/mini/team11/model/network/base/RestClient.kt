package doubleslash05.mini.team11.model.network.base

import androidx.lifecycle.LiveData
import doubleslash05.mini.team11.BuildConfig
import doubleslash05.mini.team11.model.network.RecipeRetrofit
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

object RestClient {
    fun getRecipeService(): RecipeRetrofit = retrofit.create(RecipeRetrofit::class.java)


    private val retrofit =
        Retrofit.Builder().run {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = if(BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            }

            val headerInterceptor = object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request: Request = chain.request().newBuilder()
                        .addHeader("user_id", "1234")
                        .build()
                    return chain.proceed(request)
                }
            }

            val client = OkHttpClient.Builder().run {
                addInterceptor(loggingInterceptor)
                addInterceptor(headerInterceptor)
                build()
            }

//            baseUrl("http://192.168.0.9:3000") // 도메인 주소
            baseUrl("http://double-slash.shop/") // 도메인 주소
            client(client)

            addCallAdapterFactory(LiveDataCallAdapter.Factory())
            addConverterFactory(GsonConverterFactory.create()) // GSON을 사요아기 위해 ConverterFactory에 GSON 지정
            build()
        }
}

