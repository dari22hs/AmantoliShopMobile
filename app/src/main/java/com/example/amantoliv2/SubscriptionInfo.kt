package com.example.amantoliv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.amantoliv2.Adapter.SubscriptionAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.Tab

class SubscriptionInfo : AppCompatActivity() {

    //Para el apartado de suscripciones
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subscription_info)

        //Para el apartado de suscripciones
        tabLayout = findViewById(R.id.tabLayoutSubs)
        viewPager = findViewById(R.id.viewPagerSubs)

        tabLayout.addTab(tabLayout.newTab().setText("SOY COMPRADOR"))
        tabLayout.addTab(tabLayout.newTab().setText("SOY VENDEDOR"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val adapterSubs = SubscriptionAdapter(this, supportFragmentManager, tabLayout.tabCount)
        viewPager.adapter = adapterSubs

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: Tab?) {
                viewPager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: Tab?) {}

            override fun onTabReselected(tab: Tab?) {}

        })

    }//Fin onCreate
}//Fin class SubscriptionInfo