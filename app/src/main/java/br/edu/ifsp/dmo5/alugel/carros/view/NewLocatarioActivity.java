package br.edu.ifsp.dmo5.alugel.carros.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ifsp.dmo5.alugel.carros.R;
import br.edu.ifsp.dmo5.alugel.carros.mvp.CadastroMVP;
import br.edu.ifsp.dmo5.alugel.carros.presenter.CadastroPresenter;

public class NewLocatarioActivity extends AppCompatActivity implements CadastroMVP.View{
    EditText editNomeLocador;
    EditText editCnh;
    EditText editCnhCategora;
    EditText editDataCnh;
    EditText editCpf;
    EditText editLogadouro;
    EditText editNumero;
    EditText editBairro;
    EditText editCidade;
    EditText editEstado;
    EditText editCep;
    EditText editTelefone;
    EditText editEmail;
    EditText editDataNascimento;
    EditText editSenha;
    Button buttonCadastrar;
    private CadastroMVP.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_locatario);
        presenter = new CadastroPresenter(this);
        setToolbar();
        finViewById();
        setOnCLick();
    }

    @Override
    protected void onDestroy() {
        presenter.deatach();
        super.onDestroy();
    }

    @Override
    public void setToolbar() {
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
    public void finViewById() {
        editNomeLocador = findViewById(R.id.edittext_nome_locatarios);
        editCnh = findViewById(R.id.edittext_cnh);
        editCnhCategora = findViewById(R.id.edittext_categoria);
        editDataCnh = findViewById(R.id.edittext_data_cnh);
        editCpf = findViewById(R.id.edittext_cpf);
        editLogadouro = findViewById(R.id.edittext_logradouro);
        editNumero = findViewById(R.id.edittext_numero);
        editBairro = findViewById(R.id.edittext_bairro);
        editCidade = findViewById(R.id.edittext_cidade);
        editEstado = findViewById(R.id.edittext_estado);
        editCep = findViewById(R.id.edittext_cep);
        editTelefone = findViewById(R.id.edittext_telefone);
        editEmail = findViewById(R.id.edittext_email);
        editDataNascimento = findViewById(R.id.edittext_data_nasc);
        editSenha = findViewById(R.id.editTextSenha);
        buttonCadastrar = findViewById(R.id.button_save);
    }

    @Override
    public void setOnCLick() {
        buttonCadastrar.setOnClickListener(view -> cadastrarUsuario());
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showToast(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }
    public void cadastrarUsuario() {
        //presenter.realizarCadastro();
        finish();
    }
}