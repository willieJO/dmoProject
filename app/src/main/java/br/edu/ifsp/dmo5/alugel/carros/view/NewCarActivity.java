package br.edu.ifsp.dmo5.alugel.carros.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import br.edu.ifsp.dmo5.alugel.carros.Constant.Constant;
import br.edu.ifsp.dmo5.alugel.carros.R;
import br.edu.ifsp.dmo5.alugel.carros.model.Carro;
import br.edu.ifsp.dmo5.alugel.carros.mvp.CadastroMVP;
import br.edu.ifsp.dmo5.alugel.carros.mvp.MainMVP;
import br.edu.ifsp.dmo5.alugel.carros.mvp.VeiculoCadastroMVP;
import br.edu.ifsp.dmo5.alugel.carros.presenter.MainPresenter;
import br.edu.ifsp.dmo5.alugel.carros.presenter.VeiculoCadastroPresenter;

public class NewCarActivity extends AppCompatActivity implements VeiculoCadastroMVP.View {
    public EditText marca;
    public EditText modelo;
    public EditText cor;
    public EditText placa;
    public EditText combustivel;
    public Spinner  spinner;
    public CheckBox ar;
    public CheckBox eletrico;
    public CheckBox porta;
    public CheckBox radio;
    public EditText quilometragem;
    public EditText crlve;
    public EditText cpf;
    public Button continuar;
    private VeiculoCadastroMVP.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_car);
        presenter = new VeiculoCadastroPresenter(this);
        findElement();
        click();
        setMenu();
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
        Carro carro = new Carro();
        carro.setMarca(marca.getText().toString());
        carro.setModelo(modelo.getText().toString());
        carro.setCor(cor.getText().toString());
        carro.setPlaca(placa.getText().toString());
        //carro.setCombustivel(spinner.getSelectedItem().toString());
        carro.setArCondicionado(ar.isChecked() ? "1": "0");
        carro.setEletrico(eletrico.isChecked() ? "1": "0");
        carro.setPorta(porta.isChecked() ? "1" : "0");
        carro.setRadio(radio.isChecked() ? "1" : "");
        carro.setQuilometragem(quilometragem.getText().toString());
        carro.setCrlve(crlve.getText().toString());
        carro.setCpf(cpf.getText().toString());
        presenter.continuar(carro);
    }

    @Override
    public void showToast(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }
}