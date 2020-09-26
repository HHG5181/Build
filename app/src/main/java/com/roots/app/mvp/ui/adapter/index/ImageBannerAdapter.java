package com.roots.app.mvp.ui.adapter.index;

import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.roots.app.mvp.model.entity.index.BannerBean;
import com.roots.app.mvp.utils.GlideImageUtils;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

/**
 * @author : bird
 * @Description : 轮播图适配器
 * @Date : 2020/8/29 2:08
 */

public class ImageBannerAdapter extends BannerAdapter<BannerBean, ImageBannerAdapter.BannerViewHolder> {

    public ImageBannerAdapter(List<BannerBean> datas) {
        super(datas);
    }

    @Override
    public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return new BannerViewHolder(imageView);

    }

    @Override
    public void onBindView(BannerViewHolder holder, BannerBean data, int position, int size) {
        GlideImageUtils.display(holder.imageView.getContext(), data.getImgUrl(), holder.imageView);
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public BannerViewHolder(@NonNull ImageView view) {
            super(view);
            this.imageView = view;
        }
    }
}
