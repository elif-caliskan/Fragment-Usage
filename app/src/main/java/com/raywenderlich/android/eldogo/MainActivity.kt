package com.raywenderlich.android.eldogo

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

import android.widget.ImageView


class MainActivity : AppCompatActivity(),
    DogListFragment.OnDogSelected {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val collapsingToolbar:CollapsingToolbarLayout = findViewById(R.id.collapsing_toolbar)
        collapsingToolbar.setTitle("Dog Species")
            // 1
        if (savedInstanceState == null) {
            // 2
            supportFragmentManager
                // 3
                .beginTransaction()
                // 4
                .add(R.id.root_layout, DogListFragment.newInstance(), "dogList")
                // 5
                .commit()
        }
    }
    override fun onDogSelected(position: Int) {
        intent = Intent(applicationContext, DogDetailActivity::class.java)
        intent.putExtra("position",position)
        startActivity(intent)
    }

}
