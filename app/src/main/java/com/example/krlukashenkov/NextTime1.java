package com.example.krlukashenkov;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.List;

public class NextTime1 extends AppCompatActivity {

    private int hour;
    private int minute;
    private int year;
    private int month;
    private int day;

    protected static final String CHANNEL_ID =
            "example_channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_time1);
        createNotificationChannel();

        ActionBar actionBar = getSupportActionBar();
        //При нажатии на значок, откроется боковая панель
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true); // Показать кнопку назад
            actionBar.setTitle("Запись"); // Установить заголовок
        }

        DatabaseHelperPer dbHelper1 = new DatabaseHelperPer(this);
        List<Patient> patients = dbHelper1.getAllContacts1();
        PatientAdapter adapter = new PatientAdapter(patients);

        EditText nameInput = findViewById(R.id.editTextText12);
        EditText phoneInput = findViewById(R.id.editTextText13);
        Button deleteButton = findViewById(R.id.button23);
        Button myButton1 = findViewById(R.id.button21);
        Button myButton2 = findViewById(R.id.button22);
        TextView textView = findViewById(R.id.textView21);
        TextView textView2 = findViewById(R.id.textView22);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                // Обработка выбранного времени
                // Пример: установка времени в TextView
                textView.setText(hourOfDay + ":" +minute);
            }

        }, hour, minute, true); // Использование 24- часового формата

        myButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });

        //календарь
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month,
                                  int dayOfMonth) {
                // Обработка выбора даты
                textView2.setText(dayOfMonth + "." + month + "." + year);
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this, dateSetListener,
                year, // текущий год
                month, // текущий месяц
                day); // текущий день

        myButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Создание и отображение DatePickerDialog
                datePickerDialog.show();
            }
        });

        deleteButton.setOnClickListener(v -> {
            String phone = phoneInput.getText().toString();
            String name = nameInput.getText().toString();
            String hour1 = textView.getText().toString();
            String year1 = textView2.getText().toString();
            if (dbHelper1.deleteContact1(name, phone)) {
                int position = -1;
                for (int i = 0; i < patients.size(); i++) {
                    if (patients.get(i).getPhone().equals(phone))
                    {
                        position = i;
                        patients.remove(i);
                        break;
                    }
                }
                if (position != -1) {
                    Toast.makeText(this, "Выписка сделана",
                            Toast.LENGTH_SHORT).show();
                    adapter.notifyItemRemoved(position);

                    NotificationCompat.Builder builder = new
                            NotificationCompat.Builder(NextTime1.this, CHANNEL_ID)

                            .setSmallIcon(R.drawable.password_action)
                            .setContentTitle("Операция выполнена на " + hour1)
                            .setContentText("Пациент " + name + " будет выписан в " + year1)
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                    NotificationManager notificationManager =
                            getSystemService(NotificationManager.class);
                    notificationManager.notify(1,
                            builder.build());
                } else {
                    Toast.makeText(this, "Пациент не найден",
                            Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Ошибка при записи",
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Сохраняем значение строковой переменной
        TextView textView3 = findViewById(R.id.textView21);
        TextView textView5 = findViewById(R.id.textView22);
        String fileContents1 = textView3.getText().toString();
        String fileContents2 = textView5.getText().toString();
        outState.putString("KEY_STATE", fileContents1);
        outState.putString("KEY_STATE1", fileContents2);

    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Восстанавливаем сохраненное состояние
        String state = savedInstanceState.getString("KEY_STATE");
        String state1 = savedInstanceState.getString("KEY_STATE1");
        // Используем сохраненное значение для восстановления состояния UI или других компонентов
        TextView textView4 = findViewById(R.id.textView21);
        TextView textView6 = findViewById(R.id.textView22);
        textView4.setText(state);
        textView6.setText(state1);

    }

    // Код для обычного уведомления
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            CharSequence name = "Example Channel";
            String description = "Channel for example notifications";
            int importance =
                    NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new
                    NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);

            notificationManager.createNotificationChannel(channel);
        }
    }
}