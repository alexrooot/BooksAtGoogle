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
    public String getmBookTitel(){return mBookTitel;}

    public String getmBookAuthor(){return mBookAuthor;}

    public String getmBookPrice(){return mBookPrice;}

    public String getmBookURL(){return mBookURL;}

    public String getmBoolSerial(){return mBoolSerial;}
}
