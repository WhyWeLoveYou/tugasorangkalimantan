package com.example.ugm;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.widget.Toast;

import com.example.ugm.databinding.ActivityTambahresepBinding;
import com.example.ugm.models.resep;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

public class tambahresep extends AppCompatActivity {

    private ActivityTambahresepBinding binding;
    private FirebaseFirestore db;
    private String encodedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTambahresepBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.profileImageView.setOnClickListener(v-> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            pickImage.launch(intent);
        });

        binding.simpan.setOnClickListener(v -> {
            if (validator()) {
                addItem();
            }
        });
    }

    private String encodeImage(Bitmap bitmap) {
        int previewW = 150;
        int previewH = bitmap.getHeight() * previewW / bitmap.getWidth();
        Bitmap previewBitmap = Bitmap.createScaledBitmap(bitmap, previewW, previewH, false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        previewBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    private final ActivityResultLauncher<Intent> pickImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    if (result.getData() != null) {
                        Uri imageUri = result.getData().getData();
                        try {
                            InputStream inputStream = getContentResolver().openInputStream(imageUri);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            binding.profileImageView.setImageBitmap(bitmap);
                            encodedImage = encodeImage(bitmap);
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
    );

    private void addItem() {
        db = FirebaseFirestore.getInstance();
        String namaResep = binding.namaRtext.getText().toString();
        String deskripsi = binding.descText.getText().toString();
        HashMap<String, Object> reseq = new HashMap<>();
        reseq.put("namaResep", namaResep);
        reseq.put("deskripsi", deskripsi);
        reseq.put("gambar", encodedImage);
        db.collection("daftarR").document(namaResep).set(reseq).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(tambahresep.this, "Berhasil", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private Boolean validator() {
        String namaResep = binding.namaRtext.getText().toString();
        String deskripsi = binding.descText.getText().toString();
        if (namaResep == null) {
            Toast.makeText(this, "Nama Harus Diisi", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (deskripsi == null) {
            Toast.makeText(this, "Nama Harus Diisi", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (encodedImage == null) {
            Toast.makeText(this, "Foto Harus Diisi", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}