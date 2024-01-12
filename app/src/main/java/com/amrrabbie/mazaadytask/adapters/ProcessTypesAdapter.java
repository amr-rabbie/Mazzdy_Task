package com.amrrabbie.mazaadytask.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.amrrabbie.mazaadytask.R;
import com.amrrabbie.mazaadytask.model.categoriesModel.CategoriesItem;
import com.amrrabbie.mazaadytask.model.propetiesModel.OptionsItem;
import java.util.List;

public class ProcessTypesAdapter  extends BaseAdapter {
    Context context; List<OptionsItem> list;
    public ProcessTypesAdapter(Context context, List<OptionsItem> list) {
        this.context=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public OptionsItem getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ProcessTypesAdapter.SpinnerViewHolder holder=null;
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.spinner_row, viewGroup, false);
            holder=new ProcessTypesAdapter.SpinnerViewHolder(view);
            view.setTag(holder);
        }else{
            holder= (ProcessTypesAdapter.SpinnerViewHolder) view.getTag();
        }

        OptionsItem item = list.get(i);

        holder.name.setText(item.getSlug());




        return view;
    }



    public class SpinnerViewHolder{

        TextView name;
        public SpinnerViewHolder(View v) {
            name=v.findViewById(R.id.txtname);

        }
    }
}
