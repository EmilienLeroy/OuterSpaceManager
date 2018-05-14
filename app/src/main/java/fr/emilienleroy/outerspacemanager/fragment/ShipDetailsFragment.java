package fr.emilienleroy.outerspacemanager.fragment;
;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import fr.emilienleroy.outerspacemanager.ApiService;
import fr.emilienleroy.outerspacemanager.R;
import fr.emilienleroy.outerspacemanager.activity.BuildingActivity;
import fr.emilienleroy.outerspacemanager.adapter.ShipAdapter;
import fr.emilienleroy.outerspacemanager.models.ApiShip;
import fr.emilienleroy.outerspacemanager.models.RequestCreateShip;
import fr.emilienleroy.outerspacemanager.models.ResponseListShip;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by eleroy on 13/03/2018.
 */

public class ShipDetailsFragment extends Fragment {

    private TextView id;
    private TextView name;
    private TextView life;
    private TextView speed;
    private Button btnBuy;
    private SeekBar amount;
    private int shipId;
    private String Token;
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://outer-space-manager-staging.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private ApiService service = retrofit.create(ApiService.class);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        shipId = this.getArguments().getInt("ID");
        Token = this.getArguments().getString("TOKEN");
        View view = inflater.inflate(R.layout.fragment_details_ship,container,false);
        id = (TextView) view.findViewById(R.id.id);
        name = (TextView) view.findViewById(R.id.name);
        speed = (TextView) view.findViewById(R.id.speed);
        life = (TextView) view.findViewById(R.id.life);
        btnBuy = (Button) view.findViewById(R.id.btn_buy);
        amount = (SeekBar) view.findViewById(R.id.amount);
        final TextView seekBarValue = (TextView) view.findViewById(R.id.seekbarvalue);

        amount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValue.setText("Amount : "+String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        id.setText("ID : " + String.valueOf(shipId));
        loadShip();
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buyShip(v);
            }
        });
        return view;
    }

    private void buyShip(View v) {
        loadAlert(shipId);
    }

    private void loadShip() {
        final Call<ApiShip> ship = service.getShip(Token,shipId);
        ship.enqueue(new Callback<ApiShip>() {
            @Override
            public void onResponse(Call<ApiShip> call, Response<ApiShip> response) {
                name.setText("Name : " + response.body().getName());
                speed.setText("Speed : " + String.valueOf(response.body().getSpeed()));
                life.setText("Life : " + String.valueOf(response.body().getLife()));
            }

            @Override
            public void onFailure(Call<ApiShip> call, Throwable t) {

            }
        });

    }

    private void loadAlert(final int position) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(getActivity());
        }
        builder.setTitle("Créer un navire")
                .setMessage("Attention vous allez créer un navire")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        addShip(position);
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

    private void addShip(int position) {
        RequestCreateShip amountShip = new RequestCreateShip();
        amountShip.setAmount(amount.getProgress());
        Call<ApiShip> newShip = service.createShip(Token, position, amountShip);
        newShip.enqueue(new Callback<ApiShip>() {
            @Override
            public void onResponse(Call<ApiShip> call, Response<ApiShip> response) {
                if(response.code() == 200){
                    toast("Navire créé");
                }else{
                    toast("Impossible de créer le navire !!");
                }
            }

            @Override
            public void onFailure(Call<ApiShip> call, Throwable t) {

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
