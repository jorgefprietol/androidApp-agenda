package com.cdp.agenda;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.cdp.agenda.adaptadores.ListaPrestamosAdapter;
import com.cdp.agenda.db.DbPrestamos;
import com.cdp.agenda.entidades.Prestamos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class MainPrestamoActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    public SearchView txtBuscar;
    RecyclerView listaPrestamos;
    ArrayList<Prestamos> listaArrayPrestamos;
    FloatingActionButton fabNuevo;
    ListaPrestamosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_prestamo);
        txtBuscar = findViewById(R.id.txtBuscar);
        listaPrestamos = findViewById(R.id.listaPrestamos);
        fabNuevo = findViewById(R.id.favNuevo);
        listaPrestamos.setLayoutManager(new LinearLayoutManager(this));

        DbPrestamos dbPrestamos = new DbPrestamos(MainPrestamoActivity.this);

        listaArrayPrestamos = new ArrayList<>();

        adapter = new ListaPrestamosAdapter(dbPrestamos.mostrarPrestamos());
        listaPrestamos.setAdapter(adapter);

        fabNuevo.setOnClickListener(view -> nuevoRegistro());

        txtBuscar.setOnQueryTextListener(this);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuNuevo:nuevoRegistro();
                return true;
            case R.id.menuNuevoPrestamo:nuevoPrestamo();
                return true;
            case R.id.menuVerPrestamo:verPrestamo();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void nuevoRegistro(){
        Intent intent = new Intent(this, NuevoActivity.class);
        startActivity(intent);
    }

    public void nuevoPrestamo(){
        Intent intent = new Intent(this, NuevoPrestamoActivity.class);
        startActivity(intent);
    }
    public void verPrestamo(){
        Intent intent = new Intent(this, VerPrestamoActivity.class);
        startActivity(intent);
    }
    public void editarPrestamo(){
        Intent intent = new Intent(this, EditarPrestamoActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.filtrado(Integer.parseInt(s));
        return false;
    }
}