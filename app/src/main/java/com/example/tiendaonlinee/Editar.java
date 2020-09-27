package com.example.tiendaonlinee;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Editar extends AppCompatActivity {
    TiendaOBD DatosTO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        recibirdatos();

        Button btnModificarProductos = (Button)findViewById(R.id.btnModificarProductos);
        btnModificarProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String accion = "modificar";

                TextView tempVal = (TextView)findViewById(R.id.txtNombreProducto1);
                String nombre = tempVal.getText().toString();

                tempVal = (TextView)findViewById(R.id.txtIdProducto);
                String id = tempVal.getText().toString();

                tempVal = (TextView)findViewById(R.id.txtDescripcionProducto1);
                String descripcion = tempVal.getText().toString();

                tempVal = (TextView)findViewById(R.id.txtDistribuidorProducto1);
                String distribuidor = tempVal.getText().toString();

                tempVal = (TextView)findViewById(R.id.txtCantidadProducto1);
                String cantidad = tempVal.getText().toString();

                tempVal = (TextView)findViewById(R.id.txtPrecioProducto1);
                String precio = tempVal.getText().toString();

                if (!nombre.isEmpty() && !descripcion.isEmpty() && !distribuidor.isEmpty() && !cantidad.isEmpty() && !precio.isEmpty() ){

                    String[] data = {id,nombre,descripcion,distribuidor,cantidad,precio};

                    DatosTO = new TiendaOBD(getApplicationContext(),"", null, 1);
                    DatosTO.mantenimientoProductos(accion, data);

                    Toast.makeText(getApplicationContext(),"Editado", Toast.LENGTH_LONG).show();
                    Intent mostrarProductos = new Intent(Editar.this, MainActivity.class);
                    startActivity(mostrarProductos);

                }else {
                    Toast.makeText(Editar.this, "Aun no ha llenado todos los campos", Toast.LENGTH_SHORT).show();
                }


            }
        });

        Button btnEliminarProductos = (Button)findViewById(R.id.btnBorrarProductos);
        btnEliminarProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String valor = getIntent().getExtras().getString("objetoData");
                String[] tienda = valor.split("\n");


                AlertDialog.Builder builder = new AlertDialog.Builder(Editar.this);
                builder.setTitle("ELIMINAR");
                builder.setMessage("¿Eliminar Producto?"+ tienda[0]);

                builder.setPositiveButton("ELIMINAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Eliminar();
                    }
                });
                builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });




    }

    private void Eliminar(){
        String accion = "eliminar";

        TextView tempVal = (TextView) findViewById(R.id.txtIdProducto);
        String id = tempVal.getText().toString();

        if (!id.isEmpty()) {

            String[] data = {id};

            DatosTO = new TiendaOBD(getApplicationContext(), "", null, 1);
            DatosTO.mantenimientoProductos(accion, data);

            Toast.makeText(getApplicationContext(), "Producto Eliminado", Toast.LENGTH_LONG).show();
            Intent mostrarProductos = new Intent(Editar.this, MainActivity.class);
            startActivity(mostrarProductos);

        } else {
            Toast.makeText(Editar.this, "Ha ocurrido un error al eliminar", Toast.LENGTH_SHORT).show();
        }


    }
    private void recibirdatos(){ ;

        String valor = getIntent().getExtras().getString("objetoData");

        TextView id = (TextView) findViewById((R.id.txtIdProducto));

        TextView nombre = (TextView) findViewById(R.id.txtNombreProducto1);

        TextView descripcion = (TextView) findViewById(R.id.txtDescripcionProducto1);

        TextView distribuidor = (TextView) findViewById(R.id.txtDistribuidorProducto1);

        TextView cantidad = (TextView) findViewById(R.id.txtCantidadProducto1);

        TextView precio = (TextView) findViewById(R.id.txtPrecioProducto1);

        String[] tienda = valor.split("\n");

        id.setText(tienda[5]);
        nombre.setText(tienda[0]);
        descripcion.setText(tienda[1]);
        distribuidor.setText(tienda[2]);
        cantidad.setText(tienda[3]);
        precio.setText(tienda[4]);
    }
}