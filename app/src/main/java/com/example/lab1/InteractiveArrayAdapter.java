package com.example.lab1;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class InteractiveArrayAdapter extends RecyclerView.Adapter<InteractiveArrayAdapter.OcenyViewHolder> {

        private List<GradeModel> gradeList;
        private LayoutInflater layoutInflater;


    public InteractiveArrayAdapter(Activity kontekst, List<GradeModel> listaOcen) {
        layoutInflater = kontekst.getLayoutInflater();
        this.gradeList = listaOcen;
    }

    @NonNull
    @Override
    public OcenyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View wiersz = layoutInflater.inflate(R.layout.wiersz_listy, null);

        return new OcenyViewHolder(wiersz);
    }

    @Override
    public void onBindViewHolder(@NonNull OcenyViewHolder holder, int position) {
        //Wywolywana zawsze gdy ma być wyświetlony nowy wiersz

        //Get the correct grade from the data source
        GradeModel grade = gradeList.get(position);

        //Connect radio buttons group with a list row
        holder.mRadioGroup.setTag(position);

        //set name of the subject
        holder.mNameVH.setText(grade.getSubjectName());

        //set radio button depends on grade value
        switch(gradeList.get(position).getGrade()){
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
        return gradeList.size();
    }



    public class OcenyViewHolder extends RecyclerView.ViewHolder implements RadioGroup.OnCheckedChangeListener{

        TextView mNameVH;
        RadioGroup mRadioGroup;
        RadioButton[] mRadioButtons = new RadioButton[4];


        public OcenyViewHolder(@NonNull View itemView) {
            super(itemView);

            //read references to the row elements
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

            //get position of radio button
            int position = (Integer) group.getTag();

            //select radio button depending on Tag value
        switch(checkedId){
            case R.id.radioButton4:
                //set grade value to the ArrayList element
                gradeList.get(position).setGrade(2);

                break;
            case R.id.radioButton3:
                gradeList.get(position).setGrade(3);

                break;
            case R.id.radioButton2:
                gradeList.get(position).setGrade(4);

                break;
            case R.id.radioButton:
                gradeList.get(position).setGrade(5);

                break;
        }
        }
    }
}
