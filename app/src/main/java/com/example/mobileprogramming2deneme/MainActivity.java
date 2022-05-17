package com.example.mobileprogramming2deneme;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import org.w3c.dom.Text;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    Button button;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                scanCode();


            }
        });
    }

    private void scanCode() {
        ScanOptions options = new ScanOptions(); //erişim aç
        options.setPrompt("Flash açmak için ses açma tuşuna basın");
        options.setBeepEnabled(true);   //bip sesi
        options.setOrientationLocked(true); //ekranı döndürmeme
        options.setCaptureActivity(CaptureAct.class);

        barLauncher.launch(options);


    }
    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(),result -> {
        if(result.getContents() !=null)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Result");
            builder.setMessage(result.getContents().toString());

            //okuduğumuz her ne ise çıkan şeyi otomatik olarak kopyalamamızı sağlayan kısım
            ClipboardManager manager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("text", result.getContents().toString());
            manager.setPrimaryClip(clipData);
            String str = result.getContents().toString();


            Toast.makeText(this, ""+str.toString(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplication(),webView.class);
            intent.putExtra("url",str);
            startActivity(intent);



            //kopyalandı mesajı
            Toast.makeText(getApplicationContext(),"Kopyalandı",Toast.LENGTH_SHORT).show();


            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    dialogInterface.dismiss();


                }
            }).show();

        }

    });

}