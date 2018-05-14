package fr.emilienleroy.outerspacemanager.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fr.emilienleroy.outerspacemanager.R;

/**
 * Created by eleroy on 14/05/2018.
 */

public class AttackFragment extends Fragment {

    private String username;
    private TextView textViewUsername;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        username = this.getArguments().getString("username");
        return inflater.inflate(R.layout.fragment_attack,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textViewUsername = (TextView) view.findViewById(R.id.name);
        textViewUsername.setText("Name : "+username);
    }
}
