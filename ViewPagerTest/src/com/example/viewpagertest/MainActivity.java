package com.example.viewpagertest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {

	private ViewPagerAdapter viewPagerAdapter;
	private ViewPager mViewPager;
	private boolean isLoop = true;
	private ViewGroup group;

	private ImageView[] tips;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();

	}

	public void initView() {

		// 将5个布局文件填充为View
		LayoutInflater inflater = getLayoutInflater();
		View view1 = inflater.inflate(R.layout.layout1, null);
		view1.setBackground(getResources().getDrawable(R.drawable.image1));
		View view2 = inflater.inflate(R.layout.layout2, null);
		View view3 = inflater.inflate(R.layout.layout3, null);
		View view4 = inflater.inflate(R.layout.layout4, null);
		View view5 = inflater.inflate(R.layout.layout5, null);

		// 将View添加到一个list集合中作为参数传递给Adapter
		List<View> mListViews = new ArrayList<View>();
		mListViews.add(view1);
		mListViews.add(view2);
		mListViews.add(view3);
		mListViews.add(view4);
		mListViews.add(view5);

		// 组件初始化
		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		group = (ViewGroup) findViewById(R.id.viewGroup);

		// 初始化Adapter
		viewPagerAdapter = new ViewPagerAdapter(mListViews);
		mViewPager.setAdapter(viewPagerAdapter);

		// 将点图片加入到ViewGroup中
		tips = new ImageView[mListViews.size()];
		for (int i = 0; i < tips.length; i++) {
			ImageView imageView = new ImageView(this);
			imageView.setLayoutParams(new LayoutParams(10, 10));
			tips[i] = imageView;
			if (i == 0) {
				tips[i].setBackgroundResource(R.drawable.focus);
			} else {
				tips[i].setBackgroundResource(R.drawable.un_focus);
			}

			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
					new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT,
							LayoutParams.WRAP_CONTENT));
			layoutParams.leftMargin = 5;
			layoutParams.rightMargin = 5;
			group.addView(imageView, layoutParams);
		}

		// 控制图片自动切换
		class NewThread extends Thread {
			@Override
			public void run() {
				while (isLoop) {

					SystemClock.sleep(5000);
					handler.sendEmptyMessage(0);

				}
			}
		}
		final NewThread mNewThread = new NewThread();
		mNewThread.start();

		// viewpager添加事件监听器
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageScrollStateChanged(int arg0) {
				Log.d("haha", "--------changed:" + arg0);

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				Log.d("haha", "-------scrolled arg0:" + arg0);
				Log.d("haha", "-------scrolled arg1:" + arg1);
				Log.d("haha", "-------scrolled arg2:" + arg2);
			}

			@Override
			public void onPageSelected(int cunrrentIndex) {
				Log.d("haha", "------selected:" + cunrrentIndex);

				for (int i = 0; i < tips.length; i++) {
					if (i == (cunrrentIndex % 5)) {
						tips[i].setBackgroundResource(R.drawable.focus);
					} else {
						tips[i].setBackgroundResource(R.drawable.un_focus);
					}
				}

			}
		});
		// 设置当前页卡
		// int n = Integer.MAX_VALUE / 2 % mListViews.size();
		// int itemPosition = Integer.MAX_VALUE / 2 - n;
		mViewPager.setCurrentItem(mListViews.size() * 100);

	}

	// 控制自动切换图片
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
		};
	};

}
