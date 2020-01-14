package com.example.lab6_senmas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SecondFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.second_fragment, container, false);
        final RecyclerView recyclerView = view.findViewById(R.id.list1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(50);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        final SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swipe1);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(YouTubeApiService.YOUTUBE_SEARCH_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final YouTubeApiService service = retrofit.create(YouTubeApiService.class);
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPlayListVideos(view, recyclerView, swipeRefreshLayout, service);
            }
        });
        getPlayListVideos(view, recyclerView, swipeRefreshLayout, service);
        return view;
    }

    private void getPlayListVideos(final View view, final RecyclerView recyclerView, final SwipeRefreshLayout swipeRefreshLayout, YouTubeApiService service) {
        Call<PlaylistVideos> searchCall = service.getPlaylistById("PLWz5rJ2EKKc8jQfNAUu5reIGFNNqpn26X");
        searchCall.enqueue(new Callback<PlaylistVideos>() {
            @Override
            public void onResponse(Call<PlaylistVideos> call, Response<PlaylistVideos> response) {
                try {
                    PlaylistVideos playlistVideos = response.body();
                    ListAdapter adapter = new ListAdapter(view.getContext(), playlistVideos);
                    recyclerView.setAdapter(adapter);
                    swipeRefreshLayout.setRefreshing(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<PlaylistVideos> call, Throwable t) {
            }
        });
    }
}
