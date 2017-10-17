package com.example.usuario.ficheros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EscribirInternaActivity extends AppCompatActivity implements View.OnClickListener {

    EditText operando1;
    EditText operando2;
    TextView resultado,propiedades;
    Button boton;
    final static String NOMBREFICHERO = "resultado.txt";
    Memoria miMemoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escribir_interna);

        this.boton = (Button)findViewById(R.id.Operando);
        this.operando1 = (EditText) findViewById(R.id.editText);
        this.operando2 = (EditText) findViewById(R.id.editText2);
        this.resultado = (TextView)findViewById(R.id.textView4);
        this.propiedades = (TextView)findViewById(R.id.textView5);



        this.boton.setOnClickListener(this);


        this.miMemoria = new Memoria(getApplicationContext());

    }

    @Override
    public void onClick(View view) {
        int r =0;
        String op1 = this.operando1.getText().toString();
        String op2 = this.operando2.getText().toString();
        String texto = "0";

        if(view == this.boton){
            try {
                r = Integer.parseInt(op1) + Integer.parseInt(op2);
            }catch (Exception e){
                e.getMessage();
            }
                texto = String.valueOf(r);
            resultado.setText(texto);

            if (miMemoria.escribirInterna(NOMBREFICHERO,texto,false,"UTF-8"))
                propiedades.setText(miMemoria.mostrarPropiedadesInterna(NOMBREFICHERO));
            else
                propiedades.setText("Error al escribir en el fichero "+NOMBREFICHERO);
        }
    }
}
