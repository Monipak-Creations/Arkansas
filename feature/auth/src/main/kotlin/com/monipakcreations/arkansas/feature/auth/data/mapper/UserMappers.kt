package com.monipakcreations.arkansas.feature.auth.data.mapper

import com.monipakcreations.arkansas.core.database.model.UserEntity
import com.monipakcreations.arkansas.feature.auth.domain.model.User
import com.monipakcreations.arkansas.feature.auth.data.remote.dto.LoginResponseDto

internal fun LoginResponseDto.asEntity() = UserEntity(
    id = id,
    username = username,
    email = email,
    firstName = firstName,
    lastName = lastName,
    gender = gender,
    imageUrl = image,
)

internal fun UserEntity.asExternalModel() = User(
    id = id,
    username = username,
    email = email,
    firstName = firstName,
    lastName = lastName,
    gender = gender,
    imageUrl = imageUrl,
)
