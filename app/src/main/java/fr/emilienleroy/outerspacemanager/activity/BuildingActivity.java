package fr.emilienleroy.outerspacemanager.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.emilienleroy.outerspacemanager.ApiService;
import fr.emilienleroy.outerspacemanager.R;
import fr.emilienleroy.outerspacemanager.adapter.BuildingAdapter;
import fr.emilienleroy.outerspacemanager.models.ApiBuilding;
import fr.emilienleroy.outerspacemanager.models.ResponseListBuilding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BuildingActivity extends AppCompatActivity {

    private ListView ListBuild;
    private List<ApiBuilding> ListBuilding = new ArrayList<>();
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
        ListBuild = (ListView) findViewById(R.id.listbuilding);
        Token = getIntent().getStringExtra("TOKEN");

        loadBuilding();

    }

    private void loadBuilding() {
        final Call<ResponseListBuilding> building = service.getBuilding(Token);
        building.enqueue(new Callback<ResponseListBuilding>() {
            @Override
            public void onResponse(Call<ResponseListBuilding> call, Response<ResponseListBuilding> response) {
                int i;
                ResponseListBuilding listbuilding = response.body();
                BuildingAdapter adapter = new BuildingAdapter(BuildingActivity.this, ListBuilding);
                ListBuild.setAdapter(adapter);
                for(i=0; i<listbuilding.getSize(); i++)
                {

                }
            }

            @Override
            public void onFailure(Call<ResponseListBuilding> call, Throwable t) {
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
