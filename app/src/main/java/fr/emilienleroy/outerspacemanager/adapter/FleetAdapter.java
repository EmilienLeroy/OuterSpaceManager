package fr.emilienleroy.outerspacemanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.List;

import fr.emilienleroy.outerspacemanager.R;
import fr.emilienleroy.outerspacemanager.models.ApiShip;

/**
 * Created by eleroy on 14/05/2018.
 */

public class FleetAdapter extends ArrayAdapter<ApiShip> {

    public FleetAdapter(Context context, List<ApiShip> ships) {
        super(context, 0, ships);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_fleet,parent, false);
        }

        FleetAdapter.FleetViewHolder viewHolder = (FleetAdapter.FleetViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new FleetAdapter.FleetViewHolder();
            viewHolder.informations = (TextView) convertView.findViewById(R.id.informations);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.life = (TextView) convertView.findViewById(R.id.life);
            viewHolder.speed = (TextView) convertView.findViewById(R.id.speed);
            viewHolder.amountBar = (SeekBar) convertView.findViewById(R.id.amount);
            viewHolder.seekBarValue = (TextView) convertView.findViewById(R.id.seekbarvalue);
            convertView.setTag(viewHolder);
        }

        ApiShip ship = getItem(position);
        viewHolder.informations.setText("ID: "+String.valueOf(ship.getShipID()));
        viewHolder.name.setText("Name: "+String.valueOf(ship.getName()));
        viewHolder.life.setText("LIFE "+String.valueOf(ship.getLife()));
        viewHolder.speed.setText("Speed: "+String.valueOf(ship.getSpeed()));
        viewHolder.seekBarValue.setText("Amount : "+String.valueOf(ship.getAmount()));
        viewHolder.amountBar.setMax(ship.getAmount());
        viewHolder.amountBar.setProgress(ship.getAmount()/4);


        return convertView;
    }

    private class FleetViewHolder{
        public TextView informations;
        public TextView name;
        public TextView life;
        public TextView speed;
        public TextView seekBarValue;
        public SeekBar amountBar;
    }
}
