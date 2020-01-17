package com.mrlove.roombasic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mrlove.roombasic.dao.WordDao;
import com.mrlove.roombasic.dao.WordDataBase;
import com.mrlove.roombasic.databinding.ActivityMainBinding;
import com.mrlove.roombasic.domain.Word;

import java.util.List;

public class MainActivity extends AppCompatActivity {
     MyViewModel myViewModel;
     ActivityMainBinding binding;
     WordDao wordDao;
     WordDataBase wordDataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        myViewModel = new ViewModelProvider(this,new SavedStateViewModelFactory(getApplication(),this)).get(MyViewModel.class);
        binding.setData(myViewModel);
        wordDataBase = Room.databaseBuilder(this,WordDataBase.class,"word_database")
                .allowMainThreadQueries()
                .build();
        wordDao = wordDataBase.getWordDao();
        updateView();

        binding.buttoninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word1 = new Word("hello","你好");
                Word word2 = new Word("world","世界");
                wordDao.insertWords(word1,word2);
                updateView();
            }
        });
    }

    void updateView(){
        List<Word> list = wordDao.getAllWords();
        String str = "";
        for (Word word: list
             ) {
            str += word.getId()+":"+word.getWord()+"="+word.getChineseMeaning()+"\n";
        }
        binding.textView2.setText(str);
    }

}
