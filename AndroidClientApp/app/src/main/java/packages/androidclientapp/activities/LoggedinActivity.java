package packages.androidclientapp.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import packages.androidclientapp.R;
import packages.androidclientapp.customAdapter.Adapter;
import packages.androidclientapp.customAdapter.Contents;
import packages.androidclientapp.fragments.ClientResultsFragment;
import packages.androidclientapp.fragments.DeletePeriodicJobFragment;
import packages.androidclientapp.fragments.InsertJobsFragment;
import packages.androidclientapp.fragments.StatusFragment;
import packages.androidclientapp.fragments.TotalResultsFragment;
import packages.androidclientapp.fragments.TurnOffClientFragment;


public class LoggedinActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayout;
    private ListView navList;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggedin);

        navList = (ListView) findViewById(R.id.navlist);


        Contents contents[]= new Contents[]{
                new Contents(getString(R.string.statusfragment)),
                new Contents(getString(R.string.insertjobfragment)),
                new Contents(getString(R.string.clientresultfragment)),
                new Contents(getString(R.string.totalresultsfragment)),
                new Contents(getString(R.string.deleteperiodicjobfragment)),
                new Contents(getString(R.string.turnoffclient))
        };

        Adapter adapter = new Adapter(this,R.layout.listview_item_row,contents);


        navList.setAdapter(adapter);

        navList.setOnItemClickListener(this);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.opendrawer,R.string.closedrawer );
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        actionBar = getSupportActionBar();
        // assert to get rid of null pointer exception warning
        assert getSupportActionBar() != null;

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.commit();
        LoadSelection(0);
    }
//TODO don't let user go back to login screen
//    @Override
//    public void onBackPressed() {
//        moveTaskToBack(true);
//    }

    private void LoadSelection(int i){
        navList.setItemChecked(i, true);
        switch (i){
            case 0:
                StatusFragment statusFragment = new StatusFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentholder,statusFragment);
                fragmentTransaction.commit();
                break;
            case 1:
                InsertJobsFragment insertJobsFragment = new InsertJobsFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentholder,insertJobsFragment);
                fragmentTransaction.commit();
                break;
            case 2:
                ClientResultsFragment clientResultsFragment = new ClientResultsFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentholder,clientResultsFragment);
                fragmentTransaction.commit();
                break;
            case 3:
                TotalResultsFragment totalResultsFragment = new TotalResultsFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentholder,totalResultsFragment);
                fragmentTransaction.commit();
                break;
            case 4:
                DeletePeriodicJobFragment deletePeriodicJobFragment = new DeletePeriodicJobFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentholder,deletePeriodicJobFragment);
                fragmentTransaction.commit();
                break;
            case 5:
                TurnOffClientFragment turnOffClientFragment = new TurnOffClientFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentholder,turnOffClientFragment);
                fragmentTransaction.commit();
                break;
            default:
                break;
        }


    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
        }else if (id == android.R.id.home){
            if (drawerLayout.isDrawerOpen(navList)){
                drawerLayout.closeDrawer(navList);
            }else{
                drawerLayout.openDrawer(navList);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        LoadSelection(position);
        drawerLayout.closeDrawer(navList);
    }
    public void setActionBarTitle(int title){
        actionBar.setTitle(title) ;
    }
}
