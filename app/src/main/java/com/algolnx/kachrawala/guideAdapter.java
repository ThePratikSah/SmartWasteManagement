package com.algolnx.kachrawala;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class guideAdapter extends RecyclerView.Adapter<guideAdapter.guideViewHolder>{

    private String[] data;
    public guideAdapter(String[] data){
        this.data = data;
    }

    @Override
    public guideViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_layout, parent, false);
        return new guideViewHolder(view);
    }

    @Override
    public void onBindViewHolder(guideViewHolder holder, final int position) {

        final String title = data[position];
        holder.textView.setText(title);

    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class guideViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public guideViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.itemImgId);
            textView = itemView.findViewById(R.id.itemTextId);
        }
    }

}
