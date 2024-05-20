package com.example.krlukashenkov;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelperPer extends SQLiteOpenHelper {
    //Пропишем в отдельные переменные название БД и её столбов

    //Переменные персонала
    private static final String TABLE_NAME = "Persons";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_LOGIN = "login";

    // переменные пациентов
    private static final String TABLE_NAME1 = "Patients";
    private static final String COLUMN_ID1 = "id1";
    private static final String COLUMN_NAME1 = "name1";
    private static final String COLUMN_PHONE1 = "phone1";
    private static final String COLUMN_GENDER1 = "gender1";
    private static final String COLUMN_AGE1 = "age1";
    private static final String COLUMN_PLAGUE1 = "plague1";

    public DatabaseHelperPer(Context context) {
        super(context, "Persons.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_AGE + " TEXT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_PHONE + " TEXT, " +
                COLUMN_LOGIN + " TEXT, " +
                COLUMN_PASSWORD + " TEXT)";

        String createTable1 = "CREATE TABLE " + TABLE_NAME1 + " (" +
                COLUMN_ID1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_AGE1 + " TEXT, " +
                COLUMN_NAME1 + " TEXT, " +
                COLUMN_PHONE1 + " TEXT, " +
                COLUMN_GENDER1 + " TEXT, " +
                COLUMN_PLAGUE1 + " TEXT)";

        db.execSQL(createTable); //int id, int age, String name, String phone, String login, String password
        db.execSQL(createTable1);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        onCreate(db);
    }
    // Добавление нового контакта персонала
    public boolean addContact(Person contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_AGE, contact.getAge());
        cv.put(COLUMN_NAME, contact.getName());
        cv.put(COLUMN_PHONE, contact.getPhone());
        cv.put(COLUMN_LOGIN, contact.getLogin());
        cv.put(COLUMN_PASSWORD, contact.getPassword());
        long result = db.insert(TABLE_NAME, null, cv); //int id, int age, String name, String phone, String login, String password
        db.close();
        return result != -1;
    }

    // Добавление нового контакта пациента
    public boolean addContact1(Patient contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_AGE1, contact.getAge());
        cv.put(COLUMN_NAME1, contact.getName());
        cv.put(COLUMN_PHONE1, contact.getPhone());
        cv.put(COLUMN_GENDER1, contact.getGender());
        cv.put(COLUMN_PLAGUE1, contact.getPlague());
        long result = db.insert(TABLE_NAME1, null, cv); //int id, int age, String name, String phone, String login, String password
        db.close();
        return result != -1;
    }

    //удаление пациента
    public boolean deleteContact1(String name, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME1, COLUMN_PHONE1 + " =? AND+ " + COLUMN_NAME1  + " =?",
                new String[]{phone, name});
        db.close();
        return result > 0;
    }

    // Поиск контакта по номеру телефона
    //Проверка логина и пароля персонала
    public Person findContact(String login, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Log.i("Error","Error here");
        Cursor cursor = db.query(TABLE_NAME, new
                        String[]{COLUMN_ID, COLUMN_AGE, COLUMN_NAME, COLUMN_PHONE, COLUMN_LOGIN, COLUMN_PASSWORD},
                COLUMN_LOGIN + " =? AND+ " + COLUMN_PASSWORD + " =?", new String[]{login, password}, null,
                null, null);
        if (cursor != null && cursor.moveToFirst() ) {
            Person contact = new Person(cursor.getInt(0),
                    cursor.getInt(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4),
                    cursor.getString(5));
            cursor.close();
            db.close();
            return contact;
        }
        if (cursor != null ) {
            cursor.close();
        }
        db.close();
        return null;
    }

    // Получение всех контактов персонала
    public List<Person> getAllContacts() {
        List<Person> contactList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " +
                TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                Person contact = new Person(cursor.getInt(0),
                        cursor.getInt(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4),
                        cursor.getString(5));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return contactList;
    }

    //Получение всех данных пациентов
    public List<Patient> getAllContacts1() {
        List<Patient> patientList1 = new ArrayList<>();
        SQLiteDatabase db1 = this.getReadableDatabase();
        Cursor cursor1 = db1.rawQuery("SELECT * FROM " +
                TABLE_NAME1, null);
        if (cursor1.moveToFirst()) {
            do {
                Patient patient = new Patient(cursor1.getInt(0),
                        cursor1.getInt(1), cursor1.getString(2),
                        cursor1.getString(3), cursor1.getString(4),
                        cursor1.getString(5));
                patientList1.add(patient);
            } while (cursor1.moveToNext());
        }
        cursor1.close();
        db1.close();
        return patientList1;
    }

    // обновление пароля персонала
    public boolean updateContact(String oldPass, String newPass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PASSWORD, newPass);
        int result = db.update(TABLE_NAME, cv, COLUMN_PASSWORD + " = ?", new String[]{oldPass});
        db.close();
        return result > 0;
    }

    // обновление телефона пациента
    public boolean updateContact1(String oldName, String oldAge, String newPhone, String newName, String newAge) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME1, newName);
        cv.put(COLUMN_AGE1, newAge);
        cv.put(COLUMN_PHONE1, newPhone);
        int result = db.update(TABLE_NAME1, cv, COLUMN_NAME1 + " =? AND+ " + COLUMN_AGE1
                + " = ?", new String[]{oldName, oldAge});
        db.close();
        return result > 0;
    }
}
