package hiutrun.me.kotlinmessenger.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import hiutrun.me.kotlinmessenger.R
import hiutrun.me.kotlinmessenger.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    lateinit var binding: FragmentLoginBinding

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
        binding = FragmentLoginBinding.bind(view)
        binding.btnSignIn.setOnClickListener {
            Toast.makeText(context,"Hello", Toast.LENGTH_LONG).show()
        }
        binding.tvRegister.setOnClickListener {
            replaceFragment(RegisterFragment.newInstance())
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frame_layout,fragment).addToBackStack(null).commit()
    }
}