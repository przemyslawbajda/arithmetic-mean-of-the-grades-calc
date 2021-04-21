package com.example.lab1;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class InteraktywnyAdapterTablicy extends RecyclerView.Adapter<InteraktywnyAdapterTablicy.OcenyViewHolder> {

        private List<ModelOceny> mListaOcen;
        private LayoutInflater mPompka;


    public InteraktywnyAdapterTablicy(Activity kontekst, List<ModelOceny> listaOcen) {
        mPompka = kontekst.getLayoutInflater();
        this.mListaOcen = listaOcen;
    }

    @NonNull
    @Override
    public OcenyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View wiersz = mPompka.inflate(R.layout.wiersz_listy, null);

        return new OcenyViewHolder(wiersz);
    }

    @Override
    public void onBindViewHolder(@NonNull OcenyViewHolder holder, int position) {
        //Wywolywana zawsze gdy ma być wyświetlony nowy wiersz

        //Get the correct grade from the data source
        ModelOceny grade = mListaOcen.get(position);

        //Connect radio buttons group with a list row
        holder.mRadioGroup.setTag(grade);

        //set name of the subject
        holder.mNameVH.setText(grade.getSubjectName());


        //select radio button depending on grade value
        switch(mListaOcen.get(position).getGrade()){
            case 2:
                holder.mRadioGroup.check(R.id.radioButton4);
                break;
            case 3:
                holder.mRadioGroup.check(R.id.radioButton3);
                break;
            case 4:
                holder.mRadioGroup.check(R.id.radioButton2);
                break;
            case 5:
                holder.mRadioGroup.check(R.id.radioButton);
                break;

        }

    }

    @Override
    public int getItemCount() {
        return mListaOcen.size();
    }



    public class OcenyViewHolder extends RecyclerView.ViewHolder implements RadioGroup.OnCheckedChangeListener{

        TextView mNameVH;
        RadioGroup mRadioGroup;
        RadioButton[] mRadioButtons = new RadioButton[4];


        public OcenyViewHolder(@NonNull View itemView) {
            super(itemView);

            mNameVH = itemView.findViewById(R.id.gradeName);
            mRadioGroup = itemView.findViewById(R.id.radioGroup);
            mRadioButtons[0] = itemView.findViewById(R.id.radioButton4);
            mRadioButtons[1] = itemView.findViewById(R.id.radioButton3);
            mRadioButtons[2] = itemView.findViewById(R.id.radioButton2);
            mRadioButtons[3] = itemView.findViewById(R.id.radioButton);

            mRadioGroup.setOnCheckedChangeListener(this);

        }

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            //kod tu
        }
    }
}
