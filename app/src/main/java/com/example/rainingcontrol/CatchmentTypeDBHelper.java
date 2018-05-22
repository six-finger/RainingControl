package com.example.rainingcontrol;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CatchmentTypeDBHelper extends SQLiteOpenHelper {
    public CatchmentTypeDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table Catchment(" +
                "id integer primary key autoincrement," +
                "type text not null," +
                "coefficient real not null," +
                "area real );";
        db.execSQL(sql);
        db.execSQL("insert into Catchment(type,coefficient) values('绿化屋面(绿色屋顶,基质层厚度≥300mm)', '0.35');");
        db.execSQL("insert into Catchment(type,coefficient) values('硬屋面、未铺石子的平屋面、沥青屋面', '0.85');");
        db.execSQL("insert into Catchment(type,coefficient) values('铺石子的平屋面', '0.65');");
        db.execSQL("insert into Catchment(type,coefficient) values('混凝土或沥青路面及广场', '0.85');");
        db.execSQL("insert into Catchment(type,coefficient) values('大块石等铺砌路面及广场', '0.55');");
        db.execSQL("insert into Catchment(type,coefficient) values('沥青表面处理的碎石路面及广场', '0.50');");
        db.execSQL("insert into Catchment(type,coefficient) values('级配碎石路面及广场', '0.40');");
        db.execSQL("insert into Catchment(type,coefficient) values('干砌砖石或碎石路面及广场', '0.40');");
        db.execSQL("insert into Catchment(type,coefficient) values('非铺砌土路面', '0.30');");
        db.execSQL("insert into Catchment(type,coefficient) values('绿地', '0.15');");
        db.execSQL("insert into Catchment(type,coefficient) values('下沉式绿地', '0.15');");
        db.execSQL("insert into Catchment(type,coefficient) values('水面', '1.00');");
        db.execSQL("insert into Catchment(type,coefficient) values('地下建筑覆土绿地（覆土≥500mm）', '0.15');");
        db.execSQL("insert into Catchment(type,coefficient) values('地下建筑覆土绿地（覆土<500mm）', '0.35');");
        db.execSQL("insert into Catchment(type,coefficient) values('透水铺装地面', '0.20');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
