package com.example.krlukashenkov;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.io.File;

public class Menu1 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    public DrawerLayout drawer;
    public ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu1);

        ActionBar actionBar = getSupportActionBar();
        //При нажатии на значок, откроется боковая панель
        if (actionBar != null) {
            actionBar.setTitle("Меню"); // Установить заголовок
        }

        drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                Menu1.this, drawer, R.string.nav_open,
                R.string.nav_close);
        if (drawer != null) {
            drawer.addDrawerListener(toggle);
        }
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.bringToFront();
// to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //получение данных о регистрации
        TextView textView = findViewById(R.id.textView28);
        Bundle arguments = getIntent().getExtras();
        if (arguments!=null) {
            String name = arguments.get("name2").toString();
            String phone = arguments.get("phone2").toString();
            String password = arguments.get("password2").toString();
            String login = arguments.get("login2").toString();
            int age = arguments.getInt("age2");
            textView.setText(" Добро пожаловать " + name + "\n Мобильный номер: " +
                    phone + "\n Логин: " + login + "\n Возраст: " + age);
        }

        Button myButton1 = findViewById(R.id.button228);
        Intent intent = new Intent(this, CreatePass.class);
        myButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Прогаммно нажата", Toast.LENGTH_LONG).show();
                String name = arguments.get("name2").toString();
                intent.putExtra("name3", name);
                startActivity(intent);
            }
        }
        );

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent1 = new Intent(this, MainActivity.class);
        int id = item.getItemId();
        AlertDialog.Builder builder = new AlertDialog.Builder(Menu1.this);
        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Обработка подтверждения
                Toast.makeText(Menu1.this, "До свидания", Toast.LENGTH_LONG).show();
                startActivity(intent1);
                finish();
            }
        });
        // Установка кнопки "Отмена" и ее обработчика
        builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Обработка отмены действия
                dialog.dismiss();
            }
        });
        //Button alertD = findViewById(R.id.nav_russ);
        //прописываем команды
        if (id == R.id.nav_russ) {
            builder.setTitle("Подтверждение");
            builder.setMessage("Вы уверены, что хотите покинуть нас?");
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.create().show();
        }
        else if (id == R.id.nav_notifications) {
            Intent intent2 = new Intent(this, PatientMenu.class);
            startActivity(intent2);
            finish();
        }
        else if (id == R.id.nav_setting)
        {
            Intent intent3 = new Intent(this, NextTime1.class);
            startActivity(intent3);
        }
            /*Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent); */
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}