package com.sigaritus.swu.zhihudailym.fragment.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
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
import com.sigaritus.swu.zhihudailym.db.DBManager;
import com.sigaritus.swu.zhihudailym.util.ToastUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/8/12.
 */
public class LikedStoryListAdapter extends BaseRecyclerAdapter {

    List<ZhihuDetailStory> mStoryList;
    String TAG ="likedStoryAdapter";
    private Context mContext;

    public LikedStoryListAdapter(Context mContext) {
        this.mContext = mContext;

    }

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
        final ZhihuDetailStory story = mStoryList.get(position);
        Log.d(TAG, "onBindViewHolder: "+story);
        Glide.with(holder.itemView.getContext()).load(story.getImage()).into(viewHolder.storyThumbnail);
        viewHolder.storyTitle.setText(story.getTitle());
        viewHolder.icExpand.setVisibility(View.VISIBLE);
        viewHolder.icExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(story);
            }
        });
        viewHolder.itemView.setTag(position);
        viewHolder.itemView.setOnClickListener(this);

    }

    private void showDialog(final ZhihuDetailStory story) {

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("友情提示：");
        builder.setMessage("是否确定取消收藏这篇文章");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBManager.getNewInstance(mContext).deleteZhihuDetailStory(story);
                mStoryList.remove(story);
                notifyDataSetChanged();
            }
        });
        builder.show();

    }

    @Override
    public int getItemCount() {
        return mStoryList!=null?mStoryList.size():0;
    }
}
