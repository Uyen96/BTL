package com.example.hongu.btl.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hongu.btl.R;
import com.example.hongu.btl.model.Effect;

import java.util.List;

public class EffectAdapter extends RecyclerView.Adapter<EffectAdapter.ViewHolder> {
    private Context mContext;
    private List<Effect> mEffects;
    public OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public EffectAdapter(Context context, List<Effect> functions) {
        mEffects = functions;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.list_effect, viewGroup, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.binData(mEffects.get(i));
    }

    @Override
    public int getItemCount() {
        return mEffects != null ? mEffects.size() : 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextEffectId;
        public TextView mTextEffectName;
        public TextView mTextEffectDes;
        private ImageView mImageEffectEdit;
        private ImageView mImageEffectDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextEffectId = itemView.findViewById(R.id.list_text_effect_id);
            mTextEffectName = itemView.findViewById(R.id.list_text_effect_name);
            mTextEffectDes = itemView.findViewById(R.id.list_text_effect_description);
            mImageEffectEdit = itemView.findViewById(R.id.list_image_edit);
            mImageEffectDelete = itemView.findViewById(R.id.list_image_delete);

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
            mImageEffectEdit.setOnClickListener(new View.OnClickListener() {
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
            mImageEffectDelete.setOnClickListener(new View.OnClickListener() {
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

        public void binData(Effect effect) {
            mTextEffectId.setText(String.valueOf(effect.getId()));
            mTextEffectName.setText(effect.getName());
            mTextEffectDes.setText(effect.getDescription());
        }
    }
}


