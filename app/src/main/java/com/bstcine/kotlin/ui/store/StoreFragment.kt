package com.bstcine.kotlin.ui.store


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient

import com.bstcine.kotlin.R
import com.bstcine.kotlin.api.ApiStores
import com.bstcine.kotlin.base.BaseFragment
import com.bstcine.kotlin.utils.CommonUtil


/**
 * A simple [Fragment] subclass.
 */
class StoreFragment : BaseFragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater!!.inflate(R.layout.fragment_store, container, false)

        val web = rootView.findViewById<WebView>(R.id.store)

        web.settings.javaScriptEnabled = true
        web.setWebViewClient(WebViewClient())
        web.loadUrl(CommonUtil.GetWebUrl(ApiStores.API_SERVER_URL))

        return rootView
    }

}// Required empty public constructor
