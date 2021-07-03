package com.carbit3333333.loftcoin2.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
abstract class CoinsDao {
    @Query("SELECT * FROM RoomCoin")
    abstract LiveData<List<RoomCoin>> fetchAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void insert(List<RoomCoin> coins);
}
