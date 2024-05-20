package com.example.krlukashenkov;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PatientReg extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_reg);

        // находим представление списка
        ListView usersListView = (ListView) findViewById(R.id.
                my_list_view);
        //Получаем ресурсы
        String[] userNames = getResources().getStringArray(R.array.userPlag);
        // создаем адаптер
        ArrayAdapter<String> usersAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, userNames);
        // устанавливаем адаптер для списка
        usersListView.setAdapter(usersAdapter);

        TextView selectedName = findViewById(R.id.selectedName);
        //добавляем для списка слушатель
        usersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long id) {
                // по позиции получаем выбранный элемент
                String selectedItem = userNames[position];
                // установка текста элемента TextView
                selectedName.setText(selectedItem);
            }
        });

        ActionBar actionBar = getSupportActionBar();
        //При нажатии на значок, откроется боковая панель
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true); // Показать кнопку назад
            actionBar.setTitle("Регистрация пациента"); // Установить заголовок
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        Intent intent2 = new Intent(this, PatientMenu.class);
        startActivity(intent2);
        finish();
        return true;
    }

    public void back1(View view){
        Intent intent = new Intent(this, PatientMenu.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void exit1(View view){
        TextView plagueText = findViewById(R.id.selectedName);
        EditText nameText = findViewById(R.id.editTextText7);
        EditText ageText = findViewById(R.id.editTextText8);
        EditText phoneText = findViewById(R.id.editTextText9);
        EditText genderText = findViewById(R.id.editTextText10);

        String name = nameText.getText().toString();
        String gender = genderText.getText().toString();
        String plague = plagueText.getText().toString();
        int age = Integer.parseInt(ageText.getText().toString());
        String phone = phoneText.getText().toString();

        Intent intent = new Intent(this, PatientMenu.class);
        intent.putExtra("name1", name);
        intent.putExtra("age1", age);
        intent.putExtra("gender1", gender);
        intent.putExtra("phone1", phone);
        intent.putExtra("plague1", plague);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}