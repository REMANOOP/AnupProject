package com.example.nit3213project.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nit3213project.R
import com.example.nit3213project.ui.details.DetailsActivity
import com.example.nit3213project.ui.details.EntityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

    private val entityViewModel: EntityViewModel by viewModels() // Using ViewModel injected by Hilt

    private lateinit var recyclerView: RecyclerView
    private lateinit var entityAdapter: EntityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val keypass = intent.getStringExtra("KEYPASS")

        // Observe the LiveData from ViewModel to update UI
        entityViewModel.entities.observe(this, Observer { entities ->
            if (entities.isNotEmpty()) {
                entityAdapter = EntityAdapter(entities) { entity ->
                    val intent = Intent(this@DashboardActivity, DetailsActivity::class.java)
                    intent.putExtra("ENTITY", entity)  // Pass the entity data to DetailsActivity
                    startActivity(intent)
                }
                recyclerView.adapter = entityAdapter
            } else {
                Toast.makeText(this, "No items to display", Toast.LENGTH_SHORT).show()
            }
        })

        // Fetch dashboard data if the keypass is available
        if (keypass != null) {
            entityViewModel.fetchDashboardData(keypass)
        } else {
            Toast.makeText(this, "Keypass is missing!", Toast.LENGTH_SHORT).show()
        }
    }
}
