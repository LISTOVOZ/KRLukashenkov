package com.example.krlukashenkov;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PatientAdapter extends
        RecyclerView.Adapter<PatientAdapter.ViewHolder>{

    private final List<Patient> patients;

    public static class ViewHolder extends
            RecyclerView.ViewHolder {
        private final TextView nameTextView;
        public ViewHolder(View view) {
            super(view);
            nameTextView = view.findViewById(R.id.patient_name);
        }
        public void bind(Patient contact) {
            nameTextView.setText("Пациент №" + contact.getId() + "\nФИО: " + contact.getName()
                    + "\nВозраст: " + contact.getAge() + "\nПол: " + contact.getGender()
                    + "\nДиагноз: " + contact.getPlague() + "\nНомер телефона: " + contact.getPhone());
        }
    }

    public PatientAdapter(List<Patient> patients) {
        this.patients = patients;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int
            viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_patient, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        holder.bind(patients.get(position));
    }
    @Override
    public int getItemCount() {
        return patients.size();
    }
}
