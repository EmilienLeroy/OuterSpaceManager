package fr.emilienleroy.outerspacemanager.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import fr.emilienleroy.outerspacemanager.R;
import fr.emilienleroy.outerspacemanager.models.ApiUser;

/**
 * Created by eleroy on 23/04/2018.
 */

public class RecyclerRankAdapter extends RecyclerView.Adapter<RecyclerRankAdapter.ViewHolder> {

    private List<ApiUser> listRank;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView name;
        public TextView points;
        public ViewHolder(TextView v) {
            super(v);
            name = (TextView) v.findViewById(R.id.name);
            points = (TextView) v.findViewById(R.id.points);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerRankAdapter(List<ApiUser> myDataset) {
        listRank = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerRankAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_rank, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        ApiUser user = listRank.get(position);
        holder.name.setText(user.getUser());
        holder.points.setText(user.getPoints().toString());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listRank.size();
    }
}
