package fr.emilienleroy.outerspacemanager.activity;

import android.content.res.Configuration;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import fr.emilienleroy.outerspacemanager.R;
import fr.emilienleroy.outerspacemanager.fragment.AttackFragment;
import fr.emilienleroy.outerspacemanager.fragment.RankFragment;
import fr.emilienleroy.outerspacemanager.fragment.ShipFragment;

public class AttackActivity extends AppCompatActivity {

    private String Token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attack);
        //Create a fragment
        RankFragment nextFrag = new RankFragment();
        int currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.all, nextFrag);
            ft.commit();
        }
        else {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragmentLayoutRank, nextFrag);
            ft.commit();
        }

        Token = getIntent().getStringExtra("TOKEN");
    }


}
