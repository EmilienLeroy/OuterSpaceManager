package fr.emilienleroy.outerspacemanager.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import fr.emilienleroy.outerspacemanager.ApiService;
import fr.emilienleroy.outerspacemanager.R;
import fr.emilienleroy.outerspacemanager.adapter.FleetAdapter;
import fr.emilienleroy.outerspacemanager.models.ApiShip;
import fr.emilienleroy.outerspacemanager.models.ResponseListShip;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by eleroy on 14/05/2018.
 */

public class AttackFragment extends Fragment {

    private String username;
    private TextView textViewUsername;
    private ListView listFleet;
    private SeekBar amount;

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://outer-space-manager-staging.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private ApiService service = retrofit.create(ApiService.class);
    private String Token;
    private Button btnAttack;
    private FleetAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        username = this.getArguments().getString("username");
        Token = this.getArguments().getString("TOKEN");
        return inflater.inflate(R.layout.fragment_attack,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listFleet = (ListView) view.findViewById(R.id.listfleet);
        btnAttack = (Button) view.findViewById(R.id.btn_attack);
        amount = (SeekBar) view.findViewById(R.id.amount);
        textViewUsername = (TextView) view.findViewById(R.id.name);
        textViewUsername.setText("Name : "+username);
        btnAttack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attackUser();
                //toast("Sorry, this feature will be create soon.");
            }
        });
        loadFleet();
    }

    private void attackUser() {
        
    }

    private void loadFleet() {
        final Call<ResponseListShip> fleet = service.getFleet(Token);
        fleet.enqueue(new Callback<ResponseListShip>() {
            @Override
            public void onResponse(Call<ResponseListShip> call, Response<ResponseListShip> response) {
                ResponseListShip responseShip = response.body();
                List<ApiShip> ships = responseShip.getShips();
                adapter = new FleetAdapter(getActivity(), ships);
                listFleet.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResponseListShip> call, Throwable t) {

            }
        });
    }

    private void toast(String newtext) {
        Context context = getContext();
        CharSequence text = newtext;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
