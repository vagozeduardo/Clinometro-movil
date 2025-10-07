package com.labdesarrollodesoftware.inclinometro;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class calculo1 extends AppCompatActivity {

    // estructura para conectar las cosas de la interfaz y la clase
    // private tipo_de_objeto(ej. EditText,Button,etc) nombre_de_la_variable
    private EditText alturaC;
    private EditText anguloC;
    private EditText distanciaC;
    private TextView resultadoC;
    double distancia;

    DecimalFormat formatoDescimal = new DecimalFormat("0.####"); //formato de decimales (4 decimales opcionales)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculo1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.pantalla2), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        alturaC = (EditText) findViewById(R.id.idEditText_Altura);
        anguloC = (EditText) findViewById(R.id.idEditText_Angulo);
        distanciaC = (EditText) findViewById(R.id.idEditText_Distancia);
        resultadoC = (TextView) findViewById(R.id.idTxtView_salida);

        Intent intent = getIntent();
        if (intent.hasExtra("resultado")) {
            distancia = intent.getDoubleExtra("resultado", 0.0);
            distanciaC.setText("" + formatoDescimal.format(distancia));
        }

    }

    public void calcular(View v) {
        try {

            Double altura, angulo, resultado;

            altura = Double.parseDouble(alturaC.getText().toString());
            angulo = Double.parseDouble(anguloC.getText().toString());
            distancia = Double.parseDouble(distanciaC.getText().toString());

            //validadcion para evitar angulos sin sentido
            if (angulo >= 90){
                Toast.makeText(calculo1.this, "Ingresa una ángulo menor a 90°",Toast.LENGTH_LONG).show();
            }else {
                resultado = distancia * Math.tan(angulo * (Math.PI / 180));
                resultado += altura;

                resultadoC.setText("Altura del edificio: " + formatoDescimal.format(resultado));
            }

        } catch (Exception e) {
            Toast.makeText(calculo1.this, "Llena todos los campos", Toast.LENGTH_LONG).show();
        }
    }

    public void borrar(View v) {
        alturaC.setText("");
        anguloC.setText("");
        distanciaC.setText("");
        resultadoC.setText("Altura del edificio: ");
    }

    public void menuPrincipal(View v) {
        Intent Intent = new Intent(this, MainActivity.class);
        startActivity(Intent);
    }

}
