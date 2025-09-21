package com.example.carez.data.remote.datasource

import com.example.carez.data.remote.model.UserFireStore
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserRemoteDataSourceImpl(
    private val firestore: FirebaseFirestore
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
}