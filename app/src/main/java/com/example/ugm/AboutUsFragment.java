package com.example.ugm;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ugm.databinding.FragmentAboutUsBinding;

public class AboutUsFragment extends Fragment {


    private FragmentAboutUsBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmen
        binding = FragmentAboutUsBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.igapril.setOnClickListener(v -> {
            String url = "https://instagram.com/liaannx__?igshid=YTQwZjQ0NmI0OA==";
            Intent implicit = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(implicit);
        });
        binding.iguvi.setOnClickListener(v -> {
            String url = "https://instagram.com/yyowdnuvhereee?igshid=YTQwZjQ0NmI0OA==";
            Intent implicit = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(implicit);
        });
        binding.igdila.setOnClickListener(v -> {
            String url = "https://instagram.com/nndylaaul?igshid=YTQwZjQ0NmI0OA%3D%3D&utm_source=qr";
            Intent implicit = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(implicit);
        });
    }
}