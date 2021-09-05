package com.s3.movieflex.ui.fragments;

import androidx.fragment.app.Fragment;

public class SearchFragment extends Fragment /*implements MovieItemClickListener*//*, LoaderManager.LoaderCallbacks */ {
/*
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
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        searchButton.setOnClickListener(view1 -> {
            str = searchEditText.getText().toString();
            SearchLoader loader = new SearchLoader(getContext(), str);
            adapter = new MovieFavAdapter(getContext(), loader.loadInBackground(), (MovieItemClickListener) this);
            search_result.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
            search_result.setAdapter(adapter);
            // LoaderManager.getInstance(this).initLoader(1, null, this).forceLoad();

        });


        return view;
    }

    @Override
    public void onMovieClick(MovieModel movie, ImageView movieImageView) {
        if (getActivity() != null) {

            Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
            intent.putExtra("movie", movie);

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), movieImageView, "sharedName");
            startActivity(intent, options.toBundle());
        }
    }
/*
    @NonNull
    @Override
    public Loader onCreateLoader(int id, @Nullable Bundle args) {
        Toast.makeText(getContext(),str,Toast.LENGTH_SHORT).show();
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

    }*/

}