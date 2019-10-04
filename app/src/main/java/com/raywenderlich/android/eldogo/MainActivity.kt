package com.raywenderlich.android.eldogo

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.nshmura.recyclertablayout.RecyclerTabLayout

class MainActivity : AppCompatActivity(),
    DogListFragment.OnDogSelected {
    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: DogPagerAdapter
    private lateinit var recyclerTabLayout: RecyclerTabLayout
    private lateinit var imageResIds: IntArray
    private lateinit var names: Array<String>
    private lateinit var descriptions: Array<String>
    private lateinit var urls: Array<String>
    private var dogModels:  ArrayList<DogModel> = arrayListOf<DogModel>()

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_main)

            names = resources.getStringArray(R.array.names)
            descriptions = resources.getStringArray(R.array.descriptions)
            urls = resources.getStringArray(R.array.urls)
            val typedArray = resources.obtainTypedArray(R.array.images)
            val imageCount = names.size
            imageResIds = IntArray(imageCount)
            for (i in 0 until imageCount) {
                imageResIds[i] = typedArray.getResourceId(i, 0)
                val dog  = DogModel(imageResIds[i], names[i], descriptions[i], urls[i])
                dogModels.add(dog)
            }
            typedArray.recycle()

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
        // 1
        val detailsFragment =
            DogDetailsFragment.newInstance(dogModels[position])
        supportFragmentManager
            .beginTransaction()
            // 2
            .replace(R.id.root_layout, detailsFragment, "dogDetails")
            // 3
            .addToBackStack(null)
            .commit()
    }
}
