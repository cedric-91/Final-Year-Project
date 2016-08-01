package com.cedric.fyvents;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawer extends Fragment implements RecyclerAdapter.ClickListener {

    //Initializing the variables.
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    /*These two boolean variables will indicate
    * whether the navigationDrawer is being open for the first time.
    */
    private boolean mUserLearnerDrawer;
    private boolean mFromSavedInstanceState;
    //ListView
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;

    //Initialising the name of the file in which the value will be stored when the navigationDrawer has been opened.
    public static final String PREF_FILE_NAME = "test_pref";
    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";
    private View containerView;


    public NavigationDrawer() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      mUserLearnerDrawer = Boolean.valueOf(readFromPreferences(getActivity(),KEY_USER_LEARNED_DRAWER, "false"));
        if (savedInstanceState != null) {
            mFromSavedInstanceState = true;
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);//getting the recycler id from the xml file.
        adapter = new RecyclerAdapter(getActivity(), getData());
        adapter.setClickListener(this);//Adding the click listener
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return layout;

    }

    public static List<DrawerList> getData(){//Created a method to get the data.
        List<DrawerList> drawerData = new ArrayList<>();
        int[] icons = {R.mipmap.love, R.mipmap.ic_launcher, R.mipmap.love, R.mipmap.ic_launcher, R.mipmap.love, R.mipmap.ic_launcher};
        String [] title = {"Singles' Night Out", "Family Night Out", "Couples' Night Out", "Friends' Night Out", "Preference", "Settings"};
        for (int i = 0; i <title.length && i <icons.length; i++){
            DrawerList current = new DrawerList();
            current.iconId = icons[i];//current icons position
            current.title = title[i];//Current title position
            drawerData.add(current);//Adding the data into the list
        }
        return drawerData;
    }

    //Setting the drawerNavigation Open and Close method.
    public void setUp(int fragementId, DrawerLayout layout, Toolbar toolbar) {//The setUp method.
        containerView = getView().findViewById(fragementId);
        mDrawerLayout = layout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), layout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {//it handles when the drawer is open method.
                super.onDrawerOpened(drawerView);
                if (!mUserLearnerDrawer) {
                    mUserLearnerDrawer = true;
                    saveToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearnerDrawer + "");
                }
                getActivity().invalidateOptionsMenu();


            }

            @Override
            public void onDrawerClosed(View drawerView) {//it handles when the drawer is closed method.
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }
        };
        if (!mUserLearnerDrawer && !mFromSavedInstanceState) {
            mDrawerLayout.openDrawer(containerView);
        }
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    // A method is being created to store when the navigationDrawer has been open for the first time.
    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();
    }

    // A method is being created to read when the navigationDrawer has been open for the first time.
    public static String readFromPreferences(Context context, String preferenceName, String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName, defaultValue);
    }

    @Override
    public void itemClicked(View view, int position) {
        startActivity(new Intent(getActivity(), Singles.class));
    }
}
