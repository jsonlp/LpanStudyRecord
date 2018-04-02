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


    public static void saveFriend(FriendInfo friendInfo) throws IOException {
        Friend friend = friendInfo2Friend(friendInfo);
        if (friend != null) {
            DatabaseManager.getDaoSession().getFriendDao().insertOrReplace(friend);
        }
    }

    public static void saveFriends(List<FriendInfo> friendInfos) throws IOException {
        List<Friend> list = new ArrayList<>();
        for (int i = 0; i < friendInfos.size(); i++) {
            FriendInfo friendInfo = friendInfos.get(i);
            Friend friend = friendInfo2Friend(friendInfo);
            if (friend != null) {
                list.add(friend);
            }
        }
        DatabaseManager.getDaoSession().getFriendDao().insertOrReplaceInTx(list);
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
            FriendInfo friendInfo = friend2FriendInfo(friend);
            if (friendInfo != null) {
                friendInfoList.add(friendInfo);
            }
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
     * <p>
     * <p>
     * <p>
     * insert  count=10000  parserCost= 908   insertCost= 683
     * query  count=10000 parserCost= 1502  queryCost= 123
     */


    private static Friend friendInfo2Friend(FriendInfo friendInfo) throws IOException {
        if (friendInfo == null || friendInfo.getUserInfo() == null) {
            return null;
        }
        String uuid = friendInfo.getUserInfo().getId();
        String data = MyJsonParser.getInstance().writeValueAsString(friendInfo);
        int relation = friendInfo.getRelation();
        return new Friend(uuid, relation, data);
    }

    private static FriendInfo friend2FriendInfo(Friend friend) throws IOException {
        if (friend == null) {
            return null;
        }
        String data = friend.getData();
        return MyJsonParser.getInstance().readValue(data, FriendInfo.class);
    }

}
