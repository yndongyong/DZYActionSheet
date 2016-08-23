package org.yndongyong.widget.actionsheet.demo;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.yndongyong.widget.actionsheet.DZYActionSheet;

public class MainActivity extends AppCompatActivity {

    TextView open_action_sheet;
    Button btn_hidden, btn_show;

    int lastTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        open_action_sheet = (TextView) findViewById(R.id.open_action_sheet);
        open_action_sheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActionSheet();
            }
        });
        btn_hidden = (Button) findViewById(R.id.btn_hidden);
        btn_hidden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hidden();
            }
        });
        btn_show = (Button) findViewById(R.id.btn_show);
        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               show();
            }
        });
    }

    private void openActionSheet() {
        DZYActionSheet actionSheet = new DZYActionSheet(this);
        String[] arrays = {"拍照", "从相册中选择"};
        actionSheet.show(this.getWindow().getDecorView(), arrays, new DZYActionSheet.OnDZYActionSheetListener() {
            @Override
            public void onClick(String text, int position) {
                Toast.makeText(MainActivity.this, "text: " + text + " ,position:" + position, Toast
                        .LENGTH_SHORT).show();
            }
        });
    }

    public void hidden() {
        lastTop = open_action_sheet.getTop();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(open_action_sheet, View.Y,
                lastTop, 1200);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                if (BuildConfig.DEBUG) Log.d("MainActivity", "animatedValue:" + animatedValue);
            }
        });
        objectAnimator.setDuration(2000);
        objectAnimator.start();
    }
    
    public void show() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(open_action_sheet, View.Y, 
                -open_action_sheet.getTop(), lastTop);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                if (BuildConfig.DEBUG) Log.d("MainActivity", "animatedValue:" + animatedValue);
            }
        });
        objectAnimator.setDuration(2000);
        objectAnimator.start();
    }
}
