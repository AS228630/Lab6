package com.example.lab6_senmas;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YouTubeApiService {
    String YOUTUBE_SEARCH_BASE_URL = "https://www.googleapis.com/youtube/v3/";
    // Example

//    @GET("search?part=snippet&q=soccer&type=playlist&key=AIzaSyCasm1YNWUC8QC9mRwZoFyrMQBgBwzxURQ")
//    Call<ResponseBody> results();
//
//   @GET("search?part=snippet&q=soccer&type=playlist&key=" + Confi.YOUTUBE_API_KEY)
//   Call<Object> results1();

   @GET("playlistItems?part=snippet&maxResults=50&key=" + Confi.YOUTUBE_API_KEY)
   Call<PlaylistVideos> getPlaylistById(@Query("playlistId") String playlistId);

}