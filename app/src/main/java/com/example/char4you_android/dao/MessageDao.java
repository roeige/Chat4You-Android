package com.example.char4you_android.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.char4you_android.entities.Message;

import java.util.List;

@Dao
public interface MessageDao {

//    @Query("SELECT * FROM messages")
//    List<Message> index();
//
//    @Query("SELECT * FROM messages WHERE id =:id")
//    Message get(int id);

    @Insert
    void insert(Message... messages);

    @Update
    void update(Message... messages);

    @Delete
    void delete(Message... messages);


}
