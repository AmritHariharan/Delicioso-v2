package com.spacewheel.deliciosov20;

import java.util.Comparator;

/**
 * Created by Amrit on 24/8/15.
 */
public class RecipeBookComparator implements Comparator<RecipeBook> {

    @Override
    public int compare(RecipeBook lhs, RecipeBook rhs) {
        return lhs.get_bookTitle().compareTo(rhs.get_bookTitle());
    }
}
