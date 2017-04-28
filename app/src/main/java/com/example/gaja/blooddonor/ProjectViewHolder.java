package com.example.gaja.blooddonor;

/**
 * Created by gaja on 4/27/2017.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;



/**
 * Created by PRABHU on 21-04-2017.
 */

public class ProjectViewHolder extends RecyclerView.ViewHolder {

    public TextView titleView;
    public TextView name;
    public TextView place;
    public TextView number;

    public ProjectViewHolder(View itemView) {

        super(itemView);

        titleView = (TextView) itemView.findViewById(R.id.post_title);
        name = (TextView) itemView.findViewById(R.id.post_name);
        place = (TextView) itemView.findViewById(R.id.post_place);
        number = (TextView) itemView.findViewById(R.id.post_number);
    }

    public void bindToPost(post post) {

        name.setText(post.name);
        place.setText(post.place);
        number.setText(post.phone);
    }
}
