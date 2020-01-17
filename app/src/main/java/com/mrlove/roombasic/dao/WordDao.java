package com.mrlove.roombasic.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mrlove.roombasic.domain.Word;

import java.util.List;

@Dao   //Database access object
public interface WordDao {
    @Insert
     void insertWords(Word... words);  //...表示可以传递多个同类型参数

    @Update
     void updateWords(Word... words);

    @Delete
    void  deleteWords(Word... words);

    @Query("DELETE FROM WORD")   //查询语句写的清空所有数据
    void deleteAllWords();

    @Query("SELECT * FROM Word ORDER BY id DESC")
    LiveData<List<Word>> getAllWords();  //设置数据为可观察类型LiveData
}
