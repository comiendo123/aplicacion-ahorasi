package com.grupo.inmueblesapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.SupportMapFragment;
import com.grupo.inmueblesapp.RestApi.OnCompleteLoadRest;
import com.grupo.inmueblesapp.RestApi.RestApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class principal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnCompleteLoadRest {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
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
        }

        return super.onOptionsItemSelected(item);
    }//parte de la cinco
    @Override
    public void completeLoadRest(JSONObject result) throws JSONException {
        JSONArray people = (JSONArray)result.get("results");
        for (int i = 0; i < people.length(); i++){
            JSONObject p = (JSONObject) people.get(i);
            String name = p.getString("name");
            String height = p.getString("height");
            String hair_color = p.getString("hair_color");
            String birth_year = p.getString("bird_year");//falta video 
            ItemStructure item = new ItemStructure();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        boolean FragmentTransaction = false;
        Fragment fragment = null;
        SupportMapFragment geolocalizacion = null;
        if (id == R.id.nav_camera) {
            // Handle the camera action

        } else if (id == R.id.nav_inmueble) {
            Log.i("a registrase pero falta","a hacer la opcion");
            item.setChecked(true);
            getSupportActionBar().setTitle(item.getTitle());

        } else if (id == R.id.nav_geolocalizacion) {
            fragment = new Fragment();
            FragmentTransaction = true;
            item.setChecked(true);
            getSupportActionBar().setTitle(item.getTitle());

        } else if (id == R.id.nav_registrate) {
            Log.i("a registrase pero falta","a hacer la opcion");
            item.setChecked(true);
            getSupportActionBar().setTitle(item.getTitle());

        } else if (id == R.id.nav_galeria) {
            fragment = new galeria();
            FragmentTransaction = true;

        } else if (id == R.id.nav_send) {

        }
        //content_principal es el id de content_principal
        if (FragmentTransaction){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_principal, fragment)
                    .commit();
            item.setChecked(true);
            getSupportActionBar().setTitle(item.getTitle());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    //para hacer no se quecua pero es el video 5
    private void loadApi(){
        RestApi rest = new RestApi();
        rest.setOnCompleteLoadRest(this);
        rest.execute("wes");
    }

}
