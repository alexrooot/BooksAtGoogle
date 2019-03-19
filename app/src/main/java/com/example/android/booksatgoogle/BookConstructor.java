package com.example.android.booksatgoogle;

public class BookConstructor {

    private String mBookTitel;

    private String mBookAuthor;

    private String mBookPrice;

    private String mBookURL;

    private String mBoolSerial;

    public BookConstructor(String title, String author,String price, String serial, String url ){

        mBookTitel = title;
        mBookAuthor = author;
        mBookPrice = price;
        mBookURL = url;
        mBoolSerial = serial;
    }
    public String getmBookTitel;
    public String getmBookAuthor;
    public String getmBookPrice;
    public String getmBookURL;
    public String getmBoolSerial;
}
