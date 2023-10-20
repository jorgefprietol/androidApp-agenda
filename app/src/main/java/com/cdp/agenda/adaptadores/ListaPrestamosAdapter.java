package com.cdp.agenda.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cdp.agenda.R;
import com.cdp.agenda.VerActivity;
import com.cdp.agenda.entidades.Prestamos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaPrestamosAdapter extends RecyclerView.Adapter<ListaPrestamosAdapter.PrestamoViewHolder> {

    ArrayList<Prestamos> listaPrestamos;
    ArrayList<Prestamos> listaOriginal;

    public ListaPrestamosAdapter(ArrayList<Prestamos> listaPrestamos) {
        this.listaPrestamos = listaPrestamos;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaPrestamos);
    }

    @NonNull
    @Override
    public PrestamoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_prestamo, null, false);
        return new PrestamoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PrestamoViewHolder holder, int position) {
        holder.viewMonto.setText(listaPrestamos.get(position).getMonto());
        holder.viewCantCuota.setText(listaPrestamos.get(position).getPorcentaje());
        holder.viewPorcentaje.setText((CharSequence) listaPrestamos.get(position).getFecha());
    }

    public void filtrado(final int txtBuscar) {
        int longitud = String.valueOf(txtBuscar).length();
        if (longitud == 0) {
            listaPrestamos.clear();
            listaPrestamos.addAll(listaOriginal);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                List<Prestamos> collecion = listaPrestamos.stream()
                        .filter(i -> i.getMonto() == txtBuscar)
                        .collect(Collectors.toList());
                listaPrestamos.clear();
                listaPrestamos.addAll(collecion);
            } else {
                for (Prestamos c : listaOriginal) {
                    if (c.getMonto() == txtBuscar) {
                        listaPrestamos.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaPrestamos.size();
    }

    public class PrestamoViewHolder extends RecyclerView.ViewHolder {

        TextView viewMonto, viewCantCuota, viewPorcentaje;
        public PrestamoViewHolder(@NonNull View itemView) {
            super(itemView);

            viewMonto = itemView.findViewById(R.id.viewMonto);
            viewCantCuota = itemView.findViewById(R.id.viewCantCuota);
            viewPorcentaje = itemView.findViewById(R.id.viewPorcentaje);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra("ID", listaPrestamos.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
