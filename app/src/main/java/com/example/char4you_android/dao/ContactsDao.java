package com.example.char4you_android.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.char4you_android.entities.Contact;

import java.util.List;

@Dao
public interface ContactsDao {
    @Query("SELECT * FROM contact WHERE ownerId=:id")
    List<Contact> index(String id);

    @Query("SELECT * FROM contact WHERE ownerId =:ownerId AND id=:contactId")
    Contact get(String ownerId, String contactId);

    @Query("SELECT * FROM contact")
    List<Contact> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Contact... contacts);

    @Update
    void update(Contact... contacts);


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<Contact> contacts);
}
