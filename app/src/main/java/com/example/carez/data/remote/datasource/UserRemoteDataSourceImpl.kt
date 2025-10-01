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

    override suspend fun signUpWithEmailAndPassword(
        email: String,
        password: String
    ): Result<User> {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            val remoteUser = result.user ?: return Result.failure(Exception("No User"))


            val user = User(
                uid = remoteUser.uid,
                email = remoteUser.email ?: ""
            )

            val userFireStore = UserFireStore(
                uid = user.uid,
                email = user.email
            )

            val firestoreResult = insertUserToFireStore(userFireStore)
            if (firestoreResult.isFailure) {
                val exception = firestoreResult.exceptionOrNull() ?: Exception("Lưu user lên Firestore thất bại")
                return Result.failure(exception)
            }


            Result.success(user)

        } catch (e: FirebaseAuthUserCollisionException) {
            Result.failure(Exception("Email đã được sử dụng"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    override suspend fun signInWithEmailAndPassword(email: String, password: String): Result<User> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            val userFireStore = result.user

            if (userFireStore != null) {
                val user = User(
                    id = userFireStore.uid,
                    email = userFireStore.email ?: "",
                )
                Result.success(user)
            } else {
                Result.failure(Exception("User is null"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}