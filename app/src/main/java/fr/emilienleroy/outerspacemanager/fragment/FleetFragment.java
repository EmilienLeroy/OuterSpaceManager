package fr.emilienleroy.outerspacemanager.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import fr.emilienleroy.outerspacemanager.R;

/**
 * Created by eleroy on 05/03/2018.
 */

public class FleetFragment extends Fragment {
    ListView listfleet;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all_fleet,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listfleet = (ListView) view.findViewById(R.id.listfleet);
        
    }
}
