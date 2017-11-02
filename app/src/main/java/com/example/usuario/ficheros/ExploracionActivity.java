package com.example.usuario.ficheros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ExploracionActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int ABRIRFICHERO_REQUEST_CODE = 1;
    Button btn_Abrir;
    TextView txv_Info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exploracion);

        this.btn_Abrir = (Button) findViewById(R.id.btn_Abrir);
        this.txv_Info = (TextView) findViewById(R.id.txv_Info);


        this.btn_Abrir.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Abrir:

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("file/*");
                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivityForResult(intent, ABRIRFICHERO_REQUEST_CODE);
                else
                    //informar que no hay ninguna aplicación para manejar ficheros
                    Toast.makeText(this, "No hay aplicación para manejar ficheros", Toast.LENGTH_SHORT).show();

                break;

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ABRIRFICHERO_REQUEST_CODE)
            if (resultCode == RESULT_OK) {
                // Mostramos en la etiqueta la ruta del archivo seleccionado
                String ruta = data.getData().getPath();
                txv_Info.setText(ruta);
            } else
                Toast.makeText(this, "Error: " + resultCode, Toast.LENGTH_SHORT).show();
    }
}
