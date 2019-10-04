package com.raywenderlich.android.eldogo

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
private const val MAX_VALUE = 200
// 1
class DogPagerAdapter(fragmentManager: FragmentManager, private val dogList: ArrayList<DogModel>) :
    FragmentStatePagerAdapter(fragmentManager) {

    // 2
    override fun getItem(position: Int): Fragment {
        return DogDetailsFragment.newInstance(dogList[position % dogList.size])
    }
    // 3
    override fun getCount(): Int {
        return dogList.size * MAX_VALUE
    }
    override fun getPageTitle(position: Int): CharSequence {
        return dogList[position % dogList.size].name
    }
}