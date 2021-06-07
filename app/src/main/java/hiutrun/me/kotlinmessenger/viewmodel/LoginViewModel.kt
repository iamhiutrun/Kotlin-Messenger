package hiutrun.me.kotlinmessenger.viewmodel

import android.app.Application
import android.net.Uri
import android.util.Log

import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import hiutrun.me.kotlinmessenger.model.User
import java.util.*

class LoginViewModel(
    app:Application
    ) : AndroidViewModel(app) {

    var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    var firebaseStorage : FirebaseStorage = FirebaseStorage.getInstance()

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

    fun uploadImageToFirebaseStorage(user:User,uri: Uri){
        val filename = UUID.randomUUID().toString()
        val ref = firebaseStorage.getReference("/images/$filename")
        ref.putFile(uri).addOnSuccessListener {
            Log.d("ABC", "uploadImageToFirebaseStorage: ${it.metadata?.path}")
             ref.downloadUrl.addOnSuccessListener {
                 Log.d("TAG", "uploadImageToFirebaseStorage: $it")
                 user.profileImageUrl = it.toString()
//                 saveUserToFirebaseDatabase(user)
                 val ref = FirebaseDatabase.getInstance().getReference("/users/")
                 ref.setValue("Hello")
             }
        }.addOnFailureListener{
            Log.e("TAG", "uploadImageToFirebaseStorage: ${it.message}", )
        }
    }

    private fun saveUserToFirebaseDatabase(user: User) {
        val uid = firebaseAuth.uid
        user.uid = uid!!
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")
        ref.setValue(user).addOnFailureListener {
            Log.e("ABC", "saveUserToFirebaseDatabase: ${it.message}" )
        }.addOnSuccessListener {
            Log.d("TAG", "saveUserToFirebaseDatabase: ")
        }
    }
}