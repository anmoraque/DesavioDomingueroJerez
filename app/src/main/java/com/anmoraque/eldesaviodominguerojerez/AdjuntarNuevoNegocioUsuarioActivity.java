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
import android.widget.ProgressBar; // ⭐️ NUEVA IMPORTACIÓN
import android.widget.Toast;

import android.os.Handler;
import android.os.Looper;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AdjuntarNuevoNegocioUsuarioActivity extends AppCompatActivity implements View.OnFocusChangeListener {

    // 🚨 TU URL DE GOOGLE APPS SCRIPT
    private static final String SCRIPT_URL = "https://script.google.com/macros/s/AKfycbxhceiQ4_4FTXpiPlwCvWtqpI8UxCdqLrb1pO3uRtaJs4XF7UERrInqRMIWFHoDyU7r/exec";

    private EditText editTextNombre, editTextNegocio, editTextDireccion, editTextTelefono, editTextEmail;
    private ProgressBar progressBarLoading; // ⭐️ DECLARACIÓN DEL INDICADOR

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

        // ⭐️ INICIALIZACIÓN DEL INDICADOR
        progressBarLoading = findViewById(R.id.progressBarLoading);

        // Listeners
        editTextNombre.setOnFocusChangeListener(this);
        editTextNegocio.setOnFocusChangeListener(this);
        editTextDireccion.setOnFocusChangeListener(this);
        editTextTelefono.setOnFocusChangeListener(this);
        editTextEmail.setOnFocusChangeListener(this);
    }

    // ⭐️ ELIMINADO: onActivityResult (ya no se usa)

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
     * Reemplazo del Intent de correo.
     * Ahora recopila los datos, muestra el indicador y llama a sendData.
     */
    public void mostrarDatosAdjuntarNegocioNuevo(View view) {
        if (!esFormularioValido()) {
            Toast.makeText(this, R.string.toast_form_not_valid, Toast.LENGTH_SHORT).show();
            return;
        }

        // ⭐️ CONTROL DE UI INICIO: Mostrar barra de progreso y deshabilitar botón
        progressBarLoading.setVisibility(View.VISIBLE);
        view.setEnabled(false); // Deshabilita el botón de enviar

        // 1. Recopilar datos
        final String nombre = editTextNombre.getText().toString();
        final String negocio = editTextNegocio.getText().toString();
        final String direccion = editTextDireccion.getText().toString();
        final String telefono = editTextTelefono.getText().toString();
        final String email = editTextEmail.getText().toString();

        // 2. Ejecutar la tarea de envío en un hilo de fondo (ExecutorService)
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            // Tarea en segundo plano
            boolean success = sendData(nombre, negocio, direccion, telefono, email);

            // Volver al hilo principal para actualizar la UI (Mostrar Toast y ocultar ProgressBar)
            handler.post(() -> {

                // ⭐️ CONTROL DE UI FIN: Ocultar barra de progreso y rehabilitar botón
                progressBarLoading.setVisibility(View.GONE);
                view.setEnabled(true);

                if (success) {
                    Toast.makeText(AdjuntarNuevoNegocioUsuarioActivity.this, R.string.toast_form_sent, Toast.LENGTH_SHORT).show();

                    // Reiniciar la actividad para limpiar el formulario
                    Intent reinicioIntent = getIntent();
                    finish();
                    startActivity(reinicioIntent);

                } else {
                    Toast.makeText(AdjuntarNuevoNegocioUsuarioActivity.this, R.string.toast_error_network, Toast.LENGTH_LONG).show();
                }
            });
        });
    }

    /**
     * Realiza la conexión HTTP y el envío de datos al Google Apps Script.
     * @return true si la conexión fue exitosa (HTTP 200), false en caso contrario.
     */
    private boolean sendData(String nombre, String negocio, String direccion, String telefono, String email) {
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(SCRIPT_URL);
            urlConnection = (HttpURLConnection) url.openConnection();

            // 1. Configurar la conexión para una petición POST
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true); // Indica que vamos a enviar datos
            urlConnection.setDoInput(true);
            urlConnection.setInstanceFollowRedirects(true);
            urlConnection.setReadTimeout(15000);
            urlConnection.setConnectTimeout(15000);

            // 2. Construir la cadena de datos a enviar
            String postData = "nombre=" + URLEncoder.encode(nombre, "UTF-8")
                    + "&negocio=" + URLEncoder.encode(negocio, "UTF-8")
                    + "&direccion=" + URLEncoder.encode(direccion, "UTF-8")
                    + "&telefono=" + URLEncoder.encode(telefono, "UTF-8")
                    + "&email=" + URLEncoder.encode(email, "UTF-8");

            // 3. Enviar los datos
            try (OutputStream os = urlConnection.getOutputStream()) {
                byte[] input = postData.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // 4. Leer la respuesta del script
            int responseCode = urlConnection.getResponseCode();

            // Si el código de respuesta es 200 (OK), el envío fue exitoso.
            if (responseCode == HttpURLConnection.HTTP_OK) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }
}