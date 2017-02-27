package com.java.demers_j.epicture.FlickrApi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

/**
 * Created by Hadad on 14/02/2017.
 */

public class Flickr {
    private String oauth_nonce;
    private String oauth_consumer_key = "98be8107532338d002929fbd201575b1";
    private String oauth_consumer_secret = "933ae8304cf91687";
    private String oauth_token_secret = ""; //"motdepasse21";
    private String oauth_signature_method = "HMAC-SHA1";
    private String oauth_version = "1.0";
    private String oauth_callback = "oob"; //"http://www.example.com";
    private Context ctx;

    private String resultat;

    private String response_request_token;

    public Flickr(Context ctx) {
        this.ctx = ctx;
    }

    public String getOauth_nonce() {
        return oauth_nonce;
    }

    public String flickrSearch(@NonNull String textToSearch, @NonNull String order_to_sort, int per_page, int page) {
        resultat =null;
        if (per_page >= 1 && page >= 1) {
            try {
                final OnTaskDoneListener onTaskDoneListener = new OnTaskDoneListener() {
                    @Override
                    public void onTaskDone(String responseData) {
                        resultat = responseData;
                        Toast.makeText(ctx, "Success: " + responseData, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError() {
                        Toast.makeText(ctx, "Failure", Toast.LENGTH_SHORT).show();
                    }
                };
                String url = "https://api.flickr.com/services/rest/?method=flickr.photos.search"
                        + "&api_key=" + oauth_consumer_key
                        + "&text=" + textToSearch
                        + "&sort" + order_to_sort
                        + "&per_page=" + per_page
                        + "&page=" + page
                        + "&format=json"
                        + "&nojsoncallback=1";
                WebService webService = new WebService(ctx, url, onTaskDoneListener);
                webService.execute().get();
            } catch (Exception E) {
                E.printStackTrace();
            }
        }

        return resultat;
    }

    public String flickrSearch(@NonNull String textToSearch, int per_page, int page) {
        resultat = null;
        if (per_page >= 1 && page >= 1) {
            try {
                final OnTaskDoneListener onTaskDoneListener = new OnTaskDoneListener() {
                    @Override
                    public void onTaskDone(String responseData) {
                        resultat = responseData;
                        Toast.makeText(ctx, "Success: " + responseData, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError() {
                        Toast.makeText(ctx, "Failure", Toast.LENGTH_SHORT).show();
                    }
                };
                String url = "https://api.flickr.com/services/rest/?method=flickr.photos.search"
                        + "&api_key=" + oauth_consumer_key
                        + "&text=" + textToSearch
                        + "&per_page=" + per_page
                        + "&page=" + page
                        + "&format=json";
                WebService webService = new WebService(ctx, url, onTaskDoneListener);
                webService.execute().get();
            } catch (Exception E) {
                E.printStackTrace();
            }
        }
        return null;
    }
}
