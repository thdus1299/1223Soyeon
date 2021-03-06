package com.example.kangjisung.likeroom.inventory.sales;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kangjisung.likeroom.R;

import java.util.ArrayList;

/**
 * Created by kangjisung on 2016-11-30.
 */

public class InvenAdapter extends BaseAdapter {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<InvenListViewItem> listViewItemList = new ArrayList<InvenListViewItem>() ;
    // ListViewAdapter의 생성자
    public InvenAdapter() {
    }
    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }
    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.inven_listview, parent, false);
        }
        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView BnameTextView = (TextView) convertView.findViewById(R.id.breadNameText) ;
        TextView todayTextView = (TextView) convertView.findViewById(R.id.todayText) ;
        TextView SalesTextView = (TextView) convertView.findViewById(R.id.salesText);
        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        InvenListViewItem listViewItem = listViewItemList.get(position);
        // 아이템 내 각 위젯에 데이터 반영
        BnameTextView.setText(listViewItem.getBname());
        todayTextView.setText(listViewItem.gettoday());
        SalesTextView.setText(listViewItem.getsales());
        return convertView;
    }
    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }
    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }
    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(String Bname, String today, String sales) {
        InvenListViewItem item = new InvenListViewItem();
        item.setBname(Bname);
        item.settoday(today);
        item.setsales(sales);
        listViewItemList.add(item);
    }
}