package com.example.ejercicio24;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ejercicio24.Configuracion.SQLiteConexion;
import com.example.ejercicio24.Configuracion.Signature;
import com.example.ejercicio24.Configuracion.Transaccion;

import java.util.ArrayList;
import java.util.List;

public class ActivityList extends AppCompatActivity {

    RecyclerView recycler;

    ArrayList<Signature> lista;
    List<Signature> items;

    Button btnregresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        lista = new ArrayList<>();

        GetLista();
        recycler = (RecyclerView) findViewById(R.id.list);
        btnregresar = (Button) findViewById(R.id.btnRegresar);

        recycler.setLayoutManager(new LinearLayoutManager(this));

        Adaptador adapter = new Adaptador(items);
        recycler.setAdapter(adapter);

        btnregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void GetLista() {
        SQLiteConexion conexion = new SQLiteConexion(this, Transaccion.NameDataBase, null, 1);
        SQLiteDatabase db = conexion.getReadableDatabase();
        Signature Items = null;
        lista = new ArrayList<Signature>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Transaccion.tablafirmas, null);

        while (cursor.moveToNext()) {
            Items = new Signature();
            Items.setId(cursor.getInt(0));
            Items.setImage(cursor.getBlob(1));
            Items.setDescripcion(cursor.getString(2));

            lista.add(Items);
        }

        cursor.close();
        ListaFirmas();
    }

    private void ListaFirmas() {
        items = new ArrayList<>();

        for (int i = 0;  i < lista.size(); i++){

                items.add(new Signature(
                    lista.get(i).getId(),
                    lista.get(i).getImage(),
                    lista.get(i).getDescripcion()));
        }
    }
}