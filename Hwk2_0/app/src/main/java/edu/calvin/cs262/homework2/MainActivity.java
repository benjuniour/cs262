package edu.calvin.cs262.homework2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private ArrayList<String> spinnerOptions = new ArrayList<String>();
    private SourceCodeLoader srcCodeloader;
    private String options = "";

    Spinner spinner;
    EditText userurl;
    TextView src_codeDisplay;
    TextView spinnerdisplay;
    Button loadpagecodebutt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if(getSupportLoaderManager().getLoader(0)!=null){
//            getSupportLoaderManager().initLoader(0,null,this);
//        }

        spinner = findViewById(R.id.spinner);
        src_codeDisplay = findViewById(R.id.src_code_display);
        loadpagecodebutt = findViewById(R.id.page_src_butt);
        userurl = findViewById(R.id.urlentry);
        spinnerdisplay = findViewById(R.id.spinnerdisplay);

        spinnerOptions.add("http://");
        spinnerOptions.add("https://");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, spinnerOptions);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                options = parent.getItemAtPosition(position).toString();
                spinnerdisplay.setText(options);

            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });
    }

    public void loadSourceCode(View view) {
        Log.d("result","Came here");
        String query = options + userurl.getText().toString();
        new HtmlLoader(src_codeDisplay).execute(query);
        Log.d("result","Finished here");
//        String userinput = options + userurl.getText();
//        Bundle queryBundle = new Bundle();
//        queryBundle.putString("queryString", userinput);
//        getSupportLoaderManager().restartLoader(0,queryBundle,this);

    }


    private static class HtmlLoader extends AsyncTask<String, String, String>{

        private WeakReference<TextView> htmlText;
        String webpage = "";

        HtmlLoader(TextView displayhtml){
            this.htmlText = new WeakReference<>(displayhtml);
            Log.d("html", "came here to initialize textview");
        }

        @Override
        protected String doInBackground(String... strings) {
            Log.d("html", "came here to doinginbackground");
            Log.d("html",  strings[0]);
            webpage = "";
            try{
                webpage = Jsoup.connect(strings[0]).get().html();



            }catch (Exception e){
                Log.d("html", "failed to connect");
            }

            return webpage;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            htmlText.get().setText(webpage);
        }
    }
}


//@NonNull
//    @Override
//    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
//        String queryString = "";
//
//        if (args != null){
//            queryString = args.getString("queryString");
//        }
//        return new SourceCodeLoader(this, queryString);
//    }
//
//    @Override
//    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
//        src_codeDisplay.setText(data);
//    }
//
//    @Override
//    public void onLoaderReset(@NonNull Loader<String> loader) {
//
//    }