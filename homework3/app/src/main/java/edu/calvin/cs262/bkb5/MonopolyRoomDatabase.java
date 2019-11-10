package edu.calvin.cs262.bkb5;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Player.class, Game.class}, version = 1, exportSchema = false)
public abstract class MonopolyRoomDatabase extends RoomDatabase
{
    public abstract PlayerDao playerDao();
    public abstract GameDao gameDao();
    private static MonopolyRoomDatabase INSTANCE;

    static MonopolyRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MonopolyRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MonopolyRoomDatabase.class, "monopoly_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }


    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final PlayerDao _Dao;

        PopulateDbAsync(MonopolyRoomDatabase db) {
            _Dao = db.playerDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
            _Dao.deleteAllPlayers();

            Player player = new Player("1", "simple@email.com", "Henri");
            _Dao.insertPlayer(player);

            return null;
        }
    }
}
