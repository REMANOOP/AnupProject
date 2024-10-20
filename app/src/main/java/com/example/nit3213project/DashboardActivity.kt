package com.example.nit3213project

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.Intent

class DashboardActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var entityAdapter: EntityAdapter
    private var entities: List<Entity> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val keypass = intent.getStringExtra("KEYPASS")

        if (keypass != null) {
            fetchDashboardData(keypass)
        } else {
            Toast.makeText(this, "Keypass is missing!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fetchDashboardData(keypass: String) {
        RetrofitInstance.api.getDashboardData(keypass)
            .enqueue(object : Callback<DashboardResponse> {
                override fun onResponse(
                    call: Call<DashboardResponse>,
                    response: Response<DashboardResponse>
                ) {
                    if (response.isSuccessful) {
                        // Fetching the response and converting it to a list of entities
                        entities = response.body()?.entities?.map {
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

                        if (entities.isNotEmpty()) {
                            entityAdapter = EntityAdapter(entities) { entity ->
                                val intent = Intent(this@DashboardActivity, DetailsActivity::class.java)
                                intent.putExtra("ENTITY", entity)  // Pass the entity data to DetailsActivity
                                startActivity(intent)
                            }
                            recyclerView.adapter = entityAdapter
                        } else {
                            Toast.makeText(
                                this@DashboardActivity,
                                "No items to display",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            this@DashboardActivity,
                            "Failed to load dashboard data",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<DashboardResponse>, t: Throwable) {
                    Toast.makeText(
                        this@DashboardActivity,
                        "Error: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }
}
