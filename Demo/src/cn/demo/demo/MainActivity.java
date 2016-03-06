package cn.demo.demo;

import java.io.BufferedInputStream;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.TextView;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        RoundProgressBar rpb = (RoundProgressBar) findViewById(R.id.rpb);
        rpb.setProgress(50);
        ViewPager viewPager;
        
    }
    

}
