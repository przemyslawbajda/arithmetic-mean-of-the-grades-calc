package com.example.lab1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class OcenyActivity extends AppCompatActivity {

    private static final int DEFAULT_GRADE = 2;
    List<GradeModel> gradeList;
    int gradesNumber;
    InteractiveArrayAdapter adapter;
    RecyclerView ListaOcenRV;
    Button calculateAvgButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oceny);

        if(savedInstanceState != null){
            //set grades when the screen is rotated
            int[] savedGradesList = savedInstanceState.getIntArray("gradesValue");

            gradeList = new ArrayList<GradeModel>();

            String[] subjectsNames = getResources().getStringArray(R.array.subjectsTable);

            for(int i=0; i< savedGradesList.length; i++){
                gradeList.add(new GradeModel(subjectsNames[i], savedGradesList[i]));


            }
        }else{
            //load bundle from MainActivity
            Bundle pakunek = getIntent().getExtras();
            gradesNumber = pakunek.getInt("gradeNumber");
            fillModelOceny(gradesNumber);
        }




        adapter = new InteractiveArrayAdapter(this, gradeList);
        //getting reference to listlayout
        ListaOcenRV = findViewById(R.id.listLayout);
        ListaOcenRV.setAdapter(adapter);
        ListaOcenRV.setLayoutManager(new LinearLayoutManager(this));



        //getting reference and set OnClickListener
        setCalculateAvgButton();




    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        // Create a new array the size of a grade ArrayList
        int[] grades = new int[gradeList.size()];

        for (int i = 0; i < grades.length; i++) {
            // Add next grades to the array
            grades[i] = gradeList.get(i).getGrade();
        }

        // Save an array with all chosen grades
        outState.putIntArray("gradesValue", grades);

        super.onSaveInstanceState(outState);
    }

    void fillModelOceny(int gradeNumber){
        gradeList = new ArrayList<GradeModel>();

        String[] subjectsNames = getResources().getStringArray(R.array.subjectsTable);

        for(int i=0; i <gradeNumber; i++){
            gradeList.add(new GradeModel(subjectsNames[i], DEFAULT_GRADE));
        }
    }

    void setCalculateAvgButton(){
        calculateAvgButton = findViewById(R.id.calculateAvgButton);
        calculateAvgButton.setOnClickListener(v -> {
            returnAvg(calculateAvg());
        });
    }

    double calculateAvg(){
        int sum=0;
        for(GradeModel grade: gradeList){
            sum+= grade.getGrade();
        }

        return sum/(double) gradeList.size();
    }

    void returnAvg(double result){
        Bundle resultBundle = new Bundle();
        resultBundle.putDouble("Result", result);
        Intent intent = new Intent();
        intent.putExtras(resultBundle);
        setResult(RESULT_OK, intent);
        finish();
    }
}