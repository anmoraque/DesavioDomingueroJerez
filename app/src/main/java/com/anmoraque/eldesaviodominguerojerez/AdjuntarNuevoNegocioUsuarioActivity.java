package com.anmoraque.eldesaviodominguerojerez;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.List;
import java.util.regex.Pattern;

public class AdjuntarNuevoNegocioUsuarioActivity extends AppCompatActivity implements View.OnFocusChangeListener{

    private EditText editTextNombre;
    private EditText editTextNegocio;
    private EditText editTextDireccion;
    private EditText editTextTelefono;
    private EditText editTextEmail;
    private boolean formularioValido;

    /**
     * Crea la actividad principal
     * @param savedInstanceState inicia la actividad
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjuntar_nuevo_negocio_usuario);
        //Boton de ir atras
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.iniciarActividad();

    }

    // Metodo para la accion del boton de ir atras
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home) {
            finish();
        }
        return true;
    }

    /**
     * Carga todas las vistas y llama al metodo onFocusChange
     */
    private void iniciarActividad()
    {
        //cargar las vistas
        //con this, me estoy refiriendo a la propia pantalla que estoy visualizando en este momento
        this.editTextNombre = findViewById(R.id.editTextNombre);
        this.editTextNegocio = findViewById(R.id.editTextNegocio);
        this.editTextDireccion = findViewById(R.id.editTextDireccion);
        this.editTextTelefono = findViewById(R.id.editTextTelefono);
        this.editTextEmail = findViewById(R.id.editTextEmail);

        //cuando cambie el foco sobre este elemento, llamas al método onFocusChange de esta clase
        this.editTextNombre.setOnFocusChangeListener(this);
        this.editTextNegocio.setOnFocusChangeListener(this);
        this.editTextDireccion.setOnFocusChangeListener(this);
        this.editTextTelefono.setOnFocusChangeListener(this);
        this.editTextEmail.setOnFocusChangeListener(this);
    }

    /**
     * Valida el editText del teléfono
     * @return teléfono validado
     */
    private boolean esTelefonoValido ()
    {
        boolean telefono_valido = false;

        String telefono = this.editTextTelefono.getText().toString();
        //con esta expresión regular, estoy diciendo, que el telefono es válido
        //si tiene caracteres del 0 al 9 (dígitos) y al menos uno (+)
        Pattern pattern = Patterns.PHONE;
        telefono_valido = pattern.matcher(telefono).matches();

        return telefono_valido;
    }

    /**
     * Valida el editText del nombre
     * @return nombre validado
     */
    private boolean esNombreValido ()
    {
        boolean nombre_valido = false;

        String nombre = this.editTextNombre.getText().toString();
        //con esta expresión regular, estoy diciendo, que el nombre es válido
        //si tiene caracteres del alfabéticos de la a la z mayusculas o minusuclas y espacios y al menos uno (+)
        nombre_valido = (nombre!=null) && nombre.matches("[a-zA-Z áéíóúÁÉÍÓÚñÑ\\s]+");

        return nombre_valido;
    }

    /**
     * Valida el editText del nombre del negocio
     * @return nombre del negocio validado
     */
    private boolean esNombreNegocioValido ()
    {
        boolean nombre_negocio_valido = false;

        String nombre_negocio = this.editTextNegocio.getText().toString();
        //con esta expresión regular, estoy diciendo, que el nombre del negocio es válido
        //si tiene caracteres del alfabéticos de la a la z mayusculas o minusuclas y espacios y al menos uno (+)
        nombre_negocio_valido = (nombre_negocio!=null) && nombre_negocio.matches("[a-zA-Z áéíóúÁÉÍÓÚñÑ\\s]+");

        return nombre_negocio_valido;
    }

    /**
     * Valida el editText de la dirección
     * @return dirección validada
     */
    private boolean esDireccionValida ()
    {
        boolean direccion_valida = false;

        String direccion = this.editTextDireccion.getText().toString();
        //con esta expresión regular, estoy diciendo, que la direccion es válido
        //si tiene caracteres del alfabéticos de la a la z mayusculas o minusculas y espacios y al menos uno (+)
        direccion_valida = (direccion!=null) && direccion.matches("[a-zA-Z0-9,.()áéíóúÁÉÍÓÚñÑ\\s]+");

        return direccion_valida;
    }

