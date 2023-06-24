package br.edu.ifsp.dmo5.alugel.carros.view;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import br.edu.ifsp.dmo5.alugel.carros.Constant.Constant;
import br.edu.ifsp.dmo5.alugel.carros.R;
import br.edu.ifsp.dmo5.alugel.carros.mvp.MainMVP;
import br.edu.ifsp.dmo5.alugel.carros.mvp.UserMVP;
import br.edu.ifsp.dmo5.alugel.carros.presenter.MainPresenter;
import br.edu.ifsp.dmo5.alugel.carros.presenter.UserPresenter;
import br.edu.ifsp.dmo5.alugel.carros.utils.UserSeason;

public class UserActivity extends AppCompatActivity implements UserMVP.View {
    private DrawerLayout drawerLayout;
    private UserMVP.Presenter presenter;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        finViewById();
        presenter = new UserPresenter(this);
        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    int itemId = menuItem.getItemId();
                    Intent intentItem;
                    switch (itemId) {
                        case R.id.nav_item1:
                            intentItem = new Intent(this, NewCarActivity.class);
                            startActivity(intentItem);
                            break;

                        case R.id.nav_item2:
                            intentItem = new Intent(this, MeusCarrosDisponibilizadosParaAlugarActivity.class);
                            startActivity(intentItem);
                            break;

                        case R.id.nav_item3:
                            intentItem = new Intent(this, EditarDados.class);
                            startActivity(intentItem);
                            break;

                        case R.id.nav_item4:
                            deslogar();
                            intentItem = new Intent(this,MainActivity.class);
                            startActivity(intentItem);
                            break;
                        case R.id.nav_item5:
                            intentItem = new Intent(this,CarrosQueAlugueiActivity.class);
                            startActivity(intentItem);
                            break;
                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                });

    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.populateList(recyclerView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    public void deslogar() {
        SharedPreferences sharedPreferences = getSharedPreferences(Constant.USER_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(Constant.USER_ID);
        editor.remove(Constant.DATABASE_EMAIL);
        editor.remove(Constant.DATABASE_SENHA);
        editor.apply();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void finViewById() {
        recyclerView = findViewById(R.id.recyclerview_carro);
    }

    @Override
    public void setOnCLick() {

    }
    @Override
    protected void onDestroy() {
        presenter.deatach();
        super.onDestroy();
    }
    @Override
    public void showToast(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }
}