package br.edu.ifsp.dmo5.alugel.carros.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.ifsp.dmo5.alugel.carros.R;
import br.edu.ifsp.dmo5.alugel.carros.model.User;
import br.edu.ifsp.dmo5.alugel.carros.mvp.CadastroMVP;

import br.edu.ifsp.dmo5.alugel.carros.presenter.CadastroPresenter;
import br.edu.ifsp.dmo5.alugel.carros.utils.Criptografia;

public class NewUserActivity extends AppCompatActivity implements CadastroMVP.View {

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
    Button editCadastrar;
    TextView textLeiame;
    CheckBox checkTermos;
    private CadastroMVP.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        presenter = new CadastroPresenter(this);
        setToolbar();
        finViewById();
        setOnCLick();
    }

    protected void onDestroy() {
        presenter.deatach();
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void finViewById() {
        editNomeLocador = findViewById(R.id.edittext_nome_locador);
        editCnh = findViewById(R.id.edittext_cnh_locador);
        editCnhCategora = findViewById(R.id.edittext_categoria_locador);
        editDataCnh = findViewById(R.id.edittext_data_cnh_locador);
        editCpf = findViewById(R.id.edittext_cpf_locador);
        editLogadouro = findViewById(R.id.edittext_logradouro_locador);
        editNumero = findViewById(R.id.edittext_numero_locador);
        editBairro = findViewById(R.id.edittext_bairro_locador);
        editCidade = findViewById(R.id.edittext_cidade_locador);
        editEstado = findViewById(R.id.edittext_estado_locador);
        editCep = findViewById(R.id.edittext_cep_locador);
        editTelefone = findViewById(R.id.edittext_telefone_locador);
        editEmail = findViewById(R.id.edittext_email_locador);
        editDataNascimento = findViewById(R.id.edittext_data_nasc_locador);
        editSenha = findViewById(R.id.edittext_senha_locador);
        editCadastrar = findViewById(R.id.button_save_locador);
        textLeiame = findViewById(R.id.leia_me);
        checkTermos = findViewById(R.id.termos_de_uso);
    }

    @Override
    public void setOnCLick() {
        editCadastrar.setOnClickListener(view -> cadastrarUsuario());
        textLeiame.setOnClickListener(view -> leiame());
    }

    public void leiame() {
        presenter.leiame();
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
        User user = new User();
        user.setNome(editNomeLocador.getText().toString());
        user.setCnh(editCnh.getText().toString());
        user.setDataCnh(editDataCnh.getText().toString());
        user.setCpf(editCpf.getText().toString());
        user.setCnhCategoria(editCnhCategora.getText().toString());
        user.setLogadouro(editLogadouro.getText().toString());
        user.setNumero(editNumero.getText().toString());
        user.setBairro(editBairro.getText().toString());
        user.setCidade(editCidade.getText().toString());
        user.setEstado(editEstado.getText().toString());
        user.setCep(editCep.getText().toString());
        user.setTelefone(editTelefone.getText().toString());
        user.setEmail(editEmail.getText().toString());
        user.setDataDeNascimento(editDataNascimento.getText().toString());
        user.setSenha(editSenha.getText().toString());
        presenter.realizarCadastro(user);
        finish();
    }
}