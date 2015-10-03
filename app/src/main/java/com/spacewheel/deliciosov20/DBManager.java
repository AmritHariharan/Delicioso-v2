package com.spacewheel.deliciosov20;

import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amrit on 22/8/15.
 */

public class DBManager extends SQLiteOpenHelper {

    private String TAG = "SQLite TAG";

    private Context context;

    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "recipeBooks.db";

    public static final String TABLE_BOOKS = "books";
    public static final String TABLE_RECIPES = "recipes";

    public static final String COLUMN_ID = "_id";

    // For TABLE_BOOKS
    public static final String COLUMN_BOOK_NAME = "_bookname";
    public static final String COLUMN_BOOK_DESCRIPTION = "_bookdescription";

    // For TABLE_RECIPEs
    public static final String COLUMN_RECIPE_NAME = "_recipename";
    public static final String COLUMN_RECIPE_DESCRIPTION = "_recipedescription";
    public static final String COLUMN_RECIPE_INGREDIENTS = "_recipeingredients";
    public static final String COLUMN_RECIPE_METHOD = "_recipemethod";
    public static final String COLUMN_RECIPE_NOTES = "_recipenotes";
    public static final String COLUMN_IMAGE_ID = "_imageid";
    public static final String COLUMN_PARENT_BOOK = "_parentbook";

    // To create the tables
    String CREATE_TABLE_BOOKS = "CREATE TABLE IF NOT EXISTS " + TABLE_BOOKS + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + // The various columns
            COLUMN_BOOK_NAME + " TEXT, " +
            COLUMN_BOOK_DESCRIPTION + " TEXT" +
            ");";

    String CREATE_TABLE_RECIPES = "CREATE TABLE IF NOT EXISTS " + TABLE_RECIPES + " (" +
            COLUMN_RECIPE_NAME + " TEXT, " +
            COLUMN_RECIPE_DESCRIPTION + " TEXT, " +
            COLUMN_RECIPE_INGREDIENTS + " TEXT, " +
            COLUMN_RECIPE_METHOD + " TEXT, " +
            COLUMN_RECIPE_NOTES + " TEXT, " +
            COLUMN_IMAGE_ID + " INTEGER " +
            COLUMN_PARENT_BOOK + " TEXT" +
            ");";

    // To drop the tables
    private static final String DROP_BOOKS = "DROP TABLE IF EXISTS " + TABLE_BOOKS;
    private static final String DROP_RECIPES = "DROP TABLE IF EXISTS " + TABLE_RECIPES;


    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e(TAG, "OnCreate() called");
        db.execSQL(CREATE_TABLE_BOOKS);
        db.execSQL(CREATE_TABLE_RECIPES);


        /*if (isTableBooksEmpty() == true) {

            addBook(new RecipeBook("Quick Eats", "Easy to make dishes", R.mipmap.book_icon));
            addBook(new RecipeBook("Tasty Food", "^Self explanatory...", R.mipmap.book_icon));
            addBook(new RecipeBook("Italian Food", "Pizza and pasta and stuff", R.mipmap.book_icon));
            addBook(new RecipeBook("Drinks", "Liquidy things", R.mipmap.book_icon));
            addBook(new RecipeBook("Desserts", "ICE CREAM.", R.mipmap.book_icon));
            addBook(new RecipeBook("Chewing Gum", "jk its made in a factory", R.mipmap.book_icon));
            addBook(new RecipeBook("Indian Food", "Yay tasty home food", R.mipmap.book_icon));
            addBook(new RecipeBook("'Murican Food", "Hamburgers, hotdogs and freedom", R.mipmap.book_icon));

        }*/

    }

    public boolean isTableBooksEmpty (){

        SQLiteDatabase db = this.getReadableDatabase();
        boolean isEmpty = true;

        if (DatabaseUtils.queryNumEntries(db, TABLE_BOOKS) != 0){
            isEmpty = false;
        }

        return isEmpty;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(DROP_BOOKS);
            db.execSQL(DROP_RECIPES);
            Toast.makeText(context, "onUpgrade() called", Toast.LENGTH_SHORT);
            onCreate(db);
        } catch (SQLException e) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT);
            Log.e(TAG, e.toString());
        }
    }

    // Add a new row to the database
    public void addBook(RecipeBook recipeBook) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_BOOK_NAME, recipeBook.get_bookTitle());
        values.put(COLUMN_BOOK_DESCRIPTION, recipeBook.get_bookDescription());
        //values.put(COLUMN_IMAGE_ID, recipeBook.get_bookPhotoId());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_BOOKS, null, values);
        db.close();
        //addBook(new RecipeBook("Quick Eats", "Easy to make dishes", R.mipmap.book_icon));
    }

    // Delete row from database
    public void deleteBook(String bookName) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_BOOKS + " WHERE " + COLUMN_BOOK_NAME + " =\"" + bookName + "\";"); // <-- if errors, check this
    }

    public void deleteRecipe(String recipeName) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_RECIPES + " WHERE " + COLUMN_RECIPE_NAME + " =\"" + recipeName + "\";"); // <-- if errors, check this
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

        SQLiteDatabase db = getReadableDatabase();
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

    List<Recipe> recipes;
    public List<Recipe> getRecipes(String bookName) {

        recipes = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT "+ COLUMN_PARENT_BOOK +" FROM " + TABLE_RECIPES + " WHERE " + COLUMN_PARENT_BOOK + "=" + bookName;

        // Cursor going to point to a location in the results
        Cursor c = db.rawQuery(query, null);
        // Move it to the first row of your results
        c.moveToFirst();

        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(COLUMN_BOOK_NAME)) != null) {
                recipes.add(new Recipe(
                        c.getString(c.getColumnIndex(COLUMN_RECIPE_NAME)),
                        c.getString(c.getColumnIndex(COLUMN_RECIPE_DESCRIPTION)),
                        c.getString(c.getColumnIndex(COLUMN_RECIPE_INGREDIENTS)),
                        c.getString(c.getColumnIndex(COLUMN_RECIPE_METHOD)),
                        c.getString(c.getColumnIndex(COLUMN_RECIPE_NOTES)),
                        // Add image here if required
                        c.getString(c.getColumnIndex(COLUMN_PARENT_BOOK))
                ));
            }
        }

        return recipes;
    }


    @Override
    public void onOpen(SQLiteDatabase database) {
        if(!database.isOpen()) {
            SQLiteDatabase.openDatabase(database.getPath(), null, SQLiteDatabase.NO_LOCALIZED_COLLATORS |
                    SQLiteDatabase.CREATE_IF_NECESSARY);
        }
    }

}
