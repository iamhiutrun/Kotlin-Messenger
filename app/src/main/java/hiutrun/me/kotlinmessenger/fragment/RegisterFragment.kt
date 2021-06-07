package hiutrun.me.kotlinmessenger.fragment

import android.content.ContentResolver
import android.content.Intent
import android.content.IntentFilter
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import hiutrun.me.kotlinmessenger.MainActivity
import hiutrun.me.kotlinmessenger.R
import hiutrun.me.kotlinmessenger.databinding.FragmentRegisterBinding
import hiutrun.me.kotlinmessenger.model.User
import hiutrun.me.kotlinmessenger.viewmodel.LoginViewModel

class RegisterFragment : Fragment(R.layout.fragment_register) {

    lateinit var binding: FragmentRegisterBinding
    lateinit var loginViewModel: LoginViewModel
    var selectedImage : Uri?=null

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()){ uri: Uri?->
        selectedImage = uri
        val bitmap = MediaStore.Images.Media.getBitmap(requireContext().contentResolver,uri)
        val bitmapDrawable = BitmapDrawable(bitmap)
        binding.btnSelectPicture.setBackgroundDrawable(bitmapDrawable)
    }

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
            val user = User(null,email, password,null)
            if(email.isNotEmpty() && password.isNotEmpty())
            loginViewModel.createUserWithEmailAndPassword(email,password)
            loginViewModel.uploadImageToFirebaseStorage(user, selectedImage!!)
        }
        binding.tvSignIn.setOnClickListener {
            remove()
        }

        binding.btnSelectPicture.setOnClickListener {
            getContent.launch("image/*")
        }
    }


    private fun remove(){
        val fm = requireActivity().supportFragmentManager
        val fragmentTransaction = fm.beginTransaction()
        fragmentTransaction.remove(this).commit()
        fm.popBackStack()
    }
}