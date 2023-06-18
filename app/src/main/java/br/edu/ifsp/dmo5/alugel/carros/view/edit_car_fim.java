package br.edu.ifsp.dmo5.alugel.carros.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

import br.edu.ifsp.dmo5.alugel.carros.Constant.Constant;
import br.edu.ifsp.dmo5.alugel.carros.R;
import br.edu.ifsp.dmo5.alugel.carros.model.Carro;
import br.edu.ifsp.dmo5.alugel.carros.mvp.PhotoCadastroMVP;
import br.edu.ifsp.dmo5.alugel.carros.presenter.PhotoCadastroPresenter;
import br.edu.ifsp.dmo5.alugel.carros.presenter.PhotoEditPresenter;

public class edit_car_fim extends AppCompatActivity implements PhotoCadastroMVP.View {
    private ImageView photoImageView;
    private Button tirarFoto;
    private Button salvar;
    private Carro carro;
    private String base64Photo;
    private PhotoEditPresenter presenter;
    private static final int CAMERA_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_car_fim);
        tirarFoto = findViewById(R.id.btnTakePhoto);
        photoImageView = findViewById(R.id.imgPhoto);
        salvar = findViewById(R.id.salvar);
        salvar.setOnClickListener(view -> salvarCarro());
        presenter = new PhotoEditPresenter(this);
        tirarFoto.setOnClickListener(this::takePhoto);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            carro = (Carro) bundle.getSerializable(Constant.CARRO_MODEL);
        }
        setMenu();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void takePhoto(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
        } else {
            startCamera();
        }
    }
    private void startCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
    }

    private void salvarCarro() {
        presenter.salvarVeiculo();
    }
    @Override
    public String retornarBase64() {
        return this.base64Photo;
    }

    @Override
    public Carro getCarro() {
        return carro;
    }

    @Override
    public void setMenu() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            base64Photo = Base64.encodeToString(byteArray, Base64.DEFAULT);
            photoImageView.setImageBitmap(photo);
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public Bundle getBundle() {
        return getIntent().getExtras();
    }
}