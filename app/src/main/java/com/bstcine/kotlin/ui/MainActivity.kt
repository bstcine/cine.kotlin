package com.bstcine.kotlin.ui

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.blankj.utilcode.util.FragmentUtils
import com.bstcine.kotlin.AppContext
import com.bstcine.kotlin.R
import kotlinx.android.synthetic.main.activity_main.*
import android.os.PersistableBundle
import com.bstcine.kotlin.ui.learn.LearnFragment
import com.bstcine.kotlin.ui.mine.MineFragment
import com.bstcine.kotlin.ui.store.StoreFragment


class MainActivity : AppCompatActivity(),MineFragment.OnFragmentInteractionListener {

    private var mFragments = arrayOfNulls<Fragment>(3)
    private var curIndex: Int = 1

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_learn -> {
                showCurrentFragment(0)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_store -> {
                showCurrentFragment(1)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_mine -> {
                if (!AppContext.instance!!.isLogin) {
                    startActivity(Intent(this, LoginActivity::class.java))
                    return@OnNavigationItemSelectedListener false
                } else {
                    showCurrentFragment(2)
                }
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            curIndex = savedInstanceState.getInt("curIndex");
        }

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        mFragments[0] = LearnFragment()
        mFragments[1] = StoreFragment()
        mFragments[2] = MineFragment()
        FragmentUtils.add(supportFragmentManager, mFragments, R.id.fragment_container, curIndex);
        navigation.selectedItemId = R.id.navigation_store
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putInt("curIndex", curIndex)
    }

    private fun showCurrentFragment(index: Int) {
        curIndex = index
        FragmentUtils.showHide(index, mFragments.asList())
    }

    override fun logout() {
        AppContext.instance!!.logout()
        navigation.selectedItemId = R.id.navigation_store
    }
}
