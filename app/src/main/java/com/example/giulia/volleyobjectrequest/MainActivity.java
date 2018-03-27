package com.example.giulia.volleyobjectrequest;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    String url = "https://androidtutorialpoint.com/api/volleyJsonObject";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Caricamento...");
        dialog.show();

        //Recupero stringa Json e lo visualizzo nella textView
        /*JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Service", "Response:" + response.toString());
                TextView details=(TextView) findViewById(R.id.details);
                details.setText(response.toString());
                dialog.hide();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Volley","Error", error.getMessage());
                dialog.hide();
            }
        });*/

        //Recupero stringa JSon, conversione in un oggetto e visualizzazione caratteristiche in textView
        GsonRequest jsonObjectRequest = new GsonRequest(url, Telefono.class, null,
                new Response.Listener<Telefono>() {
                    @Override
                    public void onResponse(Telefono response) {
                        TextView screenSize = (TextView) findViewById(R.id.screenSize);
                        TextView name =(TextView) findViewById(R.id.name);
                        TextView operatingSystem=(TextView) findViewById(R.id.operatingSystem);
                        screenSize.setText(response.getScreenSize());
                        name.setText(response.getName());
                        operatingSystem.setText(response.getOperatingSystem());
                        dialog.hide();
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                TextView details = (TextView) findViewById(R.id.screenSize);
                details.setText(error.getMessage());
                dialog.hide();
            }
        });
        ServiceQueueSingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }
}
