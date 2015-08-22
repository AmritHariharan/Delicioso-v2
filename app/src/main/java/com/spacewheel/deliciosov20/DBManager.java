package com.spacewheel.deliciosov20;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

/**
 * Created by Amrit on 22/8/15.
 */
public class DBManager extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "recipeBooks.db";
    public static final String TABLE_BOOKS = "books";
    public static final String TABLE_RECIPES = "recipes";

    // For TABLE_BOOKS
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_BOOK_NAME = "bookname";
    public static final String COLUMN_BOOK_DESCRIPTION = "bookdescription";
    public static final String COLUMN_IMAGE_ID = "imageid";

    public DBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_BOOKS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT " + // The various columns
                COLUMN_BOOK_NAME + " TEXT " +
                COLUMN_BOOK_DESCRIPTION + " TEXT " +
                COLUMN_IMAGE_ID + " INTEGER " +
                ");";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS); // Replacing the old table with the new one
        onCreate(db);
    }

    // Add a new row to the database
    public void addBook(RecipeBook recipeBook) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_BOOK_NAME, recipeBook.get_bookTitle());
        values.put(COLUMN_BOOK_DESCRIPTION, recipeBook.get_bookDescription());
        values.put(COLUMN_IMAGE_ID, recipeBook.get_bookPhotoId());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_BOOKS, null, values);
        db.close();
    }

    // Delete row from database
    public void deleteBook(String bookName) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_BOOKS + " WHERE " + COLUMN_BOOK_NAME + " =\" " + bookName + "\";");
    }




}
