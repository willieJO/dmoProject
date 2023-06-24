package br.edu.ifsp.dmo5.alugel.carros.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import br.edu.ifsp.dmo5.alugel.carros.R;
import br.edu.ifsp.dmo5.alugel.carros.model.Carro;
import br.edu.ifsp.dmo5.alugel.carros.mvp.MeusCarrosAlugadosMVP;
import br.edu.ifsp.dmo5.alugel.carros.mvp.UserMVP;
import br.edu.ifsp.dmo5.alugel.carros.presenter.MeusCarrosAlugadosPresenter;
import br.edu.ifsp.dmo5.alugel.carros.presenter.UserPresenter;

public class MeusCarrosDisponibilizadosParaAlugarActivity extends AppCompatActivity implements MeusCarrosAlugadosMVP.View{
    private MeusCarrosAlugadosMVP.Presenter presenter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_carros_disponibilizados_para_alugar);
        recyclerView = findViewById(R.id.recyclerview_meus_carros_alugados);
        presenter = new MeusCarrosAlugadosPresenter(this);
        setMenu();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onStart() {
        super.onStart();
        presenter.populateList(recyclerView);
    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public Bundle getBundle() {
        return null;
    }

    @Override
    public String retornarBase64() {
        return null;
    }

    @Override
    public Carro getCarro() {
        return null;
    }

    @Override
    public void setMenu() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    protected void onDestroy() {
        presenter.deatach();
        super.onDestroy();
    }
}