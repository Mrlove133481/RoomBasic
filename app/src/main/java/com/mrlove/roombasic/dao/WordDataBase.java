package com.mrlove.roombasic.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mrlove.roombasic.domain.Word;

//配置你要操作的entry类，可以配置一个或者多个，version表名这个是哪个版本，如果升级需要修改的就是这里
@Database(entities = {Word.class}, version = 1) //exportSchema 默认为true，存储展示数据库的结构信息
public abstract class WordDataBase extends RoomDatabase {
    //singleton
    private static WordDataBase wordDataBase;  //单例模式，保证获取的数据库实例是唯一的

    public static synchronized WordDataBase getWordDataBase(Context context) {
        if (wordDataBase == null) {
            wordDataBase = Room.databaseBuilder(context.getApplicationContext(), WordDataBase.class, "word_database")
                    .build();
        }
        return wordDataBase;
    }

    public abstract WordDao getWordDao();
}
