package com.example.debounce_search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import com.example.debounce_search.databinding.ActivityMainBinding

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)

        val search = menu?.findItem(R.id.menu_search)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(
            DebouncingQueryTextListener(
                this@MainActivity.lifecycle
            ){newText ->
                newText?.let {
                    if(it.isEmpty()){
                        // Reset Search
                        resetSearch()
                    }else{
                        // Searching By Query
                        Log.d(TAG, "onCreateOptionsMenu: $it")
                        binding.textview.text = it
                    }
                }
            }
        )
        return super.onCreateOptionsMenu(menu)
    }

    private fun resetSearch() {
        binding.textview.text = ""
    }



}