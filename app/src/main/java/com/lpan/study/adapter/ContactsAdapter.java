package com.lpan.study.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.lpan.R;
import com.lpan.study.model.UserInfo;
import com.lpan.study.utils.LetterUtil;

import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by lpan on 2018/3/12.
 */

public class ContactsAdapter extends AbstractAdapter<UserInfo> implements StickyListHeadersAdapter,SectionIndexer {

    public ContactsAdapter(Context context) {
        mContext = context;
        mList = new ArrayList<>();

    }

    @Override
    public void clearItem() {

    }

    @Override
    public void addItem(UserInfo s) {
        mList.add(s);
    }

    @Override
    public void addItem(List<UserInfo> list) {
        mList.addAll(list);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_text, parent, false);
            holder.mTextView = (TextView) convertView.findViewById(R.id.text1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mTextView.setText(mList.get(position).getName());
        return convertView;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;
        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_sticky_header, parent, false);

            holder.mTextView = (TextView) convertView.findViewById(R.id.text1);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }
        //set header text as first char in name
        String headerText = "" + LetterUtil.getPinYin(mList.get(position).getName()).substring(0, 1);
        holder.mTextView.setText(headerText);
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        return LetterUtil.getPinYin(mList.get(position).getName()).toUpperCase().charAt(0);
    }

    @Override
    public Object[] getSections() {
        return null;
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        if (sectionIndex == '↑') {
            return 0;
        }
        for (int i = 0; i < getCount(); i++) {
            String sortStr = LetterUtil.getPinYin(mList.get(i).getName()).substring(0, 1);
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == sectionIndex) {
                return i + 1;
            }
        }
        return -1;
    }

    @Override
    public int getSectionForPosition(int position) {
        return LetterUtil.getPinYin(mList.get(position).getName()).substring(0, 1).charAt(0);
    }

    class ViewHolder {
        TextView mTextView;
    }

    class HeaderViewHolder {
        TextView mTextView;
    }
}
