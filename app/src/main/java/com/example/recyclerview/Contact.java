package com.example.recyclerview;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

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

public class Contact implements Parcelable {
    private String name;
    private String email;
    private String imageUrl;
    private Context context;
    private String nameOfLink;
    private String APILink;
    public Contact(String name, String email, String imageUrl, Context context, String nameOfLink, String APILink) {
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
        this.context = context;
        this.nameOfLink = nameOfLink;
        this.APILink = APILink;
    }


    protected Contact(Parcel in) {
        name = in.readString();
        email = in.readString();
        imageUrl = in.readString();
        nameOfLink = in.readString();
        APILink = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(imageUrl);
        dest.writeString(nameOfLink);
        dest.writeString(APILink);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
    public void changeImage(ContactsRecViewAdapter.ViewHolder holder){
        System.out.println(imageUrl);
        System.out.println(nameOfLink);
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                APILink,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            System.out.println(response);
                            String image;
                            image = response.getString(nameOfLink);
                            imageUrl = image;
                            System.out.println("setUpGlide");
                            Glide.with(context)
                                    .asBitmap()
                                    .load(imageUrl)
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
    public void setUpGlide(Contact contact, ContactsRecViewAdapter.ViewHolder holder){
        System.out.println("setUpGlide");
        Glide.with(context)
                .asBitmap()
                .load(contact.getImageUrl())
                .into(holder.image);
    }
    public Context getContext() {
        return context;
    }

    public String getNameOfLink() {
        return nameOfLink;
    }

    public String getAPILink() {
        return APILink;
    }
}
