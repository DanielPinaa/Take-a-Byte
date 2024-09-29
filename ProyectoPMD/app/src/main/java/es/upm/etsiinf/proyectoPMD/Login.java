package es.upm.etsiinf.proyectoPMD;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView username = (TextView) findViewById(R.id.usuario);
        TextView password = (TextView) findViewById(R.id.contraseña);
        Button btnLogin = (Button) findViewById(R.id.btnlogin);
        CheckBox checkRemember = (CheckBox) findViewById(R.id.checkRecuerdame);

        SharedPreferences sp = getSharedPreferences("prefs_login", Context.MODE_PRIVATE);

        checkLogin(sp);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!username.getText().toString().equals("")
                        && !password.getText().toString().equals("")) {
                    // LOGIN CORRECTO
                    Toast.makeText(Login.this, "LOGIN CORRECTO", Toast.LENGTH_SHORT).show();

                    rememberUser(sp);
                }
                else { // LOGIN INCORRECTO
                    Toast.makeText(Login.this, "LOGIN INCORRECTO", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    private void rememberUser(SharedPreferences sp) {
        TextView username = (TextView) findViewById(R.id.usuario);
        TextView password = (TextView) findViewById(R.id.contraseña);
        Button btnLogin = (Button) findViewById(R.id.btnlogin);
        CheckBox checkRemember = (CheckBox) findViewById(R.id.checkRecuerdame);

        String user = username.getText().toString();
        String pass = password.getText().toString();

        if(!user.isEmpty() && !pass.isEmpty()) {
            if(checkRemember.isChecked()) {
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("user", user);
                editor.putString("pass", pass);
                editor.putString("active", "true");

                editor.apply();
            }
            else {
                SharedPreferences.Editor editor = sp.edit();

                editor.putString("user", "");
                editor.putString("pass", "");
                editor.putString("active", "false");

                editor.apply();
            }

            Intent intent = new Intent(this, PantallaPpal.class);
            startActivity(intent);
            finish();
        }
        else {
            Toast.makeText(Login.this, "Failed... try again", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkLogin(SharedPreferences sp) {

        TextView username = (TextView) findViewById(R.id.usuario);
        TextView password = (TextView) findViewById(R.id.contraseña);

        if(sp.getString("active","").equals("true")) {
            startActivity(new Intent(this, PantallaPpal.class));
            finish();
        }
        else if(username.onCheckIsTextEditor()) {
            username.setText(sp.getString("user",""));
            password.setText(sp.getString("pass",""));
        }
    }


}

