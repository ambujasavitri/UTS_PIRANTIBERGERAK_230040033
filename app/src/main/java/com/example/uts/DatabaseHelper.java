package com.example.uts;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import android.content.ContentValues;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "nihongo.db";
    public static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE siswa (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nama TEXT, " +
                "grade TEXT, " +
                "speaking INTEGER, " +
                "listening INTEGER, " +
                "writing INTEGER, " +
                "average REAL)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS siswa");
        onCreate(db);

    }

    public boolean insertData(
            String nama,
            String grade,
            int speaking,
            int listening,
            int writing,
            double average
    ) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("nama", nama);
        values.put("grade", grade);
        values.put("speaking", speaking);
        values.put("listening", listening);
        values.put("writing", writing);
        values.put("average", average);

        long result = db.insert("siswa", null, values);

        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public Cursor getAllData() {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM siswa",
                null
        );

        return cursor;

    }

    public boolean updateData(
            int id,
            String nama,
            String grade,
            int speaking,
            int listening,
            int writing,
            double average
    ) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("nama", nama);
        values.put("grade", grade);
        values.put("speaking", speaking);
        values.put("listening", listening);
        values.put("writing", writing);
        values.put("average", average);

        db.update(
                "siswa",
                values,
                "id=?",
                new String[]{String.valueOf(id)}
        );

        return true;
    }

    public Integer deleteData(int id) {

        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(
                "siswa",
                "id=?",
                new String[]{String.valueOf(id)}
        );
    }
}