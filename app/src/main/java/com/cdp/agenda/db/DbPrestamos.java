package com.cdp.agenda.db;

import static android.os.Build.VERSION.*;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.os.Build.VERSION_CODES;

import androidx.annotation.Nullable;

import com.cdp.agenda.entidades.Prestamos;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DbPrestamos extends DbHelper {

    Context context;
    SimpleDateFormat fechaEspanol = null;

    public DbPrestamos(@Nullable Context context) {
        super(context);
        this.context = context;
        if (SDK_INT >= VERSION_CODES.N) {
            this.fechaEspanol = new SimpleDateFormat("dd MMM yyyy", Locale.forLanguageTag("es_ES"));
        }
    }

    public long insertarPrestamo(String id_contacto, String monto, String cantidad_cuota, String porcentaje) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("id_contacto", id_contacto);
            values.put("monto", monto);
            values.put("cantidad_cuota", cantidad_cuota);
            values.put("porcentaje", porcentaje);
            if (SDK_INT >= VERSION_CODES.N) {
                values.put("fecha", fechaEspanol.format(new Date()));
            }else{
                values.put("fecha", "01/01/1983");
            }

            id = db.insert(TABLE_PRESTAMOS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public ArrayList<Prestamos> mostrarPrestamos() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Prestamos> listaPrestamos = new ArrayList<>();
        Prestamos prestamo;
        Cursor cursorPrestamos;

        cursorPrestamos = db.rawQuery("SELECT * FROM " + TABLE_PRESTAMOS + " ORDER BY monto ASC", null);

        if (cursorPrestamos.moveToFirst()) {
            do {
                prestamo = new Prestamos();
                prestamo.setId(cursorPrestamos.getInt(0));
                prestamo.setId_contacto(cursorPrestamos.getInt(1));
                prestamo.setMonto(cursorPrestamos.getInt(2));
                prestamo.setCantidad_cuota(cursorPrestamos.getInt(3));
                prestamo.setFecha(cursorPrestamos.getString(4));
                prestamo.setPorcentaje(cursorPrestamos.getInt(5));
                listaPrestamos.add(prestamo);
            } while (cursorPrestamos.moveToNext());
        }

        cursorPrestamos.close();

        return listaPrestamos;
    }

    public Prestamos verPrestamos(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Prestamos prestamo = null;
        Cursor cursorPrestamos;

        cursorPrestamos = db.rawQuery("SELECT * FROM " + TABLE_PRESTAMOS + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorPrestamos.moveToFirst()) {
            prestamo = new Prestamos();
            prestamo.setId(cursorPrestamos.getInt(0));
            prestamo.setId_contacto(cursorPrestamos.getInt(1));
            prestamo.setMonto(cursorPrestamos.getInt(2));
            prestamo.setCantidad_cuota(cursorPrestamos.getInt(3));
            prestamo.setFecha(cursorPrestamos.getString(4));
            prestamo.setPorcentaje(cursorPrestamos.getInt(5));
        }

        cursorPrestamos.close();

        return prestamo;
    }

    @SuppressLint("NewApi")
    public boolean editarPrestamo(int id, String id_contacto, String monto, String cantidad_cuota, String porcentaje) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_PRESTAMOS +
                    " SET id_contacto = '" + id_contacto +
                    "', monto = '" + monto +
                    "', cantidad_cuota = '" + cantidad_cuota +
                    "', porcentaje = '" + porcentaje +
                    "', fecha = '" + fechaEspanol.format(new Date()) +
                    "' WHERE id='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarPrestamo(int id) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_PRESTAMOS + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
}
