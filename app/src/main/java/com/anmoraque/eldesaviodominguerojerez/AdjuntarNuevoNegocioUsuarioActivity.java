package com.anmoraque.eldesaviodominguerojerez;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AdjuntarNuevoNegocioUsuarioActivity extends AppCompatActivity implements View.OnFocusChangeListener {

    // ⭐️ Nuevo: Código de solicitud para identificar el Intent de correo
    private static final int EMAIL_REQUEST_CODE = 101;

    private EditText editTextNombre, editTextNegocio, editTextDireccion, editTextTelefono, editTextEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjuntar_nuevo_negocio_usuario);


        // Inicializar la Toolbar y habilitar el botón de retroceso
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
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

    // ⭐️ Nuevo método: Se llama cuando una actividad iniciada con startActivityForResult termina
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Verifica que la respuesta provenga de nuestro Intent de correo
        if (requestCode == EMAIL_REQUEST_CODE) {
            // No importa realmente el resultCode para un Intent de correo (siempre es RESULT_CANCELED)
            // Lo importante es que la aplicación de correo se ha abierto y el usuario regresa.

            // ⭐️ Reiniciar la actividad para limpiar el formulario
            Intent reinicioIntent = getIntent();
            finish();
            startActivity(reinicioIntent);

            // Opcional: Mostrar el Toast de "Enviado" aquí si quieres que aparezca al volver
            Toast.makeText(this, R.string.toast_form_sent, Toast.LENGTH_SHORT).show();
        }
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
                    if (!esNombreValido()) editTextNombre.setError(getString(R.string.error_nombre_valido));
                    break;
                case R.id.editTextNegocio:
                    if (!esNombreNegocioValido()) editTextNegocio.setError(getString(R.string.error_nombre_negocio_valido));
                    break;
                case R.id.editTextDireccion:
                    if (!esDireccionValida()) editTextDireccion.setError(getString(R.string.error_direccion_valida));
                    break;
                case R.id.editTextTelefono:
                    if (!esTelefonoValido()) editTextTelefono.setError(getString(R.string.error_telefono_valido));
                    break;
                case R.id.editTextEmail:
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

    /**
     * Lanza un Intent de correo usando startActivityForResult.
     */
    public void mostrarDatosAdjuntarNegocioNuevo(View view) {
        if (!esFormularioValido()) {
            Toast.makeText(this, R.string.toast_form_not_valid, Toast.LENGTH_SHORT).show();
            return;
        }

        // Construcción del cuerpo del mensaje
        String enviar_mensaje = "--- Datos del Nuevo Negocio ---\n\n"
                + "Nombre del solicitante: " + editTextNombre.getText().toString() + "\n"
                + "Nombre del negocio: " + editTextNegocio.getText().toString() + "\n"
                + "Dirección: " + editTextDireccion.getText().toString() + "\n"
                + "Teléfono: " + editTextTelefono.getText().toString() + "\n"
                + "Email de contacto: " + editTextEmail.getText().toString() + "\n\n"
                + "--------------------------------";

        // Obtener Asunto y Correo de Destino
        String enviar_asunto = getString(R.string.email_subject_new_business);
        String enviar_correo = getString(R.string.email_destination_new_business);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{enviar_correo});
        intent.putExtra(Intent.EXTRA_SUBJECT, enviar_asunto);
        intent.putExtra(Intent.EXTRA_TEXT, enviar_mensaje);

        try {
            // ⭐️ CAMBIO CLAVE: Lanzamos el Intent esperando un resultado
            startActivityForResult(Intent.createChooser(intent, getString(R.string.chooser_title_email)), EMAIL_REQUEST_CODE);

            // Eliminamos el Toast y el reinicio de aquí. Se mueven a onActivityResult.

        } catch (android.content.ActivityNotFoundException ex) {
            // Manejamos el error si no hay app de correo configurada
            Toast.makeText(this, "No se encontró una aplicación de correo configurada. Por favor, configura una app de correo.", Toast.LENGTH_LONG).show();
        }
    }
}