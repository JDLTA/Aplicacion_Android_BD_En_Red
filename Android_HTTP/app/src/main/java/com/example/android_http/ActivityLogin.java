package com.example.android_http;

import android.content.Intent;
import android.os.AsyncTask;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import com.controlador.AnalizadorJSON;

public class ActivityLogin extends AppCompatActivity {

    EditText txtUsuario, txtContraseña;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsuario = findViewById(R.id.txtUsuario);
        txtContraseña = findViewById(R.id.txtContraseña);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u = txtUsuario.getText().toString();
                String c = txtContraseña.getText().toString();

                try {
                    if (new AuteticaUsuario().execute(u,c).get()){
                        startActivity(new Intent(ActivityLogin.this, MainActivity.class));
                    }else{
                        Toast.makeText(ActivityLogin.this,"No se pudo autenticar usuario...",Toast.LENGTH_SHORT).show();
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    class AuteticaUsuario extends AsyncTask<String, String, Boolean> {

        @Override
        protected Boolean doInBackground(String... strings) {
            AnalizadorJSON json = new AnalizadorJSON();


            String url = "http://176.48.16.14/web_service_http_android/login.php";
            JSONObject jsonObject = json.loginHTTP(url,strings[0],strings[1]);


            try {
                if (jsonObject.getInt("exito") == 1){
                    return true;
                }
            } catch (JSONException e1) {
                e1.printStackTrace();
            }



            return false;
        }
    }
}

