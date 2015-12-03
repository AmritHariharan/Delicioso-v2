package com.spacewheel.deliciosov20;

import android.app.Activity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    // TODO:
    // - Add create screen for recipe on RecipeListFragment FAB Click
    // - Find out how to make shapes, or randomise pictures for RecipeBooks
    //  - ^Possibly do same for Recipes?
    // - IMPLEMENT NICE LOOKING NAV DRAWER
    // - Add shopping List screen

    DBManager dbManager = new DBManager(this);

    private NavigationDrawerFragment mNavigationDrawerFragment;

    String TAGX = "Test TAG";
    private CharSequence mTitle;
    private RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    CustomRVAdapter mAdapter;
    private List<RecipeBook> recipeBooks;

    RecyclerView m2RecyclerView;
    RecyclerView.LayoutManager m2LayoutManager;
    BookRVAdapter m2Adapter;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        Log.d(TAGX, "OnCreate has begun");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recipeBooks = new ArrayList<>();
        recipeBooks = dbManager.getRecipeBooks();
        Log.d(TAGX, recipeBooks.toString());
        for (int count = 0; count < recipeBooks.size(); count++) {
            Log.d(TAGX, "Book " + count + ": " + recipeBooks.get(count).bookTitle + "\n");
        }

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getString(R.string.title_section1);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        mRecyclerView = (RecyclerView) findViewById(R.id.rv);

        mLayoutManager = new LinearLayoutManager(this);
        Log.d(TAGX, "before set layout manager");
        mRecyclerView.setLayoutManager(mLayoutManager);
        Log.d(TAGX, "before print list");
        for(int count = 0; count < recipeBooks.size(); count++) {
            Log.d(TAGX, recipeBooks.get(count) + "\n");
        }

        RecipeBookComparator comp = new RecipeBookComparator();
        if (recipeBooks != null) {
            Collections.sort(recipeBooks, comp);
        }

        mAdapter = new CustomRVAdapter(recipeBooks);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // Testing with toasts
                        Log.d(TAGX, "pls work");
                        Context context = view.getContext();

                        Bundle bundle = new Bundle();
                        bundle.putString("Book Name", recipeBooks.get(position).get_bookTitle());

                        Toast.makeText(context, "test yo, " + position, Toast.LENGTH_SHORT).show();

                        FragmentManager fragmentManager = getSupportFragmentManager();
                        Fragment fragment;
                        fragment = new RecipeListFragment();
                        fragment.setArguments(bundle);
                        fragmentManager.beginTransaction()
                                .replace(R.id.container, fragment)
                                .commit();
                    }
                })
        );


    }

    public void createBookButtonClick(View view) { // Creating window to add stuff to DB

        CreateBookFragment createBookFragment = new CreateBookFragment();
        //final DialogFragment dialogFragment = new DialogFragment();
        createBookFragment.show(getSupportFragmentManager(), "DialogBOX");

    }

    public void onUserSelectValue(String name, String description) { // Called from CreateBookFragment

        // http://stackoverflow.com/questions/27845069/add-a-new-item-to-recyclerview-programatically

        RecipeBook book = new RecipeBook(name, description, R.mipmap.book_icon);
        dbManager.addBook(book); // added book to SQLite DB
        recipeBooks.add(book); // change this to add to db
        Collections.sort(recipeBooks, new RecipeBookComparator());
        mAdapter.notifyDataSetChanged();
    }

    public Recipe tempRecipe;
    public void addRecipeFromFragment(Recipe recipe) {
        Log.d("DB TEST", "ARR 1");
        dbManager.addRecipe(recipe);
        Log.d("DB TEST", "ARR 2");
        tempRecipe = recipe;
        Log.d("DB TEST", "ARR 3");
        m2Adapter.notifyDataSetChanged();
        Log.d("DB TEST", "ARR 4");
    }

    // deliciosorecipes@gmail.com || TastyFood

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        position++;
        int frameNum = R.id.container;

        Fragment fragment = new Fragment();

        switch (position) {
            case 1:
                mTitle = getString(R.string.title_section1);
                //fragment = new RecipeList();
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                frameNum = R.layout.calculator_fragment;
                fragment = new CalculatorFragment();
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }

        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
