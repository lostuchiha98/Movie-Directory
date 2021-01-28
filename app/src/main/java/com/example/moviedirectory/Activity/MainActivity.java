package com.example.moviedirectory.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.moviedirectory.Data.MovieViewAdapter;
import com.example.moviedirectory.Model.Movie;
import com.example.moviedirectory.R;
import com.example.moviedirectory.Util.Constants;
import com.example.moviedirectory.Util.Sharedpref;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MovieViewAdapter movieViewAdapter;
    private List<Movie> movieList;
    private RequestQueue Queue;

    private Button searchButton;
    private EditText searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Queue= Volley.newRequestQueue(this);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog=new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.popup);
                dialog.show();

                searchText=(EditText) dialog.findViewById(R.id.searchBox);
                searchButton=(Button) dialog.findViewById(R.id.search);

                searchButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Sharedpref sharedpref=new Sharedpref(MainActivity.this);
                        if(searchText.getText().length()!=0){
                        String search= searchText.getText().toString();
                        sharedpref.setSearch(search);
                        movieList.clear();

                        getMovies(search);
                        movieViewAdapter.notifyDataSetChanged();
                        }
                        dialog.dismiss();
                    }
                });


            }
        });


        recyclerView=(RecyclerView) findViewById(R.id.recycleViewID);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        movieList=new ArrayList<>();


        Sharedpref sharedpref= new Sharedpref(MainActivity.this);
        String search=sharedpref.getSearch();
//        Log.d("SeeeeeeR",search);
         getMovies(search);


        movieViewAdapter=new MovieViewAdapter(MainActivity.this,getMovies(search));
        recyclerView.setAdapter(movieViewAdapter);
        movieViewAdapter.notifyDataSetChanged();
    }

    public  List<Movie> getMovies(String searchTerm){
        movieList.clear();
        final JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,
                Constants.URL_LEFT + searchTerm + Constants.URL_RIGHT, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray moviesArray = response.getJSONArray("Search");

                    for(int i=0;i<moviesArray.length();i++){
                        JSONObject movieObj = moviesArray.getJSONObject(i);
                        Movie movie=new Movie();
                        movie.setTitle(movieObj.getString("Title"));
                        movie.setYear("Year Released : "+movieObj.getString("Year"));
                        movie.setMovieType("Type : "+movieObj.getString("Type"));
                        movie.setPoster(movieObj.getString("Poster"));
                        movie.setImdbID(movieObj.getString("imdbID"));

//                        Log.d("Movies ",movie.getPoster());
                        movieList.add(movie);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Queue.add(jsonObjectRequest);
        return  movieList;
    }
}