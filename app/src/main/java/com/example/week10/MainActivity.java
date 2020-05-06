package com.example.week10;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;


import java.util.ArrayList;

import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {


    WebView web;
    EditText text;
    Button shoutButton;
    Button iniButton;
    ArrayList<String> list = new ArrayList<>();
    ListIterator<String> itr;
    String url;
    String x;
    int index = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void Search(View V){
        web = findViewById(R.id.webView);
        web.setWebViewClient(new WebViewClient());
        web.getSettings().setJavaScriptEnabled(true);
        text = findViewById(R.id.editText);
        shoutButton = findViewById(R.id.button3);
        iniButton = findViewById(R.id.button4);
        url = text.getText().toString();
        setUrlList(url);

        if (index != 10){                    // indexi alustettu arvoon 10, koska se voi suurimmillaan olla 9, sillä listan koko on suurimmillaan 10.
            itr = list.listIterator(index);
            while (itr.hasNext()){
                x = itr.next();
                if (x != url){
                    itr.remove();
                }
            }
        }

        if (list.size() > 10){
            list.remove(0);
        }


        for (String s : list ){
            System.out.println(s);
        }


        if (url.equalsIgnoreCase("index.html")){
            web.loadUrl("file:///android_asset/index.html");
            shoutButton.setVisibility(View.VISIBLE);
            iniButton.setVisibility(View.VISIBLE);

        }else{
            web.loadUrl("http://"+ url);
        }

    }
    public void Refresh(View V){
        web.reload();
    }

    public void shoutOut(View v){
        web.evaluateJavascript("javascript:shoutOut()", null);
    }

    public void initialize(View v){
        web.evaluateJavascript("javascript:initialize()", null);
    }

    public void forward(View v){
        System.out.println(url);
        System.out.println("Listan koko: "+ list.size()+"Indeksi: "+ list.indexOf(url));
        itr  = list.listIterator(list.indexOf(url)+1);   // alkaa iteroimaan viimeisimmästä urlista ja +1, jotta seuraava urli oikea
        if (itr.hasNext()){
            System.out.println("Eteen");
            System.out.println(itr.nextIndex());
            url = itr.next();
            System.out.println(url);
            web.loadUrl("http://"+url);
        } else{
            System.out.println("Eteen ei onnistu");
        }
    }

    public void back(View v){
        System.out.println(url);
        System.out.println("Listan koko: "+ list.size()+"Indeksi: "+ list.indexOf(url));
        itr  = list.listIterator(list.indexOf(url));
        if (itr.hasPrevious()){
            System.out.println("Taakse");
            url = itr.previous();
            index = list.indexOf(url)+1;
            web.loadUrl("http://"+url);
        } else{
            System.out.println("Taakse ei onnistu");
        }
    }

    public void setUrlList(String url){
        list.add(url);
    }


}
