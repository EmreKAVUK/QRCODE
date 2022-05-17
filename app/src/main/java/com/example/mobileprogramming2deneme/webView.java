package com.example.mobileprogramming2deneme;




import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

//WebView uygulamalarının tümü adına Web içeriğinin görüntülenmesine imkan tanıyan bir sistemdir. Kısaca Web içeriklerini göstermeye yarar
//Specific News yani gösterilen habere tıklandıgında tam içerik alanı için oluşturuldu
public class webView extends AppCompatActivity {
    URI uri;
    URL url;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        webView = findViewById(R.id.webview);
        Intent intent = getIntent();//A
        String str = intent.getStringExtra("url");
        try {
            uri = new URI(str);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            url= uri.toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(String.valueOf(url));////Web sayfamızın url'ini webView'e yüklüyoruz.
        Toast.makeText(this, ""+url, Toast.LENGTH_SHORT).show();
    }
}