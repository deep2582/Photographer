package com.example.photographer.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlin.toString as toString
import com.example.photographer.R
import android.content.Intent
import android.widget.Button

import android.widget.EditText
import com.example.photographer.Home
import com.example.photographer.Login


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(com.example.photographer.R.layout.fragment_profile, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val database = Firebase.database
        var Name = view.findViewById<TextView>(com.example.photographer.R.id.name)
        var Email = view.findViewById<TextView>(com.example.photographer.R.id.email)
        var Mobile = view.findViewById<TextView>(com.example.photographer.R.id.mobile)
        var n = view.findViewById<TextView>(com.example.photographer.R.id.textView4)
        var logout=view.findViewById<Button>(com.example.photographer.R.id.btn_logout)
        val data = arguments
        var m = data?.get("string").toString()

        var myRef = database.getReference("Users")
        myRef.child(m).get().addOnSuccessListener {
            var name = it.child("name").value
            Name.text = name.toString()
            n.text = name.toString()
            var email = it.child("email").value
            Email.text = email.toString()
            var mob = it.child("mobile").value
            Mobile.text = mob.toString()
        }
    }

    companion object {
        const val M = "M"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

    }
}