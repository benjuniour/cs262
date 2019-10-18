package edu.calvin.cs262.homework2;

import androidx.appcompat.app.AppCompatActivity;

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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

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

        String userinput = options + userurl.getText();
        srcCodeloader = new SourceCodeLoader(this, userinput);

        src_codeDisplay.setText(srcCodeloader.getSourceCode(userinput));
    }
}
