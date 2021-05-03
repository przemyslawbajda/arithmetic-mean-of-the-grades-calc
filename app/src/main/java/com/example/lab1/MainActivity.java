package com.example.lab1;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText surname;
    EditText gradesNumber;
    Button ocenyButton;
    TextView textResult;


    private static final String NAME_TEXT = "Name";
    private static final String SURNAME_TEXT = "Surname";
    private static final String GRADES_NUM = "Grades Number";
    private static final String BUTTON_VISIBILTY = "Button Visibility";
    private static String averageText = " ";
    private static String gradeButtonText = "";
    private boolean meanCalculated = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get references
        setContentView(R.layout.activity_main_constraint);
        name = findViewById(R.id.editTextImie);
        surname = findViewById(R.id.editTextNazwisko);
        gradesNumber = findViewById(R.id.editTextOceny);
        ocenyButton = findViewById(R.id.buttonOceny);
        textResult = findViewById(R.id.textResult);

        if (savedInstanceState != null){
            //data loading on screen rotation
            name.setText( savedInstanceState.getString(NAME_TEXT));
            surname.setText(savedInstanceState.getString(SURNAME_TEXT));
            gradesNumber.setText(savedInstanceState.getString(GRADES_NUM));
            ocenyButton.setVisibility(savedInstanceState.getInt(BUTTON_VISIBILTY));
            textResult.setText(savedInstanceState.getString(averageText));
            ocenyButton.setText(savedInstanceState.getString(gradeButtonText));
        }

        setListeners();

        //zrobic tak zeby po obrocie ostawial sie odpowiedni listener na ocenyButton

    }

    boolean isNumberCorrect(){
        int number = 0;
        if(gradesNumber.getText().toString().length() != 0){
            number=Integer.parseInt(gradesNumber.getText().toString());
            if(number<5 || number>15) {
                gradesNumber.setError(getString(R.string.wrongNumber));
                Toast.makeText(MainActivity.this, R.string.wrongNumber, Toast.LENGTH_LONG ).show();
                return false;
            }else{
                return true;
            }
        }else{
            gradesNumber.setError(getString(R.string.emptyText));
            Toast.makeText(MainActivity.this, R.string.emptyText, Toast.LENGTH_LONG ).show();
            return false;
        }
    }

    void checkData(){
        if(name.getText().length() != 0 && surname.getText().length() != 0 && gradesNumber.getText().length() !=0){
            if(isNumberCorrect()){
                ocenyButton.setVisibility(View.VISIBLE);
            }else{ ocenyButton.setVisibility(View.INVISIBLE);}

        }else{ ocenyButton.setVisibility(View.INVISIBLE); }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(NAME_TEXT, name.getText().toString());
        outState.putString(SURNAME_TEXT, surname.getText().toString());
        outState.putString(GRADES_NUM, gradesNumber.getText().toString());
        outState.putInt(BUTTON_VISIBILTY, ocenyButton.getVisibility());
        outState.putString(averageText,  textResult.getText().toString());
        outState.putString(gradeButtonText, ocenyButton.getText().toString());

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {
        super.onActivityResult(requestCode, resultCode, result);

        if(resultCode == RESULT_OK){
            Bundle activityResultBundle = result.getExtras();

            double avgResult = activityResultBundle.getDouble("Result");

            String resultString = String.format("%.2f", avgResult);
            textResult.setText(getString(R.string.yourAverage )+ " " + resultString );

            meanCalculated = showMessage(avgResult);

        }
    }

    private boolean showMessage(double avgResult){
        if(avgResult > 3.0){
            setButtonTextAndListener(R.string.congratulationButtonText, R.string.congratulationMessage);

        }else{
            setButtonTextAndListener(R.string.notThisTimeButtonText, R.string.sendingAplicationMessage);

        }
        return true;
    }

    private void setButtonTextAndListener(@StringRes int resid, @StringRes int resid2){
        ocenyButton.setText(resid);

        ocenyButton.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, resid2, Toast.LENGTH_LONG).show();
            finish();

        });
    }









    private void setListeners(){
        //Focus Listeners

        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (!hasFocus && name.getText().length() == 0) {
                    name.setError(getString(R.string.emptyText));
                    Toast.makeText(MainActivity.this, R.string.emptyText, Toast.LENGTH_LONG ).show();
                }
            }
        });


        surname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if ( !hasFocus && surname.getText().length() == 0) {
                    surname.setError(getString(R.string.emptyText));
                    Toast.makeText(MainActivity.this, R.string.emptyText, Toast.LENGTH_LONG ).show();
                }
            }

        });



        gradesNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    isNumberCorrect();
                }
            }
        });



        //Text Listeners

        name.addTextChangedListener(new android.text.TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkData();
            }

            @Override
            public void afterTextChanged(Editable s) { }


        });

        surname.addTextChangedListener( new android.text.TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkData();
            }

            @Override
            public void afterTextChanged(Editable s) { }

        });

        gradesNumber.addTextChangedListener( new android.text.TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkData();
            }

            @Override
            public void afterTextChanged(Editable s) { }

        });

        //button listener
        ocenyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int gradeNumber = Integer.parseInt(gradesNumber.getText().toString());
                Intent intencja = new Intent(MainActivity.this, OcenyActivity.class);
                intencja.putExtra("gradeNumber", gradeNumber);
                startActivityForResult(intencja, 1);
            }
        });

    }
}


