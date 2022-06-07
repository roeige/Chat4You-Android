package com.example.char4you_android.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Contact {
    private String ownerId;
    @PrimaryKey(autoGenerate=true)
    private String id;
    private String name;
    private String server;
    private String last;
    private Date lastdate;

    public Contact(String ownerId, String id, String name, String server, String last, Date lastdate) {
        this.ownerId = ownerId;
        this.id = id;
        this.name = name;
        this.server = server;
        this.last = last;
        this.lastdate = lastdate;
    }
    public Contact(String id,String name,String server){
        this.id=id;
        this.name=name;
        this.server=server;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public void setLastdate(Date lastdate) {
        this.lastdate = lastdate;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getServer() {
        return server;
    }

    public String getLast() {
        return last;
    }

    public Date getLastdate() {
        return lastdate;
    }
}
