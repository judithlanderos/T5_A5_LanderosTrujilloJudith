package com.example.t5_a5_landerotrujllojudith;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText txtNumeroaconvertir;
    TextView txtResultado;
    Spinner spinner, spinnerConvertido;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNumeroaconvertir = findViewById(R.id.txtNumeroaconvertir);
        txtResultado = findViewById(R.id.txtResultado);
        spinner = findViewById(R.id.spinner);
        spinnerConvertido = findViewById(R.id.spinnerConvertido);

        adapter = ArrayAdapter.createFromResource(this, R.array.temperaturas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinnerConvertido.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                convertirTemperatura();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerConvertido.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                convertirTemperatura();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void convertirTemperatura() {
            String unidadOrigen = spinner.getSelectedItem().toString();
            String unidadDestino = spinnerConvertido.getSelectedItem().toString();

            double valor;
            try {
                valor = Double.parseDouble(txtNumeroaconvertir.getText().toString());
            } catch (NumberFormatException e) {
                txtResultado.setText("Ingrese un número válido");
                return;
            }

            double resultado = convertir(valor, unidadOrigen, unidadDestino);

            String resultadoFormateado = String.format("%.2f", resultado);

            txtResultado.setText(resultadoFormateado);
        }


    private double convertir(double valor, String unidadOrigen, String unidadDestino) {
        double resultado = 0;

        if (unidadOrigen.equals("Fahrenheit")) {
            resultado = (valor - 32) * 5 / 9;
        } else if (unidadOrigen.equals("Rankine")) {
            resultado = (valor - 491.67) * 5 / 9;
        } else if (unidadOrigen.equals("Kelvin")) {
            resultado = valor - 273.15;
        } else {
            resultado = valor;
        }


        if (unidadDestino.equals("Fahrenheit")) {
            resultado = (resultado * 9 / 5) + 32;
        } else if (unidadDestino.equals("Rankine")) {
            resultado = (resultado + 273.15) * 9 / 5;
        } else if (unidadDestino.equals("Kelvin")) {
            resultado = resultado + 273.15;
        }

        return resultado;
    }
}
