package com.example.krlukashenkov;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class PatientSett extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_sett);



        Button regButtom = findViewById(R.id.button62);
        EditText nameInput = findViewById(R.id.editTextText62);
        EditText ageInput2 = findViewById(R.id.editTextTextPassword42);
        EditText phoneInput3 = findViewById(R.id.editTextTextPassword52);


        RecyclerView patientsList =
                findViewById(R.id.contacts_list);
        DatabaseHelperPer dbHelper1 = new DatabaseHelperPer(this);
        List<Patient> patients = dbHelper1.getAllContacts1();
        PatientAdapter adapter = new PatientAdapter(patients);
        patientsList.setLayoutManager(new
                LinearLayoutManager(this));
        patientsList.setAdapter(adapter);

        ActionBar actionBar = getSupportActionBar();
        //При нажатии на значок, откроется боковая панель
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true); // Показать кнопку назад
            actionBar.setTitle("Новый номер"); // Установить заголовок
        }

        regButtom.setOnClickListener(v -> {
            String oldName = nameInput.getText().toString();
            String oldAge = ageInput2.getText().toString();
            String newPhone = phoneInput3.getText().toString();
            String newName = nameInput.getText().toString();
            String newAge = ageInput2.getText().toString();

            if (dbHelper1.updateContact1(oldName, oldAge, newPhone, newName, newAge)) {
                Toast.makeText(this, "Номер успешно обновлён!", Toast.LENGTH_SHORT).show();
                // Обновляем список и адаптер
                refreshContactsList(dbHelper1, patients, adapter,
                        patientsList);
                Intent intent = new Intent(this, PatientMenu.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Ошибка!",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    // Метод для обновления списка контактов после изменения в базе данных
    private void refreshContactsList(DatabaseHelperPer dbHelper,
                                     List<Patient> contacts, PatientAdapter adapter, RecyclerView
                                             contactsList) {
        contacts = dbHelper.getAllContacts1(); // Загружаем обновленный список
        adapter = new PatientAdapter(contacts);
        contactsList.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent2 = new Intent(this, Menu1.class);
        startActivity(intent2);
        finish();
        return true;
    }
}