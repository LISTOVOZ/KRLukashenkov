package com.example.krlukashenkov;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegPerson extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_person);


    }

    public void back(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void exit(View view){
        EditText passText = findViewById(R.id.editTextTextPassword2);
        EditText passText1 = findViewById(R.id.editTextTextPassword3);
        String password = passText.getText().toString();
        String password1 = passText1.getText().toString();
        if (password.equals(password1)) {

            EditText nameText = findViewById(R.id.editTextText2);
            EditText ageText = findViewById(R.id.editTextText3);
            EditText phoneText = findViewById(R.id.editTextText4);
            EditText loginText = findViewById(R.id.editTextText5);

            String name = nameText.getText().toString();
            int age = Integer.parseInt(ageText.getText().toString());
            String phone = phoneText.getText().toString();
            String login = loginText.getText().toString();
            Intent intent = new Intent(this, MainActivity.class);
            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("name", name);
            intent.putExtra("age", age);
            intent.putExtra("password", password);
            intent.putExtra("phone", phone);
            intent.putExtra("login", login);
            startActivity(intent);
            finish();
        }
        else {
            Toast.makeText(this, "Неправильно введён пароль", Toast.LENGTH_LONG).show();
        }
    }
}