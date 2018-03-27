package com.bstcine.kotlin.ui.mine


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.blankj.utilcode.util.ToastUtils

import com.bstcine.kotlin.R
import com.bstcine.kotlin.api.ApiCallback
import com.bstcine.kotlin.api.ApiClient
import com.bstcine.kotlin.base.BaseFragment
import com.bstcine.kotlin.model.user.UserModel

/**
 * A simple [Fragment] subclass.
 */
class MineFragment : BaseFragment() {

    private var mListener: MineFragment.OnFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_mine, container, false)
        rootView.findViewById<Button>(R.id.exit).setOnClickListener { view -> mListener!!.logout() }

        initData(rootView)

        return rootView
    }

    fun initData(rootView: View) {
        val message = rootView.findViewById<TextView>(R.id.message)
        addObserver(ApiClient.retrofit().userInfo(), object : ApiCallback<UserModel>() {
            override fun onSuccess(model: UserModel) {
                message.text = "积分：" + model.point
            }

            override fun onFailure(msg: String) {
                ToastUtils.showLong(msg)
            }

            override fun onFinish() {

            }
        })
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is MineFragment.OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }


    interface OnFragmentInteractionListener {
        fun logout()
    }

}
