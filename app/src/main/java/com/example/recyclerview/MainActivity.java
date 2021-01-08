package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView contactsRecView;
    ArrayList<Contact> contacts = new ArrayList<>();
    private String image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactsRecView = findViewById(R.id.contactsRecView);
        System.out.println("Image " + image);
        initialImageAPI("Koala", "Tap to view a new Koala image!", "https://some-random-api.ml/img/koala", "link");
        initialImageAPI("Dog", "Tap to view a new Dog image!", "https://dog.ceo/api/breeds/image/random", "message");
        initialImageAPI("Cat", "Tap to view a new Cat image!", "https://some-random-api.ml/img/cat", "link");
        initialImageAPI("Fox", "Tap to view a new Fox image!", "https://randomfox.ca/floof/?ref=apilist.fun", "image");
        initialImageAPI("Panda", "Tap to view a new Panda image!", "https://some-random-api.ml/img/panda", "link");
        initialImageAPI("Bird", "Tap to view a Bird image!", "https://some-random-api.ml/img/birb","link", true);
    }

    private void displayCards() {
        ContactsRecViewAdapter adapter = new ContactsRecViewAdapter(MainActivity.this);
        adapter.setContacts(contacts);
        contactsRecView.setAdapter(adapter);
        contactsRecView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
    }
    public void initialImageAPI(String name, String description, String URL, String nameOfLink) {
        initialImageAPI(name, description, URL, nameOfLink, false);
    }
        public void initialImageAPI(String name, String description, String URL, String nameOfLink, boolean lastContact) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            System.out.println(response);
                            image = response.getString(nameOfLink);
                            contacts.add(new Contact(name, description, image, MainActivity.this, nameOfLink, URL));
                            if(lastContact){
                                displayCards();
                            }
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