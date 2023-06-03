package br.edu.ifsp.dmo5.alugel.carros.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import br.edu.ifsp.dmo5.alugel.carros.R;
import br.edu.ifsp.dmo5.alugel.carros.mvp.MainMVP;
import br.edu.ifsp.dmo5.alugel.carros.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainMVP.View{
    private MainMVP.Presenter presenter;
    private EditText editUsuario;
    private EditText editSenha;
    private Button buttonEntrar;
    private CheckBox checkBoxManterConectado;
    private FloatingActionMenu fabMenu;
    private FloatingActionButton fabLocador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenter(this);
        finViewById();
        setOnCLick();
    }

    @Override
    protected void onDestroy() {
        presenter.deatach();
        super.onDestroy();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void finViewById() {
        editUsuario = findViewById(R.id.editTextUsuario);
        editSenha = findViewById(R.id.editTextSenha);
        buttonEntrar =  findViewById(R.id.button_login);
        checkBoxManterConectado = findViewById(R.id.check_lembrar_login);
        fabMenu = findViewById(R.id.fab_menu);
        fabLocador = findViewById(R.id.fab_locador);
    }

    @Override
    public void setOnCLick() {
        buttonEntrar.setOnClickListener(view -> realizarLogin());
        fabMenu.setOnMenuButtonClickListener(view -> menu());
        fabLocador.setOnClickListener(view -> cadastrarUsuario());
    }

    @Override
    public void showToast(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

    public void realizarLogin() {
        presenter.realizarLogin(editUsuario.getText().toString(), editSenha.getText().toString());
    }


    public void cadastrarUsuario() {
        presenter.openCadastro();
        fabMenu.close(true);
    }

    public void menu() {
        if (fabMenu.isOpened()) {
            fabMenu.close(true);
        } else {
            fabMenu.open(true);
        }
    }

}