package fr.emilienleroy.outerspacemanager.activity;

import android.content.res.Configuration;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import fr.emilienleroy.outerspacemanager.R;
import fr.emilienleroy.outerspacemanager.fragment.ShipDetailsFragment;
import fr.emilienleroy.outerspacemanager.fragment.ShipFragment;

public class FleetActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private String Token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fleet);
        //Create a fragment
        ShipFragment nextFrag = new ShipFragment();
        int currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.all, nextFrag);
            ft.commit();
        }
        else {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragmentLayout, nextFrag);
            ft.commit();
        }

        Token = getIntent().getStringExtra("TOKEN");

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle = new Bundle();
        bundle.putInt("ID", position );
        bundle.putString("TOKEN",Token);
        int currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            ShipDetailsFragment nextFrag = new ShipDetailsFragment();
            nextFrag.setArguments(bundle);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
            ft.replace(R.id.details, nextFrag);
            ft.commit();
        }else{

            ShipDetailsFragment nextFrag = new ShipDetailsFragment();
            nextFrag.setArguments(bundle);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
            ft.replace(R.id.fragmentLayout, nextFrag);
            ft.commit();
        }
    }
}
