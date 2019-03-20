package com.example.android.booksatgoogle;

import android.util.Log;

import java.net.URL;
import java.util.List;

public class Utils {

    public static final String LOG_TAG = Utils.class.getSimpleName();
    private boolean donwloading = false;

    public static List<BookConstructor> findBookData(String requestedBookApiURL) {
        Log.e(LOG_TAG, "We are inside the Utils class");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Log.e(LOG_TAG, "We can not slow the the tread for 2 sec's");
            e.printStackTrace();//system message of error.
        }
        
        /*
        First thing first take that url and turn it into Type UTL
         */
        URL url = stringToUrl(requestedBookApiURL);

        return null;
    }

    private static URL stringToUrl(String requestedBookApiURL) {
    }
}
