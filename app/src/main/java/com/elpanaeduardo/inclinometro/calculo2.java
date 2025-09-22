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

public class calculo2 extends AppCompatActivity {
    private EditText alturaC;
    private EditText anguloC;
    private TextView resultadoC;
    private Button boton;
    double distancia = 0;


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

        alturaC = (EditText) findViewById(R.id.idEditText_AlturaP3);
        anguloC = (EditText) findViewById(R.id.idEditText_AnguloP3);
        resultadoC = (TextView) findViewById(R.id.idTxtView_salidaP3);
        boton = (Button) findViewById(R.id.idBtn_enviarDatos);
    }

    public void calcularp3(View v){
        try {
            double altura,angulo;
            altura = Double.parseDouble(alturaC.getText().toString());
            angulo = Double.parseDouble(anguloC.getText().toString());

            distancia = altura/Math.tan(angulo*(Math.PI/180));
            resultadoC.setText("Distancia: "+String.format("%.4f",distancia));

            if (distancia != 0){
                boton.setEnabled(true);
            }


        }catch (Exception e){
            Toast.makeText(calculo2.this, "hay un error ._.", Toast.LENGTH_LONG).show();
        }

    }


    public void borrarp3(View v){
        alturaC.setText("");
        anguloC.setText("");
        resultadoC.setText("Distancia: ");
    }

    public void menup3(View v){
        Intent I = new Intent(calculo2.this, MainActivity.class);
        startActivity(I);
    }

    public void pantalla1(View v){
        Intent I = new Intent(calculo2.this, calculo1.class);
        I.putExtra("resultado",distancia);
        startActivity(I);
    }


}
