package hiutrun.me.kotlinmessenger.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import hiutrun.me.kotlinmessenger.R
import hiutrun.me.kotlinmessenger.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment(R.layout.fragment_register) {

    lateinit var binding: FragmentRegisterBinding

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