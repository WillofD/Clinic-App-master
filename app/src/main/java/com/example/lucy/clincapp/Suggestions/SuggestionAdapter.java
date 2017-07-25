package com.example.lucy.clincapp.Suggestions;

/**
 * Created by Shade on 5/9/2016.
 */

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.lucy.clincapp.R;
public class SuggestionAdapter extends RecyclerView.Adapter<SuggestionAdapter.ViewHolder> {

    private String[] titles = {"Acknowledge the presence of excessive use of technology",
            "Assist yourself for healthy use",
            "Ask for Help",
           };

    private String[] details = {"It is the first step towards the promotion of healthy use of technology.\n"+"\n ★ Keep a log of your online activities for each day. \n"
            +"\n ★ keep a log of activities you are missing due to online activities and other raising concerns about your online activities. \n "
            +" \n ★ Identify the situations associated with excessive use of online activities e.g. boredom, loneliness etc.\n"
            +"\n ★ Help yourself understand /differentiate the work, recreational & non-purposive use of online activities.",
            "\u2605 Take a break if your use exceeds 30 minutes; by stretching blinking your eyes/moving head forward/backward & clockwise and anticlockwise/stretching your arms and legs.\n"
                    +"\n \u2605 Keep the reading distance no less than 50 centimeter for a desktop computer, 40 centimeter for a tablet/laptop and  30 centimeter for a smart phone. \n"+"\n \u2605 Ensure adequate sleep for yourself. Do not delay or reduce sleep for online activities. " +
                    "\n "+"\n ★ Ensure you have three meals a day with adequate intake of water. \n"+"\n \u2605 Encourage yourself to avoid using screen product while doing other tasks. E.g. having food, crossing road, talking to others.",
            "★ Do not use online activities as a method of coping with negative mood states or to feel good.",
            " ★ If the given suggestions do not work,do  not hesitate to seek help or talk to your  relatives/counselor.",

            };



    class ViewHolder extends RecyclerView.ViewHolder{

        public int currentItem;
        public ImageView itemImage;
        public TextView itemTitle;
        public TextView itemDetail;

        public ViewHolder(View itemView) {
            super(itemView);

            itemTitle = (TextView)itemView.findViewById(R.id.suggestion_title);
            itemDetail = (TextView)itemView.findViewById(R.id.suggeston_content);


       }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.suggestion_view, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i)
    {
        viewHolder.itemTitle.setText(titles[i]);
        viewHolder.itemDetail.setText(details[i]);

    }

    @Override
    public int getItemCount()
    {
        return titles.length;
    }
}