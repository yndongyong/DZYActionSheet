package org.yndongyong.widget.actionsheet;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

/**
 * 使用例子
 * DZYActionSheet actionSheet = new DZYActionSheet(this);
 * String[] arrays = {"拍照", "从相册中选择"};
 * actionSheet.show(this.getWindow().getDecorView(), arrays, new DZYActionSheet.OnDZYActionSheetListener() {
 *
 * @Override public void onClick(String text, int position) {
 * Toast.makeText(MainActivity.this, "text: " + text + " ,position:" + position, Toast
 * .LENGTH_SHORT).show();
 * }
 * @Override public void onCancel() {
 * Toast.makeText(MainActivity.this, "user cancel !", Toast.LENGTH_SHORT).show();
 * }
 * });
 * <p/>
 * Created by Dong on 2016/8/17.
 */
public class DZYActionSheet {

    private static PopupWindow mPopupWindow;
    private static ListView mListView;
    private Context mContext;
    private LinearLayout mPopupView;

    public DZYActionSheet(Context context) {
        this.mContext = context;
    }

    /**
     * @param arrays   列表项 数组
     * @param callback
     */
    public void show(View targetView, final String[] arrays, final OnDZYActionSheetListener callback) {

        //empty check
        if (arrays == null || arrays.length == 0) {
            throw new IllegalArgumentException("arrays can not be null or empty!");
        }
        if (callback == null) {
            throw new UnsupportedOperationException("callback can not be null!");
        }

        // 加载popupWindow布局

        View contentView = LayoutInflater.from(mContext).inflate(R.layout.action_sheet_popupwindow, null);

        mPopupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);

        // 设置点击事件
        contentView.findViewById(R.id.id_rootView).setOnClickListener(dismissClickListener);
        contentView.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDismiss();
                callback.onCancel();
            }
        });

        // 设置动画
        contentView.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.anim_alpha));
        mPopupView = (LinearLayout) contentView.findViewById(R.id.ll_popup);

        mPopupView.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim
                .anim_translate_popup_in));

        // 设置数据适配器
        class InnerAdapter extends BaseAdapter {
            @Override
            public int getCount() {
                return arrays.length;
            }

            @Override
            public Object getItem(int position) {
                return arrays[position];
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                ViewHolder holder;
                if (convertView == null) {
                    holder = new ViewHolder();
                    convertView = View.inflate(mContext, R.layout.item_list_popupwindow, null);
                    holder.btn_item = (Button) convertView.findViewById(R.id.btn_item);
                    convertView.setTag(holder);

                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                holder.btn_item.setText(arrays[position]);
                holder.btn_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String itemStr = arrays[position];
                        onDismiss();
                        callback.onClick(itemStr, position);

                    }
                });
                return convertView;
            }

            class ViewHolder {
                Button btn_item;
            }
        }
        mListView = (ListView) contentView.findViewById(R.id.id_listview);
        mListView.setAdapter(new InnerAdapter());
        // 显示popupWindow
        mPopupWindow.showAtLocation(targetView, Gravity.BOTTOM, 0, 0);
    }

    View.OnClickListener dismissClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onDismiss();
        }
    };

    private void onDismiss() {
        mPopupView.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.anim_translate_popup_out));
        Animation animation = mPopupView.getAnimation();
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public interface OnDZYActionSheetListener {
        void onClick(String text, int position);

        void onCancel();
    }
}
