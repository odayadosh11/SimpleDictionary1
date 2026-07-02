package com.example.final_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements DictionaryAdapter.OnItemClickListener {

    RecyclerView recyclerView;
    FloatingActionButton fab;

    ArrayList<Word> wordList;
    DictionaryAdapter adapter;

    final int ADD_REQUEST = 1;
    final int EDIT_REQUEST = 2;

    int editPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.fabAdd);

        if (savedInstanceState != null) {
            wordList = (ArrayList<Word>) savedInstanceState.getSerializable("list");
        } else {
            wordList = new ArrayList<>();
        }

        adapter = new DictionaryAdapter(wordList, this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,
                        AddEditActivity.class);

                startActivityForResult(intent, ADD_REQUEST);
            }
        });

    }

    @Override
    public void onItemClick(int position) {

        editPosition = position;

        Intent intent = new Intent(MainActivity.this,
                AddEditActivity.class);

        intent.putExtra("word",
                wordList.get(position).getWord());

        intent.putExtra("meaning",
                wordList.get(position).getMeaning());

        startActivityForResult(intent, EDIT_REQUEST);

    }

    @Override
    public void onItemLongClick(int position) {

        wordList.remove(position);

        adapter.notifyDataSetChanged();

        Toast.makeText(this,
                "Word Deleted",
                Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {

            String word = data.getStringExtra("word");
            String meaning = data.getStringExtra("meaning");

            if (requestCode == ADD_REQUEST) {

                Word newWord = new Word(word, meaning);

                wordList.add(newWord);

                adapter.notifyDataSetChanged();

                Toast.makeText(this,
                        "Word Added",
                        Toast.LENGTH_SHORT).show();

            } else if (requestCode == EDIT_REQUEST) {

                wordList.get(editPosition).setWord(word);
                wordList.get(editPosition).setMeaning(meaning);

                adapter.notifyDataSetChanged();

                Toast.makeText(this,
                        "Word Updated",
                        Toast.LENGTH_SHORT).show();

            }

        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("list", wordList);
    }

}