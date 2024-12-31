package com.example.wishlistapp.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wish-table")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name="wish-title")
    var title: String = "",
    @ColumnInfo(name = "wish-desc")
    var description: String = ""
)

object DummyData {
    val wishes = listOf(
        Wish(1, "hello world", "karna to hai pr kya kre"),
        Wish(1, "hello world", "karna to hai pr kya kre")
    )
}