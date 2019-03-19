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

public class MainActivity extends AppCompatActivity {

    public static String LOG_TAG = MainActivity.class.getSimpleName();
    public static String sample =
            "https://www.googleapis.com/books/v1/volumes?q=airforce&filter=free-ebooks&libraryRestrict=no-restrict&maxResults=10&orderBy=relevance";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e(LOG_TAG,"Started Main Activity");
        usertext();
    }

    private void usertext() {
        EditText userUIText = (EditText) findViewById(R.id.search_bar);
        final Button userUISearch = (Button) findViewById(R.id.button);


        userUISearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchWorkd = userUISearch.getText().toString();
            }
        });

    }
}
