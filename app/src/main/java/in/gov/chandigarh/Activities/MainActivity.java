package in.gov.chandigarh.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import in.gov.chandigarh.Extra_Classes.ConnectionDetector;
import in.gov.chandigarh.Fragment.FavoriteFragment;
import in.gov.chandigarh.Fragment.Home;
import in.gov.chandigarh.Fragment.RestrauntFragment;
import in.gov.chandigarh.Nearby.GooglePlacesActivity;
import in.gov.chandigarh.R;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    ConnectionDetector con=new ConnectionDetector();
    boolean flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Chandigarh");
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        if(!con.networkStatus(this))
        {
           con.displayNoNetworkDialog(this);
           // finish();
        }
                Home home=new Home();
        FragmentTransaction frt=getSupportFragmentManager().beginTransaction();
        frt.replace(R.id.frame,home);
        frt.commit();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!con.networkStatus(this))
        {
            con.displayNoNetworkDialog(this);
            // finish();
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.hotels) {
            // Handle the camera action
            toolbar.setTitle("Restaurants & Hotels");
            RestrauntFragment rst=new RestrauntFragment();
            FragmentTransaction frt=getSupportFragmentManager().beginTransaction();
            frt.replace(R.id.frame,rst);
            Bundle data = new Bundle();//create bundle instance
            data.putInt("key",1);//put string to pass with a key value
            rst.setArguments(data);//Set bundle data to fragment
            frt.commit();
        } else if (id == R.id.home1) {
            toolbar.setTitle("Chandigarh");
            Home home=new Home();
            FragmentTransaction frt=getSupportFragmentManager().beginTransaction();
            frt.replace(R.id.frame,home);
            frt.commit();

        } else if (id == R.id.places) {
            toolbar.setTitle("Places to visit");
            RestrauntFragment rst=new RestrauntFragment();
            FragmentTransaction frt=getSupportFragmentManager().beginTransaction();
            frt.replace(R.id.frame,rst);
            Bundle data = new Bundle();//create bundle instance
            data.putInt("key",2);//put string to pass with a key value
            rst.setArguments(data);//Set bundle data to fragment
            frt.commit();

        } else if (id == R.id.entertainment) {
            toolbar.setTitle("Entertainment");
            RestrauntFragment rst=new RestrauntFragment();
            FragmentTransaction frt=getSupportFragmentManager().beginTransaction();
            frt.replace(R.id.frame,rst);
            Bundle data = new Bundle();//create bundle instance
            data.putInt("key",3);//put string to pass with a key value
            rst.setArguments(data);//Set bundle data to fragment
            frt.commit();

        }
        else if (id == R.id.shoping) {
            toolbar.setTitle("Shopping");
            RestrauntFragment rst = new RestrauntFragment();
            FragmentTransaction frt = getSupportFragmentManager().beginTransaction();
            frt.replace(R.id.frame, rst);
            Bundle data = new Bundle();//create bundle instance
            data.putInt("key", 4);//put string to pass with a key value
            rst.setArguments(data);//Set bundle data to fragment
            frt.commit();
        }
        else if (id == R.id.saloon) {
            toolbar.setTitle("Salon & Spa");
            RestrauntFragment rst = new RestrauntFragment();
            FragmentTransaction frt = getSupportFragmentManager().beginTransaction();
            frt.replace(R.id.frame, rst);
            Bundle data = new Bundle();//create bundle instance
            data.putInt("key", 5);//put string to pass with a key value
            rst.setArguments(data);//Set bundle data to fragment
            frt.commit();
        }
        else if (id == R.id.education) {
            toolbar.setTitle("Education");
            RestrauntFragment rst = new RestrauntFragment();
            FragmentTransaction frt = getSupportFragmentManager().beginTransaction();
            frt.replace(R.id.frame, rst);
            Bundle data = new Bundle();//create bundle instance
            data.putInt("key", 6);//put string to pass with a key value
            rst.setArguments(data);//Set bundle data to fragment
            frt.commit();
        }
        else if (id == R.id.transport) {
            toolbar.setTitle("Transport");
            RestrauntFragment rst = new RestrauntFragment();
            FragmentTransaction frt = getSupportFragmentManager().beginTransaction();
            frt.replace(R.id.frame, rst);
            Bundle data = new Bundle();//create bundle instance
            data.putInt("key", 8);//put string to pass with a key value
            rst.setArguments(data);//Set bundle data to fragment
            frt.commit();
        }
        else if (id == R.id.health) {
            toolbar.setTitle("Health Care");
            RestrauntFragment rst = new RestrauntFragment();
            FragmentTransaction frt = getSupportFragmentManager().beginTransaction();
            frt.replace(R.id.frame, rst);
            Bundle data = new Bundle();//create bundle instance
            data.putInt("key", 9);//put string to pass with a key value
            rst.setArguments(data);//Set bundle data to fragment
            frt.commit();
        }
        else if (id == R.id.help_desk) {
            toolbar.setTitle("Help Desk");
            RestrauntFragment rst=new RestrauntFragment();
            FragmentTransaction frt=getSupportFragmentManager().beginTransaction();
            frt.replace(R.id.frame,rst);
            Bundle data = new Bundle();//create bundle instance
            data.putInt("key",10);//put string to pass with a key value
            rst.setArguments(data);//Set bundle data to fragment
            frt.commit();

        }
        else if (id == R.id.service) {
            toolbar.setTitle("Service Stations");
            RestrauntFragment rst=new RestrauntFragment();
            FragmentTransaction frt=getSupportFragmentManager().beginTransaction();
            frt.replace(R.id.frame,rst);
            Bundle data = new Bundle();//create bundle instance
            data.putInt("key",11);//put string to pass with a key value
            rst.setArguments(data);//Set bundle data to fragment
            frt.commit();

        }
        else if (id == R.id.favorite) {
            toolbar.setTitle("My Favourites");
            FavoriteFragment fvt=new FavoriteFragment();
            FragmentTransaction frt=getSupportFragmentManager().beginTransaction();
            frt.replace(R.id.frame,fvt);
          /*  Bundle data = new Bundle();//create bundle instance
            data.putInt("key",11);//put string to pass with a key value
            fvt.setArguments(data);//Set bundle data to fragment*/
            frt.commit();

        }
        else if (id == R.id.nearby) {
           Intent in=new Intent(MainActivity.this,GooglePlacesActivity.class);
           startActivity(in);
          }

      /*  else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
