package hiutrun.me.kotlinmessenger.viewmodel

import android.app.Application
import android.util.Log

import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel(
    app:Application
    ) : AndroidViewModel(app) {

    var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun createUserWithEmailAndPassword(email:String, password: String){
        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {
            if(it.isSuccessful){
                val user = firebaseAuth.currentUser
                Log.d("TAG", "createUserWithEmailAndPassword: "+user!!.email)
            }
        }
            .addOnFailureListener {
                Toast.makeText(getApplication(),it.message,Toast.LENGTH_LONG).show()
            }
    }

    fun signInWithEmailAndPassword(email:String, password: String){
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(getApplication(), "Succeed", Toast.LENGTH_LONG).show()
            }
            }
            .addOnFailureListener {
                Toast.makeText(getApplication(),it.message,Toast.LENGTH_LONG).show()
            }
    }
}