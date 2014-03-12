package com.f17y;

import com.chartboost.sdk.*;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.apportable.utils.ThreadUtils;


public class ChartboostWrapperActivity extends ChartboostActivity {
  public void didDismissInterstitial(java.lang.String location) {
    super.didDismissInterstitial(location);
    finish();
  }
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    
    //Remove title bar
    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    
    //Remove notification bar
    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
      WindowManager.LayoutParams.FLAG_FULLSCREEN);
    
    this.getWindow().getDecorView().setBackgroundColor(0xFF000000);
  }
  
  public void onStart() {
    super.onStart();
    Chartboost.sharedChartboost().showInterstitial();
  }
  
  public String getChartboostAppSignature() {
    return ChartboostWrapper.chartboostAppSignature;
  }
  
  public String getChartboostAppID() {
    return ChartboostWrapper.chartboostAppId;
  }
}


