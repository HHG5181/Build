package com.roots.app.mvp.ui.adapter;

import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.roots.app.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FindAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    int currentIndex = 0;

    public OnItemClickListen mOnItemClickListen;

    LinearLayout speedView;

    public FindAdapter(@Nullable List<String> data) {
        super(R.layout.item_find_text, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, String name) {
        baseViewHolder.setText(R.id.text, name);
        speedView = (LinearLayout) baseViewHolder.getView(R.id.lllllllllllll);
        if (baseViewHolder.getAdapterPosition() == currentIndex) {
            speedView.setScaleX(1.5f);
            speedView.setScaleY(1.5f);
        } else {
            speedView.setScaleX(1f);
            speedView.setScaleY(1f);
        }
        speedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListen !=null) {
                    currentIndex = baseViewHolder.getAdapterPosition();
                    notifyDataSetChanged();
                    mOnItemClickListen.onItemClick(speedView, baseViewHolder.getAdapterPosition());
                }
            }
        });
    }



    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public interface OnItemClickListen {
        void onItemClick(View view, int position);
    }
}
