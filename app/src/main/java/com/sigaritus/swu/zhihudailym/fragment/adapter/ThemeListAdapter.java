package com.sigaritus.swu.zhihudailym.fragment.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sigaritus.swu.zhihudailym.R;
import com.sigaritus.swu.zhihudailym.bean.ZhihuTheme;
import com.sigaritus.swu.zhihudailym.util.ToastUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/31.
 */
public class ThemeListAdapter extends BaseRecyclerAdapter {
    List<ZhihuTheme> zhihuThemeList;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.theme_list_item,parent,false);
        return new Theme(view);
    }

    public void setZhihuThemeList(List<ZhihuTheme> zhihuThemeList) {
        this.zhihuThemeList = zhihuThemeList;

        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ZhihuTheme item = zhihuThemeList.get(position);
        Theme theme = (Theme) holder;
        theme.themeDescription.setText(item.getDescription());
        theme.themeTitle.setText(item.getName());
        Glide.with(holder.itemView.getContext()).load(item.getThumbnail()).into(theme.themeThumb);
        theme.itemView.setOnClickListener(this);
        theme.itemView.setTag(item.getId());
    }

    @Override
    public int getItemCount() {
        return zhihuThemeList==null?0:zhihuThemeList.size();
    }

    class Theme extends RecyclerView.ViewHolder{
        @Bind(R.id.theme_title)
        TextView themeTitle;
        @Bind(R.id.theme_description)
        TextView themeDescription;
        @Bind(R.id.theme_thumb)
        ImageView themeThumb;
        public Theme(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
