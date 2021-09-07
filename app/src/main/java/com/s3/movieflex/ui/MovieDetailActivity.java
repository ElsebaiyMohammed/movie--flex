package com.s3.movieflex.ui;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.s3.movieflex.R;
import com.s3.movieflex.adapters.CastAdapter;
import com.s3.movieflex.adapters.sqlite.DbController;
import com.s3.movieflex.model.Cast;
import com.s3.movieflex.model.MovieModel;
import com.s3.movieflex.model.TvModel;

import java.util.ArrayList;
import java.util.Objects;

public class MovieDetailActivity extends AppCompatActivity {
    ImageView movieImg, movieCover;
    TextView movieTitle, movieDesc;
    RecyclerView rvCast;
    ArrayList<Cast> cast = new ArrayList<>();
    CastAdapter castAdapter;
    MovieModel movieDetail;
    TvModel tvDetail;
    ImageButton favorite;
    String trail;
    DbController controller;
    FloatingActionButton openTrail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        iniView();
        iniData();
    }

    private void iniView() {
        rvCast = findViewById(R.id.rv_cast);

        movieImg = findViewById(R.id.detail_movie_img);
        movieCover = findViewById(R.id.detail_movie_cover);
        movieTitle = findViewById(R.id.detail_movie_title);
        movieDesc = findViewById(R.id.detail_movie_desc);
        favorite = findViewById(R.id.fav_btn);
        openTrail = findViewById(R.id.trial);

    }


    private void iniData() {
        controller = new DbController(getApplicationContext());
        controller.open();
        if (getIntent() != null) {
            if (getIntent().getExtras().get("type").equals("movie")) {
                movieDetail = (MovieModel) getIntent().getExtras().getSerializable("movie");
                boolean is = controller.selectMovie(movieDetail.getId());
                if (is) {
                    favorite.setBackgroundResource(R.drawable.ic_baseline_favorite_red);
                    //movieDetail.setMovieId(-1);
                } else {
                    favorite.setBackgroundResource(R.drawable.ic_baseline_favorite);
                }
                String baseURL = "https://image.tmdb.org/t/p/original";
                Glide.with(this).load(baseURL + movieDetail.getPoster_path()).into(movieImg);
                Glide.with(this).load(baseURL + movieDetail.getBackdrop_path()).into(movieCover);
                //trail = movieDetail.getStreamingLink();
                movieTitle.setText(movieDetail.getTitle());
                movieDesc.setText(movieDetail.getOverview());
                // cast = movieDetail.getCast();

                movieCover.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_photo));
                Objects.requireNonNull(getSupportActionBar()).setTitle(movieDetail.getTitle());

          /*  openTrail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(movieDetail.getStreamingLink()));
                    startActivity(intent);
                }
            });*/
           /* castAdapter = new CastAdapter(this, cast);
            rvCast.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            rvCast.setAdapter(castAdapter);*/

                favorite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (is) {
                            favorite.setBackgroundResource(R.drawable.ic_baseline_favorite);
                            controller.delete(movieDetail.getId());
                            //movieDetail.setMovieId(-1);
                            Toast.makeText(MovieDetailActivity.this.getApplicationContext(), "remove", Toast.LENGTH_SHORT).show();

                        } else {
                            favorite.setBackgroundResource(R.drawable.ic_baseline_favorite_red);
                            movieDetail.setId(controller.addMovie(movieDetail));

                            Toast.makeText(MovieDetailActivity.this.getApplicationContext(), "favorite", Toast.LENGTH_SHORT).show();


                        }
                    }
                });

            } else {

                tvDetail = (TvModel) getIntent().getExtras().getSerializable("movie");
                boolean is = controller.selectMovie(tvDetail.getId());
                if (is) {
                    favorite.setBackgroundResource(R.drawable.ic_baseline_favorite_red);
                } else {
                    favorite.setBackgroundResource(R.drawable.ic_baseline_favorite);
                }
                String baseURL = "https://image.tmdb.org/t/p/original";
                Glide.with(this).load(baseURL + tvDetail.getPoster_path()).into(movieImg);
                Glide.with(this).load(baseURL + tvDetail.getBackdrop_path()).into(movieCover);
                //trail = movieDetail.getStreamingLink();
                movieTitle.setText(tvDetail.getName());
                movieDesc.setText(tvDetail.getOverview());
                // cast = movieDetail.getCast();

                movieCover.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_photo));
                Objects.requireNonNull(getSupportActionBar()).setTitle(tvDetail.getName());

          /*  openTrail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(movieDetail.getStreamingLink()));
                    startActivity(intent);
                }
            });*/
           /* castAdapter = new CastAdapter(this, cast);
            rvCast.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            rvCast.setAdapter(castAdapter);*/

                favorite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (is) {
                            favorite.setBackgroundResource(R.drawable.ic_baseline_favorite);
                            controller.delete(tvDetail.getId());
                            //movieDetail.setMovieId(-1);
                            Toast.makeText(MovieDetailActivity.this.getApplicationContext(), "remove", Toast.LENGTH_SHORT).show();

                        } else {
                            favorite.setBackgroundResource(R.drawable.ic_baseline_favorite_red);
                            tvDetail.setId(controller.addTvShow(tvDetail));

                            Toast.makeText(MovieDetailActivity.this.getApplicationContext(), "favorite", Toast.LENGTH_SHORT).show();


                        }
                    }
                });


            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        controller.close();
    }
}