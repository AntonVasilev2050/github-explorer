package com.avvsoft2050.githubtool.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "loaded_repo_table")
data class LoadedRepo(

   @PrimaryKey(autoGenerate = true)
   var id: Int?,

   @ColumnInfo(name = "owner_name") val ownerName: String,
   @ColumnInfo(name = "repo_name") val repoName: String
)
