package com.kellaritehdas.diaryproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "diaryproject.db";
    private static final int TABLE_VERSION = 1;

    private static final String COL_ID = "ID";
    private static final String COL_NAME = "name";
    private static final String COL_STORY = "story";
    private static final String COL_DATE = "date";

    private static final String TABLE_DIARY = "tbl_diary";
    private static final String CREATE_TABLE_DIARY = "CREATE TABLE " + TABLE_DIARY + "( "
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_NAME + " TEXT NOT NULL, "
            + COL_STORY + " TEXT NOT NULL, "
            + COL_DATE + " TEXT NOT NULL)";

    public List<Diary> getAllDiary() {

        List<Diary> diaryList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * from " + TABLE_DIARY, null);

        if (cursor.moveToLast()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String story = cursor.getString(2);
                String date = cursor.getString(3);

                Diary page = new Diary(id, name, story, date);
                diaryList.add(page);
            } while (cursor.moveToPrevious());

        }

        return diaryList;
    }
    public List<Diary> getSecondDiary() {

        List<Diary> diaryList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * from " + TABLE_DIARY + " ORDER BY name ASC ", null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String story = cursor.getString(2);
                String date = cursor.getString(3);

                Diary page = new Diary(id, name, story, date);
                diaryList.add(page);
            } while (cursor.moveToNext());

        }

        return diaryList;
    }

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, TABLE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_DIARY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP  TABLE IF EXISTS " + TABLE_DIARY);
        onCreate(db);
    }


    public long addPage(Diary diary) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, diary.getName());
        values.put(COL_STORY, diary.getStory());
        values.put(COL_DATE, diary.getDate());

        return db.insert(TABLE_DIARY, null, values);
    }

    public int updatePage(Diary diary) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_NAME, diary.getName());
        values.put(COL_STORY, diary.getStory());
        values.put(COL_DATE, diary.getDate());

        return db.update(TABLE_DIARY, values, "id=?", new String[]{String.valueOf(diary.getID())});
    }

    public int deletePage(Diary diary) {
        SQLiteDatabase db = getWritableDatabase();

        return db.delete(TABLE_DIARY, "id=?", new String[]{String.valueOf(diary.getID())});

    }
}