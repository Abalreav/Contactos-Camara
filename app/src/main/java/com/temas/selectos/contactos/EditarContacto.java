package com.temas.selectos.contactos;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.SimpleTimeZone;


public class EditarContacto extends AppCompatActivity {

    EditText edtTelefono;
    EditText edtCorreo;
    ImageButton imagPerfil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_contacto);
        Bundle extras = getIntent().getExtras();
        String Telefono = extras.getString("Telefono");
        String Correo = extras.getString("Correo");

        edtTelefono = findViewById(R.id.edtTelefono);
        edtCorreo = findViewById(R.id.edtCorreo);

        edtCorreo.setText(Correo);
        edtTelefono.setText(Telefono);


    }
    public void imgonClick(View v){
        Intent intentcamara= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intentcamara,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmapImagen=(Bitmap)data.getExtras().get("data");
        imagPerfil.setImageBitmap(bitmapImagen);
        SalvaImagen(this,bitmapImagen);
    }
    public void SalvaImagen(Context contexto,Bitmap Imagen)
    {
        String nombreDirectorio="/Carpeta_Imagen";
        String nombre_arachivo="imagen";
        String filepath= Environment.getExternalStorageDirectory().getAbsolutePath();
        String CurrentDateAndTime=getDateAndTime();

        File dir=new File(filepath);
        if(!dir.exists())
        {
            dir.mkdirs();
        }
        File file=new File(dir,nombre_arachivo+CurrentDateAndTime+".jpg");
        try {
            FileOutputStream fo=new FileOutputStream(file);
            Imagen.compress(Bitmap.CompressFormat.JPEG,85,fo);
            fo.flush();
            fo.close();
            VerificaArchivoGuardado(file);
            Toast.makeText(contexto,"Se ha guardado correctamente",Toast.LENGTH_LONG).show();
        }catch (FileNotFoundException a)
        {
            Toast.makeText(contexto,"No se ha guardado",Toast.LENGTH_LONG).show();
        }
        catch (IOException e)
        {
            Toast.makeText(contexto,"No se ha guardado",Toast.LENGTH_LONG).show();
        }
    }
    private void VerificaArchivoGuardado(File file)
    {
        MediaScannerConnection.scanFile(this, new String[]{file.toString()}, null, new MediaScannerConnection.MediaScannerConnectionClient() {
            @Override
            public void onMediaScannerConnected() {

            }

            @Override
            public void onScanCompleted(String path, Uri uri) {

            }
        });

    }
    private String getDateAndTime(){
        Calendar c=Calendar.getInstance();
        SimpleDateFormat DateForm=new SimpleDateFormat("yyyy-MM-dd-MM-mm");
        String formattedDate=DateForm.format(c.getTime());
        return formattedDate;
    }
}
