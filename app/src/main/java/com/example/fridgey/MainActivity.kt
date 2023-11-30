package com.example.fridgey

import android.app.Activity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page)

        val searchview = findViewById<SearchView>(R.id.searchview);
        val listview = findViewById<ListView>(R.id.listview);

        var alimente = arrayOf("@string/alimente")
        val arrayAdapter: ArrayAdapter<*>
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, alimente)
        listview.adapter = arrayAdapter


    }
}