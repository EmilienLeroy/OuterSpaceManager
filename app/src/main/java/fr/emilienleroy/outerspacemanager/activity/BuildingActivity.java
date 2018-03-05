package fr.emilienleroy.outerspacemanager.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
        ListBuild = (ListView) findViewById(R.id.listB);
        Token = getIntent().getStringExtra("TOKEN");
        loadBuilding();
        ListBuild.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                loadAlert(position);
            }
        });
    }

    private void loadAlert(final int position) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(BuildingActivity.this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(BuildingActivity.this);
        }
        builder.setTitle("Créer un building")
                .setMessage("Attention vous allez créer un building")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        add_building(position);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void loadBuilding() {
        final Call<ResponseListBuilding> building = service.getBuilding(Token);
        building.enqueue(new Callback<ResponseListBuilding>() {
            @Override
            public void onResponse(Call<ResponseListBuilding> call, Response<ResponseListBuilding> response) {
                ResponseListBuilding responseBuilding = response.body();
                List<ApiBuilding> building = responseBuilding.getBuilding();
                BuildingAdapter adapter = new BuildingAdapter(BuildingActivity.this, building);
                ListBuild.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResponseListBuilding> call, Throwable t) {
                Log.e("et",t.getLocalizedMessage());
            }
        });
    }


    private void add_building(int position) {
        Call<ApiBuilding> building = service.createBuilding(Token,position);
        building.enqueue(new Callback<ApiBuilding>() {
            @Override
            public void onResponse(Call<ApiBuilding> call, Response<ApiBuilding> response) {
                if(response.code() == 200){
                    toast("Building créé");
                }else{
                    toast("Impossible de créer le building !!");
                }

            }

            @Override
            public void onFailure(Call<ApiBuilding> call, Throwable t) {
                toast("Impossible de créer le building");
            }
        });
    }

    private void toast(String newtext) {
        Context context = getApplicationContext();
        CharSequence text = newtext;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
