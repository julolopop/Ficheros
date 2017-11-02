package com.example.usuario.ficheros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LeerMemoriaActivity extends AppCompatActivity implements View.OnClickListener {
    //definicion de variables
    EditText edt_operando1, edt_operando2, edt_operando3, edt_operando4;
    TextView txv_resultado;
    Button btn_suma;
    Memoria miMemoria;
    public static final String NUMERO = "numero";
    public static final String VALOR = "valor.txt";
    public static final String DATO_SD = "dato_sd.txt";
    public static final String DATO = "dato.txt";
    public static final String OPERACIONES = "operaciones.txt";
    public static final String CODIFICACION = "UTF-8";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leer_memoria);
        //valores internos de la clase
        Resultado resultado = new Resultado();
        //asignacion de las variables
        this.edt_operando1 = (EditText) findViewById(R.id.edt_operando1);
        this.edt_operando2 = (EditText) findViewById(R.id.edt_operando2);
        this.edt_operando3 = (EditText) findViewById(R.id.edt_operando3);
        this.edt_operando4 = (EditText) findViewById(R.id.edt_operando4);
        this.txv_resultado = (TextView) findViewById(R.id.txv_resultado);
        this.btn_suma = (Button) findViewById(R.id.btn_suma);
        this.miMemoria = new Memoria(LeerMemoriaActivity.this);


        this.btn_suma.setOnClickListener(this);
        resultado = this.miMemoria.leerRaw(this.NUMERO);
        if (resultado.getCodigo()) {
            this.edt_operando1.setText(resultado.getContenido());
        } else {
            this.edt_operando1.setText("0");
            Toast.makeText(LeerMemoriaActivity.this, "Error al leer raw", Toast.LENGTH_SHORT).show();
        }

        resultado = this.miMemoria.leerAsset(this.VALOR);
        if (resultado.getCodigo()) {
            this.edt_operando2.setText(resultado.getContenido());
        } else {
            this.edt_operando2.setText("0");
            Toast.makeText(LeerMemoriaActivity.this, "Error al leer assest", Toast.LENGTH_SHORT).show();
        }


        if (miMemoria.escribirInterna(this.DATO, "7", false, this.CODIFICACION)) {
            resultado = this.miMemoria.leerInterna(this.DATO, this.CODIFICACION);
            if (resultado.getCodigo()) {
                this.edt_operando3.setText(resultado.getContenido());
            } else {
                this.edt_operando3.setText("0");
                Toast.makeText(LeerMemoriaActivity.this, "Error al leer interna", Toast.LENGTH_SHORT).show();
            }
        }

        if (miMemoria.disponibleEscritura()) {
            if (miMemoria.escribirExterna(this.DATO_SD, "5", false, this.CODIFICACION)) {
                resultado = this.miMemoria.leerExterna(this.DATO_SD, this.CODIFICACION);
                if (resultado.getCodigo()) {
                    this.edt_operando4.setText(resultado.getContenido());

                } else {
                    this.edt_operando4.setText("0");
                    Toast.makeText(LeerMemoriaActivity.this, "Error al leer externa", Toast.LENGTH_SHORT).show();
                }
            }
        } else
            Toast.makeText(LeerMemoriaActivity.this, "Memoria externa no disponible", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(View v) {

        String op1 = this.edt_operando1.getText().toString();
        String op2 = this.edt_operando2.getText().toString();
        String op3 = this.edt_operando3.getText().toString();
        String op4 = this.edt_operando4.getText().toString();
        int cantidad;
        String operacion, mensage;

        if (v == btn_suma) {
            try {
                cantidad = Integer.parseInt(op1) + Integer.parseInt(op2) + Integer.parseInt(op3) + Integer.parseInt(op4);
                operacion = op1 + "+" + op2 + "+" + op3 + "+" + op4 + "=" + String.valueOf(cantidad);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                operacion = "0";
                mensage = e.getMessage();
            }
            txv_resultado.setText(operacion);

           miMemoria.escribirExterna(this.OPERACIONES, operacion, false, this.CODIFICACION);


        }

    }
}
