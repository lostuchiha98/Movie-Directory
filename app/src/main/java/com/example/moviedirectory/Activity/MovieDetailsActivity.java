package com.example.moviedirectory.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.moviedirectory.Model.Movie;
import com.example.moviedirectory.R;
import com.example.moviedirectory.Util.Constants;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MovieDetailsActivity extends AppCompatActivity {
    private Movie movie;
    private TextView movieTitle,movieYear,Director,Actor,
                     category,RatingR,writers,plot,boxOffice,runtime;
    private ImageView movieImage;

    public RequestQueue Queue2;
    private String movieID;

    public MovieDetailsActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Queue2= Volley.newRequestQueue(this);
        movie=(Movie) getIntent().getSerializableExtra("movie");
        movieID=movie.getImdbID();

        movieImage=(ImageView) findViewById(R.id.movieImage);
        movieTitle=(TextView) findViewById(R.id.movieTitle);
        movieYear=(TextView) findViewById(R.id.releaseDate);
        writers=(TextView) findViewById(R.id.writers);
        Actor=(TextView) findViewById(R.id.actors);
        category=(TextView) findViewById(R.id.category);
        RatingR=(TextView) findViewById(R.id.rating);
        plot=(TextView)findViewById(R.id.plot);
        boxOffice=(TextView)findViewById(R.id.Boxoffice);
        runtime=(TextView) findViewById(R.id.runtime);

        Log.d("Details",movieID);
        getMovieDetails(movieID);

    }

    public void getMovieDetails(String id) {
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, Constants.URL + id + Constants.URL_RIGHT, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.has("Ratings")) {
                        JSONArray rating = response.getJSONArray("Ratings");
                        String source=null;
                        String value=null;

                        if(rating.length()>0){
                            JSONObject mRating= rating.getJSONObject(rating.length()-1);
                            source=mRating.getString("Source");
                            value=mRating.getString("Value");
                            RatingR.setText(source +" : "+value);

                        }
                        else {
                            RatingR.setText("N/A");
                        }
                        movieTitle.setText(response.getString("Title"));
                        movieYear.setText("Released :" + response.getString("Released"));
                        writers.setText("Writers :"+response.getString("Writer"));
                        plot.setText("Plot: "+ response.getString("Plot"));
                        runtime.setText("Runtime "+response.getString("Runtime"));
                        Actor.setText("Actors: "+response.getString("Actors"));

                        Picasso.get().load(response.getString("Poster")).into(movieImage);
                        boxOffice.setText("Box Office: "+ response.getString("BoxOffice"));
                       }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Queue2.add(jsonObjectRequest);
    }
}