package com.slowlife.facedoor.domain;

/**
 * Created by 泽林 on 2018/4/5.
 */
/**
 * 主页TAB对应实体
 * Created by HFS on 2017/5/7.
 */

public class Tab {
    private int title; // 文字
    private int icon; // 图标
    private Class fragment; // 对应fragment

    public Tab(Class fragment,int title, int icon ) {
        this.title = title;
        this.icon = icon;
        this.fragment = fragment;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public Class getFragment() {
        return fragment;
    }

    public void setFragment(Class fragment) {
        this.fragment = fragment;
    }
}
