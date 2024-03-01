package com.example.mytodoapplication;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class MainDao_Impl implements MainDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MainData> __insertionAdapterOfMainData;

  private final EntityDeletionOrUpdateAdapter<MainData> __deletionAdapterOfMainData;

  private final SharedSQLiteStatement __preparedStmtOfUpate;

  public MainDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMainData = new EntityInsertionAdapter<MainData>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `table_name` (`ID`,`text`) VALUES (nullif(?, 0),?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MainData value) {
        stmt.bindLong(1, value.getID());
        if (value.getText() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getText());
        }
      }
    };
    this.__deletionAdapterOfMainData = new EntityDeletionOrUpdateAdapter<MainData>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `table_name` WHERE `ID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MainData value) {
        stmt.bindLong(1, value.getID());
      }
    };
    this.__preparedStmtOfUpate = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE Table_name SET text =? WHERE ID = ?";
        return _query;
      }
    };
  }

  @Override
  public void insert(final MainData mainData) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfMainData.insert(mainData);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final MainData mainData) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfMainData.handle(mainData);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void reset(final List<MainData> mainData) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfMainData.handleMultiple(mainData);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void upate(final int sID, final String sText) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpate.acquire();
    int _argIndex = 1;
    if (sText == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, sText);
    }
    _argIndex = 2;
    _stmt.bindLong(_argIndex, sID);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpate.release(_stmt);
    }
  }

  @Override
  public List<MainData> getAll() {
    final String _sql = "SELECT * FROM table_name";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfID = CursorUtil.getColumnIndexOrThrow(_cursor, "ID");
      final int _cursorIndexOfText = CursorUtil.getColumnIndexOrThrow(_cursor, "text");
      final List<MainData> _result = new ArrayList<MainData>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final MainData _item;
        _item = new MainData();
        final int _tmpID;
        _tmpID = _cursor.getInt(_cursorIndexOfID);
        _item.setID(_tmpID);
        final String _tmpText;
        _tmpText = _cursor.getString(_cursorIndexOfText);
        _item.setText(_tmpText);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
