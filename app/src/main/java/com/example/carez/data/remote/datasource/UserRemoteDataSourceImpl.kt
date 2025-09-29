package com.example.carez.data.remote.datasource

import com.example.carez.data.remote.model.UserFireStore
import com.example.carez.domain.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserRemoteDataSourceImpl(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : UserRemoteDataSource {

    override suspend fun insertUserToFireStore(user: UserFireStore): Result<UserFireStore> {
        return try {
            firestore.collection("users")
                .document(user.uid)
                .set(user)
                .await()
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getUserFromFireStore(uid: String): Result<UserFireStore> {
        return try {
            val snapshot = firestore.collection("users")
                .document(uid)
                .get()
                .await()

            val remoteUser = snapshot.toObject(UserFireStore::class.java)
                ?: return Result.failure(Exception("User not found"))

            Result.success(remoteUser)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signUpWithGmailAndPassword(
        email: String,
        password: String
    ): Result<User> {
    return try {
        val result = auth.createUserWithEmailAndPassword(email, password).await()
        val firebaseUser = result.user ?: return Result.failure(Exception("No User"))
        val user = User(
            uid = firebaseUser.uid,
            email = firebaseUser.email ?: "null"
        )
        Result.success(user)
    } catch (e: FirebaseAuthUserCollisionException) {
        // Lỗi email đã tồn tại
        Result.failure(Exception("Email đã được sử dụng"))
    } catch (e: Exception) {
        Result.failure(e)
    }


    }

}