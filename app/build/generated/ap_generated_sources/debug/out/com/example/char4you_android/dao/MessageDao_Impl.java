package com.example.char4you_android.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.char4you_android.entities.Message;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class MessageDao_Impl implements MessageDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Message> __insertionAdapterOfMessage;

  private final EntityDeletionOrUpdateAdapter<Message> __deletionAdapterOfMessage;

  private final EntityDeletionOrUpdateAdapter<Message> __updateAdapterOfMessage;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public MessageDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMessage = new EntityInsertionAdapter<Message>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `Message` (`id`,`content`,`created`,`sent`,`fromId`,`toId`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Message value) {
        stmt.bindLong(1, value.getId());
        if (value.getContent() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getContent());
        }
        if (value.getCreated() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getCreated());
        }
        final int _tmp = value.isSent() ? 1 : 0;
        stmt.bindLong(4, _tmp);
        if (value.getFromId() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getFromId());
        }
        if (value.getToId() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getToId());
        }
      }
    };
    this.__deletionAdapterOfMessage = new EntityDeletionOrUpdateAdapter<Message>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Message` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Message value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfMessage = new EntityDeletionOrUpdateAdapter<Message>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Message` SET `id` = ?,`content` = ?,`created` = ?,`sent` = ?,`fromId` = ?,`toId` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Message value) {
        stmt.bindLong(1, value.getId());
        if (value.getContent() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getContent());
        }
        if (value.getCreated() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getCreated());
        }
        final int _tmp = value.isSent() ? 1 : 0;
        stmt.bindLong(4, _tmp);
        if (value.getFromId() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getFromId());
        }
        if (value.getToId() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getToId());
        }
        stmt.bindLong(7, value.getId());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM message WHERE fromId=? AND toId=?";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Message... messages) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfMessage.insert(messages);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAll(final List<Message> messages) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfMessage.insert(messages);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Message... messages) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfMessage.handleMultiple(messages);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Message... messages) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfMessage.handleMultiple(messages);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll(final String fromId, final String toId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    int _argIndex = 1;
    if (fromId == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, fromId);
    }
    _argIndex = 2;
    if (toId == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, toId);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public List<Message> index(final String fromId, final String toId) {
    final String _sql = "SELECT * FROM message WHERE fromId=? AND toId=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (fromId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, fromId);
    }
    _argIndex = 2;
    if (toId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, toId);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
      final int _cursorIndexOfCreated = CursorUtil.getColumnIndexOrThrow(_cursor, "created");
      final int _cursorIndexOfSent = CursorUtil.getColumnIndexOrThrow(_cursor, "sent");
      final int _cursorIndexOfFromId = CursorUtil.getColumnIndexOrThrow(_cursor, "fromId");
      final int _cursorIndexOfToId = CursorUtil.getColumnIndexOrThrow(_cursor, "toId");
      final List<Message> _result = new ArrayList<Message>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Message _item;
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpContent;
        if (_cursor.isNull(_cursorIndexOfContent)) {
          _tmpContent = null;
        } else {
          _tmpContent = _cursor.getString(_cursorIndexOfContent);
        }
        final String _tmpCreated;
        if (_cursor.isNull(_cursorIndexOfCreated)) {
          _tmpCreated = null;
        } else {
          _tmpCreated = _cursor.getString(_cursorIndexOfCreated);
        }
        final boolean _tmpSent;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfSent);
        _tmpSent = _tmp != 0;
        final String _tmpFromId;
        if (_cursor.isNull(_cursorIndexOfFromId)) {
          _tmpFromId = null;
        } else {
          _tmpFromId = _cursor.getString(_cursorIndexOfFromId);
        }
        final String _tmpToId;
        if (_cursor.isNull(_cursorIndexOfToId)) {
          _tmpToId = null;
        } else {
          _tmpToId = _cursor.getString(_cursorIndexOfToId);
        }
        _item = new Message(_tmpId,_tmpContent,_tmpCreated,_tmpSent,_tmpFromId,_tmpToId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Message> getAllData() {
    final String _sql = "SELECT * FROM message";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
      final int _cursorIndexOfCreated = CursorUtil.getColumnIndexOrThrow(_cursor, "created");
      final int _cursorIndexOfSent = CursorUtil.getColumnIndexOrThrow(_cursor, "sent");
      final int _cursorIndexOfFromId = CursorUtil.getColumnIndexOrThrow(_cursor, "fromId");
      final int _cursorIndexOfToId = CursorUtil.getColumnIndexOrThrow(_cursor, "toId");
      final List<Message> _result = new ArrayList<Message>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Message _item;
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpContent;
        if (_cursor.isNull(_cursorIndexOfContent)) {
          _tmpContent = null;
        } else {
          _tmpContent = _cursor.getString(_cursorIndexOfContent);
        }
        final String _tmpCreated;
        if (_cursor.isNull(_cursorIndexOfCreated)) {
          _tmpCreated = null;
        } else {
          _tmpCreated = _cursor.getString(_cursorIndexOfCreated);
        }
        final boolean _tmpSent;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfSent);
        _tmpSent = _tmp != 0;
        final String _tmpFromId;
        if (_cursor.isNull(_cursorIndexOfFromId)) {
          _tmpFromId = null;
        } else {
          _tmpFromId = _cursor.getString(_cursorIndexOfFromId);
        }
        final String _tmpToId;
        if (_cursor.isNull(_cursorIndexOfToId)) {
          _tmpToId = null;
        } else {
          _tmpToId = _cursor.getString(_cursorIndexOfToId);
        }
        _item = new Message(_tmpId,_tmpContent,_tmpCreated,_tmpSent,_tmpFromId,_tmpToId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Message get(final int id) {
    final String _sql = "SELECT * FROM message WHERE id =?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
      final int _cursorIndexOfCreated = CursorUtil.getColumnIndexOrThrow(_cursor, "created");
      final int _cursorIndexOfSent = CursorUtil.getColumnIndexOrThrow(_cursor, "sent");
      final int _cursorIndexOfFromId = CursorUtil.getColumnIndexOrThrow(_cursor, "fromId");
      final int _cursorIndexOfToId = CursorUtil.getColumnIndexOrThrow(_cursor, "toId");
      final Message _result;
      if(_cursor.moveToFirst()) {
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpContent;
        if (_cursor.isNull(_cursorIndexOfContent)) {
          _tmpContent = null;
        } else {
          _tmpContent = _cursor.getString(_cursorIndexOfContent);
        }
        final String _tmpCreated;
        if (_cursor.isNull(_cursorIndexOfCreated)) {
          _tmpCreated = null;
        } else {
          _tmpCreated = _cursor.getString(_cursorIndexOfCreated);
        }
        final boolean _tmpSent;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfSent);
        _tmpSent = _tmp != 0;
        final String _tmpFromId;
        if (_cursor.isNull(_cursorIndexOfFromId)) {
          _tmpFromId = null;
        } else {
          _tmpFromId = _cursor.getString(_cursorIndexOfFromId);
        }
        final String _tmpToId;
        if (_cursor.isNull(_cursorIndexOfToId)) {
          _tmpToId = null;
        } else {
          _tmpToId = _cursor.getString(_cursorIndexOfToId);
        }
        _result = new Message(_tmpId,_tmpContent,_tmpCreated,_tmpSent,_tmpFromId,_tmpToId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
