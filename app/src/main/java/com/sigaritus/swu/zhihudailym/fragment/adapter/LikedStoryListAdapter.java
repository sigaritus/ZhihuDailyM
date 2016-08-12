package com.sigaritus.swu.zhihudailym.fragment.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.sigaritus.swu.zhihudailym.R;
import com.sigaritus.swu.zhihudailym.bean.ZhihuDetailStory;
import com.sigaritus.swu.zhihudailym.bean.ZhihuHotStory;
import com.sigaritus.swu.zhihudailym.bean.ZhihuStory;

import java.util.List;

/**
 * Created by Administrator on 2016/8/12.
 */
public class LikedStoryListAdapter extends BaseRecyclerAdapter {

    List<ZhihuDetailStory> mStoryList;
    String TAG ="likedStoryAdapter";
    public void setmStoryList(List<ZhihuDetailStory> mStoryList) {
        this.mStoryList = mStoryList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.story_list_item, parent, false);

        return new Story(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        Story viewHolder = (Story) holder;
        ZhihuDetailStory story = mStoryList.get(position);
        Log.d(TAG, "onBindViewHolder: "+story);
        Glide.with(holder.itemView.getContext()).load(story.getImage()).into(viewHolder.StoryThumbnail);
        viewHolder.StoryTitle.setText(story.getTitle());
        viewHolder.itemView.setTag(story.getId());
        viewHolder.itemView.setOnClickListener(this);

    }

    @Override
    public int getItemCount() {
        return mStoryList!=null?mStoryList.size():0;
    }
}
