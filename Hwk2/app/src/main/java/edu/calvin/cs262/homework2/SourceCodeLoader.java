package edu.calvin.cs262.homework2;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class SourceCodeLoader extends AsyncTaskLoader<String> {

    private String mQueryString;

    public SourceCodeLoader(@NonNull Context context, String queryString) {
        super(context);
        mQueryString = queryString;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return getSourceCode(mQueryString);
    }



    public String getSourceCode(String url){

        String result = "";
        try{
            Document document = Jsoup.connect(url).get();
            result = document.toString();


        }catch (Exception e){

        }

        return  result;
    }
}

//    URL urlObject = new URL(url);
//            URLConnection urlConnection = urlObject.openConnection();
//            urlConnection.setRequestProperty( "User-Agent", "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; H010818)" );
//
//            if (urlConnection.getInputStream() == null){
//
//            }
//            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//            String strLine = "";
//
//            //Loop through every line in the source
//            while ((strLine = in.readLine()) != null){
//
//                //Prints each line to the console
//                result += strLine;
//            }
