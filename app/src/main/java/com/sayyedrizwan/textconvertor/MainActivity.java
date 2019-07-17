package com.sayyedrizwan.textconvertor;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Context context = MainActivity.this;
    EditText editText;
    Button speech, texttospeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        editText = findViewById(R.id.edittext);
        speech = findViewById(R.id.speech);
        texttospeech = findViewById(R.id.texttospeech);

        speech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextConvertor.speechtoText(context);
            }
        });


        texttospeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String msg = editText.getText().toString();

                TextConvertor.textToSpeech(context, msg);


            }
        });

    }
}
