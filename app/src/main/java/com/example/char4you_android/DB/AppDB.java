package com.example.char4you_android.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.char4you_android.dao.ContactsDao;
import com.example.char4you_android.dao.MessageDao;
import com.example.char4you_android.dao.SettingsDao;
import com.example.char4you_android.entities.Contact;
import com.example.char4you_android.entities.Message;
import com.example.char4you_android.entities.Settings;

import java.io.Serializable;

@Database(entities = {Message.class, Contact.class, Settings.class}, version = 1)
public abstract class AppDB extends RoomDatabase implements Serializable {
    private static AppDB appDB;

    public static AppDB getInstance(Context context) {
        if (appDB == null) {
            appDB = Room.databaseBuilder(context.getApplicationContext(), AppDB.class, "roomDB")
                    .allowMainThreadQueries().build();
        }
        return appDB;
    }

    public abstract MessageDao messageDao();

    public abstract SettingsDao settingsDao();

    public abstract ContactsDao contactsDao();


}
