package com.example.cse_323voicerecongnitionproject;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageButton imageButton;
    EditText editText;

    int count=0;
    SpeechRecognizer speechRecognizer;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageButton=findViewById(R.id.button);
        editText=findViewById(R.id.edittext);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO},1);
        }

        speechRecognizer=SpeechRecognizer.createSpeechRecognizer(this);

        final Intent speechRecognizerIntent= new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        imageButton.setOnClickListener (v -> {

            if(count==0)
            {
                imageButton.setImageDrawable(getDrawable(R.drawable.ic_baseline_mic_24));
                //start listening

                speechRecognizer.startListening(speechRecognizerIntent);
                count=1;
            }
            else
            {
                imageButton.setImageDrawable(getDrawable(R.drawable.ic_baseline_mic_off_24));
                //stop listening
                speechRecognizer.stopListening();
                count=0;
            }
        });

                    speechRecognizer.setRecognitionListener(new RecognitionListener() {
                        @Override
                        public void onReadyForSpeech(Bundle bundle) {

                        }

                        @Override
                        public void onBeginningOfSpeech() {
                        //here

                        }

                        @Override
                        public void onRmsChanged(float v) {

                        }

                        @Override
                        public void onBufferReceived(byte[] bytes) {

                        }

                        @Override
                        public void onEndOfSpeech() {

                        }

                        @Override
                        public void onError(int i) {

                        }

                        @Override
                        public void onResults(Bundle results) {
                        //here
                            ArrayList<String> data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                            editText.setText(data.get(0));
                        }

                        @Override
                        public void onPartialResults(Bundle bundle) {

                        }

                        @Override
                        public void onEvent(int i, Bundle bundle) {

                        }
                    });
    }


    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {


        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}



