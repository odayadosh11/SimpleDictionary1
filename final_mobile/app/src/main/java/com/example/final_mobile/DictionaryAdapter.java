package com.example.final_mobile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DictionaryAdapter extends RecyclerView.Adapter<DictionaryAdapter.WordViewHolder> {

    private ArrayList<Word> wordList;

    private OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(int position);
        void onItemLongClick(int position);
    }

    public DictionaryAdapter(ArrayList<Word> wordList,
                             OnItemClickListener listener){

        this.wordList = wordList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_word,parent,false);

        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {

        Word currentWord = wordList.get(position);

        holder.txtWord.setText(currentWord.getWord());
        holder.txtMeaning.setText(currentWord.getMeaning());

    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }

    class WordViewHolder extends RecyclerView.ViewHolder{

        TextView txtWord;
        TextView txtMeaning;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);

            txtWord = itemView.findViewById(R.id.txtWord);
            txtMeaning = itemView.findViewById(R.id.txtMeaning);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(listener!=null){

                        int position = getAdapterPosition();

                        if(position!=RecyclerView.NO_POSITION){

                            listener.onItemClick(position);

                        }
                    }

                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    if(listener!=null){

                        int position = getAdapterPosition();

                        if(position!=RecyclerView.NO_POSITION){

                            listener.onItemLongClick(position);

                        }
                    }

                    return true;
                }
            });

        }
    }

}