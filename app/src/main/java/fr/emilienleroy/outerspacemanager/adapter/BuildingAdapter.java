package fr.emilienleroy.outerspacemanager.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import fr.emilienleroy.outerspacemanager.R;
import fr.emilienleroy.outerspacemanager.models.ApiBuilding;

/**
 * Created by eleroy on 27/02/2018.
 */

public class BuildingAdapter extends ArrayAdapter<ApiBuilding> {

    public BuildingAdapter(Context context, List<ApiBuilding> buildings) {
        super(context, 0, buildings);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_building,parent, false);
        }

        BuildingViewHolder viewHolder = (BuildingViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new BuildingViewHolder();
            viewHolder.informations = (TextView) convertView.findViewById(R.id.informations);
            viewHolder.level = (TextView) convertView.findViewById(R.id.level);
            viewHolder.effect = (TextView) convertView.findViewById(R.id.effect);
            viewHolder.logo = (ImageView) convertView.findViewById(R.id.logo);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(viewHolder);
        }

        ApiBuilding building = getItem(position);

        viewHolder.informations.setText("ID: "+String.valueOf(building.getBuildingId()));
        if(building.getLevel() == null){
            viewHolder.level.setText("Level UP");
        }else{
            viewHolder.level.setText("Level "+String.valueOf(building.getLevel()));
        }
        viewHolder.effect.setText("Effect: "+ building.getEffect());
        viewHolder.name.setText("Name: "+building.getName());
        Picasso.with(getContext()).load(building.getImageUrl()).into(viewHolder.logo);
        return convertView;
    }

    private class BuildingViewHolder{
        public TextView informations;
        public TextView level;
        public TextView effect;
        public ImageView logo;
        public TextView name;
    }
}
