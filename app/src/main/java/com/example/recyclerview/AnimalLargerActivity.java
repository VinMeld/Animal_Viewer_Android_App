package com.example.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONException;
import org.json.JSONObject;

public class AnimalLargerActivity extends AppCompatActivity {


    private ImageView animalImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle data = getIntent().getExtras();
        Contact currentContact = (Contact) data.getParcelable("contact");
        setContentView(R.layout.larger_image);
        final TextView animalText = (TextView) findViewById(R.id.animal);
        animalText.setText(currentContact.getName());
        animalImage = findViewById(R.id.imageAnimal);
        setUpGlide(currentContact, animalImage);
        animalImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  changeImage(currentContact, animalImage);
            }
        });
    }
    public void setUpGlide(Contact contact, ImageView holder){
        Glide.with(AnimalLargerActivity.this) //1
                .load(contact.getImageUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .skipMemoryCache(true) //2
                .into(holder);
//        RequestOptions options = new RequestOptions()
//                .centerCrop()
//                .placeholder(R.mipmap.ic_launcher_round)
//                .error(R.mipmap.ic_launcher_round);
//        Glide.with(AnimalLargerActivity.this).load(contact.getImageUrl()).apply(options).into(holder);
    }
    public void changeImage(Contact contact, ImageView holder){
        System.out.println(contact.getImageUrl());
        System.out.println(contact.getNameOfLink());
        RequestQueue requestQueue= Volley.newRequestQueue(AnimalLargerActivity.this);
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                contact.getAPILink(),
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            System.out.println(response);
                            String image;
                            image = response.getString(contact.getNameOfLink());
                            contact.setImageUrl(image);
                            setUpGlide(contact, holder);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.toString());
                    }
                }
        );
        requestQueue.add(objectRequest);
    }
}



