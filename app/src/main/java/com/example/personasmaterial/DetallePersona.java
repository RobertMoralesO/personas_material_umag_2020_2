package com.example.personasmaterial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetallePersona extends AppCompatActivity {
	private ImageView foto;
	private TextView cedula, nombre, apellido;
	private Bundle bundle;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle_persona);

		String ced, nomb, apell;
		foto = findViewById(R.id.imgFotoDetalle);
		cedula = findViewById(R.id.lblCedulaDetalle);
		nombre = findViewById(R.id.lblNombreDetalle);
		apellido = findViewById(R.id.lblApellidoDetalle);

		intent = getIntent();
		bundle = intent.getBundleExtra("datos");

		ced = bundle.getString("cedula");
		nomb = bundle.getString("nombre");
		apell = bundle.getString("apellido");

		cedula.setText(ced);
		nombre.setText(nomb);
		apellido.setText(apell);
	}
}
