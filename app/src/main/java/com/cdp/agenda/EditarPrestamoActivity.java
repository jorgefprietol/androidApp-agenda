package com.cdp.agenda;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cdp.agenda.db.DbContactos;
import com.cdp.agenda.db.DbPrestamos;
import com.cdp.agenda.entidades.Contactos;
import com.cdp.agenda.entidades.Prestamos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarPrestamoActivity extends AppCompatActivity {

    EditText txtMonto;
    EditText txtCantCuota;
    EditText txtPorcentaje;
    EditText txtCliente;
    Button btnGuarda;
    FloatingActionButton fabEditar, fabEliminar;
    boolean correcto = false;
    Prestamos prestamo;
    int id = 0;

    @SuppressLint({"RestrictedApi", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtMonto = findViewById(R.id.txtMonto);
        txtCantCuota = findViewById(R.id.txtCantCuota);
        txtPorcentaje = findViewById(R.id.txtPorcentaje);
        txtCliente = findViewById(R.id.txtCliente);
        btnGuarda = findViewById(R.id.btnGuarda);
        fabEditar = findViewById(R.id.fabEditar);
        fabEditar.setVisibility(View.INVISIBLE);
        fabEliminar = findViewById(R.id.fabEliminar);
        fabEliminar.setVisibility(View.INVISIBLE);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final DbPrestamos dbPrestamos = new DbPrestamos(EditarPrestamoActivity.this);
        prestamo = dbPrestamos.verPrestamos(id);

        if (prestamo != null) {
            txtMonto.setText(prestamo.getMonto());
            txtCantCuota.setText(prestamo.getCantidad_cuota());
            txtPorcentaje.setText(prestamo.getPorcentaje());
            txtCliente.setText(prestamo.getId_contacto());
        }

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtMonto.getText().toString().equals("") && !txtCantCuota.getText().toString().equals("")) {
                    correcto = dbPrestamos.editarPrestamo(id, txtCliente.getText().toString(), txtMonto.getText().toString(), txtCantCuota.getText().toString(), txtPorcentaje.getText().toString());

                    if(correcto){
                        Toast.makeText(EditarPrestamoActivity.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else {
                        Toast.makeText(EditarPrestamoActivity.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(EditarPrestamoActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void verRegistro(){
        Intent intent = new Intent(this, VerActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}