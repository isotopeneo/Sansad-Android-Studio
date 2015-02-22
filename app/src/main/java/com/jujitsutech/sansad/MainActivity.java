package com.jujitsutech.sansad;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

import com.jujitsutech.sansad.fragment.BillsInParliament;
import com.jujitsutech.sansad.fragment.Headlines;
import com.jujitsutech.sansad.fragment.KnowYourRepresentative;
import com.jujitsutech.sansad.fragment.SearchDialog;


public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private ProgressDialog progressDialog;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    private BillsInParliament billsInParliament;
    private SearchDialog searchDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActionBar().setTitle("test");
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        switch (position) {
            case 0:
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.container,
                                Headlines.newInstance()).commit();
            default:
                break;
            case 1:
                billsInParliament = BillsInParliament.newInstance(this);
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.container,
                                billsInParliament).commit();
                break;
            case 2:
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.container,
                                KnowYourRepresentative.newInstance()).commit();
                break;
        }
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

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
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
            getMenuInflater().inflate(R.menu.search, menu);
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
        switch (id) {
            case R.id.action_settings:
                return true;
            case R.id.action_search:
                if (mTitle.equals(getString(R.string.title_section2))) showSearchDialog();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Method to show a search Dialog. Called only in the
     * Bills Parliament fragment
     */
    private void showSearchDialog() {
        searchDialog = SearchDialog.newInstance(this);
        searchDialog.show(getFragmentManager().beginTransaction(), "searchDialog");
    }

    public void searchForData(String title) {
        searchDialog.dismiss();
        if (!title.equals("")) {
            // Initiate a new search
            Bundle arg1 = new Bundle();
            arg1.putString("title", title);
            billsInParliament.searchBills(arg1);
        }
    }

    public void showProgressBar(String message) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void dismissProgressBar() {
        if (null != progressDialog && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    public boolean isDeviceConnectedToNetwork() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public void showAlertDialog(String msg) {
        new AlertDialog.Builder(this).setMessage(msg).show();
    }
}
