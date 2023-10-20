package com.cdp.agenda;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.cdp.agenda.db.DbPrestamos;
import com.cdp.agenda.entidades.Prestamos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerPrestamoActivity extends AppCompatActivity {

    EditText txtMonto, txtCantCuota, txtPorcentaje;
    Button btnGuarda;
    FloatingActionButton fabEditar, fabEliminar;

    Prestamos prestamo;
    int id = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle onRestoreInstanceState) {
        super.onCreate(onRestoreInstanceState);
        setContentView(R.layout.activity_ver);

        txtMonto = findViewById(R.id.txtMonto);
        txtCantCuota = findViewById(R.id.txtCantCuota);
        txtPorcentaje = findViewById(R.id.txtPorcentaje);
        fabEditar = findViewById(R.id.fabEditar);
        fabEliminar = findViewById(R.id.fabEliminar);
        btnGuarda = findViewById(R.id.btnGuarda);
        btnGuarda.setVisibility(View.INVISIBLE);

        if(onRestoreInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = 0;
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) onRestoreInstanceState.getSerializable("ID");
        }

        final DbPrestamos dbPrestamos = new DbPrestamos(VerPrestamoActivity.this);
        prestamo = dbPrestamos.verPrestamos(id);

        if(prestamo != null){
            txtMonto.setText(prestamo.getMonto());
            txtCantCuota.setText(prestamo.getCantidad_cuota());
            txtPorcentaje.setText(prestamo.getPorcentaje());
            txtMonto.setInputType(InputType.TYPE_NULL);
            txtCantCuota.setInputType(InputType.TYPE_NULL);
            txtPorcentaje.setInputType(InputType.TYPE_NULL);
        }

        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerPrestamoActivity.this, EditarPrestamoActivity.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerPrestamoActivity.this);
                builder.setMessage("¿Desea eliminar este prestamo?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if(dbPrestamos.eliminarPrestamo(id)){
                                    lista();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });
    }

    private void lista(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}