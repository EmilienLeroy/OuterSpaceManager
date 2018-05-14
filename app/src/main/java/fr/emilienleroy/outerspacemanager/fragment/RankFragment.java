package fr.emilienleroy.outerspacemanager.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import fr.emilienleroy.outerspacemanager.ApiService;
import fr.emilienleroy.outerspacemanager.R;
import fr.emilienleroy.outerspacemanager.activity.AttackActivity;
import fr.emilienleroy.outerspacemanager.activity.FleetActivity;
import fr.emilienleroy.outerspacemanager.activity.RankingActivity;
import fr.emilienleroy.outerspacemanager.adapter.LoadData;
import fr.emilienleroy.outerspacemanager.adapter.RankAdapter;
import fr.emilienleroy.outerspacemanager.adapter.RecyclerRankAdapter;
import fr.emilienleroy.outerspacemanager.models.ApiUser;
import fr.emilienleroy.outerspacemanager.models.ResponseListUsers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by eleroy on 23/04/2018.
 */

public class RankFragment extends Fragment {

    private RecyclerView recyclerRank;
    private List<ApiUser> listRank = new ArrayList<>();
    private RecyclerRankAdapter adapterRank;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressBar progressBarRank;

    private String Token;

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://outer-space-manager-staging.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private ApiService service = retrofit.create(ApiService.class);

    private Integer beginUser = 11;
    private Integer endUser = 20;
    private Integer intervalUser = 10;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rank,container,false);
        Token = getActivity().getIntent().getExtras().getString("TOKEN");

        progressBarRank = (ProgressBar) view.findViewById(R.id.progressBarFragRank);
        recyclerRank = (RecyclerView) view.findViewById(R.id.recyclerRank);

        recyclerRank.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerRank.setLayoutManager(layoutManager);

        adapterRank = new RecyclerRankAdapter(listRank, new LoadData() {
            @Override
            public void onLoadMore(int position) {
                progressBarRank.setVisibility(View.VISIBLE);
                updateUser(position);
            }
        });
        recyclerRank.setAdapter(adapterRank);
        loadUser();
        return  view;
    }

    private void updateUser(int skip) {

        Call<ResponseListUsers> rank = service.getRank(Token, beginUser, endUser);
        rank.enqueue(new Callback<ResponseListUsers>() {
            @Override
            public void onResponse(Call<ResponseListUsers> call, Response<ResponseListUsers> response) {

                ResponseListUsers responseRank = response.body();
                if(response.code() == 200){
                    adapterRank.setLoaded();
                    adapterRank.update(responseRank.getRank());
                    beginUser = beginUser + intervalUser;
                    endUser = endUser + intervalUser;
                }
                progressBarRank.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ResponseListUsers> call, Throwable t) {

            }
        });
    }

    private void loadUser() {
        Call<ResponseListUsers> rank = service.getRank(Token, 0, intervalUser);
        rank.enqueue(new Callback<ResponseListUsers>() {
            @Override
            public void onResponse(Call<ResponseListUsers> call, Response<ResponseListUsers> response) {
                progressBarRank.setVisibility(View.GONE);

                ResponseListUsers responseRank = response.body();
                listRank.clear();
                listRank.addAll(responseRank.getRank());
                adapterRank.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseListUsers> call, Throwable t) {

            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
