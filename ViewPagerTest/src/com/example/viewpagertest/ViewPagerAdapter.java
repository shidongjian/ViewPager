package com.example.viewpagertest;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class ViewPagerAdapter extends PagerAdapter {

	
	private List<View> mListViews;
	
	public ViewPagerAdapter(List<View> mListViews) {
        this.mListViews = mListViews;//构造方法，参数是我们的页卡，这样比较方便。
    }
	
	
	
	/** 
     * 销毁预加载以外的view对象, 会把需要销毁的对象的索引位置传进来，就是position， 
     * 因为mImageViewList只有五条数据，而position将会取到很大的值， 
     * 所以使用取余数的方法来获取每一条数据项。 
     */  
	
	@Override
    public void destroyItem(ViewGroup container, int position, Object object)     {    
        container.removeView(mListViews.get(position % mListViews.size()));//删除页卡
    }


	/** 
     * 创建一个view， 
     */  
    @Override
    public Object instantiateItem(ViewGroup container, int position) {    //这个方法用来实例化页卡        
         container.addView(mListViews.get(position % mListViews.size()),0);//添加页卡
         return mListViews.get(position % mListViews.size());
    }
	
	
	//返回页卡的数量
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
	return Integer.MAX_VALUE;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0==arg1;
	}
	
	
	
	
	
	

}
