package com.anmoraque.eldesaviodominguerojerez;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AdjuntarNuevoNegocioUsuarioActivity extends AppCompatActivity implements View.OnFocusChangeListener {

    private EditText editTextNombre, editTextNegocio, editTextDireccion, editTextTelefono, editTextEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjuntar_nuevo_negocio_usuario);


        // ✅ Inicializar la Toolbar y habilitar el botón de retroceso
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            // Si quieres que la Toolbar tenga título, puedes añadirlo aquí:
            // getSupportActionBar().setTitle(R.string.toolbar_title_add_business);
        }

        // Campos del formulario
        editTextNombre = findViewById(R.id.editTextNombre);
        editTextNegocio = findViewById(R.id.editTextNegocio);
        editTextDireccion = findViewById(R.id.editTextDireccion);
        editTextTelefono = findViewById(R.id.editTextTelefono);
        editTextEmail = findViewById(R.id.editTextEmail);

        // Listeners
        editTextNombre.setOnFocusChangeListener(this);
        editTextNegocio.setOnFocusChangeListener(this);
        editTextDireccion.setOnFocusChangeListener(this);
        editTextTelefono.setOnFocusChangeListener(this);
        editTextEmail.setOnFocusChangeListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus) {
            switch (v.getId()) {
                case R.id.editTextNombre:
                    // MODIFICADO: Usar string resource para el setError
                    if (!esNombreValido()) editTextNombre.setError(getString(R.string.error_nombre_valido));
                    break;
                case R.id.editTextNegocio:
                    // MODIFICADO: Usar string resource para el setError
                    if (!esNombreNegocioValido()) editTextNegocio.setError(getString(R.string.error_nombre_negocio_valido));
                    break;
                case R.id.editTextDireccion:
                    // MODIFICADO: Usar string resource para el setError
                    if (!esDireccionValida()) editTextDireccion.setError(getString(R.string.error_direccion_valida));
                    break;
                case R.id.editTextTelefono:
                    // MODIFICADO: Usar string resource para el setError
                    if (!esTelefonoValido()) editTextTelefono.setError(getString(R.string.error_telefono_valido));
                    break;
                case R.id.editTextEmail:
                    // MODIFICADO: Usar string resource para el setError
                    if (!esEmailValido()) editTextEmail.setError(getString(R.string.error_email_valido));
                    break;
            }
        }
    }

    private boolean esNombreValido() {
        String nombre = editTextNombre.getText().toString().trim();
        return !TextUtils.isEmpty(nombre) && nombre.matches("[a-zA-Z áéíóúÁÉÍÓÚñÑ]+");
    }

    private boolean esNombreNegocioValido() {
        String nombreNegocio = editTextNegocio.getText().toString().trim();
        return !TextUtils.isEmpty(nombreNegocio) && nombreNegocio.matches("[a-zA-Z áéíóúÁÉÍÓÚñÑ]+");
    }

    private boolean esDireccionValida() {
        String direccion = editTextDireccion.getText().toString().trim();
        return !TextUtils.isEmpty(direccion) && direccion.matches("[a-zA-Z0-9,.()áéíóúÁÉÍÓÚñÑ\\s]+");
    }

    private boolean esTelefonoValido() {
        String telefono = editTextTelefono.getText().toString().trim();
        return !TextUtils.isEmpty(telefono) && Patterns.PHONE.matcher(telefono).matches();
    }

    private boolean esEmailValido() {
        String email = editTextEmail.getText().toString().trim();
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean esFormularioValido() {
        return esNombreValido() && esNombreNegocioValido() && esDireccionValida() && esTelefonoValido() && esEmailValido();
    }

    public void mostrarDatosAdjuntarNegocioNuevo(View view) {
        if (!esFormularioValido()) {
            // MODIFICADO: Usar string resource para el Toast de formulario no válido
            Toast.makeText(this, R.string.toast_form_not_valid, Toast.LENGTH_SHORT).show();
            return;
        }

        // MODIFICADO: Ahora el mensaje usa \n
        String enviar_mensaje = editTextNombre.getText() + "\n"
                + editTextNegocio.getText() + "\n"
                + editTextDireccion.getText() + "\n"
                + editTextTelefono.getText() + "\n"
                + editTextEmail.getText();

        // MODIFICADO: Usar string resource para el asunto
        String enviar_asunto = getString(R.string.email_subject_new_business);

        // MODIFICADO: Usar string resource para el correo de destino (OPCIONAL, pero recomendado)
        String enviar_correo = getString(R.string.email_destination_new_business);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(android.net.Uri.parse("mailto:" + enviar_correo));
        intent.putExtra(Intent.EXTRA_SUBJECT, enviar_asunto);
        intent.putExtra(Intent.EXTRA_TEXT, enviar_mensaje);

        // MODIFICADO: Usar string resource para el título del selector de correo
        startActivity(Intent.createChooser(intent, getString(R.string.chooser_title_email)));

        // MODIFICADO: Usar string resource para el Toast de formulario enviado
        Toast.makeText(this, R.string.toast_form_sent, Toast.LENGTH_SHORT).show();
    }
}