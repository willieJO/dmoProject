package br.edu.ifsp.dmo5.alugel.carros.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import br.edu.ifsp.dmo5.alugel.carros.Constant.Constant;
import br.edu.ifsp.dmo5.alugel.carros.R;
import br.edu.ifsp.dmo5.alugel.carros.model.Carro;
import br.edu.ifsp.dmo5.alugel.carros.mvp.CarroDetailsMVP;
import br.edu.ifsp.dmo5.alugel.carros.mvp.MainMVP;
import br.edu.ifsp.dmo5.alugel.carros.presenter.DetailsCarroPresenter;

public class DetalhesCarroActivity extends AppCompatActivity implements CarroDetailsMVP.View{
    private Carro carro;
    private ImageView imagemCarro;
    private TextView modeloCarro;
    private TextView donoCarro;
    private Button alugar;
    private EditText dataEdit;
    private SimpleDateFormat dateFormatter;

    private CarroDetailsMVP.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhes_carro_activity);
        presenter = new DetailsCarroPresenter(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            carro = (Carro) bundle.getSerializable(Constant.CARRO_MODEL);
        }
        setMenu();
        finViewById();
        setOnCLick();
        setDados();
        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setMenu() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void finViewById() {
        imagemCarro = findViewById(R.id.image_car);
        modeloCarro = findViewById(R.id.text_modelo_carro_);
        donoCarro = findViewById(R.id.text_dono);
        alugar = findViewById(R.id.button_alugar);
        dataEdit = findViewById(R.id.editTextDate);
    }

    @Override
    public void setOnCLick() {
        alugar.setOnClickListener(view -> alugarCarro());
        dataEdit.setOnClickListener(view -> showDatePickerDialog());
    }

    private void showDatePickerDialog() {
        Calendar newCalendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year, monthOfYear, dayOfMonth) -> {
                    Calendar selectedCalendar = Calendar.getInstance();
                    selectedCalendar.set(year, monthOfYear, dayOfMonth);

                    String selectedDate = dateFormatter.format(selectedCalendar.getTime());
                    dataEdit.setText(selectedDate);
                },
                newCalendar.get(Calendar.YEAR),
                newCalendar.get(Calendar.MONTH),
                newCalendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.getDatePicker().setMinDate(newCalendar.getTimeInMillis());
        datePickerDialog.show();
    }

    public void alugarCarro() {
        presenter.alugaCarro(carro.getId(),dataEdit.getText().toString());
    }

    @Override
    public void showToast(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void setDados() {
        if (carro.getFoto() != null) {
            byte[] decodedBytes = Base64.decode(carro.getFoto(), Base64.DEFAULT);
            if (decodedBytes.length > 0) {
                Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
                imagemCarro.setImageBitmap(decodedBitmap);
            }
        }
        modeloCarro.setText(Constant.MODELO_CARRO + " " + carro.getModelo());
        donoCarro.setText(Constant.DONO_CARRO + " " + carro.getCpf());
    }
}