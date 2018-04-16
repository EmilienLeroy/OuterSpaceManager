package fr.emilienleroy.outerspacemanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import fr.emilienleroy.outerspacemanager.ApiService;
import fr.emilienleroy.outerspacemanager.R;
import fr.emilienleroy.outerspacemanager.activity.BuildingActivity;
import fr.emilienleroy.outerspacemanager.models.ApiUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String Token;
    private ArrayList<String> player = new ArrayList<String>();
    private TextView user_text;
    private TextView gas_text;
    private TextView mine_text;
    private TextView points_text;
    private TextView name;
    private TextView point;
    private ProgressBar progressBarMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://outer-space-manager-staging.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService service = retrofit.create(ApiService.class);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        progressBarMain = (ProgressBar) findViewById(R.id.progressBarMain);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View nav = navigationView.getHeaderView(0);
        name = (TextView) nav.findViewById(R.id.name);
        point = (TextView) nav.findViewById(R.id.point);

        Token = getIntent().getStringExtra("TOKEN");
        user_text = (TextView) findViewById(R.id.username);
        gas_text = (TextView) findViewById(R.id.gas);
        mine_text = (TextView) findViewById(R.id.mine);
        points_text = (TextView) findViewById(R.id.points);

        setup(Token,service);

    }

    public void setup(String token,ApiService api) {
        Call<ApiUser> user = api.currentuser(token);
        user.enqueue(new Callback<ApiUser>() {
            @Override
            public void onResponse(Call<ApiUser> call, Response<ApiUser> response) {
                progressBarMain.setVisibility(View.GONE);
                user_text.setText(response.body().getUser());
                name.setText(response.body().getUser());
                gas_text.setText(Integer.toString(response.body().getGas()));
                mine_text.setText(Integer.toString(response.body().getMinerals()));
                points_text.setText(response.body().getPoints().toString());
                point.setText(response.body().getPoints().toString());
            }

            @Override
            public void onFailure(Call<ApiUser> call, Throwable t) {

            }
        });

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
        getMenuInflater().inflate(R.menu.main, menu);
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
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_building) {
            Intent intent = new Intent(getBaseContext(), BuildingActivity.class);
            intent.putExtra("TOKEN", Token);
            startActivity(intent);
        } else if (id == R.id.nav_fleet) {
            Intent intent = new Intent(getBaseContext(), FleetActivity.class);
            intent.putExtra("TOKEN", Token);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
