package fr.emilienleroy.outerspacemanager.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import fr.emilienleroy.outerspacemanager.R;
import fr.emilienleroy.outerspacemanager.models.ApiUser;

/**
 * Created by eleroy on 23/04/2018.
 */

public class RecyclerRankAdapter extends RecyclerView.Adapter<RecyclerRankAdapter.ViewHolder> {

    private List<ApiUser> listRank;
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    private LoadData loadData;
    private boolean loading = false;
    View.OnClickListener clickListener;


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView points;

        public ViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.name);
            points = (TextView) v.findViewById(R.id.points);
        }
    }

    public RecyclerRankAdapter(List<ApiUser> myDataset,LoadData onLoadData) {
        listRank = myDataset;
        loadData = onLoadData;
    }



    @Override
    public RecyclerRankAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);


        View rankView = inflater.inflate(R.layout.row_rank, parent, false);

        ViewHolder viewHolder = new ViewHolder(rankView);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onClick(view);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ApiUser user = listRank.get(position);
            holder.name.setText(user.getUser());
            holder.points.setText(user.getPoints().toString());
            if (!loading && position == listRank.size()-1) {
                // End has been reached
                // Do something
                if (loadData != null) {
                    loadData.onLoadMore(position);
                }
                loading = true;
            }
        }
    }

    @Override
    public int getItemCount() {
        return listRank.size();
    }

    public void setLoaded() {
        loading = false;
    }

    @Override
    public int getItemViewType(int position) {
        return listRank.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    public void update(List<ApiUser> newItems){
        listRank.addAll(newItems);
        notifyDataSetChanged();
    }

    public void setClickListener(View.OnClickListener callback) {
        clickListener = callback;
    }

    public ApiUser getItem(int position) {
        return listRank.get(position);
    }

}
