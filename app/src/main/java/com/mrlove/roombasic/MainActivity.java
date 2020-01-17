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
    private static final String LAG = "mrlove_str";
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
       binding.textView2.setText("你is的广泛的非官方大哥\n但是梵蒂冈梵蒂冈和开发\n的方式的开发工具或多个\n你is的广泛的非官方大哥\n但是梵蒂冈梵蒂冈和开发\n的方式的开发工具或多个\n你is的广泛的非官方大哥\n但是梵蒂冈梵蒂冈和开发\n的方式的开发工具或多个\n你is的广泛的非官方大哥\n但是梵蒂冈梵蒂冈和开发\n的方式的开发工具或多个\n你is的广泛的非官方大哥\n但是梵蒂冈梵蒂冈和开发\n的方式的开发工具或多个\n");
        Log.d(LAG,binding.textView2.getText().toString());
        updateView();

        binding.buttoninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word1 = new Word("hello","你好");
                Word word2 = new Word("world","世界");
                wordDao.insertWords(word1,word2);
                Log.d(LAG,"aaa");
                updateView();
            }
        });
        binding.setLifecycleOwner(this);
    }

    void updateView(){
        Log.d(LAG,"bbb");
        List<Word> list = wordDao.getAllWords();
        Log.d(LAG,list.toString());
        String str = "";
        for (Word word: list
             ) {
            str += word.getId()+":"+word.getWord()+"="+word.getChineseMeaning()+"\n";
        }
        Log.d(LAG,str);
        binding.textView2.setText(str);
    }

}
