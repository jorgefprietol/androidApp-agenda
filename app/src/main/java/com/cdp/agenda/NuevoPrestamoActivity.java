package com.cdp.agenda;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.cdp.agenda.db.DbPrestamos;

public class NuevoPrestamoActivity extends AppCompatActivity {

    EditText txtMonto;
    EditText txtCantCuota;
    EditText txtPorcentaje;

    EditText txtCliente;
    Button btnGuarda;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_prestamo);

        txtMonto = findViewById(R.id.txtMonto);
        txtCantCuota = findViewById(R.id.txtCantCuota);
        txtPorcentaje = findViewById(R.id.txtPorcentaje);
        txtCliente = findViewById(R.id.txtCliente);
        btnGuarda = findViewById(R.id.btnGuarda);

        btnGuarda.setOnClickListener(view -> {

            if(!txtMonto.getText().toString().equals("") && !txtCantCuota.getText().toString().equals("")) {

                try {
                    DbPrestamos dbPrestamos = new DbPrestamos(NuevoPrestamoActivity.this);
                    long id = dbPrestamos.insertarPrestamo(txtCliente.getText().toString(), txtMonto.getText().toString(), txtCantCuota.getText().toString(), txtPorcentaje.getText().toString());

                    if (id > 0) {
                        Toast.makeText(NuevoPrestamoActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        limpiar();
                    } else {
                        Toast.makeText(NuevoPrestamoActivity.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception ex) {
                    ex.toString();
                }


            } else {
                Toast.makeText(NuevoPrestamoActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void limpiar() {
        txtMonto.setText("");
        txtCantCuota.setText("");
        txtPorcentaje.setText("");
    }
}