package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

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

        Bundle pakunek = getIntent().getExtras();
        gradesNumber = pakunek.getInt("gradeNumber");
        fillModelOceny(gradesNumber);
        adapter = new InteractiveArrayAdapter(this, gradeList);

        ListaOcenRV = findViewById(R.id.listLayout);
        ListaOcenRV.setAdapter(adapter);
        ListaOcenRV.setLayoutManager(new LinearLayoutManager(this));
        setCalculateAvgButton();

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