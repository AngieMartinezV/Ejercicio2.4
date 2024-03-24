package com.example.ejercicio24;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ejercicio24.Configuracion.SQLiteConexion;
import com.example.ejercicio24.Configuracion.Transaccion;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    EditText descripcion;
    Button btnSlvar, btnList;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        descripcion = (EditText) findViewById(R.id.txtdescripcion);
        view = (View) findViewById(R.id.viewfirma);
        btnSlvar = (Button)findViewById(R.id.btnSalvar);
        btnList = (Button)findViewById(R.id.btnLista);

        btnSlvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardaFirma();
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityList.class);
                startActivity(intent);
            }
        });
    }

    private void guardaFirma() {
        SQLiteConexion conexion = new SQLiteConexion(this, Transaccion.NameDataBase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        try {
            String descripcionText = descripcion.getText().toString();

            // Validar que haya una descripción antes de guardar
            if (descripcionText.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Por favor, ingresa una descripción antes de guardar", Toast.LENGTH_LONG).show();
                return; // Salir del método si no hay descripción
            }

            ContentValues valores = new ContentValues();

            valores.put(Transaccion.image, ViFirma(view));
            valores.put(Transaccion.descripcion, descripcionText);

            Long resultado = db.insert(Transaccion.tablafirmas, Transaccion.id, valores);

            Toast.makeText(getApplicationContext(), "FIRMA REGISTRADA: " + resultado.toString(), Toast.LENGTH_LONG).show();
            descripcion.setText("");
            view.setDrawingCacheEnabled(false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private byte[] ViFirma(View view2) {
        view2.setDrawingCacheEnabled(true);
        Bitmap bitmap = view2.getDrawingCache();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    private void ClearScreen() {
        descripcion.setText("");
        view.setDrawingCacheEnabled(false);
    }
}