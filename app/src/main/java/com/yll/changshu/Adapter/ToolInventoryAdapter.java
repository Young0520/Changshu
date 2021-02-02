package com.yll.changshu.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.yll.changshu.R;
import com.yll.changshu.entity.ToolInventory;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ToolInventoryAdapter extends BaseAdapter {
    private TextView type;
    private TextView parameter;
    private TextView rest_number;
//    private CheckBox checkBox;

    private Map<Integer,Boolean> map=new HashMap<>();

    private int user_rank;

    private Context context;
    private List<ToolInventory> toolInventoryList;

    public ToolInventoryAdapter(Context context, List<ToolInventory> toolInventoryList, int user_rank){
        this.context = context;
        this.toolInventoryList = toolInventoryList;
        this.user_rank = user_rank;
    }
    @Override
    public int getCount() {
        return toolInventoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return toolInventoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.tool_item, null);
            type = (TextView) convertView.findViewById(R.id.type);
            parameter = (TextView) convertView.findViewById(R.id.parameter);
            rest_number = (TextView) convertView.findViewById(R.id.rest_number);
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
            if(user_rank == 1){
                viewHolder.checkBox.setVisibility(View.VISIBLE);
            }

            viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (viewHolder.checkBox.isChecked()){
                        map.put(position,true);

                    }else {
                        map.remove(position);

                    }
                }
            });

            if(map!=null&&map.containsKey(position)){
                viewHolder.checkBox.setChecked(true);
            }else {
                viewHolder.checkBox.setChecked(false);
            }

            ToolInventory toolInventory = toolInventoryList.get(position);
            if(toolInventory!=null){
                convertView.setTag(toolInventory);
                type.setText(toolInventory.getTooltype());
                parameter.setText(toolInventory.getParameter());
                rest_number.setText(""+toolInventory.getNow_number());
            }
        }
        return convertView;
    }

    public class ViewHolder {
        public CheckBox checkBox;
    }
}
