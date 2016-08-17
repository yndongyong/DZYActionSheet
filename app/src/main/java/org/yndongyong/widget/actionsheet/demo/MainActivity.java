package org.yndongyong.widget.actionsheet.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.yndongyong.widget.actionsheet.DZYActionSheet;

public class MainActivity extends AppCompatActivity {

    TextView open_action_sheet;
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
    }

    private void openActionSheet() {
        DZYActionSheet actionSheet = new DZYActionSheet(this);
        String[] arrays = {"拍照", "从相册中选择"};
        actionSheet.show(this.getWindow().getDecorView(),arrays, new DZYActionSheet.OnDZYActionSheetListener() {
            @Override
            public void onClick(String text, int position) {
                Toast.makeText(MainActivity.this, "text: "+text+" ,position:"+position, Toast
                        .LENGTH_SHORT).show();
            }
        });
    }
}
