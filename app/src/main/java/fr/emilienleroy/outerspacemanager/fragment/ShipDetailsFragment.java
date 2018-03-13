package fr.emilienleroy.outerspacemanager.fragment;
;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.emilienleroy.outerspacemanager.R;

/**
 * Created by eleroy on 13/03/2018.
 */

public class ShipDetailsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Integer myValue = this.getArguments().getInt("ID");
        return inflater.inflate(R.layout.fragment_details_ship,container,false);
    }
}
