package com.example.liquorshops.jetpack.room;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.liquorshops.jetpack.dao.LiquorShopDao;
import com.example.liquorshops.jetpack.entities.FavoriteShopsModel;
import com.example.liquorshops.utils.MyApplication;
import com.example.liquorshops.utils.interfaces.DatabaseKey;

@Database(entities = FavoriteShopsModel.class, version = 1)
public abstract class LiquorShopsDatabase extends RoomDatabase {
    private static LiquorShopsDatabase liquorShopsDatabase;

    public static LiquorShopsDatabase getDatabaseInstance() {
        if (liquorShopsDatabase == null)
            liquorShopsDatabase = Room
                    .databaseBuilder(MyApplication.getContext(), LiquorShopsDatabase.class, DatabaseKey.LIQUORSHOP_DB).build();
        return liquorShopsDatabase;
    }
//    static final Migration MIGRATION_1_2 = new Migration(1,2) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            database.execSQL("Drop Table FAVSHOPS");
//        }
//    };

    public abstract LiquorShopDao getLiquorShopsDao();
}
