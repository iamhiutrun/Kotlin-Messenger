package hiutrun.me.kotlinmessenger.model

data class User(
    var uid:String?,
    val email:String,
    val password:String,
    var profileImageUrl:String?
)
