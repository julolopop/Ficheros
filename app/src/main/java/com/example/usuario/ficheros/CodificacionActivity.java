package com.example.usuario.ficheros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.nbsp.materialfilepicker.MaterialFilePicker;

import java.util.regex.Pattern;

public class CodificacionActivity extends AppCompatActivity implements View.OnClickListener {

    EditText ficheroLectura, ficheroEscritura, contenido;
    Button leer, guardar;
    RadioButton rdb_Utf8, rdb_Utf16, rdb_Iso;
    Memoria miMemoria;
    private static final int ABRIRFICHERO_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codificacion);

        Inicializar();

        leer.setOnClickListener(CodificacionActivity.this);
        guardar.setOnClickListener(CodificacionActivity.this);
    }

    private void Inicializar() {
        this.ficheroLectura = (EditText) findViewById(R.id.edt_in);
        this.ficheroEscritura = (EditText) findViewById(R.id.edt_out);
        this.contenido = (EditText) findViewById(R.id.edt_Contenido);
        this.leer = (Button) findViewById(R.id.btn_leer);
        this.guardar = (Button) findViewById(R.id.btn_guardar);
        this.rdb_Utf8 = (RadioButton) findViewById(R.id.rdb_Utf8);
        this.rdb_Utf16 = (RadioButton) findViewById(R.id.rdb_Utf16);
        this.rdb_Iso = (RadioButton) findViewById(R.id.rdb_Utf16);

        this.miMemoria = new Memoria(CodificacionActivity.this);
    }

    @Override
    public void onClick(View v) {

        String cadena3 = ficheroEscritura.getText().toString();
        String mensaje = "";
        String codificacion = "UTF-8";



        if (rdb_Utf8.isChecked())
            codificacion = "UTF-8";
        else if (rdb_Utf16.isChecked())
            codificacion = "UTF-16";
        else if (rdb_Iso.isChecked())
            codificacion = "ISO-8859-15";

        switch (v.getId()) {
            case R.id.btn_leer:
                /*resultado = miMemoria.leerExterna(cadena1, codificacion);
                if (resultado.getCodigo()) {
                    contenido.setText(resultado.getContenido());
                    mensaje = "Fichero leido correctamente";
                } else
                    mensaje = "Error al leer el fichero " + cadena1 + " " + resultado.getMensaje();*/
//https://github.com/nbsp-team/MaterialFilePicker

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");

                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivityForResult(intent, ABRIRFICHERO_REQUEST_CODE);
                else
                    //informar que no hay ninguna aplicación para manejar ficheros
                    Toast.makeText(this, "No hay aplicación para manejar ficheros", Toast.LENGTH_SHORT).show();


                break;
            case R.id.btn_guardar:
                if (cadena3.isEmpty())
                    mensaje = "Debe introducir un nombre al fichero";
                else if (miMemoria.disponibleEscritura())
                    if (miMemoria.escribirExterna(cadena3,  contenido.getText().toString(), false, codificacion))
                        mensaje = "Fichero escrito correctamente";
                    else
                        mensaje = "error al escribir en el fichero";
                else
                    mensaje = "Memoria externa no disponible";
                break;
        }
        Toast.makeText(CodificacionActivity.this, mensaje, Toast.LENGTH_SHORT).show();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Resultado resultado;
        String mensaje = "";
        String codificacion = "UTF-8";



        if (rdb_Utf8.isChecked())
            codificacion = "UTF-8";
        else if (rdb_Utf16.isChecked())
            codificacion = "UTF-16";
        else if (rdb_Iso.isChecked())
            codificacion = "ISO-8859-15";


        if (requestCode == ABRIRFICHERO_REQUEST_CODE)
            if (resultCode == RESULT_OK) {
                // Mostramos en la etiqueta la ruta del archivo seleccionado
                String ruta = data.getData().getPath();
                int index = ruta.indexOf(':')+1;


                ruta = ruta.substring(index);
                ficheroLectura.setText(ruta);
            } else
                Toast.makeText(this, "Error: " + resultCode, Toast.LENGTH_SHORT).show();

        resultado = miMemoria.leerExterna( ficheroLectura.getText().toString(), codificacion);
        if (resultado.getCodigo()) {
            contenido.setText(resultado.getContenido());
            mensaje = "Fichero leido correctamente";
        } else
            mensaje = "Error al leer el fichero " +  ficheroLectura.getText().toString() + " " + resultado.getMensaje();
    }
}
