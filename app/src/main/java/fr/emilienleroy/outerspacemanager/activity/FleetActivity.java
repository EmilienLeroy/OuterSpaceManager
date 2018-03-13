package fr.emilienleroy.outerspacemanager.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fr.emilienleroy.outerspacemanager.R;
import fr.emilienleroy.outerspacemanager.fragment.FleetFragment;

public class FleetActivity extends AppCompatActivity {

    private String Token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fleet);
        Token = getIntent().getStringExtra("TOKEN");
        
    }
}
