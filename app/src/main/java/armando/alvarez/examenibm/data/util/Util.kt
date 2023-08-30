package armando.alvarez.examenibm.data.util

import retrofit2.Response

object Util {

    fun <T> responseToResource(response: Response<T>): Resource<T> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }

        return if (response.errorBody() == null) {
            Resource.Error(null, response.body())
        } else {
            Resource.Error(response.errorBody().toString(), response.body())
        }

    }

}