package com.sayyedrizwan.textconvertor;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.Toast;

import java.util.Locale;

import static android.content.ContentValues.TAG;


public class TextConvertor {


    static TextToSpeech speech;

    public static Intent speechtoText(Context context) {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hey Speak something");

        try {
            ((Activity) context).startActivityForResult(intent, 1);

        } catch (ActivityNotFoundException e) {
            
            Log.i(TAG, "texttospeech: " + e.getMessage());
        }

        return null;
    }


    public static TextToSpeech textToSpeech(final Context context, final String text){

        if (text.isEmpty()){
            Toast.makeText(context, "Text cannot be empty", Toast.LENGTH_SHORT).show();
        }else {
            speech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {

                    if (status == TextToSpeech.SUCCESS) {

                        int result = speech.setLanguage(Locale.ENGLISH);
                        if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {

                            Toast.makeText(context, "Language not supported", Toast.LENGTH_SHORT).show();

                        } else {

                            speech.setPitch(0.6f);
                            speech.setSpeechRate(1.0f);

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                speech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
                            }else {

                                speech.speak(text, TextToSpeech.QUEUE_FLUSH,  null);

                            }

                        }


                    }

                }
            });
        }

        return null;
    }

}
