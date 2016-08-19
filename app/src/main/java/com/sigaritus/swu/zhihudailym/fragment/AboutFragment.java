package com.sigaritus.swu.zhihudailym.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sigaritus.swu.zhihudailym.R;

import java.util.Calendar;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;


public class AboutFragment extends Fragment {

    public AboutFragment() {
        // Required empty public constructor
    }

    public static AboutFragment newInstance(String param1, String param2) {
        AboutFragment fragment = new AboutFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    /**
     * setTitle(String)	Set title of the element
     setColor(Int)	Set color of the element
     setIcon(Int)	Set icon of the element
     setValue(String)	Set Element value like Facebook ID
     setTag(String)	Set a unique tag value to the element
     setIntent(Intent)	Set an intent to be called on onClickListener
     setGravity(Gravity)	Set a Gravity for the element
     setOnClickListener(View.OnClickListener)
     If intent isn't suitable for you need,
     implement your custom behaviour by overriding the click listener
     **/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Element versionElement = new Element("","版本检查",R.drawable.ic_get_app_black_36dp);
        Element weiboElement = new Element("","微博",R.drawable.ic_sina);
        return new AboutPage(getContext())
                .isRTL(false)
                .setDescription(getString(R.string.app_description))
                .addGroup("联系我")
                .addEmail("sigaritus@163.com")
                .addGitHub("sigaritus")
                .addItem(weiboElement)
                .addItem(versionElement)
                .create();

    }

    Element getCopyRightsElement() {
        Element copyRightsElement = new Element();
        final String copyrights = String.format(getString(R.string.copy_right), Calendar.getInstance().get(Calendar.YEAR));
        copyRightsElement.setTitle(copyrights);
//        copyRightsElement.setIcon();
        copyRightsElement.setColor(ContextCompat.getColor(getContext(), mehdi.sakout.aboutpage.R.color.about_item_icon_color));
        copyRightsElement.setGravity(Gravity.CENTER);
        copyRightsElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return copyRightsElement;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

}
