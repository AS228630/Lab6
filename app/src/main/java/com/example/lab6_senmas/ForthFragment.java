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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForthFragment extends Fragment {

//    List<Item> items = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);


        final View view = inflater.inflate(R.layout.forth_fragment, container, false);
//        setInitialData();
//        final RecyclerView recyclerView = view.findViewById(R.id.list);
//        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(layoutManager);
//        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(true);

        final RecyclerView recyclerView = view.findViewById(R.id.list);
        //to use RecycleView, you need a layout manager. default is LinearLayoutManager
//        recyclerView.setHasFixedSize(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(50);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

//        final ProgressDialog progressDoalog;
//        progressDoalog = new ProgressDialog(getContext());
//        progressDoalog.setMax(100);
//        progressDoalog.setMessage("Its loading....");
//        progressDoalog.setTitle("ProgressDialog bar example");
//        progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // show it
//        progressDoalog.show();
//        view.findViewById(R.id.progressbar).setVisibility(View.VISIBLE);
        final SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swipe);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(YouTubeApiService.YOUTUBE_SEARCH_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        final YouTubeApiService service = retrofit.create(YouTubeApiService.class);
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPlayListVideos(view, recyclerView, swipeRefreshLayout, service);
            }
        });




//https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&key=AIzaSyCasm1YNWUC8QC9mRwZoFyrMQBgBwzxURQ&playlistId=PLWz5rJ2EKKc9ofd2f-_-xmUi07wIGZa1c
        getPlayListVideos(view, recyclerView, swipeRefreshLayout, service);

        return view;
    }

    private void getPlayListVideos(final View view, final RecyclerView recyclerView, final SwipeRefreshLayout swipeRefreshLayout, YouTubeApiService service) {
        Call<PlaylistVideos> searchCall = service.getPlaylistById("PLWz5rJ2EKKc9ofd2f-_-xmUi07wIGZa1c");
        searchCall.enqueue(new Callback<PlaylistVideos>() {
            @Override
            public void onResponse(Call<PlaylistVideos> call, Response<PlaylistVideos> response) {
                try {
//                    Log.i(TAG, "Response is: " + response.body().string());
                    PlaylistVideos playlistVideos = response.body();
                    ListAdapter adapter = new ListAdapter(view.getContext(), playlistVideos);
                    recyclerView.setAdapter(adapter);
//                    progressDoalog.dismiss();
                    swipeRefreshLayout.setRefreshing(false);
//                    view.findViewById(R.id.progressbar).setVisibility(View.GONE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            //
            @Override
            public void onFailure(Call<PlaylistVideos> call, Throwable t) {
//                progressDoalog.dismiss();
            }
        });
    }

//    private void setInitialData() {
////        items.add(new Item("https://i5.walmartimages.ca/images/Large/094/514/6000200094514.jpg"));
//        items.clear();
//        items.add(new Item("https://www.iguides.ru/upload/medialibrary/5dd/5dd5d12fd192bb2da2135aca0e04dc9a.jpg"));
//        items.add(new Item("https://www.iguides.ru/upload/medialibrary/c6a/c6a5448ecd53dec9472d8bb4b5708af3.png"));
//        items.add(new Item("https://uploads.dev.by/resources/d8ef4201-8ba7-4aca-ac5e-2a98087a8386/cover/2ec26faa97.jpg"));
//        items.add(new Item("https://www.xda-developers.com/files/2019/10/Dark-Mode-Android-9-Pie-and-older-featured.jpg"));
//        items.add(new Item("https://pbs.twimg.com/profile_images/1168932726461935621/VRtfrDXq_400x400.png"));
//        items.add(new Item("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQekq3a2xNt5WykUxkgFjK7zL6dFaPPA7nr1sGI-XSOKE3GpU0h&s"));
//        items.add(new Item("https://miro.medium.com/max/15332/1*kqt8WntffBYIXRRzGCXWJg.png"));
//        items.add(new Item("https://pngicon.ru/file/uploads/android.png"));
//    }
}
