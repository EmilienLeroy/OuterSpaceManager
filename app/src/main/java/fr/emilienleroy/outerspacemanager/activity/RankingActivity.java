package fr.emilienleroy.outerspacemanager.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.List;

import fr.emilienleroy.outerspacemanager.ApiService;
import fr.emilienleroy.outerspacemanager.R;
import fr.emilienleroy.outerspacemanager.adapter.BuildingAdapter;
import fr.emilienleroy.outerspacemanager.adapter.RankAdapter;
import fr.emilienleroy.outerspacemanager.models.ApiBuilding;
import fr.emilienleroy.outerspacemanager.models.ApiUser;
import fr.emilienleroy.outerspacemanager.models.ResponseListBuilding;
import fr.emilienleroy.outerspacemanager.models.ResponseListShip;
import fr.emilienleroy.outerspacemanager.models.ResponseListUsers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RankingActivity extends AppCompatActivity {


    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://outer-space-manager-staging.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private ApiService service = retrofit.create(ApiService.class);
    private String Token;
    private ListView ListRank;
    private ProgressBar progressBarRank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        ListRank = (ListView) findViewById(R.id.listRank);
        progressBarRank = (ProgressBar) findViewById(R.id.progressBarRank);
        Token = getIntent().getStringExtra("TOKEN");
        loadRank();
    }

    private void loadRank() {
        Call<ResponseListUsers> rank = service.getRank(Token, 0, 20);
        rank.enqueue(new Callback<ResponseListUsers>() {
            @Override
            public void onResponse(Call<ResponseListUsers> call, Response<ResponseListUsers> response) {
                progressBarRank.setVisibility(View.GONE);
                ResponseListUsers responseRank = response.body();
                List<ApiUser> users = responseRank.getRank();
                RankAdapter adapter = new RankAdapter(RankingActivity.this, users);
                ListRank.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResponseListUsers> call, Throwable t) {

            }
        });
    }
}
