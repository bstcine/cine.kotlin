package com.bstcine.kotlin.ui.store


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bstcine.kotlin.R
import com.bstcine.kotlin.base.BaseFragment


/**
 * A simple [Fragment] subclass.
 */
class StoreFragment : BaseFragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_store, container, false)
    }

}// Required empty public constructor
