package com.anmoraque.eldesaviodominguerojerez;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.appbar.MaterialToolbar;

public class PantallaCreditosActivity extends AppCompatActivity {

    private ImageView logoLinkedIn;
    private ImageView logoGitHub;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_creditos);

        // Forzar modo claro y asegurar compatibilidad con insets
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getWindow().setDecorFitsSystemWindows(true);

        // Configurar toolbar
        MaterialToolbar toolbar = findViewById(R.id.toolbar_creditos);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.toolbar_title_credits);
        }

        // Forzar color de la status bar igual al de la toolbar
        getWindow().setStatusBarColor(
                getResources().getColor(R.color.md_theme_light_primaryContainer, getTheme())
        );

        // Listeners de los iconos
        logoLinkedIn = findViewById(R.id.logo_linkedin);
        logoGitHub = findViewById(R.id.logo_github);

        logoLinkedIn.setOnClickListener(v ->
                abrirEnlace("https://www.linkedin.com/in/alberto-morales-ab373a235/"));
        logoGitHub.setOnClickListener(v ->
                abrirEnlace("https://github.com/anmoraque"));
    }

    private void abrirEnlace(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(Intent.createChooser(intent, getString(R.string.chooser_title_open_with)));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
