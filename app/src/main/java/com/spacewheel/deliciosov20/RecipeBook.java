package com.spacewheel.deliciosov20;

import java.util.Comparator;

/**
 * Created by Amrit on 17/08/2015.
 */
public class RecipeBook implements Comparator<RecipeBook> {
    String bookTitle;
    String bookDescription;
    int bookPhotoId;

    @Override
    public int compare(RecipeBook lhs, RecipeBook rhs) {
        return lhs.get_bookTitle().compareTo(rhs.get_bookTitle());
    }

    public RecipeBook(String bookTitle, String bookDescription, int bookPhotoId) {
        this.bookTitle = bookTitle;
        this.bookDescription = bookDescription;
        this.bookPhotoId = bookPhotoId;
    }

    public RecipeBook() {
        // Blank constructor so we can initialise variables later if needed
    }

    public String get_bookDescription() {
        return bookDescription;
    }

    public void set_bookDescription(String _bookDescription) {
        this.bookDescription = _bookDescription;
    }

    public int get_bookPhotoId() {
        return bookPhotoId;
    }

    public void set_bookPhotoId(int bookPhotoId) {
        this.bookPhotoId = bookPhotoId;
    }

    public String get_bookTitle() {
        return bookTitle;
    }

    public void set_bookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
}
