package com.spacewheel.deliciosov20;

/**
 * Created by Amrit on 17/08/2015.
 */
public class RecipeBook {
    String _bookTitle;
    String _bookDescription;
    int _id;
    int _bookPhotoId;

    public RecipeBook(String _bookTitle, String _bookDescription, int _bookPhotoId) {
        this._bookTitle = _bookTitle;
        this._bookDescription = _bookDescription;
        this._bookPhotoId = _bookPhotoId;
    }

    public RecipeBook() {
        // Blank constructor so we can initialise variables later if needed
    }

    public String get_bookDescription() {
        return _bookDescription;
    }

    public void set_bookDescription(String _bookDescription) {
        this._bookDescription = _bookDescription;
    }

    public int get_bookPhotoId() {
        return _bookPhotoId;
    }

    public void set_bookPhotoId(int _bookPhotoId) {
        this._bookPhotoId = _bookPhotoId;
    }

    public String get_bookTitle() {
        return _bookTitle;
    }

    public void set_bookTitle(String _bookTitle) {
        this._bookTitle = _bookTitle;
    }
}
