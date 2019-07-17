package com.sayyedrizwan.textconvertor;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 1:
                if (resultCode == RESULT_OK && null!= data){
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    editText.setText(result.get(0));
                }
                break;
        }


    }
}
