package com.example.carez.data.mapper

import com.example.carez.data.local.entities.UserEntity
import com.example.carez.data.remote.model.UserFireStore
import com.example.carez.domain.model.User

fun UserEntity.toDomain(): User {
    return User(
        uid = uid,
        id = id,
        email = email,
        name = name,
        gender = gender,
        age = age,
        height = height,
        weight = weight
    )
}
    
fun User.toEntity(): UserEntity {
    return UserEntity(
        uid = uid,
        id = id,
        email = email,
        name = name,
        gender = gender,
        age = age,
        height = height,
        weight = weight
    )
}

fun User.toFireStore(): UserFireStore {
    return UserFireStore(
        uid = uid,
        email = email,
        name = name,
        gender = gender,
        age = age,
        height = height,
        weight = weight
    )
}

fun UserFireStore.toEntity(): UserEntity {
    return UserEntity(
        id = "",
        uid = uid,
        email = email,
        name = name,
        gender = gender,
        age = age,
        height = height,
        weight = weight
    )
}
fun UserFireStore.toDomain(): User {
    return User(
        id = "",
        uid = uid,
        email = email,
        name = name,
        gender = gender,
        age = age,
        height = height,
        weight = weight
    )
}
