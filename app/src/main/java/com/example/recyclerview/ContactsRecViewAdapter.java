package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ContactsRecViewAdapter extends RecyclerView.Adapter<ContactsRecViewAdapter.ViewHolder>{

    private ArrayList<Contact> contacts = new ArrayList<>();
    private Context context;
    public ContactsRecViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtName.setText(contacts.get(position).getName());
        holder.txtEmail.setText(contacts.get(position).getEmail());

        holder.parent.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick(View v) {
              Toast.makeText(context, contacts.get(position).getName() + " Selected", Toast.LENGTH_SHORT).show();
              if(contacts.get(position).getName().equals("Koala")){
                  String URL = "https://some-random-api.ml/img/koala";
                  changeImage(contacts.get(position), holder, URL, "link");
              } else if(contacts.get(position).getName().equals("Dog")){
                  String URL = "https://dog.ceo/api/breeds/image/random";
                  changeImage(contacts.get(position), holder, URL, "message");
              }else if(contacts.get(position).getName().equals("Fox")){
                  String URL = "https://randomfox.ca/floof/?ref=apilist.fun";
                  changeImage(contacts.get(position), holder, URL, "image");
              } else if(contacts.get(position).getName().equals("Panda")){
                  String URL = "https://some-random-api.ml/img/panda";
                  changeImage(contacts.get(position), holder, URL, "link");
              } else if(contacts.get(position).getName().equals("Cat")){
                  String URL = "https://some-random-api.ml/img/cat";
                  changeImage(contacts.get(position), holder, URL, "link");
              } else if(contacts.get(position).getName().equals("Bird")){
                  String URL = "https://some-random-api.ml/img/birb";
                  changeImage(contacts.get(position), holder, URL, "link");
              }
          }
        });
        Glide.with(context)
                .asBitmap()
                .load(contacts.get(position).getImageUrl())
                .into(holder.image);

    }
    public void changeImage(Contact contact, ViewHolder holder, String URL, String nameOfLink){
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            System.out.println(response);
                            String image;
                            image = response.getString(nameOfLink);
                            contact.setImageUrl(image);
                            Glide.with(context)
                                    .asBitmap()
                                    .load(contact.getImageUrl())
                                    .into(holder.image);
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
    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        private TextView txtName, txtEmail;
        private CardView parent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            parent = itemView.findViewById(R.id.parent);
            txtEmail = itemView.findViewById(R.id.txtEmail);
            image = itemView.findViewById(R.id.image);
        }
    }
}
