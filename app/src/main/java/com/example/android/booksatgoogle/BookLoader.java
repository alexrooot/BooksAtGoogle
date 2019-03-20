package com.example.android.booksatgoogle;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;


public class BookLoader extends AsyncTaskLoader {

    public static final String LOG_TAG = BookConstructor.class.getSimpleName();

    private String mainUrl;

    public BookLoader(Context context, String url) {
        super(context);
        mainUrl = url;
    }

    @Override
    public Object loadInBackground() {
        ArrayList<BookConstructor> testadapter = new ArrayList<>();
        testadapter.add(new BookConstructor("funny poem book","Alejandro Ferreyra",
                "25.99","00002","http://www.google.com"));
        testadapter.add(new BookConstructor("funny poem book","Alejandro Ferreyra",
                "25.99","00002","http://www.google.com"));
        testadapter.add(new BookConstructor("funny poem book","Alejandro Ferreyra",
                "25.99","00002","http://www.google.com"));
        testadapter.add(new BookConstructor("funny poem book","Alejandro Ferreyra",
                "25.99","00002","http://www.google.com"));
        testadapter.add(new BookConstructor("funny poem book","Alejandro Ferreyra",
                "25.99","00002","http://www.google.com"));

        ArrayList<BookConstructor> results = (ArrayList<BookConstructor>) Utils.findBookData(mainUrl);
        Log.e(LOG_TAG, "we are casting results");
        return results;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
