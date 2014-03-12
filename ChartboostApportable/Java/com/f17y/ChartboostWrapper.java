package com.f17y;

import com.chartboost.sdk.*;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.apportable.utils.ThreadUtils;


public class ChartboostWrapper extends Object {
  private Chartboost cb;
  private Activity activity;
  
  public static String chartboostAppId;
  public static String chartboostAppSignature;
  
  public ChartboostWrapper(String appId, String appSignature, Activity activity) {
    final String s_appId = appId;
    final String s_appSignature = appSignature;
    final ChartboostWrapper wrapper = this;
    this.activity = activity;
    
    chartboostAppId = appId;
    chartboostAppSignature = appSignature;
    
    this.activity.runOnUiThread(new Runnable() {
      @Override
      public void run() {
        Log.i("ChartboostWrapper", "create in activity: " + wrapper.activity.getClass().getSimpleName() +
            " with appId: " + s_appId + ", appSignature: " + s_appSignature + ", threadId: " +
            (int)Thread.currentThread().getId());
        wrapper.cb = Chartboost.sharedChartboost();
        wrapper.cb.onCreate(wrapper.activity, s_appId, s_appSignature, null);
        wrapper.cb.setAnimationsOff(true);
        
        wrapper.cb.setDelegate(new ChartboostDefaultDelegate() {
          @Override
          public void didDismissInterstitial(String location) {
            wrapper.destroyFirstChildRelativeLayout(wrapper.activity.getWindow().getDecorView().getRootView());
            
            //wrapper.cb.dismissChartboostView();
            //wrapper.cb.cacheInterstitial(location);
          }
          
          @Override
          public void didFailToLoadUrl(String url) {
          }
        });
      }
    });
  }
  
  private boolean destroyFirstChildRelativeLayout(View view)
  {
    if (view instanceof RelativeLayout)
    {
      ((ViewGroup)view.getParent()).removeView(view);
      return true;
    }
    
    if (view instanceof ViewGroup)
    {
      ViewGroup vg = (ViewGroup)view;
      for (int i = 0; i < vg.getChildCount(); i++) {
        if (destroyFirstChildRelativeLayout(vg.getChildAt(i)))
          return true;
      }
    }
    
    return false;
  }
  
  public void onStart() {
    final ChartboostWrapper wrapper = this;
    
    wrapper.activity.runOnUiThread(new Runnable() {
      @Override
      public void run() {
        Log.i("ChartboostWrapper", "onStart in activity: " + wrapper.activity.getClass().getSimpleName());
        wrapper.cb.onStart(wrapper.activity);
        // Notify the beginning of a user session. Must not be dependent on user actions or any prior network requests.
        wrapper.cb.startSession();
      }
    });
  }
  
  public void onStop() {
    final ChartboostWrapper wrapper = this;
    
    wrapper.activity.runOnUiThread(new Runnable() {
      @Override
      public void run() {
        Log.i("ChartboostWrapper", "onStop in activity: " + wrapper.activity.getClass().getSimpleName());
        wrapper.cb.onStop(wrapper.activity);
      }
    });
  }
  
  public void onDestroy() {
    final ChartboostWrapper wrapper = this;
    
    wrapper.activity.runOnUiThread(new Runnable() {
      @Override
      public void run() {
        Log.i("ChartboostWrapper", "onDestroy in activity: " + wrapper.activity.getClass().getSimpleName());
        wrapper.cb.onDestroy(wrapper.activity);
      }
    });
  }
  
  public void cacheInterstitial() {
    final ChartboostWrapper wrapper = this;
    
    wrapper.activity.runOnUiThread(new Runnable() {
      @Override
      public void run() {
        Log.i("ChartboostWrapper", "cacheInterstitial in activity: " + wrapper.activity.getClass().getSimpleName());
        wrapper.cb.cacheInterstitial();
      }
    });
  }
  
  public void showInterstitial() {
    final ChartboostWrapper wrapper = this;
    
    wrapper.activity.runOnUiThread(new Runnable() {
      @Override
      public void run() {
        Log.i("ChartboostWrapper", "showInterstitial in activity: " + wrapper.activity.getClass().getSimpleName());
        
        boolean has = wrapper.cb.hasCachedInterstitial();
        Log.i("ChartboostWrapper", "hasCachedInterstitial: " + has);
        
        if (has)
        {
          wrapper.activity.startActivity(new Intent(wrapper.activity, ChartboostWrapperActivity.class));
        }
        else
        {
          wrapper.cb.cacheInterstitial();
        }
      }
    });
    
    // ThreadUtils.runOnGlThread(new Runnable() {
    //   @Override
    //   public void run() {
    //     Log.i("ChartboostWrapper", "showInterstitial in activity: " + wrapper.activity.getClass().getSimpleName() +
    //         ", threadId: " +
    //         (int)Thread.currentThread().getId());
    //     wrapper.cb.showInterstitial();
    //   }
    // });
  }
}


