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
import fr.emilienleroy.outerspacemanager.models.ApiShip;

/**
 * Created by eleroy on 13/03/2018.
 */

public class ShipAdapter extends ArrayAdapter<ApiShip> {

    public ShipAdapter(Context context, List<ApiShip> ships) {
        super(context, 0, ships);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_ship,parent, false);
        }

        ShipViewHolder viewHolder = (ShipViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new ShipViewHolder();
            viewHolder.informations = (TextView) convertView.findViewById(R.id.informations);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.life = (TextView) convertView.findViewById(R.id.life);
            viewHolder.speed = (TextView) convertView.findViewById(R.id.speed);
            convertView.setTag(viewHolder);
        }

        ApiShip ship = getItem(position);
        viewHolder.informations.setText("ID: "+String.valueOf(ship.getShipID()));
        viewHolder.name.setText("Name: "+String.valueOf(ship.getName()));
        viewHolder.life.setText("LIFE "+String.valueOf(ship.getLife()));
        viewHolder.speed.setText("Speed: "+String.valueOf(ship.getSpeed()));
        return convertView;
    }

    private class ShipViewHolder{
        public TextView informations;
        public TextView name;
        public TextView life;
        public TextView speed;
    }
}
