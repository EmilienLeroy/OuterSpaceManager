package fr.emilienleroy.outerspacemanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import fr.emilienleroy.outerspacemanager.R;
import fr.emilienleroy.outerspacemanager.models.ApiBuilding;

/**
 * Created by eleroy on 27/02/2018.
 */

public class BuildingAdapter extends ArrayAdapter<ApiBuilding> {

    public BuildingAdapter(Context context, List<ApiBuilding> tweets) {
        super(context, 0, tweets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_building,parent, false);
        }

        BuildingViewHolder viewHolder = (BuildingViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new BuildingViewHolder();
            viewHolder.id = (TextView) convertView.findViewById(R.id.informations);
            convertView.setTag(viewHolder);
        }

        ApiBuilding building = getItem(position);

        viewHolder.id.setText(building.getBuildingId());

        return convertView;
    }

    private class BuildingViewHolder{
        public TextView id;
    }
}
