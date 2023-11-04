package com.example.roomdb;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private Context context;
    private List<User> userList;
    private AdapterListener adapterListener;

    public Adapter(Context context, AdapterListener adapterListener) {
        this.context = context;
        userList = new ArrayList<>();
        this.adapterListener = adapterListener;
    }

    //add data
    @SuppressLint("NotifyDataSetChanged")
    public void addUser(User user){
        userList.add(user);
        notifyDataSetChanged();
    }

    //delete data in recyclerview
    @SuppressLint("NotifyDataSetChanged")
    public void removeDataRecyclerView(int position){
        userList.remove(position);
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void clearData(){
        userList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_details, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        User user = userList.get(position);
        holder.nameText.setText(user.getName());
        holder.emailText.setText(user.getEmail());

        holder.updateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //adapterListener.onUpdate(user.getId(), user.getName(), user.getEmail());
                Intent intent = new Intent(new Intent(holder.updateText.getContext(), MainActivity2.class));
                intent.putExtra("u_id", String.valueOf(user.getId()));
                intent.putExtra("u_name", user.getName());
                intent.putExtra("u_email", user.getEmail());
                holder.updateText.getContext().startActivity(intent);
            }
        });

        holder.deleteText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterListener.onDelete(user.getId(), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView nameText, emailText;
        ImageView updateText, deleteText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardView);
            nameText = itemView.findViewById(R.id.nameText);
            emailText = itemView.findViewById(R.id.emailText);
            updateText = itemView.findViewById(R.id.updateText);
            deleteText = itemView.findViewById(R.id.deleteText);
        }
    }
}
