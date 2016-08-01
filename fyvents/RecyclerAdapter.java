package com.cedric.fyvents;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

/**
 * Created by Cedric on 28/03/2015.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    private ClickListener clickListener;
    List<DrawerList> drawerData = Collections.emptyList();

    public RecyclerAdapter(Context context, List<DrawerList> drawerData) {//Created a constructor.
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.drawerData = drawerData;
    }

    /*public void delete(int position){
        drawerData.remove(position);
        notifyItemRemoved(position);
    }*/


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Get the custom layout "drawer_list.xml"
        View view = inflater.inflate(R.layout.drawer_list, parent, false);
        //And place the custom layout into the view holder.
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        DrawerList current = drawerData.get(position);//Get the current position from the list.
        holder.title.setText(current.title);//Display the title
        holder.iconTitle.setImageResource(current.iconId);//Display the image icons.

    }

    public void setClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return drawerData.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView iconTitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            //Getting the id from the custom layout title and the image in the drawer_list.xml
            title = (TextView) itemView.findViewById(R.id.drawerList_title);
            iconTitle = (ImageView) itemView.findViewById(R.id.drawerList_icon);
           // iconTitle.setOnClickListener(this);
            //title.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

       @Override
        public void onClick(View v) {
           //delete(getPosition());
            //context.startActivity(new Intent(context, Singles.class));
           if (clickListener != null){
                clickListener.itemClicked(v, getPosition());
           }
        }

    }
    public interface ClickListener{
        /*
        * Making an interface to set the item clicked.
        * Then implement the clickListener on the drawer class
        */
        public void itemClicked(View view, int position);
    }
}
