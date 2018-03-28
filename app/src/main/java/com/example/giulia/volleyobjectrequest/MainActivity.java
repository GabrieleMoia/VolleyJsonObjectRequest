package com.example.giulia.volleyobjectrequest;

import android.app.ProgressDialog;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    String url = "https://goo.gl/images/VJUoPQ";
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        final ImageView imageView = (ImageView) findViewById(R.id.iv_internal);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
                dialog.setMessage("Caricamento...");
                dialog.show();

                ImageRequest imageRequest = new ImageRequest("https://cdn.pixabay.com/photo/2017/07/11/19/50/free-pictures-2494806_960_720.jpg", new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        Uri uri = saveImageToInternalStorage(response);
                        imageView.setImageURI(uri);
                        dialog.hide();
                    }
                }, 483, 480, ImageView.ScaleType.CENTER_CROP, Bitmap.Config.RGB_565,
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                            }
                        }
                );
                ServiceQueueSingleton.getInstance(getApplicationContext()).addToRequestQueue(imageRequest);
            }
        });
    }

    protected Uri saveImageToInternalStorage(Bitmap bitmap) {
        // Initialize ContextWrapper
        ContextWrapper wrapper = new ContextWrapper(getApplicationContext());

        // Initializing a new file
        // The bellow line return a directory in internal storage
        File file = wrapper.getDir("Images", MODE_PRIVATE);

        // Create a file to save the image
        file = new File(file, "UniqueFileName" + ".jpg");

        try {
            // Initialize a new OutputStream
            OutputStream stream = null;

            // If the output file exists, it can be replaced or appended to it
            stream = new FileOutputStream(file);

            // Compress the bitmap
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

            // Flushes the stream
            stream.flush();

            // Closes the stream
            stream.close();

        } catch (IOException e) // Catch the exception
        {
            e.printStackTrace();
        }

        // Parse the gallery image url to uri
        Uri savedImageURI = Uri.parse(file.getAbsolutePath());

        // Return the saved image Uri
        return savedImageURI;
    }
}
