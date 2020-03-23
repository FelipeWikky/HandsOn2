package com.example.handson.ui.prontuario;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
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
import android.widget.TextView;

import com.example.handson.R;
import com.example.handson.controller.PacienteAdapter;
import com.example.handson.dao.PacienteDAO;
import com.example.handson.model.Paciente;

import java.util.List;

import static com.example.handson.controller.Functions.filterPatients;
import static com.example.handson.util.Constants.CODE_INTERNADO;
import static com.example.handson.util.Constants.CODE_LIBERADO;
import static com.example.handson.util.Constants.CODE_NULL;
import static com.example.handson.util.Constants.CODE_QUARENTENA;

public class ListFragment extends Fragment {
    private View root;
    private Button btnLiberado, btnQuarentena, btnInternado;
    private Button[] buttonsList;
    private TextView txtTitle;

    private ListView lstProntuarios;
    private List<Paciente> pacientes;
    private List<Paciente> pacientesFilter;
    private ArrayAdapter<Paciente> adapter;
    private PacienteDAO pacienteDAO;

    private int buttonClicked = 0;

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

        txtTitle = root.findViewById(R.id.txtTitleList);
        lstProntuarios = (ListView) root.findViewById(R.id.lstProntuarios);

        pacienteDAO = new PacienteDAO(getActivity());
        pacientes = pacienteDAO.readAll();
        pacientesFilter = pacienteDAO.readAll();

        if ( pacientesFilter.size() > 0 )
            txtTitle.setText("Todos Prontuários cadastrados");
        else
            txtTitle.setText("Nenhum Prontuário encontrado");

        //ArrayAdapter<Paciente> adapter = new ArrayAdapter<Paciente>(getActivity(), android.R.layout.simple_list_item_1, pacientes);
        adapter = new PacienteAdapter(getActivity(), pacientesFilter);
        lstProntuarios.setAdapter(adapter);
        registerForContextMenu(lstProntuarios);

        lstProntuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog alert = new AlertDialog.Builder(getActivity())
                        .setTitle(pacientesFilter.get(i).getName())
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

        buttonsList = new Button[]{btnLiberado, btnQuarentena, btnInternado};

        return root;
    }

    private void setCommandButton(final Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonClicked == v.getId()) {
                    buttonClicked = 0;
                    showFilterPatients(CODE_NULL);
                    for(Button b : buttonsList){
                        b.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.buttonshape));
                    }
                    return;
                }
                switch (v.getId()){
                    case R.id.btnLiberado:
                        buttonClicked = v.getId();
                        setSelectedButton((Button)v);
                        showFilterPatients(CODE_LIBERADO);
                        break;
                    case R.id.btnQuarentena:
                        buttonClicked = v.getId();
                        setSelectedButton((Button)v);
                        showFilterPatients(CODE_QUARENTENA);
                        break;
                    case R.id.btnInternado:
                        buttonClicked = v.getId();
                        setSelectedButton((Button)v);
                        showFilterPatients(CODE_INTERNADO);
                        break;
                }
            }
        });
    }

    @SuppressLint("WrongConstant")
    private void setSelectedButton(final Button button) {
        if (button.getFocusable() == 1){
            button.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.buttonshape2));
            for(Button b : buttonsList){
                if ( button.getId() != b.getId() ){
                    b.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.buttonshape));
                }
            }
        }
         else
            button.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.buttonshape));
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
                                pacientesFilter.remove(p);
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

    private void showFilterPatients(int diagnosis){
        pacientesFilter.clear();
        switch (diagnosis) {
            case CODE_LIBERADO:
                for(Paciente p : filterPatients(CODE_LIBERADO, pacientes) ){
                    pacientesFilter.add(p);
                }
                txtTitle.setText("Pacientes Liberados");
                break;
            case CODE_QUARENTENA:
                for(Paciente p : filterPatients(CODE_QUARENTENA, pacientes) ){
                    pacientesFilter.add(p);
                }
                txtTitle.setText("Pacientes em Quarentena");
                break;
            case CODE_INTERNADO:
                for(Paciente p : filterPatients(CODE_INTERNADO, pacientes) ){
                    pacientesFilter.add(p);
                }
                txtTitle.setText("Pacientes Internados");
                break;
            default:
                for(Paciente p : pacientes ){
                    pacientesFilter.add(p);
                }
                txtTitle.setText("Todos Prontuários cadastrados");
        }
        if (pacientesFilter.size() == 0 ){
            txtTitle.setText("Nenhum Prontuário encontrado");
        }

        lstProntuarios.invalidateViews();
    }

}
