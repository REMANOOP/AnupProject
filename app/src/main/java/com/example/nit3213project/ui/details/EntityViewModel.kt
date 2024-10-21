package com.example.nit3213project.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nit3213project.model.DashboardResponse
import com.example.nit3213project.model.Entity
import com.example.nit3213project.R
import com.example.nit3213project.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class EntityViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {

    private val _entities = MutableLiveData<List<Entity>>()
    val entities: LiveData<List<Entity>> get() = _entities

    fun fetchDashboardData(keypass: String) {
        viewModelScope.launch {
            apiService.getDashboardData(keypass).enqueue(object : Callback<DashboardResponse> {
                override fun onResponse(
                    call: Call<DashboardResponse>,
                    response: Response<DashboardResponse>
                ) {
                    if (response.isSuccessful) {
                        // Mapping the response to the list of entities
                        val entityList = response.body()?.entities?.map {
                            Entity(
                                artistName = it.artistName,
                                albumTitle = it.albumTitle,
                                releaseYear = it.releaseYear,
                                genre = it.genre,
                                trackCount = it.trackCount,
                                description = it.description,
                                popularTrack = it.popularTrack,
                                imageResId = when (it.artistName) {
                                    "Radiohead" -> R.drawable.radiohead
                                    "Nirvana" -> R.drawable.nirvana
                                    "Kendrick Lamar" -> R.drawable.kendrick
                                    "Miles Davis" -> R.drawable.miles
                                    "The Beatles" -> R.drawable.beatles
                                    "Pink Floyd" -> R.drawable.floyd
                                    "Portishead" -> R.drawable.portishead
                                    else -> R.drawable.empty // Fallback image
                                }
                            )
                        } ?: emptyList()

                        _entities.postValue(entityList) // Update LiveData with the entity list
                    } else {
                        _entities.postValue(emptyList()) // Clear list on failure
                    }
                }

                override fun onFailure(call: Call<DashboardResponse>, t: Throwable) {
                    _entities.postValue(emptyList()) // Clear list on failure
                }
            })
        }
    }
}
