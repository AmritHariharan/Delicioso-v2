package com.spacewheel.deliciosov20;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

import java.util.ArrayList;
import java.util.List;

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
    public static final String COLUMN_BOOK_NAME = "_awd";
    public static final String COLUMN_BOOK_DESCRIPTION = "RECIPEBOOKDESCRIPTION";
    public static final String COLUMN_IMAGE_ID = "IMAGEID";

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

        addBook(new RecipeBook("Quick Eats", "Easy to make dishes", R.mipmap.book_icon));
        addBook(new RecipeBook("Tasty Food", "^Self explanatory...", R.mipmap.book_icon));
        addBook(new RecipeBook("Italian Food", "Pizza and pasta and stuff", R.mipmap.book_icon));
        addBook(new RecipeBook("Drinks", "Liquidy things", R.mipmap.book_icon));
        addBook(new RecipeBook("Desserts", "ICE CREAM.", R.mipmap.book_icon));
        addBook(new RecipeBook("Chewing Gum", "jk its made in a factory", R.mipmap.book_icon));
        addBook(new RecipeBook("Indian Food", "Yay tasty home food", R.mipmap.book_icon));
        addBook(new RecipeBook("'Murican Food", "Hamburgers, hotdogs and freedom", R.mipmap.book_icon));

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

    public String DBToString() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_BOOKS + " WHERE 1";

        // Cursor going to point to a location in the results
        Cursor c = db.rawQuery(query, null);
        // Move it to the first row of your results
        c.moveToFirst();

        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(COLUMN_BOOK_NAME)) != null) {
                dbString += c.getString(c.getColumnIndex(COLUMN_BOOK_NAME));
                dbString += "\n";
            }
        }
        db.close();
        return dbString;
    }

    List<RecipeBook> recipeBooks;
    public List<RecipeBook> getRecipeBooks() {

        recipeBooks = new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_BOOKS + " WHERE 1";

        // Cursor going to point to a location in the results
        Cursor c = db.rawQuery(query, null);
        // Move it to the first row of your results
        c.moveToFirst();

        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(COLUMN_BOOK_NAME)) != null) {
                recipeBooks.add(new RecipeBook(
                        c.getString(c.getColumnIndex(COLUMN_BOOK_NAME)),
                        c.getString(c.getColumnIndex(COLUMN_BOOK_DESCRIPTION)),
                        R.mipmap.book_icon));
            }
        }

        return recipeBooks;

    }


}
