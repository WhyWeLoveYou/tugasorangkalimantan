package com.example.ugm;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.ugm.databinding.FragmentReceiptBinding;
import com.example.ugm.models.resep;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ReceiptFragment extends Fragment {

    private FirebaseFirestore db;
    private ArrayList<resep> resepArrayList;
    private resepAdapter itemRvAdapter;
    private FragmentReceiptBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentReceiptBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.tambah.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), tambahresep.class);
            startActivity(intent);
        });
        getItem();
    }

    private void getItem() {
        db = FirebaseFirestore.getInstance();
        resepArrayList = new ArrayList<resep>();
        binding.recycler.setHasFixedSize(true);
        binding.recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        itemRvAdapter = new resepAdapter(resepArrayList, getContext());
        binding.recycler.setAdapter(itemRvAdapter);

        db.collection("daftarR").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot d:list) {
                        resep c = d.toObject(resep.class);
                        resepArrayList.add(c);
                    }
                    itemRvAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "No Data", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}