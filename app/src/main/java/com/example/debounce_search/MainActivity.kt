package com.example.debounce_search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
                    }else{
                        Log.d(TAG, "onCreateOptionsMenu: $it")
                        searchThroughDatabase(it)
                    }
                }
            }
        )
        return super.onCreateOptionsMenu(menu)
    }



    private fun searchThroughDatabase(query: String) {
        // Searching By Query
    }

}