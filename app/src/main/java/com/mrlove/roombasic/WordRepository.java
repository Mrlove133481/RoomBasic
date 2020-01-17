package com.mrlove.roombasic;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.mrlove.roombasic.dao.WordDao;
import com.mrlove.roombasic.dao.WordDataBase;
import com.mrlove.roombasic.domain.Word;

import java.util.List;

public class WordRepository {
    private LiveData<List<Word>> allWordsLive;
    private WordDao wordDao;
    public WordRepository(Context context) {
        WordDataBase wordDataBase = WordDataBase.getWordDataBase(context.getApplicationContext());
        wordDao = wordDataBase.getWordDao();
        allWordsLive = wordDao.getAllWords();
    }

    void insertWords(Word... words){
        new InsertAsyncTask(wordDao).execute(words);
    }

    void updateWords(Word... words){
        new UpdateAsyncTask(wordDao).execute(words);
    }

    void deleteWords(Word... words){
        new DeleteAsyncTask(wordDao).execute(words);
    }

    void clearWords(){
        new ClearAsyncTask(wordDao).execute();
    }


    public LiveData<List<Word>> getAllWordslive() {
        return allWordsLive;
    }


    static class InsertAsyncTask extends AsyncTask<Word,Void,Void>{
        private WordDao wordDao;
        public InsertAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.insertWords(words);
            return null;
        }
    }

    static class ClearAsyncTask extends AsyncTask<Void,Void,Void>{
        private WordDao wordDao;
        public ClearAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Void... Void) {
            wordDao.deleteAllWords();
            return null;
        }
    }

    static class UpdateAsyncTask extends AsyncTask<Word,Void,Void>{
        private WordDao wordDao;
        public UpdateAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.updateWords(words);
            return null;
        }
    }

    static class DeleteAsyncTask extends AsyncTask<Word,Void,Void>{
        private WordDao wordDao;
        public DeleteAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.deleteWords(words);
            return null;
        }
    }
}
