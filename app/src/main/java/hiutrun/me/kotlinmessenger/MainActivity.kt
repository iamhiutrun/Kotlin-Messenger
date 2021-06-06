package hiutrun.me.kotlinmessenger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import hiutrun.me.kotlinmessenger.fragment.LoginFragment
import hiutrun.me.kotlinmessenger.viewmodel.LoginViewModel
import hiutrun.me.kotlinmessenger.viewmodel.LoginViewModelProviderFactory

class MainActivity : AppCompatActivity() {
    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginViewModelProviderFactory = LoginViewModelProviderFactory(application)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        replaceFragment(LoginFragment.newInstance())
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = this.supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment).commit()
    }
}