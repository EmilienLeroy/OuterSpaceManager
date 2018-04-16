package fr.emilienleroy.outerspacemanager.fragment;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import fr.emilienleroy.outerspacemanager.ApiService;
import fr.emilienleroy.outerspacemanager.R;
import fr.emilienleroy.outerspacemanager.activity.FleetActivity;
import fr.emilienleroy.outerspacemanager.adapter.ShipAdapter;
import fr.emilienleroy.outerspacemanager.models.ApiShip;
import fr.emilienleroy.outerspacemanager.models.ResponseListShip;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by eleroy on 05/03/2018.
 */

public class ShipFragment extends Fragment {

    private ListView listFleet;
    private String Token;
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://outer-space-manager-staging.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private ApiService service = retrofit.create(ApiService.class);
    private ProgressBar progressBarSheet;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all_fleet,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listFleet = (ListView) view.findViewById(R.id.listfleet);
        progressBarSheet = (ProgressBar) view.findViewById(R.id.progressBarFleet);
        Token = getActivity().getIntent().getExtras().getString("TOKEN");
        loadFleet();
        listFleet.setOnItemClickListener((FleetActivity)getActivity());
    }

    private void loadFleet() {
        final Call<ResponseListShip> ships = service.getShips(Token);
        ships.enqueue(new Callback<ResponseListShip>() {
            @Override
            public void onResponse(Call<ResponseListShip> call, Response<ResponseListShip> response) {
                progressBarSheet.setVisibility(View.GONE);
                ResponseListShip responseShip = response.body();
                List<ApiShip> ships = responseShip.getShips();
                ShipAdapter adapter = new ShipAdapter(getActivity(), ships);
                listFleet.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResponseListShip> call, Throwable t) {

            }
        });
    }


}
