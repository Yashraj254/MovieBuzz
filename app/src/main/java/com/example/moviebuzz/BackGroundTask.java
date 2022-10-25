package com.example.moviebuzz;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class BackGroundTask  extends AsyncTask<String, Void,String> {
    Context context;
    MovieInfo info;
    Button watchTrailer;
    String uri,URL;
    private static final String TAG = "BackGroundTask";
    public BackGroundTask(Context context,MovieInfo info,Button watchTrailer) {
        this.context = context;
        this.info = info;
        this.watchTrailer = watchTrailer;
    }

    protected String doInBackground(String... urls) {
        try {
            String url = urls[0];
            Document document = Jsoup.connect(url).get();
             Elements data = document.getElementsByClass("slate");
             uri = data.select("a").attr("href");
            Log.d(TAG, "doInBackground: " + url);
            Log.d(TAG, "doInBackground: " + uri);
        } catch (IOException e) {
            Log.d(TAG, "Some Error Occured ");
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        info.getUri(uri);
        Log.d(TAG, "onPostExecute: " + uri);

        watchTrailer.setVisibility(View.VISIBLE);

    }

}
