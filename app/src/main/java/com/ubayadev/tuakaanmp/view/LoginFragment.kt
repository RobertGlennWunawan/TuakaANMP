package com.ubayadev.tuakaanmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.ubayadev.tuakaanmp.R
import com.ubayadev.tuakaanmp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener {
            val user = binding.txtUsername.text.toString()
            val pass = binding.txtPassword.text.toString()

            if (user == "student" && pass == "123") {
                binding.txtError.visibility = View.GONE

                val action = LoginFragmentDirections.actionDashboardFragment()
                view.findNavController().navigate(action)
            } else {
                binding.txtError.text = "Username atau password salah! coba kembali username = student, password = 123"
                binding.txtError.visibility = View.VISIBLE
            }
        }
    }
}