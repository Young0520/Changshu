package com.yll.changshu.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yll.changshu.R;
import com.yll.changshu.entity.BorrowTool;

import java.util.List;

public class AuditDetailAdapter extends BaseAdapter {
    private TextView type;
    private TextView rest_number;
    private TextView toolname;
    private TextView number;

    private Context context;
    private List<BorrowTool> borrowToolList;
    //    private ViewHolder holder;
    private int defItem;

    public AuditDetailAdapter(Context context, List<BorrowTool> borrowToolList){
        this.context = context;
        this.borrowToolList = borrowToolList;
    }
    @Override
    public int getCount() {
        return borrowToolList.size();
    }

    @Override
    public Object getItem(int position) {
        return borrowToolList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.detail_item, null);
            toolname = (TextView) convertView.findViewById(R.id.toolname);
            type = (TextView) convertView.findViewById(R.id.type);
            rest_number = (TextView) convertView.findViewById(R.id.rest_number);
            number = (TextView) convertView.findViewById(R.id.borrow_num);

            BorrowTool borrowTool = borrowToolList.get(position);
            if(borrowTool!=null){
                convertView.setTag(borrowTool);
                toolname.setText(borrowTool.getToolname());
                type.setText(borrowTool.getType());
                rest_number.setText(""+borrowTool.getNumber());
                number.setText(""+borrowTool.getNumber());
            }
        }
        return convertView;
    }
}
