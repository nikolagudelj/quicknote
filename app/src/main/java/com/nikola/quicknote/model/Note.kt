package com.nikola.quicknote.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(@PrimaryKey var uid : Int,
                @ColumnInfo(name = "text") var text : String) {
}