package br.edu.ifsp.dmo5.alugel.carros.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import br.edu.ifsp.dmo5.alugel.carros.R;
import br.edu.ifsp.dmo5.alugel.carros.mvp.CarrosQueAlugueiMVP;
import br.edu.ifsp.dmo5.alugel.carros.presenter.CarrosQueAlugueiPresenter;

public class CarrosQueAlugueiActivity extends AppCompatActivity implements CarrosQueAlugueiMVP.View{
    private CarrosQueAlugueiMVP.Presenter presenter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carris_que_aluguei);
        presenter = new CarrosQueAlugueiPresenter(this);
        setToolbar();
        finViewById();
    }
    @Override
    protected void onStart() {
        super.onStart();
        presenter.populateList(recyclerView);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void setToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void finViewById() {
        recyclerView = findViewById(R.id.recyclerview_carros_que_aluguei);
    }

    @Override
    public void setOnCLick() {

    }

    @Override
    public void showToast(String mensagem) {

    }
}