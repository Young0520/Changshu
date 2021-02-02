package com.yll.changshu.Adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.yll.changshu.R;
import com.yll.changshu.entity.BorrowTool;
import com.yll.changshu.entity.ToolInventory;

import org.json.JSONException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BorrowToolAdapter extends BaseAdapter {
    private TextView toolname;
    private TextView type;
    private TextView rest_number;
//    private EditText borrow_num;

    private Context context;
    private List<BorrowTool> borrowToolList;


    private Map<Integer,String> map=new HashMap<>();

    public BorrowToolAdapter(Context context, List<BorrowTool> borrowToolList){
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.borrow_item, null);

            toolname = (TextView) convertView.findViewById(R.id.toolname);
            type = (TextView) convertView.findViewById(R.id.type);
            rest_number = (TextView) convertView.findViewById(R.id.rest_number);
            viewHolder.borrow_num = (EditText)convertView.findViewById(R.id.borrow_num);

            viewHolder.borrow_num.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    try {
                        //取位置
                        map.put(position, viewHolder.borrow_num.getText().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            if(map!=null&&map.containsKey(position)){
                viewHolder.borrow_num.setText(map.get(position));
            }

            BorrowTool borrowTool = borrowToolList.get(position);
            if(borrowTool!=null){
                convertView.setTag(borrowTool);
                toolname.setText(borrowTool.getToolname());
                type.setText(borrowTool.getType());
                rest_number.setText(""+borrowTool.getRest_number());
            }
        }
        return convertView;
    }

    public class ViewHolder {
        public EditText borrow_num;
    }
}
