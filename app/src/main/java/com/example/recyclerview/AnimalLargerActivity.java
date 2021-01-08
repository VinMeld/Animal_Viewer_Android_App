package com.example.recyclerview;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class AnimalLargerActivity extends Fragment {
    private Contact contact;
    private TextView animalText;
    private Context context;

    public AnimalLargerActivity(TextView animalText, Context context, Contact contact) {
        this.contact = contact;
        this.animalText = animalText;
        this.context = context;
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.larger_image, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RelativeLayout relativeLayout = null;
        relativeLayout.findViewById(R.id.animalImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
    }

}
