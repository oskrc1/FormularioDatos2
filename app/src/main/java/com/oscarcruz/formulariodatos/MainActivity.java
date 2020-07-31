package com.oscarcruz.formulariodatos;

//import android.annotation.SuppressLint;
//import android.app.DatePickerDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
//import android.widget.DatePicker;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//import java.util.Calendar;
//import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {


    EditText nombres;
    EditText celular;
    EditText email;
    EditText fecha;
    EditText descripcion;
    DatePickerDialog datePickerDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fecha = (EditText) findViewById(R.id.etFecha);
        // perform click event on edit text
        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                Calendar userAge = new GregorianCalendar(year, monthOfYear, dayOfMonth);
                                Calendar minAdultAge = new GregorianCalendar();
                                minAdultAge.add(Calendar.YEAR, -18);
                                if (minAdultAge.before(userAge)) {
                                    datePickerDialog.hide();
                                }
                                fecha.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        nombres = findViewById(R.id.tilNombres);
        celular = findViewById(R.id.tilCelular);
        email = findViewById(R.id.tilEmail);
        descripcion = findViewById(R.id.tilDescripcion);



        Button btnaceptar = findViewById(R.id.boton_aceptar);
        btnaceptar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                validarDatos();
            }


            private boolean esNombreValido(String nombre) {
                Pattern patron = Pattern.compile("^[a-zA-Z á-úÁ-Ú]+$");
                if (!patron.matcher(nombre).matches() || nombre.length() > 30) {
                    nombres.setError("Nombre inválido");
                    return false;
                } else {
                    nombres.setError(null);
                }

                return true;
            }

            private boolean esCelularValido(String celulares) {
                if (!Patterns.PHONE.matcher(celulares).matches() || celular.length() < 10) {
                    celular.setError("Celular inválido");
                    return false;
                } else {
                    celular.setError(null);
                }

                return true;
            }

            private boolean esCorreoValido(String correo) {
                if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
                    email.setError("Correo electrónico inválido");
                    return false;
                } else {
                    email.setError(null);
                }

                return true;
            }

            private boolean esfechaValida(String fechas) {
                if (fecha.getText().toString().isEmpty()) {
                    fecha.setError("Ingrese su fecha de nacimiento");
                    return false;
                } else {
                    fecha.setError(null);
                }

                return true;
            }

            private void validarDatos() {
                String nombre = nombres.getText().toString();
                String celulares = celular.getText().toString();
                String correo = email.getText().toString();
                String fechas = email.getText().toString();

                boolean a = esNombreValido(nombre);
                boolean b = esCelularValido(celulares);
                boolean c = esCorreoValido(correo);
                boolean d = esfechaValida(fechas);


                if (a && b && c && d) {

                    /*Toast.makeText(MainActivity.this,
                            "Usted ha ingresado el nombre " + nombres.getText().toString()
                                    + " Teléfono " + celular.getText().toString()
                                    + " Email " + email.getText().toString()
                                    + " Fecha " + fecha.getText().toString()
                                    + " Descripcion " + descripcion.getText().toString()
                            ,
                            Toast.LENGTH_LONG).show();*/

                    Bundle extras = new Bundle();

                    extras.putString("NOMBRES", nombres.getText().toString());
                    extras.putString("FECHA", fecha.getText().toString());
                    extras.putString("CELULAR", celular.getText().toString());
                    extras.putString("EMAIL", email.getText().toString());
                    extras.putString("DESCRIPCION", descripcion.getText().toString());

                    Intent intent = new Intent(MainActivity.this, ConfirmacionDatos.class);

                    intent.putExtras(extras);

                    startActivity(intent);

                }
            }

        });




    }


}






