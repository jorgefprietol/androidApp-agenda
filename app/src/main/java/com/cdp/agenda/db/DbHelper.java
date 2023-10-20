package com.cdp.agenda.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NOMBRE = "agenda.db";
    public static final String TABLE_CONTACTOS = "t_contactos";
    public static final String TABLE_PRESTAMOS = "t_prestamos";
    public static final String TABLE_CUOTAS = "t_cuotas";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CONTACTOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "telefono TEXT," +
                "correo_electronico TEXT)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PRESTAMOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_contacto TEXT NOT NULL," +
                "monto INTEGER NOT NULL," +
                "cantidad_cuota INTEGER NOT NULL," +
                "fecha DATE NOT NULL," +
                "porcentaje INTEGER NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CUOTAS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_prestamo INTEGER NOT NULL," +
                "cuota INTEGER NOT NULL," +
                "fecha DATE NOT NULL," +
                "interes INTEGER NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_CONTACTOS);
        onCreate(sqLiteDatabase);

    }
}
