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
        holder.enlargePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Enlarging photo", Toast.LENGTH_SHORT).show();
            }
        });
        holder.parent.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick(View v) {
              if(contacts.get(position).getName().equals("Koala")){
                  String URL = "https://some-random-api.ml/img/koala";
                  contacts.get(position).changeImage(URL, "link");
                  setUpGlide(contacts.get(position), holder);
              } else if(contacts.get(position).getName().equals("Dog")){
                  String URL = "https://dog.ceo/api/breeds/image/random";
                  contacts.get(position).changeImage(URL, "message");
                  setUpGlide(contacts.get(position), holder);
              }else if(contacts.get(position).getName().equals("Fox")){
                  String URL = "https://randomfox.ca/floof/?ref=apilist.fun";
                  contacts.get(position).changeImage(URL, "image");
                  setUpGlide(contacts.get(position), holder);
              } else if(contacts.get(position).getName().equals("Panda")){
                  String URL = "https://some-random-api.ml/img/panda";
                  contacts.get(position).changeImage(URL, "link");
                  setUpGlide(contacts.get(position), holder);
              } else if(contacts.get(position).getName().equals("Cat")){
                  String URL = "https://some-random-api.ml/img/cat";
                  contacts.get(position).changeImage(URL, "link");
                  setUpGlide(contacts.get(position), holder);
              } else if(contacts.get(position).getName().equals("Bird")){
                  String URL = "https://some-random-api.ml/img/birb";
                  contacts.get(position).changeImage(URL, "link");
                  setUpGlide(contacts.get(position), holder);
              }
          }
        });
        Glide.with(context)
                .asBitmap()
                .load(contacts.get(position).getImageUrl())
                .into(holder.image);
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
        private ImageView image, enlargePhoto;
        private TextView txtName, txtEmail;
        private CardView parent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            parent = itemView.findViewById(R.id.parent);
            txtEmail = itemView.findViewById(R.id.txtEmail);
            image = itemView.findViewById(R.id.image);
            enlargePhoto = itemView.findViewById(R.id.enlargePhoto);
        }
    }
    public void setUpGlide(Contact contact, ViewHolder holder){
        Glide.with(context)
                .asBitmap()
                .load(contact.getImageUrl())
                .into(holder.image);
    }
}
