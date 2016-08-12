package com.sigaritus.swu.zhihudailym.fragment.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.sigaritus.swu.zhihudailym.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/29.
 */
public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter implements View.OnClickListener{

    private OnRecyclerItemClickListener OnRecyclerViewListener;
    public interface OnRecyclerItemClickListener{
        void onClick(View v,String data);
//        void onClick(View v,String data,int position);
    }


    public void setOnRecyclerViewListener(OnRecyclerItemClickListener onRecyclerViewListener) {
        OnRecyclerViewListener = onRecyclerViewListener;
    }

    @Override
    public void onClick(View view) {
        if (OnRecyclerViewListener!=null)
            OnRecyclerViewListener.onClick(view,view.getTag()+"");
    }



    class Story extends RecyclerView.ViewHolder {
        @Bind(R.id.story_thumbnail)
        ImageView StoryThumbnail;
        @Bind(R.id.story_title)
        TextView StoryTitle;

        public Story(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
