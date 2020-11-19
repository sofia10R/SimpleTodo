package com.example.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//displays data from model into a row in the recycler view
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder>{
    public interface OnLongClickListener{
        void onItemLongClicked(int position);
    }
    List<String> items;
    OnLongClickListener longClickListener;
    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener) {
        this.items = items;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Use layout to inflate a view, wrap it inside a View Holder and return it
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false );
        return new ViewHolder(todoView);
    }
    //binds data to a View Holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    //grab item at position
       String item = items.get(position);
        //bind item into specified view holder
        holder.bind(item);
    }
    //Tells the Recycler View how many Items are in the list
    @Override
    public int getItemCount() {
        return items.size();
    }

    //Container to provide easy access to views that represent each row of the list
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);

        }
        //update view inside of the view holder with this data
        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //notify listener position where it was long pressed
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
