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
                    //下面注释表示允许主线程进行数据库操作，但是不推荐这样做。
                    //他可能造成主线程lock以及anr
                    //所以我们的操作都是在新线程完成的
                    // .allowMainThreadQueries()
                    .build();
        }
        return wordDataBase;
    }
    //RoomDatabase提供直接访问底层数据库实现，我们通过定义抽象方法返回具体Dao
    //然后进行数据库增删该查的实现。
    public abstract WordDao getWordDao();
}
