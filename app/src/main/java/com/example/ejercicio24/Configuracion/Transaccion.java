package com.example.ejercicio24.Configuracion;

public class Transaccion {
    public static final String tablafirmas = "tbfirma";

    public static final String id = "id";
    public static final String image = "image";
    public static final String descripcion = "descripcion";

    public static final String CreateTableFirma= "CREATE TABLE tbfirma" +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "image BLOB, " +
            "descripcion TEXT)";

    public static final String DropTableFirmas= "DROP TABLE IF EXISTS tbfirma";

    public static final String NameDataBase = "BDFirmas";
}
