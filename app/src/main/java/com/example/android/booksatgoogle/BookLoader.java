package com.example.android.booksatgoogle;
import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;


public class BookLoader extends AsyncTaskLoader {

    public static final String LOG_TAG = BookConstructor.class.getSimpleName();

    public BookLoader(Context context) {
        super(context);
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


        return testadapter;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
