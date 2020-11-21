package com.example.personasmaterial;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
	private RecyclerView lista;
	private AdaptadorPersona adapter;
	private LinearLayoutManager llm;
	private ArrayList<Persona> personas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        lista = findViewById(R.id.lstPersonas);

        personas = Datos.obtener();
        llm = new LinearLayoutManager(this);
        adapter = new AdaptadorPersona(personas);
        llm.setOrientation(RecyclerView.VERTICAL);

        lista.setLayoutManager(llm);
        lista.setAdapter(adapter);
    }

    public void agregar(View v){
		Intent intent;
		intent = new Intent(MainActivity.this, CrearPersonas.class);
		startActivity(intent);
	}




}
