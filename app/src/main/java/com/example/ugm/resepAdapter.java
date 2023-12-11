package com.example.ugm;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ugm.models.resep;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class resepAdapter extends RecyclerView.Adapter<resepAdapter.ViewHolder> {

    private ArrayList<resep> ResepArrayList;
    private Context context;
    private FirebaseFirestore db;
    private String Key_Name;

    public resepAdapter(ArrayList<resep> ResepArrayList, Context context) {
        this.ResepArrayList = ResepArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public resepAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_rv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull resepAdapter.ViewHolder holder, int position) {
        resep Resepnya = ResepArrayList.get(position);
        holder.namaR.setText(Resepnya.getNamaResep());
        if (Resepnya.getgambar() == null) {
            holder.imageView.setImageResource(R.drawable.gdgd);
        } else {
            String bytea = Resepnya.getgambar();
            byte[] bytes = Base64.decode(bytea, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            if (bitmap != null) {
                holder.imageView.setImageBitmap(bitmap);
            } else {
                holder.imageView.setImageResource(R.drawable.gdgd);
            }
        }
    }

    @Override
    public int getItemCount() {
        return ResepArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView namaR;
        private final LinearLayout resepnya;
        private final ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namaR = itemView.findViewById(R.id.textViewResep);
            resepnya = itemView.findViewById(R.id.linearLayoutR);
            imageView = itemView.findViewById(R.id.imageView);

            resepnya.setOnClickListener(v -> {
                resep Resepnya = ResepArrayList.get(getAdapterPosition());
                Intent val = new Intent(context.getApplicationContext(), detailAct.class);
                val.putExtra("jenengR", Resepnya.getNamaResep());
                context.startActivity(val);
            });
        }
    }
}
