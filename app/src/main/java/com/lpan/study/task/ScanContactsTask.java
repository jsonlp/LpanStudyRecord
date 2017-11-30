package com.lpan.study.task;

import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import com.lpan.study.context.AppContext;
import com.lpan.study.model.ContactInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liaopan on 2017/11/30 10:02.
 */

public class ScanContactsTask extends AsyncTask<Void, Void, List<ContactInfo>> {

    @Override
    protected List<ContactInfo> doInBackground(Void... params) {
        try {
            List<ContactInfo> list = new ArrayList<>();
            Uri contactUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
            Cursor cursor = AppContext.getContext().getContentResolver().query(contactUri,
                    new String[]{"display_name", "sort_key", "contact_id", "data1"},
                    null, null, "sort_key");
            String contactName;
            String contactNumber;
            String contactSortKey;
            int contactId;
            while (cursor.moveToNext()) {
                contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                contactNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contactId = cursor.getInt(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
                contactSortKey = getSortkey(cursor.getString(1));
                ContactInfo contactsInfo = new ContactInfo(contactId, contactName, contactNumber, contactSortKey);
                if (contactName != null)
                    list.add(contactsInfo);
            }
            cursor.close();//使用完后一定要将cursor关闭，不然会造成内存泄露等问题
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return null;
    }


    @Override
    protected void onPostExecute(List<ContactInfo> contactInfos) {
        super.onPostExecute(contactInfos);

    }

    private String getSortkey(String sortKeyString) {
        String key = sortKeyString.substring(0, 1).toUpperCase();
        if (key.matches("[A-Z]")) {
            return key;
        } else
            return "#";   //获取sort key的首个字符，如果是英文字母就直接返回，否则返回#。
    }

}
