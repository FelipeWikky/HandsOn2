package com.example.handson.controller;


import android.util.Log;

import com.example.handson.model.Paciente;

import java.util.ArrayList;
import java.util.List;

import static com.example.handson.util.Constants.CODE_INTERNADO;
import static com.example.handson.util.Constants.CODE_LIBERADO;
import static com.example.handson.util.Constants.CODE_QUARENTENA;

public class Functions {

    public static int verifyDiagnosis(Paciente paciente){
        int visits = 0;
        for(int week : paciente.getCountryVisit() ){
            if ( (week > 0) && (week <= 6) ) {
                visits++;
            }
        }

        //Visitou algum dos países da lista há pelo menos 6 semanas?
        if (visits > 0) {
            if ( paciente.getCoughDays() > 5 && paciente.getHeadacheDays() > 5 ) { //Tosse e Dor de cabeça a mais de 5 dias?
                if (isFebre(paciente)){
                    return CODE_INTERNADO;
                }
            }
            return CODE_QUARENTENA;
        }
        if ( paciente.getAge() > 60 || paciente.getAge() < 10 ) { //Tem menos de 10 anos e mais de 60?
            if ( isFebre(paciente) || paciente.getHeadacheDays() > 3 || paciente.getCoughDays() > 5) {
                return CODE_QUARENTENA;
            }
        } else {
            if ( isFebre(paciente) && paciente.getHeadacheDays() > 5 && paciente.getCoughDays() > 5 ) {
                return CODE_QUARENTENA;
            }
        }
        return CODE_LIBERADO;
    }

    public static boolean isFebre(Paciente paciente){
        return (paciente.getBodyTemp() > 37);
    }

    public static ArrayList<Paciente> filterPatients(int codeStatus, List<Paciente> pacientes){
        ArrayList<Paciente> pacientesFilter = new ArrayList<>();
        switch (codeStatus) {
            case CODE_LIBERADO:
                for(Paciente p : pacientes ){
                    if(p.getDiagnosis() == CODE_LIBERADO) {
                        pacientesFilter.add(p);
                    }
                }
                break;
            case CODE_QUARENTENA:
                for(Paciente p : pacientes ){
                    if(p.getDiagnosis() == CODE_QUARENTENA) {
                        pacientesFilter.add(p);
                    }
                }
                break;
            case CODE_INTERNADO:
                for(Paciente p : pacientes ){
                    if(p.getDiagnosis() == CODE_INTERNADO) {
                        pacientesFilter.add(p);
                    }
                }
                break;
        }
        return pacientesFilter;
    }

}
