package com.example.android.booksatgoogle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Loader;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<BookConstructor>>{

    public static String LOG_TAG = MainActivity.class.getSimpleName();
    public static String searchWorkd ="";
           // "https://www.googleapis.com/books/v1/volumes?q=airforce&filter=free-ebooks&libraryRestrict=no-restrict&maxResults=10&orderBy=relevance";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e(LOG_TAG,"Started Main Activity");
        usertext();
        Log.e(LOG_TAG, "Set a click listener on button");


        ProgressBar mainProgress = (ProgressBar) findViewById(R.id.loading_spinner);
        mainProgress.setVisibility(View.INVISIBLE);



        
    }

    private void usertext() {
        final EditText userUIText = (EditText) findViewById(R.id.search_bar);
        final Button userUISearch = (Button) findViewById(R.id.button);


        userUISearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressBar visibleProgress = (ProgressBar) findViewById(R.id.loading_spinner);
                visibleProgress.setVisibility(View.VISIBLE);
                searchWorkd = userUIText.getText().toString();
                searchWorkd = "https://www.googleapis.com/books/v1/volumes?q="+searchWorkd+
                        "&filter=free-ebooks&libraryRestrict=no-restrict&maxResults=10&orderBy=relevance";

                Log.e(LOG_TAG, "we grab the search word :" + searchWorkd);
                userUIText.setText(null);
                startLoaderManager();

            }
        });

    }

    private void startLoaderManager() {
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(1, null, this);
    }


    @Override
    public Loader<List<BookConstructor>> onCreateLoader(int id, Bundle args) {
        Log.e(LOG_TAG,"Loader manger started calling on BookLoader");
        String sample =
                "https://www.googleapis.com/books/v1/volumes?q=airforce&filter=free-ebooks&libraryRestrict=no-restrict&maxResults=10&orderBy=relevance";


        return new BookLoader(this, sample);
    }

    @Override
    public void onLoadFinished(Loader<List<BookConstructor>> loader, List<BookConstructor> data) {
        ListView listView = (ListView) findViewById(R.id.list);
        BookAdapter bookAdapter = new BookAdapter(this,data);
        Log.e(LOG_TAG, "generated an adapter instance with data list");
        listView.setAdapter(bookAdapter);
        Log.e(LOG_TAG,"Started the adapter to show onto list view");
        ProgressBar doneProgress = (ProgressBar) findViewById(R.id.loading_spinner);
        doneProgress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onLoaderReset(Loader<List<BookConstructor>> loader) {

    }
}
