package com.example.krlukashenkov;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Получение id всех нужных компонентов
        EditText logInput = findViewById(R.id.editTextText);
        EditText passInput = findViewById(R.id.editTextTextPassword);
        Button exitButtom = findViewById(R.id.button);

        DatabaseHelperPer dbHelper = new DatabaseHelperPer(this);
        List<Person> contacts = dbHelper.getAllContacts();

        //получение данных о регистрации
        Bundle arguments = getIntent().getExtras();
        if (arguments!=null) {
            String name = arguments.get("name").toString();
            String phone = arguments.get("phone").toString();
            String password = arguments.get("password").toString();
            String login = arguments.get("login").toString();
            int age = arguments.getInt("age");
            //Сохранение данных о новых пользователях
            if (dbHelper.addContact(new Person(0, age, name, phone, login, password))) //int id, int age, String name, String phone, String login, String password
            {
                contacts.add(new Person(0, age, name, phone, login, password));
                //adapter.notifyItemInserted(contacts.size() - 1);
                Toast.makeText(this, "Регистрация успешна", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Ошибка в регистрации",
                        Toast.LENGTH_LONG).show();
            }
        }
            //EditText loginText = findViewById(R.id.editTextText);
            //loginText.setText(login);

            //Ищем персону по логину и паролю
            exitButtom.setOnClickListener(v -> {
                String login2 = logInput.getText().toString();
                String password2 = passInput.getText().toString();
                Log.i("Error","Error here");
                Person foundPer = dbHelper.findContact(login2, password2);
                Log.i("Error","Error here2");
                if (foundPer != null) {
                    logInput.setText(foundPer.getLogin());
                    passInput.setText(foundPer.getPassword());

                    String password = foundPer.getPassword();
                    String name = foundPer.getName();
                    int age = foundPer.getAge();
                    String phone = foundPer.getPhone();
                    String login = foundPer.getLogin();

                    Toast.makeText(this, "Добро пожаловать", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, Menu1.class);
                    intent.putExtra("name2", name);
                    intent.putExtra("age2", age);
                    intent.putExtra("password2", password);
                    intent.putExtra("phone2", phone);
                    intent.putExtra("login2", login);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "В доступе отказано",
                            Toast.LENGTH_SHORT).show();
                }
            });
    }

    public void but2(View view) {
        Intent intent = new Intent(this, RegPerson.class);
        startActivity(intent);
    }

}