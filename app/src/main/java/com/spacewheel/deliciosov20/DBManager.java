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

    private static final int DATABASE_VERSION = 6;
    private static final String DATABASE_NAME = "recipeBooks.db";

    public static final String TABLE_BOOKS = "books";
    public static final String TABLE_RECIPES = "recipes";
    //public static final String TABLE_SHOPPING_LIST = "shoppinglist";

    public static final String COLUMN_ID = "_id";

    // For TABLE_BOOKS
    public static final String COLUMN_BOOK_NAME = "_bookname";
    public static final String COLUMN_BOOK_DESCRIPTION = "_bookdescription";

    // For TABLE_RECIPES
    public static final String COLUMN_RECIPE_NAME = "_recipename";
    public static final String COLUMN_RECIPE_DESCRIPTION = "_recipedescription";
    public static final String COLUMN_RECIPE_INGREDIENTS = "_recipeingredients";
    public static final String COLUMN_RECIPE_METHOD = "_recipemethod";
    public static final String COLUMN_RECIPE_NOTES = "_recipenotes";
    public static final String COLUMN_IMAGE_ID = "_imageid";
    public static final String COLUMN_PARENT_BOOK = "_parentbook";

    // For TABLE_SHOPPING_LIST
    //public static final String COLUMN_TEXT

    // To create the tables
    String CREATE_TABLE_BOOKS = "CREATE TABLE IF NOT EXISTS " + TABLE_BOOKS + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + // The various columns
            COLUMN_BOOK_NAME + " TEXT, " +
            COLUMN_BOOK_DESCRIPTION + " TEXT" +
            ");";

    String CREATE_TABLE_RECIPES = "CREATE TABLE IF NOT EXISTS " + TABLE_RECIPES + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
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

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_BOOKS, null, values);
        db.close();
        //addBook(new RecipeBook("Quick Eats", "Easy to make dishes", R.mipmap.book_icon));
    }

    public void addRecipe(Recipe recipe) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_RECIPE_NAME, recipe.getRecipeTitle());
        values.put(COLUMN_RECIPE_DESCRIPTION, recipe.getRecipeDescription());
        values.put(COLUMN_RECIPE_INGREDIENTS, recipe.getIngredients());
        values.put(COLUMN_RECIPE_METHOD, recipe.getMethod());
        values.put(COLUMN_RECIPE_NOTES, recipe.getNotes());
        values.put(COLUMN_IMAGE_ID, recipe.getImageId());
        values.put(COLUMN_PARENT_BOOK, recipe.getParentBook());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_RECIPES, null, values);
        db.close();
    }

    public void updateRecipeItems(Recipe recipe) { // Make this change the SQLite entry

        ContentValues values = new ContentValues();
        values.put(COLUMN_RECIPE_NAME, recipe.getRecipeTitle());
        values.put(COLUMN_RECIPE_DESCRIPTION, recipe.getRecipeDescription());
        values.put(COLUMN_RECIPE_INGREDIENTS, recipe.getIngredients());
        values.put(COLUMN_RECIPE_METHOD, recipe.getMethod());
        values.put(COLUMN_RECIPE_NOTES, recipe.getNotes());
        values.put(COLUMN_IMAGE_ID, recipe.getImageId());
        values.put(COLUMN_PARENT_BOOK, recipe.getParentBook());

        //SQLiteDatabase db = this.getWritableDatabase();
        //db.insert(TABLE_RECIPES, null, values);
        //db.close();
    }

    // Delete row from database
    public void deleteBook(String bookName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_BOOKS + " WHERE " + COLUMN_BOOK_NAME + " =\"" + bookName + "\";");
    }

    public void deleteRecipe(String recipeName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_RECIPES + " WHERE " + COLUMN_RECIPE_NAME + " =\"" + recipeName + "\";");
    }

    public String DBToString() {
        String dbString = "";
        SQLiteDatabase db = this.getWritableDatabase();
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
        //db.close();
        return dbString;
    }

    List<RecipeBook> recipeBooks;
    public List<RecipeBook> getRecipeBooks() {

        recipeBooks = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_BOOKS;// + " WHERE 1";

        // Cursor going to point to a location in the results
        Cursor c = db.rawQuery(query, null);
        // Move it to the first row of your results
        c.moveToFirst();

        if (c.moveToFirst()) {
            do {
                if (c.getString(c.getColumnIndex(COLUMN_BOOK_NAME)) != null) {
                    recipeBooks.add(new RecipeBook(
                            c.getString(c.getColumnIndex(COLUMN_BOOK_NAME)),
                            c.getString(c.getColumnIndex(COLUMN_BOOK_DESCRIPTION)),
                            R.mipmap.book_icon));
                }
            } while (c.moveToNext());
        }
        db.close();
        return recipeBooks;

    }

    List<Recipe> recipes;
    public List<Recipe> getRecipes(String bookName) {

        recipes = new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT "+ COLUMN_PARENT_BOOK +" FROM " + TABLE_RECIPES + " WHERE " + COLUMN_PARENT_BOOK + "=" + bookName;

        // Cursor going to point to a location in the results
        Cursor c = db.rawQuery(query, null);
        // Move it to the first row of your results
        c.moveToFirst();

        if (c.moveToFirst()) {
            do {
                if (c.getString(c.getColumnIndex(COLUMN_RECIPE_NAME)) != null) {
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
            } while (c.moveToNext());
        }
        db.close();
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
