package com.example.tiendaonlinee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TiendaOBD DatosTO;
    Cursor ProductosTO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        obtenerDatosProductos();

        ImageButton ibmostrar = (ImageButton) findViewById(R.id.ibmostrar);
        ibmostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AgregarProductos = new Intent(MainActivity.this, Guardar.class);
                startActivity(AgregarProductos);
            }
        });

    }
    void obtenerDatosProductos(){
        DatosTO = new TiendaOBD(getApplicationContext(), "", null, 1);
        ProductosTO = DatosTO.mantenimientoProductos("consultar", null);
        if( ProductosTO.moveToFirst() ){
            mostrarDatosProductos();
        } else{
            Toast.makeText(getApplicationContext(), "Ningun Registro",Toast.LENGTH_LONG).show();
            Intent agregarProductos = new Intent(MainActivity.this, Guardar.class);
            startActivity(agregarProductos);
        }
    }

    void mostrarDatosProductos(){
        ListView ltsProductos = (ListView)findViewById(R.id.ltsProductos);
        final ArrayList<String> stringArrayList = new ArrayList<String>();

        final ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,stringArrayList);
        ltsProductos.setAdapter(stringArrayAdapter);


        do {
            stringArrayList.add( ProductosTO.getString(1) + "\n" + ProductosTO.getString(2) + "\n" + ProductosTO.getString(3)+ "\n" + ProductosTO.getString(4) + "\n" + ProductosTO.getString(5)+ "\n" + ProductosTO.getString(0) + "\n" );

        }while(ProductosTO.moveToNext());
        stringArrayAdapter.notifyDataSetChanged();


        TextView BuscarProductos = (TextView) findViewById(R.id.txtBuscarProductos);
        BuscarProductos.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                stringArrayAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        ltsProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent mostrarProductos = new Intent(MainActivity.this, Editar.class);
                mostrarProductos.putExtra("objetoData", stringArrayList.get(i));
                startActivity(mostrarProductos);
            }
        });
    }
}