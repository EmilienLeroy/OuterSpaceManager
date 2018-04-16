package fr.emilienleroy.outerspacemanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import fr.emilienleroy.outerspacemanager.R;
import fr.emilienleroy.outerspacemanager.models.ApiBuilding;
import fr.emilienleroy.outerspacemanager.models.ApiUser;

/**
 * Created by eleroy on 16/04/2018.
 */

public class RankAdapter extends ArrayAdapter<ApiUser> {

    public RankAdapter(Context context, List<ApiUser> rank) {
        super(context, 0, rank);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_rank,parent, false);
        }

        RankAdapter.RankViewHolder viewHolder = (RankAdapter.RankViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new RankViewHolder();
            viewHolder.points = (TextView) convertView.findViewById(R.id.points);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.rank = (TextView) convertView.findViewById(R.id.rank);
            convertView.setTag(viewHolder);
        }

        ApiUser user = getItem(position);

        viewHolder.points.setText("Points:  "+ user.getPoints());
        viewHolder.name.setText("Name: "+ user.getUser());
        viewHolder.rank.setText("RANK: "+Integer.toString(position+1));

        return convertView;
    }

    private class RankViewHolder{
        public TextView points;
        public TextView name;
        public TextView rank;
    }
}
