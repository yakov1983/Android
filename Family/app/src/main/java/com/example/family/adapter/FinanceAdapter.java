package com.example.family.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.family.R;
import com.example.family.model.Finance;

public class FinanceAdapter extends BaseAdapter {

    private Finance[] finances;
    private Context context;     ////  хранить информацию о запущенном в системе процессе

    public FinanceAdapter(Context context, Finance[] finances) {
        this.finances = finances;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.finances.length;
    }

    @Override
    public Object getItem(int position) {
        return this.finances[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {  //  вызывается самостоятельно опер. системой
        Finance f = this.finances[position];
        View view = convertView;

        ///  если элемент списка отображается впервые, то
        ///   convertView == null, и нам необходимо загружить
        //  макет элемента списка самостоятельно

        if (view == null){  ///  отображается впервые
            view = LayoutInflater.from(this.context).inflate(R.layout.finance_item, parent, false);
        }

        TextView sum = view.findViewById(R.id.sum);
        TextView date = view.findViewById(R.id.date);
        TextView name = view.findViewById(R.id.name);
        TextView creditLabel = view.findViewById(R.id.creditLabel);

        sum.setText(String.valueOf(f.sum));
        date.setText(f.date);
        name.setText(f.name);
        creditLabel.setText(f.creditLabel);

        return view;
    }
}
