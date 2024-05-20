package com.example.krlukashenkov;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CreatePass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pass);

        DatabaseHelperPer dbHelper = new DatabaseHelperPer(this);
        List<Person> contacts = dbHelper.getAllContacts();

        ActionBar actionBar = getSupportActionBar();
        //При нажатии на значок, откроется боковая панель
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true); // Показать кнопку назад
            actionBar.setTitle("Новый пароль"); // Установить заголовок
        }

        TextView textView1 = findViewById(R.id.textView61);
        Button regButtom = findViewById(R.id.button61);
        EditText passInput = findViewById(R.id.editTextText61);
        EditText passInput2 = findViewById(R.id.editTextTextPassword41);
        EditText passInput3 = findViewById(R.id.editTextTextPassword51);
        Bundle arguments = getIntent().getExtras();
        if (arguments!=null) {
            String name = arguments.get("name3").toString();
            textView1.setText("Введите текущий пароль " + name);

        }

        regButtom.setOnClickListener(v -> {

            String oldPass = passInput.getText().toString();
            String newPass = passInput2.getText().toString();
            String newPass1 = passInput3.getText().toString();

            if (dbHelper.updateContact(oldPass, newPass) && newPass.equals(newPass1)) {
                Toast.makeText(this, "Пароль изменён успешно!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, Menu1.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Неправильный пароль",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public boolean onSupportNavigateUp() {
        Intent intent2 = new Intent(this, Menu1.class);
        startActivity(intent2);
        finish();
        return true;
    }
}