package com.foc.pmdm.u3.migrabadora;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends Activity {


    // Ejemplo basado en https://developer.android.com/guide/topics/media/mediarecorder.html#example

    private static final int PETICION_PERMISO_GRABACION = 200; // Constante para saber el permiso que solicitamos

    private String [] permisos = {Manifest.permission.RECORD_AUDIO};
    MediaRecorder mr=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, permisos, PETICION_PERMISO_GRABACION);

        LinearLayout ll=new LinearLayout(this);

        RecordButton recordButton=new RecordButton(this);
        ll.addView(recordButton);
        setContentView(ll);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean bPermisoAceptado = false;
        switch (requestCode){
            case PETICION_PERMISO_GRABACION:
                bPermisoAceptado  = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (!bPermisoAceptado ) finish();

    }


    public void startRecording()

    {
        mr=new MediaRecorder();
        mr.setAudioSource(MediaRecorder.AudioSource.MIC);
        mr.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mr.setOutputFile(getExternalCacheDir().getAbsolutePath()+"/Grabacion.3gp");
        mr.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try

        {
            mr.prepare();
            mr.start();
        }
        catch (Exception e)

        {
            e.printStackTrace();
        }


    }


    public void stopRecording()
    {
        mr.stop();
        mr.release();
        mr=null;
    }
}
