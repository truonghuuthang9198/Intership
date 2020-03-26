package com.example.demotuan2chuan

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentDetails : Fragment() {
    lateinit var listFragmentView: View
//    lateinit var userViewModel: UserViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var userAdapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
//        userViewModel = ViewModelProvider(activity!!).get(UserViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listFragmentView = inflater!!.inflate(R.layout.fragment_fragment_details, container, false)
        initVars(listFragmentView)
        recyclerView.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        userAdapter = UserAdapter()
        recyclerView.adapter = UserAdapter()

//        userViewModel.getAllUsers().observe(viewLifecycleOwner, object: Observer<List<UserClass>> {
//            override fun onChanged(t: List<UserClass>) {
//                userAdapter.setUser(t)
//            }
//        })
        return listFragmentView
    }

    private fun initVars(view: View?) {
        recyclerView= view!!.findViewById(R.id.recyclerviewUser)
    }
}