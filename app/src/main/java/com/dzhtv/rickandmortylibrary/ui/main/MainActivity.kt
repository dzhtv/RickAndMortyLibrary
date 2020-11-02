package com.dzhtv.rickandmortylibrary.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import com.dzhtv.rickandmortylibrary.R
import com.dzhtv.rickandmortylibrary.network.NetworkFactory
import com.dzhtv.rickandmortylibrary.network.repository.NetworkRepositoryImpl
import com.dzhtv.rickandmortylibrary.network.repository.NetworkRepository
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var postBtn: Button
    private lateinit var progress: ProgressBar
    private lateinit var repository: NetworkRepository
    private var viewJob = Job()
    private val viewScope = CoroutineScope(Dispatchers.Main + viewJob)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        postBtn = findViewById(R.id.post_btn)
        progress = findViewById(R.id.progress_bar)
        repository = NetworkRepositoryImpl(NetworkFactory.RickAndMortyService.createService())
    }


    override fun onStart() {
        super.onStart()
        postBtn.setOnClickListener {
            progress.visibility = View.VISIBLE
            viewScope.launch {
                withContext(Dispatchers.IO) {
                    val response = repository.getCharacters(null, null)
                    withContext(Dispatchers.Main) {
                        progress.visibility = View.GONE
                    }
                    Log.d("dzhtv", "character size: ${response?.size}")
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        viewJob.cancelChildren()
    }
}