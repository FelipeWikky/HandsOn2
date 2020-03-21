package com.example.handson.ui.prontuario;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.handson.R;
import com.example.handson.model.Paciente;

import static com.example.handson.controller.Functions.verifyDiagnosis;
import static com.example.handson.util.Constants.CODE_ERROR_EMPTY;
import static com.example.handson.util.Constants.CODE_INTERNADO;
import static com.example.handson.util.Constants.CODE_LIBERADO;
import static com.example.handson.util.Constants.CODE_OK;
import static com.example.handson.util.Constants.CODE_QUARENTENA;

public class CadFragment extends Fragment {
    private View root;
    private Paciente paciente = new Paciente();

    private EditText inputCpf, inputName, inputAge, inputTemp;
    private CheckBox chkCough; private EditText inputCough;
    private CheckBox chkHeadache; private EditText inputHeadache;

    private CheckBox chkItalia; private EditText inputItalia;
    private CheckBox chkChina; private EditText inputChina;
    private CheckBox chkIndonesia; private EditText inputIndonesia;
    private CheckBox chkPortugal; private EditText inputPortugal;
    private CheckBox chkEua; private EditText inputEua;

    public CadFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_cad, container, false);
        setHasOptionsMenu(true);

        inputCpf = root.findViewById(R.id.edtCpf);
        inputName = root.findViewById(R.id.edtName);
        inputAge = root.findViewById(R.id.edtAge);
        inputTemp = root.findViewById(R.id.edtTemp);

        chkCough = root.findViewById(R.id.chkCough);
        inputCough = root.findViewById(R.id.edtCough);
        syncCheckBoxInput(chkCough, inputCough);

        chkHeadache = root.findViewById(R.id.chkHeadache);
        inputHeadache = root.findViewById(R.id.edtHeadache);
        syncCheckBoxInput(chkHeadache, inputHeadache);

        chkItalia = root.findViewById(R.id.chkItalia);
        inputItalia = root.findViewById(R.id.edtItalia);
        syncCheckBoxInput(chkItalia, inputItalia);

        chkChina = root.findViewById(R.id.chkChina);
        inputChina = root.findViewById(R.id.edtChina);
        syncCheckBoxInput(chkChina, inputChina);

        chkIndonesia = root.findViewById(R.id.chkIndonesia);
        inputIndonesia = root.findViewById(R.id.edtIndonesia);
        syncCheckBoxInput(chkIndonesia, inputIndonesia);

        chkPortugal = root.findViewById(R.id.chkPortugal);
        inputPortugal = root.findViewById(R.id.edtPortugal);
        syncCheckBoxInput(chkPortugal, inputPortugal);

        chkEua = root.findViewById(R.id.chkEua);
        inputEua = root.findViewById(R.id.edtEua);
        syncCheckBoxInput(chkEua, inputEua);


        return root;
    }

    private void syncCheckBoxInput(final CheckBox checkBox, final EditText input){
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    input.setVisibility(EditText.VISIBLE);
                    input.requestFocus();
                }else{
                    input.setText("");
                    input.setVisibility(EditText.INVISIBLE);
                }
            }
        });
    }

    private int getData(){
        try {
            paciente.setCpf(inputCpf.getText().toString());
            paciente.setName(inputName.getText().toString());
            paciente.setAge(Integer.parseInt(inputAge.getText().toString()));
            paciente.setBodyTemp(Integer.parseInt(inputTemp.getText().toString()));

            paciente.setCoughDays(
                    getChecked(chkCough) ? Integer.parseInt(inputCough.getText().toString()) : 0
            );

            paciente.setHeadacheDays(
                    getChecked(chkHeadache) ? Integer.parseInt(inputCough.getText().toString()) : 0
            );

            paciente.addCountryVisit("Italia" ,
                    getChecked(chkItalia) ? Integer.parseInt(inputItalia.getText().toString()) : 0
            );

            paciente.addCountryVisit("China",
                    getChecked(chkChina) ? Integer.parseInt(inputChina.getText().toString()) : 0);

            paciente.addCountryVisit("Indonesia",
                    getChecked(chkIndonesia)? Integer.parseInt(inputIndonesia.getText().toString()) : 0);

            paciente.addCountryVisit("Portugal",
                    getChecked(chkPortugal) ? Integer.parseInt(inputPortugal.getText().toString()) : 0);

            paciente.addCountryVisit("Eua",
                    getChecked(chkEua) ? Integer.parseInt(inputEua.getText().toString()) : 0);

            return CODE_OK;
        }catch (NumberFormatException e ){ //NumberFormatException | NullPointerException
            return CODE_ERROR_EMPTY;
        }
    }

    private boolean getChecked(final CheckBox checkBox){
        return checkBox.isChecked();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuItem mItem = menu.findItem(R.id.menu_sobre);
        mItem.setVisible(false);
        inflater.inflate(R.menu.cadastro, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_cadastrar: {
                switch ( getData() ){
                    case CODE_OK:
                        paciente.setDiagnosis( verifyDiagnosis(paciente) );
                        responseDiagnosis();
                        break;
                    case CODE_ERROR_EMPTY:
                        Toast.makeText(getActivity(), "Alguns campos ficaram vazios. Verifique-os", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void responseDiagnosis(){
        switch ( paciente.getDiagnosis() ) {
            case CODE_INTERNADO:
                Toast.makeText(getActivity(), "Procure um atendimento Médico urgente. Você precisa ser Internado.", Toast.LENGTH_LONG).show();
                break;
            case CODE_QUARENTENA:
                Toast.makeText(getActivity(), "Aconselhamos a ficar em Quarentena.", Toast.LENGTH_LONG).show();
                break;
            case CODE_LIBERADO:
                Toast.makeText(getActivity(), "Você está liberado. Mas continue se cuidado ao máximo.", Toast.LENGTH_LONG).show();
                break;
        }
        Toast.makeText(getActivity(), paciente.toString(), Toast.LENGTH_LONG).show();
    }

}
