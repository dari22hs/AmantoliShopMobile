package com.example.amantoliv2.Adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.amantoliv2.Fragment.HomeFragment
import com.example.amantoliv2.Fragment.SubBuyerFragment
import com.example.amantoliv2.Fragment.SubSellerFragment

internal class SubscriptionAdapter (var context: Context, fm: FragmentManager, var totalTabs: Int): FragmentPagerAdapter(fm){

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> {
                SubBuyerFragment()
            }
            1 -> {
                SubSellerFragment()
            }
            else -> getItem(position)
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }

}//Fin internal class SubscriptionAdapter