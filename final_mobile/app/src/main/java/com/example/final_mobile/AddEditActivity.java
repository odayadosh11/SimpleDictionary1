package com.example.final_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddEditActivity extends AppCompatActivity {

    EditText edtWord;
    EditText edtMeaning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        edtWord = findViewById(R.id.edtWord);
        edtMeaning = findViewById(R.id.edtMeaning);

        Intent intent = getIntent();

        if(intent.hasExtra("word")){

            edtWord.setText(intent.getStringExtra("word"));
            edtMeaning.setText(intent.getStringExtra("meaning"));

        }

    }

    public void saveWord(View view) {

        String word = edtWord.getText().toString().trim();
        String meaning = edtMeaning.getText().toString().trim();

        if (word.isEmpty()) {
            edtWord.setError("Enter Word");
            edtWord.requestFocus();
            return;
        }

        if (meaning.isEmpty()) {
            edtMeaning.setError("Enter Meaning");
            edtMeaning.requestFocus();
            return;
        }

        Intent intent = new Intent();

        intent.putExtra("word", word);
        intent.putExtra("meaning", meaning);

        setResult(RESULT_OK, intent);

        finish();
    }

}