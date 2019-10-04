package com.raywenderlich.android.eldogo

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.nshmura.recyclertablayout.RecyclerTabLayout
//coordinatorLayout

class DogDetailActivity : FragmentActivity() {
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
        setContentView(R.layout.activity_dogs)
        viewPager = findViewById(R.id.viewPager)
        names = resources.getStringArray(R.array.names)
        descriptions = resources.getStringArray(R.array.descriptions)
        urls = resources.getStringArray(R.array.urls)
        val typedArray = resources.obtainTypedArray(R.array.images)
        val imageCount = names.size
        imageResIds = IntArray(imageCount)
        for (i in 0 until imageCount) {
            imageResIds[i] = typedArray.getResourceId(i, 0)
            val dog = DogModel(imageResIds[i], names[i], descriptions[i], urls[i])
            dogModels.add(dog)
        }
        typedArray.recycle()
        val bundle:Bundle = intent.extras
        val dogIndex:Int = bundle.getInt("position")
        pagerAdapter = DogPagerAdapter(supportFragmentManager, dogModels)
        viewPager.adapter = pagerAdapter
        recyclerTabLayout = findViewById(R.id.recyclerTabLayout)
        recyclerTabLayout.setUpWithViewPager(viewPager)
        viewPager.currentItem = (pagerAdapter.count / 2) + dogIndex

    }
}
