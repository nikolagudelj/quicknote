package com.nikola.quicknote.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(@PrimaryKey var uid : Int,
                @ColumnInfo(name = "text") var text : String,
                @ColumnInfo(name = "title") var title : String) {

    constructor() : this(-1, "", "")
}