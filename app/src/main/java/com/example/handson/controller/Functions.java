package com.example.handson.controller;


import android.util.Log;

import com.example.handson.model.Paciente;

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

    private static boolean isFebre(Paciente paciente){
        return (paciente.getBodyTemp() > 37);
    }
}
