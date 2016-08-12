package com.sigaritus.swu.zhihudailym;

import android.app.Application;
import android.content.Context;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.sigaritus.swu.zhihudailym.greendao.gen.DaoMaster;
import com.sigaritus.swu.zhihudailym.greendao.gen.DaoSession;

/**
 * Created by Administrator on 2016/7/19.
 */
public class ZhihuDailyMApp extends Application {
    private static Context mContext;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;
    private final static String DBNAME = "zhihu_story_db";
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        Iconify.with(new FontAwesomeModule());

    }

    public static Context getContext() {
        return mContext;
    }

    /**
     * 取得DaoMaster
     *
     * @param context        上下文
     * @return               DaoMaster
     */
    public static DaoMaster getDaoMaster(Context context,int flag) {
        if (daoMaster == null) {
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(mContext, DBNAME, null);

            daoMaster = flag==1?new DaoMaster(helper.getWritableDatabase())
                    :new DaoMaster(helper.getReadableDatabase());
        }
        return daoMaster;
    }

    /**
     * 取得DaoSession
     *
     * @param context        上下文
     * @return               DaoSession
     */
    public static DaoSession getDaoSession(Context context,int flag) {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster(context,flag);
            }
            daoSession = daoMaster.newSession();
        }

        return daoSession;
    }

}
