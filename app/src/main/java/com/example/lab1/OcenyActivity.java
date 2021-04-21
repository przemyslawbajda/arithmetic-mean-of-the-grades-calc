package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class OcenyActivity extends AppCompatActivity {

    private static final int DEFAULT_GRADE = 2;
    List<ModelOceny> mDane;
    int gradesNumber;
    InteraktywnyAdapterTablicy adapter;
    RecyclerView ListaOcenRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oceny);

        Bundle pakunek = getIntent().getExtras();
        gradesNumber = pakunek.getInt("gradeNumber");
        fillModelOceny(gradesNumber);
        adapter = new InteraktywnyAdapterTablicy(this,mDane);

        ListaOcenRV = findViewById(R.id.listLayout);
        ListaOcenRV.setAdapter(adapter);
        ListaOcenRV.setLayoutManager(new LinearLayoutManager(this));

    }

    void fillModelOceny(int gradeNumber){
        mDane = new ArrayList<ModelOceny>();

        String[] subjectsNames = getResources().getStringArray(R.array.subjectsTable);

        for(int i=0; i <gradeNumber; i++){
            mDane.add(new ModelOceny(subjectsNames[i], DEFAULT_GRADE));
        }
    }
}