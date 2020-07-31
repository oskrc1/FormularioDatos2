package com.oscarcruz.formulariodatos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmacionDatos extends AppCompatActivity {

    TextView tvnombres;
    TextView tvfecha;
    TextView tvemail;
    TextView tvcelular;
    TextView tvdescripcion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion_datos);

        tvnombres = findViewById(R.id.tvNombresSet);
        tvfecha = findViewById(R.id.tvFechaset);
        tvcelular = findViewById(R.id.tvCelularset);
        tvemail = findViewById(R.id.tvEmailset);
        tvdescripcion = findViewById(R.id.tvDescripcionset);

        Bundle parametros = this.getIntent().getExtras();
        if(parametros !=null){

            String nombre = parametros.getString("NOMBRES");
            tvnombres.setText(nombre);
            String fecha = parametros.getString("FECHA");
            tvfecha.setText(fecha);
            String email = parametros.getString("EMAIL");
            tvemail.setText(email);
            String celular = parametros.getString("CELULAR");
            tvcelular.setText(celular);
            String descripcion = parametros.getString("DESCRIPCION");
            tvdescripcion.setText(descripcion);}

        Button btneditar = findViewById(R.id.boton_editar);
        btneditar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }}


