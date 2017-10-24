package com.example.usuario.ficheros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button Eje1;
    private Button Eje2;
    private Button Eje3;
    private Button Eje4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.Eje1 = (Button) findViewById(R.id.Eje1);
        this.Eje2 = (Button) findViewById(R.id.Eje2);
        this.Eje3 = (Button) findViewById(R.id.Eje3);
        this.Eje4 = (Button) findViewById(R.id.Eje4);

        this.Eje1.setOnClickListener(this);
        this.Eje2.setOnClickListener(this);
        this.Eje3.setOnClickListener(this);
        this.Eje4.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        Intent Ejercicio;

        switch (view.getId()) {
            case R.id.Eje1:
                Ejercicio = new Intent(MainActivity.this, EscribirInternaActivity.class);
                startActivity(Ejercicio);
                break;
            case R.id.Eje2:
                Ejercicio = new Intent(MainActivity.this, EscribirExternaActivity.class);
                startActivity(Ejercicio);
                break;
            case R.id.Eje3:
                Ejercicio = new Intent(MainActivity.this, LeerMemoriaActivity.class);
                startActivity(Ejercicio);
                break;
        }
    }
}
