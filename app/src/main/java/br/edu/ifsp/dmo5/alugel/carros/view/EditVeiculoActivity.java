package br.edu.ifsp.dmo5.alugel.carros.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import br.edu.ifsp.dmo5.alugel.carros.Constant.Constant;
import br.edu.ifsp.dmo5.alugel.carros.R;
import br.edu.ifsp.dmo5.alugel.carros.model.Carro;
import br.edu.ifsp.dmo5.alugel.carros.mvp.VeiculoCadastroMVP;
import br.edu.ifsp.dmo5.alugel.carros.presenter.EditCarroPresenter;
import br.edu.ifsp.dmo5.alugel.carros.presenter.VeiculoCadastroPresenter;

public class EditVeiculoActivity extends AppCompatActivity implements VeiculoCadastroMVP.View, AdapterView.OnItemSelectedListener {
    public EditText marca;
    public EditText modelo;
    public EditText cor;
    public EditText placa;
    public Spinner spinner;
    public CheckBox ar;
    public CheckBox eletrico;
    public CheckBox porta;
    public CheckBox radio;
    public EditText quilometragem;
    public EditText crlve;
    public EditText cpf;
    public Button continuar;
    private Carro carroParaEditar;
    private EditText precoVeiculo;
    private VeiculoCadastroMVP.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_car);
        presenter = new EditCarroPresenter(this);
        findElement();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.tipo_combustivel, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        click();
        setMenu();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            carroParaEditar = (Carro) bundle.getSerializable(Constant.CARRO_MODEL);
        }
        setDados();
    }

    public void setDados() {
        marca.setText(carroParaEditar.getMarca());
        modelo.setText(carroParaEditar.getModelo());
        cor.setText(carroParaEditar.getCor());
        placa.setText(carroParaEditar.getPlaca());
        ar.setChecked(carroParaEditar.getArCondicionado().equals("1"));
        eletrico.setChecked(carroParaEditar.getEletrico().equals("1"));
        porta.setChecked(carroParaEditar.getPorta().equals("1"));
        radio.setChecked(carroParaEditar.getRadio().equals("1"));
        quilometragem.setText(carroParaEditar.getQuilometragem());
        crlve.setText(carroParaEditar.getCrlve());
        cpf.setText(carroParaEditar.getCpf());
        precoVeiculo.setText(carroParaEditar.getPreço());
        String[] dataSet = getResources().getStringArray(R.array.tipo_combustivel);
        int indiceSelecionado = -1;
        for (int i = 0; i < dataSet.length; i++) {
            if (dataSet[i].equals(carroParaEditar.getCombustivel())) {
                indiceSelecionado = i;
                break;
            }
        }
        if (indiceSelecionado != -1) {
            spinner.setSelection(indiceSelecionado);
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void setMenu() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void findElement() {
        marca = findViewById(R.id.edittext_marca);
        modelo = findViewById(R.id.edittext_modelo);
        cor = findViewById(R.id.edittext_cor);
        placa = findViewById(R.id.edittext_placa);
        spinner = findViewById(R.id.spinner_combustivel);
        ar = findViewById(R.id.check_ar);
        eletrico = findViewById(R.id.check_trio);
        porta = findViewById(R.id.check_portas);
        radio = findViewById(R.id.check_radio);
        quilometragem = findViewById(R.id.edittext_quilometragem);
        crlve = findViewById(R.id.edittext_crlve);
        cpf = findViewById(R.id.edittext_cpf_proprietario);
        precoVeiculo = findViewById(R.id.edittext_valor_por_dia);
        continuar = findViewById(R.id.button_salvar_carro);
    }

    @Override
    public void click() {
        continuar.setOnClickListener(view-> continuar());
    }

    @Override
    public Bundle getBundle() {
        return getIntent().getExtras();
    }

    public void continuar() {
        carroParaEditar.setMarca(marca.getText().toString());
        carroParaEditar.setModelo(modelo.getText().toString());
        carroParaEditar.setCor(cor.getText().toString());
        carroParaEditar.setPlaca(placa.getText().toString());
        carroParaEditar.setArCondicionado(ar.isChecked() ? "1": "0");
        carroParaEditar.setEletrico(eletrico.isChecked() ? "1": "0");
        carroParaEditar.setPorta(porta.isChecked() ? "1" : "0");
        carroParaEditar.setRadio(radio.isChecked() ? "1" : "0");
        carroParaEditar.setQuilometragem(quilometragem.getText().toString());
        carroParaEditar.setCrlve(crlve.getText().toString());
        carroParaEditar.setCpf(cpf.getText().toString());
        carroParaEditar.setCombustivel(spinner.getSelectedItem().toString());
        carroParaEditar.setPreço(precoVeiculo.getText().toString());
        presenter.continuar(carroParaEditar);
    }

    @Override
    public void showToast(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    @Override
    protected void onDestroy() {
        presenter.deatach();
        super.onDestroy();
    }
}