package com.java.demers_j.epicture.FlickrApi;

/**
 * Created by Hadad on 18/02/2017.
 */

public interface OnTaskDoneListener {
    void onTaskDone(String responseData);

    void onError();
}