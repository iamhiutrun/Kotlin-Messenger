package hiutrun.me.kotlinmessenger.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import hiutrun.me.kotlinmessenger.MainActivity
import hiutrun.me.kotlinmessenger.R
import hiutrun.me.kotlinmessenger.databinding.FragmentRegisterBinding
import hiutrun.me.kotlinmessenger.viewmodel.LoginViewModel

class RegisterFragment : Fragment(R.layout.fragment_register) {

    lateinit var binding: FragmentRegisterBinding
    lateinit var loginViewModel: LoginViewModel

    companion object {
        @JvmStatic
        fun newInstance() =
            RegisterFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterBinding.bind(view)
        loginViewModel = (activity as MainActivity).loginViewModel

        binding.btnRegister.setOnClickListener {
            val email = binding.edtEmail.text.trim().toString()
            val password = binding.edtPassword.text.trim().toString()
            if(email.isNotEmpty() && password.isNotEmpty())
            loginViewModel.createUserWithEmailAndPassword(email,password)
        }
        binding.tvSignIn.setOnClickListener {
            remove()
        }
    }

    private fun remove(){
        val fm = requireActivity().supportFragmentManager
        val fragmentTransaction = fm.beginTransaction()
        fragmentTransaction.remove(this).commit()
        fm.popBackStack()
    }
}