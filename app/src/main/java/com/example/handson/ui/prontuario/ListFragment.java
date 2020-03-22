package com.example.handson.ui.prontuario;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.handson.R;
import com.example.handson.controller.PacienteAdapter;
import com.example.handson.dao.PacienteDAO;
import com.example.handson.model.Paciente;

import java.util.ArrayList;
import java.util.List;

import static com.example.handson.util.Constants.CODE_INTERNADO;
import static com.example.handson.util.Constants.CODE_LIBERADO;
import static com.example.handson.util.Constants.CODE_QUARENTENA;

public class ListFragment extends Fragment {
    private View root;
    private ListView lstProntuarios;
    private List<Paciente> pacientes;
    private List<Paciente> pacientesFilter;
    ArrayAdapter<Paciente> adapter;
    private PacienteDAO pacienteDAO;

    private Button btnLiberado, btnQuarentena, btnInternado;

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_list, container, false);
        setHasOptionsMenu(true);

        lstProntuarios = (ListView) root.findViewById(R.id.lstProntuarios);

        pacienteDAO = new PacienteDAO(getActivity());
        pacientes = pacienteDAO.readAll();
        pacientesFilter = pacienteDAO.readAll();

        //ArrayAdapter<Paciente> adapter = new ArrayAdapter<Paciente>(getActivity(), android.R.layout.simple_list_item_1, pacientes);
        adapter = new PacienteAdapter(getActivity(), pacientesFilter);
        lstProntuarios.setAdapter(adapter);
        registerForContextMenu(lstProntuarios);

        lstProntuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog alert = new AlertDialog.Builder(getActivity())
                        .setTitle("Prontuário")
                        .setMessage(pacientesFilter.get(i).toString())
                        .setPositiveButton("OK", null)
                        .create();
                alert.show();
            }
        });

        btnLiberado = root.findViewById(R.id.btnLiberado);
        setCommandButton(btnLiberado);
        btnQuarentena = root.findViewById(R.id.btnQuarentena);
        setCommandButton(btnQuarentena);
        btnInternado = root.findViewById(R.id.btnInternado);
        setCommandButton(btnInternado);

        return root;
    }

    private void setCommandButton(final Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btnLiberado:
                        getActivity().setTitle("Pacientes Liberados");
                        filterPacients(CODE_LIBERADO);
                        Toast.makeText(getContext(), "Listando os Pacientes que podem ser Liberados", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.btnQuarentena:
                        getActivity().setTitle("Pacientes em Quarentena");
                        filterPacients(CODE_QUARENTENA);
                        Toast.makeText(getContext(), "Listando os Pacientes que deverão ficar em Quarentena", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.btnInternado:
                        getActivity().setTitle("Pacientes Internados");
                        filterPacients(CODE_INTERNADO);
                        Toast.makeText(getContext(), "Listando os Pacientes que deverão se Internar", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.options_list_prontuarios, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_action_excluir:
                AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                final Paciente p = pacientes.get(menuInfo.position);
                AlertDialog alert = new AlertDialog.Builder(getActivity())
                        .setTitle("Confirmar Exclusão")
                        .setMessage("Deseja realmente Excluir este Prontuário selecionado?")
                        .setNegativeButton("Não", null)
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int code = pacienteDAO.delete(p);
                                pacientes.remove(p);
                                lstProntuarios.invalidateViews();
                            }
                        })
                        .create();
                alert.show();
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuItem menuItem = menu.findItem(R.id.menu_sobre);
        menuItem.setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void filterPacients(int diagnosis){
        pacientesFilter.clear();
        switch (diagnosis) {
            case CODE_LIBERADO:
                for(Paciente p : pacientes ){
                    if(p.getDiagnosis() == CODE_LIBERADO) {
                        pacientesFilter.add(p);
                    }
                }
                break;
        case CODE_QUARENTENA:
            for(Paciente p : pacientes ){
                if(p.getDiagnosis() == CODE_QUARENTENA) {
                    pacientesFilter.add(p);
                }
            }
            break;
        case CODE_INTERNADO:
            for(Paciente p : pacientes ){
                if(p.getDiagnosis() == CODE_INTERNADO) {
                    pacientesFilter.add(p);
                }
            }
            break;
        }
        lstProntuarios.invalidateViews();
    }

}