    /**
     * Valida el editText del email
     * @return email validado
     */
    private boolean esEmailValido ()
    {
        boolean email_valido = false;

        String email = this.editTextEmail.getText().toString();
        //con esta expresión regular, estoy diciendo, que el email es válido
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        email_valido = pattern.matcher(email).matches();

        return email_valido;
    }

    /**
     * Valida el formulario completo
     * @return formulario validado
     */
    private boolean esFormularioValido ()
    {
        //LOS BOOLEAN SE INICIALIZAN A FALSE POR DEFECTO
        boolean formulario_valido = false;
        boolean nombre_valido = false;
        boolean nombre_negocio_valido = false;
        boolean direccion_valida = false;
        boolean email_valido = false;
        boolean telefono_valido = false;

        nombre_valido = esNombreValido();
        nombre_negocio_valido = esNombreNegocioValido();
        direccion_valida = esDireccionValida();
        email_valido = esEmailValido();
        telefono_valido = esTelefonoValido();

        if (!telefono_valido) //telefono_valido == false
        {
            this.editTextTelefono.setError("Introduce un número correcto");
        }

        if (!nombre_valido)
        {
            this.editTextNombre.setError("Introduce un nombre correcto");
        }

        if (!nombre_negocio_valido)
        {
            this.editTextNegocio.setError("Introduce un nombre de negocio correcto");
        }

        if (!direccion_valida)
        {
            this.editTextDireccion.setError("Introduce una dirección correcta");
        }

        if (!email_valido)
        {
            this.editTextEmail.setError("Introduce un email correcto");
        }

        formulario_valido = telefono_valido && nombre_valido && nombre_negocio_valido && direccion_valida && email_valido;

        return formulario_valido;
    }

    /**
     * Envia el formulario una vez validado
     * @param view Envia mediante el boton "ACEPTAR" un email con los datos añadidos al formulario
     */
    public void mostrarDatosAdjuntarNegocioNuevo(View view) {
        Log.d("ETIQUETA_LOG", "Nombre intro = " + this.editTextNombre.getText().toString());
        Log.d("ETIQUETA_LOG", "Nombre Negocio intro = " + this.editTextNegocio.getText().toString());
        Log.d("ETIQUETA_LOG", "Direccion intro = " + this.editTextDireccion.getText().toString());
        Log.d("ETIQUETA_LOG", "Telefono intro = " + this.editTextTelefono.getText().toString());
        Log.d("ETIQUETA_LOG", "Email intro = " + this.editTextEmail.getText().toString());

        formularioValido = this.esFormularioValido();
        Log.d("ETIQUETA_LOG", "ES VALIDO despues de Aceptar = "+ formularioValido);
        //si el formulario es válido, vamos a mandar un email con los datos introducidos
        if (formularioValido == true){
            Toast.makeText(this, "Formulario enviado", Toast.LENGTH_SHORT).show();
            String nombre = this.editTextNombre.getText().toString();
            String nombre_negocio = this.editTextNegocio.getText().toString();
            String direccion = this.editTextDireccion.getText().toString();
            String telefono = this.editTextTelefono.getText().toString();
            String email = this.editTextEmail.getText().toString();

            String enviar_mensaje = nombre +"\n"+ nombre_negocio +"\n"+ direccion +"\n"+ telefono +"\n"+ email;
            String enviar_asunto = "Cliente que quiere añadir negocio nuevo";
            String enviar_correo = "ansmorales@gmail.com";
            // Defino mi Intent y hago uso del objeto ACTION_SEND
            Intent intent = new Intent(Intent.ACTION_SEND);

            // Defino los Strings Email, Asunto y Mensaje con la función putExtra
            intent.putExtra(Intent.EXTRA_EMAIL,
                    new String[] { enviar_correo });
            intent.putExtra(Intent.EXTRA_SUBJECT, enviar_asunto);
            intent.putExtra(Intent.EXTRA_TEXT, enviar_mensaje);

            // Establezco el tipo de Intent
            intent.setType("message/rfc822");

            // Lanzo el selector de cliente de Correo
            startActivity(
                    Intent
                            .createChooser(intent,
                                    "Elije un cliente de Correo:"));
        }else{
            Toast.makeText(this, "Formulario no valido", Toast.LENGTH_SHORT).show();
        }



    }

    /**
     * Es requerido para usar el focus
     * @param v son las vistas de la actividad
     * @param hasFocus pone el foco en cada vista que toques
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {

    }
}