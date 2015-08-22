package com.spacewheel.deliciosov20;

import android.app.Activity;
import android.app.DialogFragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    String temp_book_name, temp_book_description; // To use with DialogFragment to add stuff to the SQLite DB (unless can to in that class)

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    String TAGX = "Test TAG";
    private CharSequence mTitle;
    private RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getString(R.string.title_section1);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        mRecyclerView = (RecyclerView) findViewById(R.id.rv);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        CustomRVAdapter adapter;

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // Testing with toasts
                        Log.d(TAGX, "pls work");
                        Context context = view.getContext();
                        Toast.makeText(context, "test yo, " + position, Toast.LENGTH_SHORT).show();


                        // Fragment switching stuff
                        /*FragmentManager fragmentManager = getSupportFragmentManager();
                        Fragment fragment;
                        fragment = new CreateBookFragment();
                        fragmentManager.beginTransaction()
                                .replace(R.id.container, fragment)
                                .commit();*/
                    }
                })
        );

        initialiseData();
        adapter = new CustomRVAdapter(recipeBooks);
        mRecyclerView.setAdapter(adapter);

    }

    public void createBookButtonClick(View view) { // Adding a new entry to the database

        CreateBookFragment createBookFragment = new CreateBookFragment();
        final DialogFragment dialogFragment = new DialogFragment();
        createBookFragment.show(getSupportFragmentManager(), "DialogBOX");

    }

    private List<RecipeBook> recipeBooks;
    private void initialiseData() {
        recipeBooks = new ArrayList<>();
        // Do all SQLite stuff here
        recipeBooks.add(new RecipeBook("Quick Eats", "Easy to make dishes", R.mipmap.book_icon));
        recipeBooks.add(new RecipeBook("Tasty Food", "^Self explanatory...", R.mipmap.book_icon));
        recipeBooks.add(new RecipeBook("Italian Food", "Pizza and pasta and stuff", R.mipmap.book_icon));
        recipeBooks.add(new RecipeBook("Drinks", "Liquidy things", R.mipmap.book_icon));
        recipeBooks.add(new RecipeBook("Desserts", "ICE CREAM.", R.mipmap.book_icon));
        recipeBooks.add(new RecipeBook("Chewing Gum", "jk its made in a factory", R.mipmap.book_icon));
        recipeBooks.add(new RecipeBook("Indian Food", "Yay tasty home food", R.mipmap.book_icon));
        recipeBooks.add(new RecipeBook("'Murican Food", "Hamburgers, hotdogs and freedom", R.mipmap.book_icon));
    }

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
                fragment = new Calculator();
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

    public void onUserSelectValue(String name, String description) { //

        // TODO add your implementation.
    }



    // Fragments!!!

    public static class RecipeList extends Fragment {
        public RecipeList() {

        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            //RecyclerView rv = (RecyclerView) findViewById(R.id.rv);

            return inflater.inflate(R.layout.activity_main, container, false);
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
