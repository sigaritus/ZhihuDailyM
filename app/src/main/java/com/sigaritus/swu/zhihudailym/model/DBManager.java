//package com.sigaritus.swu.zhihudailym.model;
//
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//
//import com.anye.greendao.gen.DaoMaster;
//import com.anye.greendao.gen.DaoSession;
//import com.anye.greendao.gen.UserDao;
//
//import org.greenrobot.greendao.query.QueryBuilder;
//
//import java.util.List;
//
//
//
//public class DBManager {
//    private final static String DBNAME = "clients_db";
//    private static DBManager newInstance;
//    //private DaoMaster.DevOpenHelper mOpenHelper;
//    private Context mContext;
//
//    public DBManager(Context context) {
//        mContext = context;
//        //mOpenHelper = new DaoMaster.DevOpenHelper(context, DBNAME, null);
//    }
//
//    /**
//     * 获取单例
//     *
//     * @param context
//     * @return DBManager实例
//     */
//    public static DBManager getNewInstance(Context context) {
//        if (newInstance == null) {
//            synchronized (DBManager.class) {
//                if (newInstance == null) {
//                    newInstance = new DBManager(context);
//                }
//            }
//        }
//        return newInstance;
//    }
//
//    /**
//     * 获取可读数据库
//     *
//     * @return
//     */
//    private SQLiteDatabase getReadableDatabase() {
//        if (mOpenHelper == null) {
//            mOpenHelper = new DaoMaster.DevOpenHelper(mContext, DBNAME, null);
//        }
//        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
//        return db;
//    }
//
//    /**
//     * 获取可写数据库
//     *
//     * @return
//     */
//    private SQLiteDatabase getWriteableDatabase() {
//        if (mOpenHelper == null) {
//            mOpenHelper = new DaoMaster.DevOpenHelper(mContext, DBNAME, null);
//        }
//        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
//        return db;
//    }
//
//    public void insertUSER(User user) {
//        if (user==null){
//            return;
//        }
//        DaoMaster daoMaster = new DaoMaster(getWriteableDatabase());
//        DaoSession daoSession = daoMaster.newSession();
//        UserDao userDao = daoSession.getUserDao();
//        userDao.insert(user);
//    }
//
//
//    public List<User> queryUSER() {
//        DaoMaster daoMaster = new DaoMaster(getWriteableDatabase());
//        DaoSession daoSession = daoMaster.newSession();
//        UserDao userDao = daoSession.getUserDao();
//        QueryBuilder<User> qb=userDao.queryBuilder();
//        List<User> list=qb.list();
//        return list;
//    }
//
//}
