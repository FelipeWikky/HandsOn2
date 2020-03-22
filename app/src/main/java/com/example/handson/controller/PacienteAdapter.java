package com.example.handson.controller;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.handson.R;
import com.example.handson.model.Paciente;

import java.util.ArrayList;
import java.util.List;

import static com.example.handson.util.Constants.STATUS_DIAGNOSIS;

public class PacienteAdapter extends ArrayAdapter<Paciente> {
    private Context context;
    private List<Paciente> pacientes;
    private View view;

    public PacienteAdapter(@NonNull Context context, List<Paciente> object) {
        super(context, 0, object);
        this.context = context;
        this.pacientes = object;
    }

    @Override
    public int getCount() {
        return pacientes.size();
    }

    @Nullable
    @Override
    public Paciente getItem(int position) {
        return pacientes.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = ((Activity) context).getLayoutInflater().inflate(R.layout.adapter_list_prontuario, parent, false);
        if (pacientes != null) {
            TextView name = (TextView) view.findViewById(R.id.txtAdapterName);
            TextView cpfAge = (TextView) view.findViewById(R.id.txtAdapterCpf);
            TextView diagnosis = (TextView) view.findViewById(R.id.txtAdapterDiagnosis);
            Paciente p = pacientes.get(position);
            name.setText(p.getName());
            cpfAge.setText("CPF: " + p.getCpf() + "\t\t Idade: " + p.getAge() + " Anos.");
            diagnosis.setText("Diagnóstico: " + STATUS_DIAGNOSIS[p.getDiagnosis()]);
        }

        return view;
    }
}
