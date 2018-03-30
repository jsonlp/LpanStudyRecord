package com.lpan.study.db;


import com.lpan.study.db.entity.Friend;
import com.lpan.study.greendao.FriendDao;
import com.lpan.study.jsonparser.MyJsonParser;
import com.lpan.study.model.FriendInfo;
import com.lpan.study.utils.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lpan on 2018/3/29.
 */

public class FriendDaoManager {


    public static void saveFriend(FriendInfo friendInfo) {
        String data = null;
        try {
            data = MyJsonParser.getInstance().writeValueAsString(friendInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String uuid = friendInfo.getUserInfo().getId();
        int relation = friendInfo.getRelation();
        Friend friend = new Friend(0, uuid, relation, data);
        DatabaseManager.getDaoSession().getFriendDao().insertOrReplace(friend);
    }

    public static void saveFriends(List<FriendInfo> friendInfos) throws IOException {
        long startTime = System.currentTimeMillis();
        long parserCost = 0;
        long insertCost = 0;

        List<Friend> list = new ArrayList<>();
        for (int i = 0; i < friendInfos.size(); i++) {
            FriendInfo friendInfo = friendInfos.get(i);
            String data = MyJsonParser.getInstance().writeValueAsString(friendInfo);
            String uuid = friendInfo.getUserInfo().getId();
            int relation = friendInfo.getRelation();
            Friend friend = new Friend(i, uuid, relation, data);
            list.add(friend);
        }
        parserCost = System.currentTimeMillis() - startTime;
        startTime = System.currentTimeMillis();
        DatabaseManager.getDaoSession().getFriendDao().insertOrReplaceInTx(list);
        insertCost = System.currentTimeMillis() - startTime;
        if (Log.DEBUG) {
            Log.d("FriendDaoManager", "saveFriends--------save friend success  count=" + list.size() + "  parserCost= " + parserCost + "   insertCost= " + insertCost);
        }
    }

    public static List<FriendInfo> getAllFriend() throws IOException {
        long startTime = System.currentTimeMillis();
        long parserCost = 0;
        long queryCost = 0;

        List<Friend> list = DatabaseManager.getDaoSession().getFriendDao().queryBuilder().list();
        queryCost = System.currentTimeMillis() - startTime;
        startTime = System.currentTimeMillis();

        List<FriendInfo> friendInfoList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Friend friend = list.get(i);
            String data = friend.getData();
            FriendInfo friendInfo = MyJsonParser.getInstance().readValue(data, FriendInfo.class);
            friendInfoList.add(friendInfo);
        }
        parserCost = System.currentTimeMillis() - startTime;
        if (Log.DEBUG) {
            Log.d("FriendDaoManager", "getAllFriend--------fetch friend success count=" + list.size() + " parserCost= " + parserCost + "  queryCost= " + queryCost);
        }
        return friendInfoList;
    }

    public static int getFriendCount() {
        int size = DatabaseManager.getDaoSession().getFriendDao().queryBuilder().list().size();
        if (Log.DEBUG) {
            Log.d("FriendDaoManager", "getFriendCount--------size=" + size);
        }
        return size;
    }

    public static List<Friend> querybyName(String id) {
        return DatabaseManager.getDaoSession().getFriendDao().queryBuilder()
                .where(FriendDao.Properties.Uuid.eq(id)).list();
    }


    public static void deletebyName(String id) {
        DatabaseManager.getDaoSession().getFriendDao().queryBuilder()
                .where(FriendDao.Properties.Uuid.eq(id)).buildDelete()
                .executeDeleteWithoutDetachingEntities();
    }
    /**
     * insert  count=1000  parserCost= 104   insertCost= 84
     * query count=1000 parserCost= 150  queryCost= 12
     *
     *
     *
     *insert  count=10000  parserCost= 908   insertCost= 683
     * query  count=10000 parserCost= 1502  queryCost= 123
     */

}
