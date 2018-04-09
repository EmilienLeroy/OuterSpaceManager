package fr.emilienleroy.outerspacemanager.activity;

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
        ShipFragment nextFrag = new ShipFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentLayout, nextFrag);
        ft.commit();
        Token = getIntent().getStringExtra("TOKEN");

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle = new Bundle();
        bundle.putInt("ID", position );
        bundle.putString("TOKEN",Token);
        ShipDetailsFragment nextFrag = new ShipDetailsFragment();
        nextFrag.setArguments(bundle);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentLayout, nextFrag);
        ft.commit();
    }
}
