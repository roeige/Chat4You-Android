package com.example.char4you_android.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.char4you_android.entities.Message;

import java.util.List;

@Dao
public interface MessageDao {

    @Query("SELECT * FROM message WHERE fromId=:fromId AND toId=:toId")
    List<Message> index(String fromId, String toId);

    @Query("SELECT * FROM message")
    List<Message> getAllData();

    @Query("SELECT * FROM message WHERE id =:id")
    Message get(int id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Message... messages);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<Message> messages);


    @Update
    void update(Message... messages);

    @Delete
    void delete(Message... messages);


}
