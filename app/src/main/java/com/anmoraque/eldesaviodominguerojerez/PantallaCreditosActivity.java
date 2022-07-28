package com.anmoraque.eldesaviodominguerojerez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class PantallaCreditosActivity extends AppCompatActivity {

    private ImageView logo_linkedin;
    private ImageView logo_github;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_creditos);

        //Boton de ir atras
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.logo_linkedin = findViewById(R.id.logo_linkedin);
        this.logo_github = findViewById(R.id.logo_github);

        //Escucho los logos y le añado la URL mediante un Intent
        this.logo_linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url_linkedin = "https://www.linkedin.com/in/alberto-morales-ab373a235/";
                Intent intent_abrir_web = new Intent();
                intent_abrir_web.setAction(Intent.ACTION_VIEW);
                intent_abrir_web.setData(Uri.parse(url_linkedin));
                view.getContext().startActivity(intent_abrir_web);
            }
        });
        this.logo_github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url_linkedin = "https://github.com/anmoraque";
                Intent intent_abrir_web = new Intent();
                intent_abrir_web.setAction(Intent.ACTION_VIEW);
                intent_abrir_web.setData(Uri.parse(url_linkedin));
                view.getContext().startActivity(intent_abrir_web);
            }
        });
    }

    //Metodo para la accion del boton de ir atras
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home) {
            finish();
        }
        return true;
    }
}