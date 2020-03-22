package com.example.handson.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;

import com.example.handson.R;
import com.example.handson.connection.DatabaseOpenHelper;
import com.example.handson.model.Paciente;

import java.util.ArrayList;
import java.util.List;

import static com.example.handson.util.Constants.CODE_INSERT_ERROR_UNIQUE;

public class PacienteDAO {
    private DatabaseOpenHelper databaseOpenHelper;
    private SQLiteDatabase database;

    public PacienteDAO(Context context){
        this.databaseOpenHelper = new DatabaseOpenHelper(context, 2);
        database = databaseOpenHelper.getWritableDatabase();
    }

    public long insert(Paciente paciente){
        try{
            ContentValues values = new ContentValues();
            values.put("cpf", paciente.getCpf());
            values.put("name", paciente.getName());
            values.put("age", paciente.getAge());
            values.put("body_temp", paciente.getBodyTemp());
            values.put("cough_days", paciente.getCoughDays());
            values.put("headache_days", paciente.getHeadacheDays());
            StringBuilder builder = new StringBuilder();
            for(int week : paciente.getCountryVisit()){
                builder.append(week + ",");
            }
            values.put("country_visit", builder.toString());
            values.put("diagnosis", paciente.getDiagnosis());

            long d = database.insert("pacientes", null, values); //Se retornar -1 = fail
            return d;
        } catch (Exception e){
            return CODE_INSERT_ERROR_UNIQUE;
        }
    }

    public List<Paciente> readAll(){
        List<Paciente> encounters = new ArrayList<>();

        Cursor cursor = database.query("pacientes", new String[]{"cpf", "name", "age", "body_temp", "cough_days", "headache_days", "country_visit", "diagnosis"},
                null, null, null, null, null);

        while(cursor.moveToNext()){
            Paciente p = new Paciente();
            p.setCpf(cursor.getString(0));
            p.setName(cursor.getString(1));
            p.setAge(cursor.getInt(2));
            p.setBodyTemp(cursor.getInt(3));
            p.setCoughDays(cursor.getInt(4));
            p.setHeadacheDays(cursor.getInt(5));

            String[] visits = cursor.getString(6).split(",");
            for(int i = 0; i < visits.length; i++){
                p.addCountryVisit(Integer.parseInt(visits[i]));
            }

            p.setDiagnosis(cursor.getInt(7));
            encounters.add(p);
        }
        return encounters;
    }

    public int delete(Paciente p){
        return database.delete("pacientes", "cpf = ?", new String[]{p.getCpf()} );
    }
}
