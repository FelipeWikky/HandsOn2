package com.example.handson.util;

public class Constants {
    public final static String GENERAl_VERSION = "1.0.0";

    public final static int CODE_OK = 200;
    public final static int CODE_NOT_FOUND = 404;

    public final static int CODE_FIELD_OK = 10010;
    public final static int CODE_FIELD_EMPTY = 10020;
    public final static int CODE_FIELD_NOT_FORMATTED = 10030;

    /*Database*/
    public final static int CODE_INSERT_OK = 20010;
    public final static int CODE_INSERT_ERROR = -1;
    public final static int CODE_INSERT_ERROR_UNIQUE = 20020; //duplicação de chave primária

    /*Status Diagnosis*/
    public final static int CODE_NULL = -10;
    public final static int CODE_LIBERADO = 0;
    public final static int CODE_QUARENTENA = 1;
    public final static int CODE_INTERNADO = 2;

    public final static int MAX_COUNTRYS = 5;
    public static String[] COUNTRYS = {"Italia", "China", "Indonesia", "Portugal", "Eua"};
    public static String[] STATUS_DIAGNOSIS = {"LIBERADO", "QUARENTENA", "INTERNADO"};
}
