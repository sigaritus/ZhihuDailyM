package com.sigaritus.swu.zhihudailym.fragment.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sigaritus.swu.zhihudailym.R;
import com.sigaritus.swu.zhihudailym.bean.ZhihuHotStory;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/19.
 */
public class HotStoryListAdapter extends RecyclerView.Adapter {

    List<ZhihuHotStory> hotStories;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hot_list_item, parent, false);


        return new HotStory(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HotStory viewHolder = (HotStory) holder;
        ZhihuHotStory item = hotStories.get(position);
        Glide.with(holder.itemView.getContext()).load(item.getThumbnail()).into(viewHolder.hotStoryThumbnail);
        viewHolder.hotStoryTitle.setText(item.getTitle());
    }

    @Override
    public int getItemCount() {
        return hotStories == null ? 0 : hotStories.size();
    }

    public void setHotStories(List<ZhihuHotStory> hotStories) {
        this.hotStories = hotStories;
        notifyDataSetChanged();
    }

    class HotStory extends RecyclerView.ViewHolder {
        @Bind(R.id.hot_story_thumbnail)
        ImageView hotStoryThumbnail;
        @Bind(R.id.hot_story_title)
        TextView hotStoryTitle;

        public HotStory(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
