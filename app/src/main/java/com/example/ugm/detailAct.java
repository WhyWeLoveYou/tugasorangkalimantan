package com.example.ugm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;

import com.example.ugm.databinding.ActivityDetailBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class detailAct extends AppCompatActivity {
    private ActivityDetailBinding binding;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getDataFromF();
    }

    private void getDataFromF() {
        db = FirebaseFirestore.getInstance();
        String namanya = getIntent().getStringExtra("jenengR");
        db.collection("daftarR").document(namanya).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if (documentSnapshot != null && documentSnapshot.exists()) {
                        String bytea = documentSnapshot.getString("gambar");
                        byte[] bytes = Base64.decode(bytea, Base64.DEFAULT);
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        binding.namaR.setText(documentSnapshot.getString("namaResep"));
                        binding.descText.setText(documentSnapshot.getString("deskripsi"));
                        binding.profileImageView.setImageBitmap(bitmap);
                    }
                }
            }
        });
    }
}