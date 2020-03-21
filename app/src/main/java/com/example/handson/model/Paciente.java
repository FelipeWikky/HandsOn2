package com.example.handson.model;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class Paciente {
    private String cpf;
    private String name;
    private int age;
    private int bodyTemp; //Temperatura Corporal
    private int coughDays; //Dias em tosse
    private int headacheDays; //Dias com dor de cabeça
    private Map<String, Integer> countryVisit;
    private int diagnosis;

    public Paciente() {
        this.countryVisit = new HashMap<String, Integer>();
    }

    public Paciente(String cpf, String name, int age){
        this.countryVisit = new HashMap<String, Integer>();
        this.cpf = cpf;
        this.name = name;
        this.age = age;
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

    public Map<String, Integer> getCountryVisit() {
        return countryVisit;
    }

    public void addCountryVisit(String country, int days){
        getCountryVisit().put(country, days);
    }

    public int getDiagnosis(){
        return diagnosis;
    }

    public void setDiagnosis(int diagnosis){
        this.diagnosis = diagnosis;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Paciente: " + getName() + "\n");
        builder.append("Idade: " + getAge() + "\n");
        builder.append("Temp. Corporal: " + getBodyTemp() + "\n");
        builder.append("Dias de Tosse: " + getCoughDays() + "\n");
        builder.append("Dias com Dor de Cabeça: " + getHeadacheDays() + "\n");
        for (String key : getCountryVisit().keySet()) {
            if (getCountryVisit().get(key) > 0)
                builder.append("Visitou " + key + " há " + getCountryVisit().get(key) + " Semanas atrás.\n");
        }

        return builder.toString(); //super.toString();
    }
}
