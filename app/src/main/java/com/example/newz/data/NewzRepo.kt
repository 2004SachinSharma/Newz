package com.example.newz.data

import com.example.newz.data.ApiBuilder.ApiBuilder
import com.example.newz.data.model.ApiResponse

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.Serializable
import retrofit2.HttpException
import java.io.Serial

@Serializable
class NewzRepo {
    val apiInstace = ApiBuilder.ApiBuilder.retrofitObject()

    suspend fun getHeadLine(country: String = "us"): Flow<ApiState> {
        return flow {
            emit(ApiState(loading = true))
            try {
                val response = apiInstace.getHeadLines(country = country)
                emit(ApiState(data = response))

            } catch (e: HttpException) {
                emit(ApiState(error = e.localizedMessage))
            } catch (e: Exception) {
                emit(ApiState(error = e.localizedMessage))
            }
        }
    }

        suspend fun getEverything(q   : String = "us"): Flow<ApiState> {
            return flow {
                emit(ApiState(loading = true))
                try {
                    val response = apiInstace.getEverything(q = q)
                    emit(ApiState(data = response))

                } catch (e: HttpException) {
                    emit(ApiState(error = e.localizedMessage))
                } catch (e: Exception) {
                    emit(ApiState(error = e.localizedMessage))
                }
            }

    }
}


data class ApiState(
    var loading: Boolean? = null,
    var error: String? = null,
    var data: ApiResponse? = null

)
