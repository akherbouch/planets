package com.ayoub.jpmcodingexercise.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "remote_keys")
data class RemoteKey(
    @PrimaryKey
    val planetId: String,
    val nextPage: Int?
)