package com.example.moviebuzz;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class TrailerScreen extends YouTubeBaseActivity {

    String movieTrailerId;
    YouTubePlayerView youtubeView;
    YouTubePlayer.OnInitializedListener onInitializedListener;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailer_screen);

        youtubeView = findViewById(R.id.trailerLayout);

        movieTrailerId = getIntent().getStringExtra("Url");


        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(movieTrailerId);
                youTubePlayer.setFullscreen(true);

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(getApplicationContext(), "Video not available", Toast.LENGTH_SHORT).show();
            }
        };
        youtubeView.initialize("AIzaSyCL3nP82ToAvuHO-OSFkcZY3W-tBMLuqPg", onInitializedListener);
    }


}