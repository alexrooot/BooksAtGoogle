package com.example.android.booksatgoogle;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
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

        String jsonSocket =  makeHTTPSocket(url);
        ArrayList<BookConstructor> arrayOfBooks = extractBooksFromJson(jsonSocket);
        return arrayOfBooks;
    }

    private static ArrayList<BookConstructor> extractBooksFromJson(String jsonSocket) {
        Log.e(LOG_TAG, "We are going to star prasing ");
        if (TextUtils.isEmpty(jsonSocket)) {
            Log.e(LOG_TAG, "Check that we get some data before we try to prase");
            return null;
        }

        JSONObject jsonResults = null;
        Log.e(LOG_TAG, "created a blank JSONObject");
        ArrayList<BookConstructor> BookPrase = new ArrayList<>();

        try {
            Log.e(LOG_TAG, "Try to prase with try");
            jsonResults = new JSONObject(jsonSocket);//save the string/BufferedReader into this type
            Log.e(LOG_TAG, "save the string data into JSON=jsonResult");
            JSONArray booksArray = jsonResults.getJSONArray("items");//indicate where to start search
            Log.e(LOG_TAG, "created an array specific for JSON");
            //loop to all things under items JSON
            for (int i = 0; i < booksArray.length(); i++) { Log.e(LOG_TAG,"start loop currently on"+i);
                JSONObject currentBook = booksArray.getJSONObject(i);// make a temp JSONArray of our data;
                Log.e(LOG_TAG, "local json");

                        JSONObject saleInfo = booksArray.getJSONObject(i);//selling properties
                        Log.e(LOG_TAG, "Saleinfo worked");

                        JSONObject volumeInfo = currentBook.getJSONObject("volumeInfo");//
                        Log.e(LOG_TAG,"Volumeinfo");

                JSONArray identifier = volumeInfo.getJSONArray("industryIdentifiers");

                JSONObject currentID = identifier.getJSONObject(i);
                                //JSONObject industryIdentifiers = currentBook.getJSONObject("industryIdentifiers");
                                Log.e(LOG_TAG,"indentifer");



                String author = "";
                String donw = volumeInfo.getString("previewLink");
                Log.e(LOG_TAG, "donw is " + donw);

                String serial = currentID.getString("identifier");
                Log.e(LOG_TAG, serial);
                try{
                    String saleability = saleInfo.getString("saleability");
                    Log.e(LOG_TAG, saleability);
                }catch (JSONException e){
                    Log.e(LOG_TAG, "could not get price");
                }
                String saleability = " ";
                String title = volumeInfo.getString("title");
                Log.e(LOG_TAG, title);

                BookPrase.add(new BookConstructor(title,author, serial, saleability,donw ));
            }

        } catch (JSONException e) {
            Log.e(LOG_TAG, "There was and error parsing");
            e.printStackTrace();
        }
        Log.e(LOG_TAG,"We are returning ArrayList<BookConstructor> BookPrase");
        return BookPrase;
    }

    private static String makeHTTPSocket(URL url) {
        Log.e(LOG_TAG, "going to setup socket");
        String jsonResponse = "";
        HttpURLConnection connection = null;//has to be setup with more properties
        InputStream rawDownload = null;// will use first to save download
        if (url == null){
            Log.e(LOG_TAG, "We got a null URL return early");
        }

        try {
            Log.e(LOG_TAG, "going to try and setup properties to connection socket");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(30000);
            connection.setReadTimeout(25000);
            /*
            Add the network permission in the manifest
             */
            connection.connect();//starts connection base on what propertis are given to connection

            if (connection.getResponseCode() == 200)//200 is good connection for http
            {
                Log.e(LOG_TAG, "We got a good connection");
                rawDownload = connection.getInputStream();
                jsonResponse = readFromStream(rawDownload);
            }
        } catch (IOException e) {
            Log.e(LOG_TAG,"Problem in the connection or results returning form readFromStream()");
            e.printStackTrace();
        }
        finally {
            if (connection != null) {
                Log.e(LOG_TAG, "Clossing the socket all");
                connection.disconnect();
            }
            if (rawDownload != null) {
                try {
                    rawDownload.close();
                } catch (IOException e) {
                    Log.e(LOG_TAG, "Fail to get a good response from readFromStream");
                    e.printStackTrace();
                    return null;
                }
            }

        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream rawDownload) {
        Log.e(LOG_TAG, "We are going to read and format the raw data");
        StringBuilder stringBuilderdataFromRaw = new StringBuilder();//this is a fast string reader
        if (rawDownload != null) {
            /*
            setup how to read this data
             */
            InputStreamReader readrawAsProtocal = new InputStreamReader(rawDownload, Charset.forName("UTF-8"));
            /*
            read the data as bufferreader to go by each  like
             */
            BufferedReader lineofJson = new BufferedReader(readrawAsProtocal);
            Log.e(LOG_TAG, "setup  line reader and text protocol");
            try {
                String line = lineofJson.readLine();
                Log.e(LOG_TAG, "going to loop the lines");
                while (line != null) {
                    stringBuilderdataFromRaw.append(line);
                    line = lineofJson.readLine();
                }
            } catch (IOException e) {
                Log.e(LOG_TAG, "Could not read each line of lineofJson");
                e.printStackTrace();
            }
        }
        return stringBuilderdataFromRaw.toString();
    }

    private static URL stringToUrl(String requestedBookApiURL) {
        URL url = null;
        Log.e(LOG_TAG, "we are going to convert String url into URL");
        try {
            url = new URL(requestedBookApiURL);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "could not convert :" + requestedBookApiURL + " into type URL");
            e.printStackTrace();
            return null;
        }
        return url;
    }
}
