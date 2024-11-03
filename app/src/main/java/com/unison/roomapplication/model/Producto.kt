package com.unison.roomapplication.model

import java.util.Date
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
//import kotlinx.serialization.Serializable


@Entity(tableName = "products")
data class Producto(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "name", defaultValue = "")
    var name: String = "",

    @ColumnInfo(name = "description", defaultValue = "")
    var description: String = "",

    @ColumnInfo(name = "price", typeAffinity = ColumnInfo.REAL)
    var price: Double = 0f.toDouble(),

    @ColumnInfo(name = "date", defaultValue = "")
    var date: String = ""

)
