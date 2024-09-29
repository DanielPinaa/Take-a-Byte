package es.upm.etsiinf.proyectoPMD;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Notifications extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        textView = findViewById(R.id.textViewData);
        String data = getIntent().getStringExtra("data");
        textView.setText(data);
    }
}