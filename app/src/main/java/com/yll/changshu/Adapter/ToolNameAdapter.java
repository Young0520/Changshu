package com.yll.changshu.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.yll.changshu.R;
import com.yll.changshu.entity.ToolName;

import java.util.List;

public class ToolNameAdapter extends BaseAdapter {
    private TextView toolname;
    private Context context;
    private List<ToolName> toolnameList;

    public ToolNameAdapter(Context context, List<ToolName> toolNames){
        this.context = context;
        this.toolnameList = toolNames;
    }
    @Override
    public int getCount() {
        return toolnameList.size();
    }

    @Override
    public Object getItem(int position) {
        return toolnameList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.second_item, null);
            toolname = (TextView) convertView.findViewById(R.id.toolname);

            ToolName toolName = toolnameList.get(position);
            if(toolName!=null){
                convertView.setTag(toolName);
                toolname.setText(toolName.getNode_name());
            }
        }
        return convertView;
    }

}
