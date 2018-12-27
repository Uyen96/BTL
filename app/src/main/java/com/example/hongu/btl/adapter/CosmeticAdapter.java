package com.example.hongu.btl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hongu.btl.R;
import com.example.hongu.btl.model.Cosmetic;

import java.util.List;


public class CosmeticAdapter extends ArrayAdapter {
    private Context mContext;
    private int mResoure;
    private List<Cosmetic> mCosmetics;

    public CosmeticAdapter(Context context, int resource, List<Cosmetic> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResoure = resource;
        this.mCosmetics = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        //mCosmetics = new ArrayList<>();
        if (convertView != null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.item_list_cosmetic, parent, false);
            holder = new ViewHolder();
            holder.image = convertView.findViewById(R.id.image_cosmetic);
            holder.textId = convertView.findViewById(R.id.id);
            holder.textName = convertView.findViewById(R.id.name);
            holder.textPrice = convertView.findViewById(R.id.price);
            holder.textFunction = convertView.findViewById(R.id.function);
            holder.textType = convertView.findViewById(R.id.type);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Cosmetic cosmetic = mCosmetics.get(position);
        holder.textId.setText(cosmetic.getId());
        holder.textName.setText(cosmetic.getName());
        holder.textPrice.setText(String.valueOf(cosmetic.getPrice()));
        holder.textFunction.setText(cosmetic.getFunction());
        holder.textType.setText(cosmetic.getType());
        if (cosmetic.getType() == "Viên") {
            holder.image.setImageResource(R.drawable.vien);
        }
        if (cosmetic.getType() == "Bột") {
            holder.image.setImageResource(R.drawable.bot);
        }
        if (cosmetic.getType() == "Nước") {
            holder.image.setImageResource(R.drawable.nuoc);
        }
        return convertView;
    }

    public class ViewHolder {
        private ImageView image;
        private TextView textId;
        private TextView textName;
        private TextView textPrice;
        private TextView textFunction;
        private TextView textType;

    }
}
