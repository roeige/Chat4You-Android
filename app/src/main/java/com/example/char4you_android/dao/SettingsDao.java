package com.example.char4you_android.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Update;

import com.example.char4you_android.entities.Settings;

@Dao
public interface SettingsDao {
    @Insert
    void insert(Settings... settings);

    @Update
    void update(Settings... settings);
}
