package com.example.moviedirectory.Data;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviedirectory.Activity.MovieDetailsActivity;
import com.example.moviedirectory.Model.Movie;
import com.example.moviedirectory.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieViewAdapter extends RecyclerView.Adapter<MovieViewAdapter.ViewHolder> {
    private Context ctx;
    private List<Movie> movieList;
    public MovieViewAdapter(Context context, List<Movie> movies) {
        ctx=context;
        movieList=movies;
    }

    @NonNull
    @Override
    public MovieViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_row,parent,false);
        return new ViewHolder(view,ctx);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Movie movie=movieList.get(position);

            String posterLink= movie.getPoster();
            holder.title.setText(movie.getTitle());
            holder.type.setText(movie.getMovieType());
            holder.year.setText(movie.getYear());

            Picasso.get()
                    .load(posterLink)
                    .placeholder(android.R.drawable.btn_plus)
                    .into(holder.poster);

    }


    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        TextView year;
        TextView type;
        ImageView poster;
        public ViewHolder(@NonNull View itemView, final Context context) {
            super(itemView);
            title=(TextView) itemView.findViewById(R.id.textView);
            poster=(ImageView) itemView.findViewById(R.id.imageView2);
            year=(TextView) itemView.findViewById(R.id.textView2);
            type=(TextView) itemView.findViewById(R.id.textView3);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  Movie movie=movieList.get(getAdapterPosition());
                  Intent intent=new Intent(ctx, MovieDetailsActivity.class);

                  intent.putExtra("movie",movie);
                  ctx.startActivity(intent);
                }
            });
        }

        @Override
        public void onClick(View v) {

        }
    }
}
