package fr.emilienleroy.outerspacemanager;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BuildingActivity extends AppCompatActivity {

    private FloatingActionButton add_button;
    private String Token;
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://outer-space-manager.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private ApiService service = retrofit.create(ApiService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Token = getIntent().getStringExtra("TOKEN");
        loadBuilding();
        add_button = (FloatingActionButton) findViewById(R.id.fab);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_building(v);
            }
        });
    }

    private void loadBuilding() {
        Call<ApiBuilding> building = service.getBuilding(Token);
        building.enqueue(new Callback<ApiBuilding>() {
            @Override
            public void onResponse(Call<ApiBuilding> call, Response<ApiBuilding> response) {
                Log.e("build",Integer.toString(response.body().getBuiding()));
            }

            @Override
            public void onFailure(Call<ApiBuilding> call, Throwable t) {
                Log.e("et",t.getLocalizedMessage());
            }
        });
    }

    private void add_building(View v) {
        Call<ApiBuilding> building = service.createBuilding(Token,1);
        building.enqueue(new Callback<ApiBuilding>() {
            @Override
            public void onResponse(Call<ApiBuilding> call, Response<ApiBuilding> response) {
                Log.e("build",response.body().getCode());
            }

            @Override
            public void onFailure(Call<ApiBuilding> call, Throwable t) {

            }
        });
    }
}
