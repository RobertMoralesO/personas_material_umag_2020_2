package com.example.personasmaterial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class CrearPersonas extends AppCompatActivity {
	private EditText cedula, nombre, apellido;
	private ImageView foto;
	private Uri uri;
	private StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_personas);

        cedula = findViewById(R.id.txtCedula);
        nombre = findViewById(R.id.txtNombre);
        apellido = findViewById(R.id.txtApellido);
        foto = findViewById(R.id.imgFotoSeleccionada);

        storageReference = FirebaseStorage.getInstance().getReference();
    }

    public void guardar(View v){
    	String ced, nom, apell, id;
    	Persona p;
    	InputMethodManager imp;

    	ced = cedula.getText().toString();
    	nom = nombre.getText().toString();
    	apell = apellido.getText().toString();
    	id = Datos.getId();
    	imp = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

    	p = new Persona(ced,nom,apell,id);
    	p.guardar();
    	imp.hideSoftInputFromWindow(cedula.getWindowToken(),0);
		limpiar();
		subir_foto(id);
		Snackbar.make(v, R.string.mensaje_guardado_exitosamente,
				Snackbar.LENGTH_LONG).show();
	}

	public void subir_foto(String id){
    	StorageReference child = storageReference.child(id);
		UploadTask uploadTask = child.putFile(uri);
	}
	public void limpiar(View v){
    	limpiar();
	}
	public void limpiar(){
    	cedula.setText("");
    	nombre.setText("");
    	apellido.setText("");
    	cedula.requestFocus();
    	foto.setImageResource(android.R.drawable.ic_menu_gallery);
	}

	public void onBackPressed(){
	  finish();
	  Intent i = new Intent(CrearPersonas.this, MainActivity.class);
	  startActivity(i);
	}

	public void seleccionarFoto(View v){
    	Intent in = new Intent();
    	in.setType("image/*");
    	in.setAction(Intent.ACTION_GET_CONTENT);
    	startActivityForResult(Intent.createChooser(in,
				getString(R.string.seleccionar_foto)),
				1);
	}

	protected void onActivityResult(int requesCode, int resultCode, Intent data){
    	super.onActivityResult(requesCode, resultCode, data);
    	if(requesCode == 1){
    		uri = data.getData();
    		if(uri != null){
    			foto.setImageURI(uri);
			}
		}
	}
}
