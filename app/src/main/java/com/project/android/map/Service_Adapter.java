package com.project.android.map;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Service_Adapter extends RecyclerView.Adapter<Service_Adapter.MyViewHolder> {
    private ArrayList<module> mDataset;

    private ArrayList<String> longtitude = new ArrayList<>();
    private ArrayList<String> attitude = new ArrayList<>();

    Context mcontext;


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView En_name;
        public ImageView Img;
        public MyViewHolder(View v) {
            super(v);



            En_name = (TextView) v.findViewById(R.id.serviceName);

            Img = (ImageView) v.findViewById(R.id.serviceimage);
        }
    }


    public Service_Adapter(Context context,ArrayList<module> myDataset, ArrayList<String> length, ArrayList<String> width) {

        this.mcontext= context;
        this.mDataset = myDataset;
        this.longtitude = length;
        this.attitude = width;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public Service_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        // create a new view
        View v = LayoutInflater.from(mcontext)
                .inflate(R.layout.service_item, parent, false);


        return new MyViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        module currentitem = mDataset.get(position);



        String Ename = currentitem.getText();

        String image  = currentitem.getImage();


        holder.En_name.setText(Ename);



        Log.e("hhh",image);

        Picasso.with(mcontext).load(image).into(holder.Img);

        Log.e("pp", String.valueOf(holder.getAdapterPosition()));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = (int) getItemId(position) + 1 ;// get Id
                Intent intent = new Intent(mcontext, CommentActivity.class);

                intent.putExtra("leng", longtitude.get(position));
                intent.putExtra("wid", attitude.get(position));

                Log.e("location", String.valueOf(longtitude.get(position)+" , "+attitude.get(position)));

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mcontext.startActivity(intent);
            }
        });


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}