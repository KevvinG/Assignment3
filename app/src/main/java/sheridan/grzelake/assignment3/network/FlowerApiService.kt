package sheridan.grzelake.assignment3.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://tetervak.dev.fast.sheridanc.on.ca/Examples/jQuery/Flowers3/data/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface FlowerApiService {
    @GET("catalog.json")
    suspend fun getCatalog(): CatalogJson
}

object FlowerApi {
    val retrofitService : FlowerApiService by lazy {
        retrofit.create(FlowerApiService::class.java) }
}