package com.sigaritus.swu.zhihudailym.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.sigaritus.swu.zhihudailym.ZhihuDailyMApp;
import com.sigaritus.swu.zhihudailym.bean.ZhihuDetailStory;
import com.sigaritus.swu.zhihudailym.bean.ZhihuTopStory;
import com.sigaritus.swu.zhihudailym.greendao.gen.DaoMaster;
import com.sigaritus.swu.zhihudailym.greendao.gen.DaoSession;
import com.sigaritus.swu.zhihudailym.greendao.gen.ZhihuDetailStoryDao;
import com.sigaritus.swu.zhihudailym.greendao.gen.ZhihuTopStoryDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class DBManager {

    private static final int WRITABLE_DATABASE=1;
    private static final int READABLE_DATABASE=0;
    private static DBManager newInstance;
    private DaoMaster.DevOpenHelper mOpenHelper;
    private Context mContext;

    public DBManager(Context context) {
        mContext = context;

    }

    /**
     * 获取单例
     *
     * @param context
     * @return DBManager实例
     */
    public static DBManager getNewInstance(Context context) {
        if (newInstance == null) {
            synchronized (DBManager.class) {
                if (newInstance == null) {
                    newInstance = new DBManager(context);
                }
            }
        }
        return newInstance;
    }


    public void insertStory(ZhihuDetailStory zhihuDetailStory) {
        if (zhihuDetailStory==null){
            return;
        }
        DaoMaster daoMaster = ZhihuDailyMApp.getDaoMaster(mContext,WRITABLE_DATABASE);
        DaoSession daoSession = ZhihuDailyMApp.getDaoSession(mContext,WRITABLE_DATABASE);
        ZhihuDetailStoryDao zhihuDetailStoryDao = daoSession.getZhihuDetailStoryDao();
        zhihuDetailStoryDao.insert(zhihuDetailStory);
    }


    public List<ZhihuDetailStory> queryZhihuDetailStory() {
        DaoMaster daoMaster = ZhihuDailyMApp.getDaoMaster(mContext,READABLE_DATABASE);
        DaoSession daoSession = daoMaster.newSession();
        ZhihuDetailStoryDao zhihuDetailStoryDao = daoSession.getZhihuDetailStoryDao();
        QueryBuilder<ZhihuDetailStory> qb=zhihuDetailStoryDao.queryBuilder();

        List<ZhihuDetailStory> list = qb.list();
        return list;
    }

}
