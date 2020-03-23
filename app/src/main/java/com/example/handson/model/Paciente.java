package com.example.handson.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.example.handson.controller.Functions.isFebre;
import static com.example.handson.util.Constants.COUNTRYS;
import static com.example.handson.util.Constants.MAX_COUNTRYS;
import static com.example.handson.util.Constants.STATUS_DIAGNOSIS;

public class Paciente {

    private String cpf;
    private String name;
    private int age;
    private int bodyTemp; //Temperatura Corporal
    private int coughDays; //Dias com tosse
    private int headacheDays; //Dias com dor de cabeça
    private List<Integer> countryVisit;
    private int diagnosis;

    public Paciente() {
        this.countryVisit = new ArrayList<>();
    }

    public Paciente(String name){
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getBodyTemp() {
        return bodyTemp;
    }

    public void setBodyTemp(int bodyTemp) {
        this.bodyTemp = bodyTemp;
    }

    public int getCoughDays() {
        return coughDays;
    }

    public void setCoughDays(int coughDays) {
        this.coughDays = coughDays;
    }

    public int getHeadacheDays() {
        return headacheDays;
    }

    public void setHeadacheDays(int headacheDays) {
        this.headacheDays = headacheDays;
    }

    public List<Integer> getCountryVisit(){
        return this.countryVisit;
    }

    public void addCountryVisit(int week){
        if (getCountryVisit().size() <= MAX_COUNTRYS) {
            this.countryVisit.add(week);
        }

    }

    public int getDiagnosis(){
        return diagnosis;
    }

    public void setDiagnosis(int diagnosis){
        this.diagnosis = diagnosis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Paciente)) return false;
        Paciente paciente = (Paciente) o;
        return cpf.equals(paciente.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CPF: " + getCpf() + "\n");
        builder.append("Idade: " + getAge() + " Anos.\n");
        builder.append("Temperatura Corporal: " + getBodyTemp() + "ºC " + (isFebre(this) ? " - Febre\n": "\n") );
        builder.append("Dias com Tosse: " + getCoughDays() + " dias.\n");
        builder.append("Dias com Dor de Cabeça: " + getHeadacheDays() + " dias.\n");
        int val = 0;
        for (int visit : getCountryVisit()) {
            if (visit > 0) {
                builder.append("Visitou " + COUNTRYS[val] + " há " + visit + " Semanas.\n");
            }
            val++;
        }
        builder.append("Diagnóstico: " + STATUS_DIAGNOSIS[getDiagnosis()]);

        return builder.toString();
    }
}
