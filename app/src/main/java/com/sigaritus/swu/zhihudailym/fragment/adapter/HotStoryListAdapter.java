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
public class HotStoryListAdapter extends BaseRecyclerAdapter{

    List<ZhihuHotStory> hotStories;


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.story_list_item, parent, false);

        return new Story(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Story viewHolder = (Story) holder;
        ZhihuHotStory item = hotStories.get(position);
        Glide.with(holder.itemView.getContext()).load(item.getThumbnail()).into(viewHolder.storyThumbnail);
        viewHolder.storyTitle.setText(item.getTitle());
        viewHolder.itemView.setTag(item.getNews_id());
        viewHolder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return hotStories == null ? 0 : hotStories.size();
    }

    public void setHotStories(List<ZhihuHotStory> hotStories) {
        this.hotStories = hotStories;
        notifyDataSetChanged();
    }


}
