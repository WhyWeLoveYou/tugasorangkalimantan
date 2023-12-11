package com.example.ugm;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class RecomendFragment extends Fragment {
    Button resepsosis, resepsoto;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recomend, container, false);
        resepsosis = view.findViewById(R.id.resepsosis);
        resepsoto = view.findViewById(R.id.resepsoto);

        resepsosis.setOnClickListener(v -> {
            resepsosis();
        });

        resepsoto.setOnClickListener(v -> {
            resepsoto();
        });

        return view;
    }

    private void resepsoto() {
        
    }

    public void resepsosis() {
        Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.kompas.com/food/read/2021/09/27/080300575/resep-sosis-solo-ayam-suwir-bikin-kulitnya-bisa-pakai-teflon"));
        startActivity(intent);
    }
}