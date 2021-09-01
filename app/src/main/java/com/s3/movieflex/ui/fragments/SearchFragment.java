package com.s3.movieflex.ui.fragments;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.s3.movieflex.R;
import com.s3.movieflex.adapters.MovieFavAdapter;
import com.s3.movieflex.adapters.MovieItemClickListener;
import com.s3.movieflex.adapters.asynclodar.SearchLoader;
import com.s3.movieflex.model.Cast;
import com.s3.movieflex.model.Movie;
import com.s3.movieflex.ui.MovieDetailActivity;

import java.util.ArrayList;

public class SearchFragment extends Fragment implements MovieItemClickListener, LoaderManager.LoaderCallbacks {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    Button searchButton;
    EditText searchEditText;

    private String str;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    MovieFavAdapter adapter;
    ArrayList<Movie> film = new ArrayList<>();
    ArrayList<Cast> casts = new ArrayList<>();
    RecyclerView search_result;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        search_result = view.findViewById(R.id.search_result);
        searchButton = view.findViewById(R.id.btnSearch);
        searchEditText = view.findViewById(R.id.searchEditText);

        searchButton.setOnClickListener(view1 -> {
            str = searchEditText.getText().toString();
            LoaderManager.getInstance(this).initLoader(1, null, this);
        });
        return view;
    }

    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {
        if (getActivity() != null) {

            Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
            intent.putExtra("movie", movie);

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), movieImageView, "sharedName");
            startActivity(intent, options.toBundle());
        }
    }

    @NonNull
    @Override
    public Loader onCreateLoader(int id, @Nullable Bundle args) {
        return new SearchLoader(getContext(), str);
    }

    @Override
    public void onLoadFinished(@NonNull Loader loader, Object data) {
        adapter = new MovieFavAdapter(getContext(), (ArrayList<Movie>) data, (MovieItemClickListener) this);
        search_result.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        search_result.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(@NonNull Loader loader) {

    }
}