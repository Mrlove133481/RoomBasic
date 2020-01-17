package com.mrlove.roombasic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        myViewModel = new ViewModelProvider(this,new SavedStateViewModelFactory(getApplication(),this)).get(MyViewModel.class);
        binding.setData(myViewModel);
        myViewModel.getAllWordsLive().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                String str = "";
                for (Word word: words
                ) {
                    str += word.getId()+":"+word.getWord()+"="+word.getChineseMeaning()+"\n";
                }
                binding.textView2.setText(str);
            }
        });
        //添加
        binding.buttoninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word1 = new Word("hello","你好");
                Word word2 = new Word("world","世界");
                myViewModel.insertWords(word1,word2);

            }
        });
        //删除所有
        binding.buttonclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewModel.clearWords();

            }
        });
        //更新
        binding.buttonupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word = new Word("haha","哈哈");
                word.setId(40);
                myViewModel.updateWords(word);
            }
        });
        //删除某一项
        binding.buttondelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word = new Word("haha","哈哈");
                word.setId(40);
                myViewModel.deleteWords(word);
            }
        });


    }
}
