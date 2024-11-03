package com.unison.roomapplication.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.unison.roomapplication.model.Producto
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDatabaseDao {

    @Query("SELECT * FROM products")
    fun getAllProducts(): Flow<List<Producto>>

    @Query("SELECT * FROM products WHERE id = :id")
    fun getProductById(id: Int): Flow<Producto?>

    @Insert(entity = Producto::class,  onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Producto): Long

    @Update(entity = Producto::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateProduct(product: Producto): Int

    @Delete
    suspend fun deleteProduct(product: Producto): Int
}