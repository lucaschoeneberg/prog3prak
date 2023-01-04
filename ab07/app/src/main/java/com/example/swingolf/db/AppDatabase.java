package com.example.swingolf.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;

import com.example.swingolf.db.dao.fieldDao;
import com.example.swingolf.db.dao.matchDao;
import com.example.swingolf.db.dao.matchPlayerJoinDao;
import com.example.swingolf.db.dao.playerDao;
import com.example.swingolf.db.dao.scoresDao;
import com.example.swingolf.db.entity.field;
import com.example.swingolf.db.entity.player;
import com.example.swingolf.db.entity.match;
import com.example.swingolf.db.entity.matchPlayerJoin;
import com.example.swingolf.db.entity.scores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {player.class, match.class, matchPlayerJoin.class, field.class, scores.class}, version = 5, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract fieldDao getFieldDao();

    public abstract playerDao getPlayerDao();

    public abstract matchDao getMatchDao();

    public abstract matchPlayerJoinDao getMatchPlayerJoinDao();

    public abstract scoresDao getScoresDao();

    private static final String DB_NAME = "swingolf_database.db";
    private static volatile AppDatabase appDatabase;
    private static final int NUMBER_OF_THREADS = 2;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static synchronized AppDatabase getInstance(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "swingolf_database")
                    .build();
        }
        return appDatabase;
    }

    private static AppDatabase create(final Context context) {
        return Room.databaseBuilder(
                context,
                AppDatabase.class,
                DB_NAME).build();
    }

    static AppDatabase getDatabase(final Context context) {
        if (appDatabase == null) {
            synchronized (AppDatabase.class) {
                if (appDatabase == null) {
                    appDatabase = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "app_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return appDatabase;
    }

    public static void destroyInstance() {
        appDatabase = null;
    }

    public static Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(androidx.sqlite.db.SupportSQLiteDatabase database) {
            //delete all old tables
            database.execSQL("DROP TABLE IF EXISTS player");
            database.execSQL("DROP TABLE IF EXISTS 'match'");
            database.execSQL("DROP TABLE IF EXISTS matchPlayerJoin");
            database.execSQL("DROP TABLE IF EXISTS field");
            database.execSQL("DROP TABLE IF EXISTS scores");
        }
    };

    public static void dropAllTables(androidx.sqlite.db.SupportSQLiteDatabase database) {
        database.execSQL("DROP TABLE IF EXISTS player");
        database.execSQL("DROP TABLE IF EXISTS 'match'");
        database.execSQL("DROP TABLE IF EXISTS matchPlayerJoin");
        database.execSQL("DROP TABLE IF EXISTS field");
        database.execSQL("DROP TABLE IF EXISTS scores");
    }
}