package com.example.krlukashenkov;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class PatientMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_menu);

        ActionBar actionBar = getSupportActionBar();
        //При нажатии на значок, откроется боковая панель
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true); // Показать кнопку назад
            actionBar.setTitle("Список пациентов"); // Установить заголовок
        }

        //Button exitButtom = findViewById(R.id.button5);
        RecyclerView patientsList =
                findViewById(R.id.contacts_list);
        DatabaseHelperPer dbHelper1 = new DatabaseHelperPer(this);
        List<Patient> patients = dbHelper1.getAllContacts1();
        PatientAdapter adapter = new PatientAdapter(patients);
        patientsList.setLayoutManager(new
                LinearLayoutManager(this));
        patientsList.setAdapter(adapter);

        //получение данных о регистрации
        Bundle arguments = getIntent().getExtras();
        if (arguments!=null) {
            String name = arguments.get("name1").toString();
            String phone = arguments.get("phone1").toString();
            String gender = arguments.get("gender1").toString();
            String plague = arguments.get("plague1").toString();
            int age = arguments.getInt("age1");
            //Сохранение данных о новых пользователях
            if (dbHelper1.addContact1(new Patient(0, age, name, phone, gender, plague))) //int id, int age, String name, String phone, String login, String password
            {
                patients.add(new Patient(0, age, name, phone, gender, plague));
                adapter.notifyItemInserted(patients.size() - 1);
                Toast.makeText(this, "Регистрация пациента успешна", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Ошибка в регистрации",
                        Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent2 = new Intent(this, Menu1.class);
        startActivity(intent2);
        finish();
        return true;
    }
    public void onNext5(View view) {
        Intent intent = new Intent(this, PatientReg.class);
        startActivity(intent);
    }

    public void onNext6(View view) {
        Intent intent = new Intent(this, PatientSett.class);
        startActivity(intent);
    }
}