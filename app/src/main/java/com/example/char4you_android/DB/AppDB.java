package com.example.char4you_android.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.char4you_android.dao.MessageDao;
import com.example.char4you_android.entities.Message;

@Database(entities = {Message.class},version = 1)
public abstract class AppDB extends RoomDatabase {

    public abstract MessageDao messageDao();

}
