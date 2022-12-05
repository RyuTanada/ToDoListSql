package com.ryutanada.Exp3.todolist;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList list_id, list_todo, list_date, list_duration;

    CustomAdapter(Activity activity, Context context, ArrayList book_id, ArrayList book_title, ArrayList book_author,
                  ArrayList book_pages){
        this.activity = activity;
        this.context = context;
        this.list_id = book_id;
        this.list_todo = book_title;
        this.list_date = book_author;
        this.list_duration = book_pages;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.list_id_txt.setText(String.valueOf(list_id.get(position)));
        holder.list_title_txt.setText(String.valueOf(list_todo.get(position)));
        holder.list_date_txt.setText(String.valueOf(list_date.get(position)));
        holder.list_duration_txt.setText(String.valueOf(list_duration.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(list_id.get(position)));
                intent.putExtra("title", String.valueOf(list_todo.get(position)));
                intent.putExtra("date", String.valueOf(list_date.get(position)));
                intent.putExtra("duration", String.valueOf(list_duration.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView list_id_txt, list_title_txt, list_date_txt, list_duration_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            list_id_txt = itemView.findViewById(R.id.book_id_txt);
            list_title_txt = itemView.findViewById(R.id.book_title_txt);
            list_date_txt = itemView.findViewById(R.id.book_author_txt);
            list_duration_txt = itemView.findViewById(R.id.book_pages_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);

            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
