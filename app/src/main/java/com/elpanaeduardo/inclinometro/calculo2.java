package com.elpanaeduardo.inclinometro;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class calculo2 extends AppCompatActivity {
    private EditText alturaC;
    private EditText anguloC;
    private TextView resultadoC;
    private Button boton;
    double distancia = 0;


    DecimalFormat formatoDescimal = new DecimalFormat("0.####"); //formato de decimales (4 decimales opcionales)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculo2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.pantalla3), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        alturaC =  findViewById(R.id.idEditText_AlturaP3);
        anguloC =  findViewById(R.id.idEditText_AnguloP3);
        resultadoC = findViewById(R.id.idTxtView_salidaP3);
        boton = findViewById(R.id.idBtn_enviarDatos);
    }

    public void calcularp3(View v) {
        try {
            double altura, angulo;
            altura = Double.parseDouble(alturaC.getText().toString());
            angulo = Double.parseDouble(anguloC.getText().toString());

            // validacion para evitar angulos sin sentido
            if (angulo > 0 && angulo < 90) {
                distancia = altura / Math.tan(angulo * (Math.PI / 180));
                resultadoC.setText("Distancia: " + formatoDescimal.format(distancia));
                boton.setEnabled(true);
            } else {
                Toast.makeText(this, "El ángulo debe ser mayor a 0° y menor a 90°", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Toast.makeText(calculo2.this, "Llena todos los campos", Toast.LENGTH_LONG).show();
        }

    }


    public void borrarp3(View v) {
        alturaC.setText("");
        anguloC.setText("");
        resultadoC.setText("Distancia: ");
    }

    public void menup3(View v) {
        Intent I = new Intent(calculo2.this, MainActivity.class);
        startActivity(I);
    }

    public void pantalla1(View v) {
        Intent I = new Intent(calculo2.this, calculo1.class);
        I.putExtra("resultado", distancia);
        startActivity(I);
    }


}
