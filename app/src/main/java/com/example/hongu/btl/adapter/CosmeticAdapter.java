package com.example.hongu.btl.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hongu.btl.R;
import com.example.hongu.btl.model.Cosmetic;

import java.util.List;


public class CosmeticAdapter extends RecyclerView.Adapter<CosmeticAdapter.CosmeticViewHolder> {
    private Context mContext;
    private List<Cosmetic> mCosmetics;

    public OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public CosmeticAdapter(Context context, List<Cosmetic> cosmetics){
        mContext = context;
        mCosmetics = cosmetics;
    }

    @NonNull
    @Override
    public CosmeticViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_list_cosmetic, viewGroup, false);
        return new CosmeticViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CosmeticViewHolder cosemticViewHolder, int i) {

        cosemticViewHolder.binData(mCosmetics.get(i));
    }

    @Override
    public int getItemCount() {
        return mCosmetics != null ? mCosmetics.size() : 0;
    }

    public class CosmeticViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageCos;
        private TextView mTextIdCos;
        private TextView mTextNameCos;
        private TextView mTextPriceCos;
        private TextView mTextEffectCos;
        private TextView mTextTypeCos;
        private ImageView mImageEdit;
        private ImageView mImageDel;

        public CosmeticViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageCos = itemView.findViewById(R.id.image_item_cosmetic);
            mTextIdCos = itemView.findViewById(R.id.text_item_id);
            mTextNameCos = itemView.findViewById(R.id.text_item_name);
            mTextPriceCos = itemView.findViewById(R.id.text_item_price);
            mTextEffectCos = itemView.findViewById(R.id.text_item_effect);
            mTextTypeCos = itemView.findViewById(R.id.text_item_type);
            mImageEdit = itemView.findViewById(R.id.image_edit_cos);
            mImageDel = itemView.findViewById(R.id.image_del_cos);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onRowClicked(position);
                        }
                    }
                }
            });
            mImageEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onViewClicked(v, position);
                        }
                    }
                }
            });
            mImageDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onViewClicked(v, position);
                        }
                    }
                }
            });
        }

        public void binData(Cosmetic cosmetic){
            mTextIdCos.setText(String.valueOf(cosmetic.getId()));
            mTextNameCos.setText(cosmetic.getName());
            mTextPriceCos.setText(String.valueOf(cosmetic.getPrice()));
            mTextEffectCos.setText(cosmetic.getEffect());
            mTextTypeCos.setText(cosmetic.getType());
            if(cosmetic.getType() == "Nước"){
                mImageCos.setImageResource(R.mipmap.ic_launcher);
            }
            if(cosmetic.getType() == "Bột"){
                mImageCos.setImageResource(R.drawable.bot);
            }
            if(cosmetic.getType() == "Viên"){
               mImageCos.setImageResource(R.drawable.vien);
            }
        }
    }
}
