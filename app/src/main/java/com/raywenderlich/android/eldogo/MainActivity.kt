package com.raywenderlich.android.eldogo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

class MainActivity : AppCompatActivity(),
    DogListFragment.OnDogSelected {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
    override fun onDogSelected(dogModel: DogModel) {
        Toast.makeText(this, "Hey, you selected " + dogModel.name + "!",
            Toast.LENGTH_SHORT).show()
    }
}
