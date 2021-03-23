package com.yll.changshu.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yll.changshu.R;
import com.yll.changshu.entity.BorrowList;
import com.yll.changshu.entity.BorrowTool;
import com.yll.changshu.util.pubFun;

import java.util.List;

public class BorrowListAdpter extends BaseAdapter {
    private TextView list_id;
    private TextView apply_time;
    private TextView state;

    private Context context;
    private List<BorrowList> borrowLists;

    public BorrowListAdpter(Context context, List<BorrowList> borrowLists){
        this.context = context;
        this.borrowLists = borrowLists;
    }
    @Override
    public int getCount() {
        return borrowLists.size();
    }

    @Override
    public Object getItem(int position) {
        return borrowLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.apply_item, null);
            list_id = (TextView) convertView.findViewById(R.id.list_id);
            apply_time = (TextView) convertView.findViewById(R.id.apply_time);
            state = (TextView) convertView.findViewById(R.id.state);

            BorrowList borrowList = borrowLists.get(position);
            if(borrowList!=null){
                convertView.setTag(borrowList);
                String stateText = "已审核";
                list_id.setText(""+borrowList.getList_id());
                apply_time.setText(pubFun.format(borrowList.getApply_out()));
                if(borrowList.getState() == 0){
                    stateText = "未审核";
                }
                state.setText(stateText);
            }
        }
        return convertView;
    }
}
