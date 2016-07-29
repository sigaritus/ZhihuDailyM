package com.sigaritus.swu.zhihudailym.fragment.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.sigaritus.swu.zhihudailym.R;
import com.sigaritus.swu.zhihudailym.bean.ZhihuHotStory;
import com.sigaritus.swu.zhihudailym.bean.ZhihuStory;

import java.util.List;

/**
 * Created by Administrator on 2016/7/29.
 */
public class LatestStoryListAdapter extends BaseRecyclerAdapter {
    private List<ZhihuStory> laststStory;

    public void setLaststStory(List<ZhihuStory> laststStory) {
        this.laststStory = laststStory;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.story_list_item,parent,false);
        return new Story(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ZhihuStory item = laststStory.get(position);
        Story viewHolder = (Story) holder;
        Glide.with(holder.itemView.getContext()).load(item.getImages()[0]).into(viewHolder.StoryThumbnail);
        viewHolder.StoryTitle.setText(item.getTitle());
        viewHolder.itemView.setTag(item.getId());
        viewHolder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return laststStory==null?0:laststStory.size();
    }


}
