package hiutrun.me.kotlinmessenger.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import hiutrun.me.kotlinmessenger.MainActivity
import hiutrun.me.kotlinmessenger.R
import hiutrun.me.kotlinmessenger.databinding.FragmentLoginBinding
import hiutrun.me.kotlinmessenger.viewmodel.LoginViewModel

class LoginFragment : Fragment(R.layout.fragment_login) {

    lateinit var binding: FragmentLoginBinding
    lateinit var loginViewModel: LoginViewModel

    companion object {
        @JvmStatic
        fun newInstance() =
            LoginFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = (activity as MainActivity).loginViewModel
        binding = FragmentLoginBinding.bind(view)
        binding.btnSignIn.setOnClickListener {
            Toast.makeText(context,"Hello", Toast.LENGTH_LONG).show()
        }
        binding.tvRegister.setOnClickListener {
            replaceFragment(RegisterFragment.newInstance())
        }
        binding.btnSignIn.setOnClickListener {
            val email = binding.edtEmail.text.trim().toString()
            val password = binding.edtPassword.text.trim().toString()
            if(email.isNotEmpty() && password.isNotEmpty())
                loginViewModel.signInWithEmailAndPassword(email,password)
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frame_layout,fragment).addToBackStack(null).commit()
    }
}