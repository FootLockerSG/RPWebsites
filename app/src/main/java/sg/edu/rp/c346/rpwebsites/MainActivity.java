package sg.edu.rp.c346.rpwebsites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Spinner spinner1;
    Spinner spinner2;
    Button go;
    ArrayList<String> alNumbers;
    ArrayAdapter<String> aaNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner1 = findViewById(R.id.spinnerCategory);
        spinner2 = findViewById(R.id.spinnerSubCategory);
        go = findViewById(R.id.buttonGo);

        ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Category));
        spinnerArrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinner1.setAdapter(spinnerArrayAdapter1);
        alNumbers = new ArrayList<>();
        aaNumbers = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, alNumbers);
        spinner2.setAdapter(aaNumbers);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                switch (i) {
                    case 0:
                        // Your code for item 1 selected
                        final String[] strNumbersEven = getResources().getStringArray(R.array.Sub_Category_RP);
                        alNumbers.clear();
                        alNumbers.addAll(Arrays.asList(strNumbersEven));
                        aaNumbers.notifyDataSetChanged();
                        break;
                    case 1:
                        //Your code for item 2 selected
                        final String[] strNumbersOdd = getResources().getStringArray(R.array.Sub_Category_SOI);
                        alNumbers.clear();
                        alNumbers.addAll(Arrays.asList(strNumbersOdd));
                        aaNumbers.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> AdapterView) {

            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), webview.class);
                intent.putExtra("category",spinner1.getSelectedItemPosition());
                intent.putExtra("site",spinner2.getSelectedItemPosition());
                startActivity(intent);
            }
        });





    }

    @Override
    protected void onPause() {
        super.onPause();

        // Step 1a: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        // Step 1b : Obtain an instance of the SharedPreference Editor for update later
        SharedPreferences.Editor prefEdit = prefs.edit();
        // Step 1c : Add the key-value pair
        prefEdit.putInt("category",spinner1.getSelectedItemPosition());
        prefEdit.putInt("site",spinner2.getSelectedItemPosition());
        // step 1d : Call commit() method to save changes into the SharedPreferences
        prefEdit.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();

        // Step 2a: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        // step 2b: Retrieve the saved data with the key "greeting" from the SharedPreferences object
        int category = prefs.getInt("category",0);
        int site = prefs.getInt("site",0);

        spinner1.setSelection(category);
        if(category == 0) {
            final String[] strNumbersEven = getResources().getStringArray(R.array.Sub_Category_RP);
            alNumbers.clear();
            alNumbers.addAll(Arrays.asList(strNumbersEven));
            spinner2.setSelection(site);
            aaNumbers.notifyDataSetChanged();
        }
        else {
            final String[] strNumbersOdd = getResources().getStringArray(R.array.Sub_Category_SOI);
            alNumbers.clear();
            alNumbers.addAll(Arrays.asList(strNumbersOdd));
            spinner2.setSelection(site);
            aaNumbers.notifyDataSetChanged();
        }

    }
}
