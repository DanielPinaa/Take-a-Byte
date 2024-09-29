package es.upm.etsiinf.proyectoPMD;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.content.res.Configuration;
import android.widget.Toast;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import java.util.Calendar;
import java.util.Locale;
import java.text.SimpleDateFormat;

public class ReservarMesa extends AppCompatActivity {

    private Spinner spinnerPersonas;
    private Spinner spinnerHora;
    private EditText editTextFecha;
    private Calendar fechaSeleccionada;

    String personasSeleccionadas;
    String horaSeleccionada;
    String fechaFormateada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservar_mesa);

        spinnerPersonas = findViewById(R.id.spinnerPersonas);
        spinnerHora = findViewById(R.id.spinnerHora);
        editTextFecha = findViewById(R.id.editTextFecha);

        // Spinner de personas
        ArrayAdapter<CharSequence> adapterPersonas = ArrayAdapter.createFromResource(
                this, R.array.personas_array, android.R.layout.simple_spinner_item);
        adapterPersonas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPersonas.setAdapter(adapterPersonas);

        // Spinner de horas
        ArrayAdapter<CharSequence> adapterHoras = ArrayAdapter.createFromResource(
                this, R.array.horas_array, android.R.layout.simple_spinner_item);
        adapterHoras.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHora.setAdapter(adapterHoras);

        // DatePickerDialogue
        fechaSeleccionada = Calendar.getInstance();
        editTextFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        // Configurar los listeners
        configurarListeners();

        Button btnReservar = findViewById(R.id.btnReservar);

        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.TIRAMISU){
            if(ContextCompat.checkSelfPermission(ReservarMesa.this,
                    android.Manifest.permission.POST_NOTIFICATIONS)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(ReservarMesa.this,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS},101);
            }
        }
        btnReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(personasSeleccionadas != null && !personasSeleccionadas.isEmpty()
                        && horaSeleccionada != null && !horaSeleccionada.isEmpty()
                        && fechaFormateada != null && !fechaFormateada.isEmpty()){
                    makeNotification();
                }
                else{
                    Toast.makeText(ReservarMesa.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button btnVolver = (Button) findViewById(R.id.btnVolver);

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Volver();
                finish();
            }
        });
    }

    public void makeNotification(){
        String chanelID = "CHANEL_ID";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),chanelID);
        builder.setSmallIcon(R.drawable.ic_notifications)
                .setContentTitle("Reserva Confirmada")
                .setContentText(personasSeleccionadas + " " + horaSeleccionada + " " + fechaFormateada)
                .setAutoCancel(true).setPriority(NotificationCompat.PRIORITY_DEFAULT);
        Intent intent = new Intent(getApplicationContext(),Notifications.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("data","Some value");

        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),
                0,intent,PendingIntent.FLAG_MUTABLE);
        builder.setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //revisar if
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel =
                    notificationManager.getNotificationChannel (chanelID);
            if (notificationChannel == null) {
                int importance = NotificationManager.IMPORTANCE_HIGH;
                notificationChannel = new NotificationChannel(chanelID,
                        "Some description", importance);
                notificationChannel.setLightColor(Color.GREEN);
                notificationChannel.enableVibration(true);
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }

        notificationManager.notify(0,builder.build());
    }




    private void configurarListeners() {
        // Listener para el Spinner de personas
        spinnerPersonas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                personasSeleccionadas = parentView.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });


        // Listener para el Spinner de horas
        spinnerHora.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                horaSeleccionada = parentView.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    private void showDatePickerDialog() {
        // Establecer el idioma español (español de España) a nivel de la aplicación
        Locale spanishLocale = new Locale("es", "ES");
        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(spanishLocale);
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());

        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder();
        constraintsBuilder.setStart(System.currentTimeMillis());

        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.YEAR, 1);
        constraintsBuilder.setEnd(endDate.getTimeInMillis());

        MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Selecciona una fecha");
        builder.setCalendarConstraints(constraintsBuilder.build());

        final MaterialDatePicker<Long> materialDatePicker = builder.build();

        materialDatePicker.addOnPositiveButtonClickListener(selection -> {
            Calendar fechaSeleccionada = Calendar.getInstance();
            fechaSeleccionada.setTimeInMillis(selection);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            fechaFormateada = sdf.format(fechaSeleccionada.getTime());
            editTextFecha.setText(fechaFormateada);
        });

        // Muestra el DatePicker
        materialDatePicker.show(getSupportFragmentManager(), "DATE_PICKER");
    }

    public void Volver (){
        startActivity(new Intent(this,PantallaPpal.class));
    }


}
