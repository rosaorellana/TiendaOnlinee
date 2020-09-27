package com.example.tiendaonlinee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Guardar extends AppCompatActivity {
    TiendaOBD DatosTO;
    String accion = "nuevo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardar);

        Button btnMostrarProductos = (Button)findViewById(R.id.btnMostrarProductos);
        btnMostrarProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mostrarProductos = new Intent(Guardar.this, MainActivity.class);
                startActivity(mostrarProductos);
            }
        });

        Button btnGuardarProductos = (Button)findViewById(R.id.btnGuardarProductos);
        btnGuardarProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tempVal = (TextView)findViewById(R.id.txtNombreProducto);
                String nombre = tempVal.getText().toString();

                tempVal = (TextView)findViewById(R.id.txtDescripcionProducto);
                String descripcion = tempVal.getText().toString();

                tempVal = (TextView)findViewById(R.id.txtDistribuidorProducto);
                String distribuidor = tempVal.getText().toString();

                tempVal = (TextView)findViewById(R.id.txtCantidadProducto);
                String cantidad = tempVal.getText().toString();

                tempVal = (TextView)findViewById(R.id.txtPrecioProducto);
                String precio = tempVal.getText().toString();

                if (!nombre.isEmpty() && !descripcion.isEmpty() && !distribuidor.isEmpty() && !cantidad.isEmpty() && !precio.isEmpty() ){

                    String[] data = {"",nombre,descripcion,distribuidor,cantidad,precio};

                    DatosTO = new TiendaOBD(getApplicationContext(),"", null, 1);
                    DatosTO.mantenimientoProductos(accion, data);

                    Toast.makeText(getApplicationContext(),"Guardado", Toast.LENGTH_LONG).show();
                    Intent mostrarProductos = new Intent(Guardar.this, MainActivity.class);
                    startActivity(mostrarProductos);

                }else {
                    Toast.makeText(Guardar.this, "Aun no ha llenado todos los campos", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}