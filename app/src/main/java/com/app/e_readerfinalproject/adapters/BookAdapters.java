package com.app.e_readerfinalproject.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.e_readerfinalproject.R;
import com.app.e_readerfinalproject.models.Bookmodels;

import java.util.ArrayList;

public class BookAdapters extends RecyclerView.Adapter<BookAdapters.viewHolder> {
    ArrayList<Bookmodels> list;
    Context context;

    public BookAdapters(ArrayList<Bookmodels> _list, Activity mainActivity)
    {
        list = _list;
        context = mainActivity;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.book_layout,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
           final Bookmodels bookmodels = list.get(position);
           holder.booktitle.setText(bookmodels.getBookname());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView booktitle;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            booktitle = itemView.findViewById(R.id.booktitle);
        }
    }
}
