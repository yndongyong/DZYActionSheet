package org.yndongyong.widget.actionsheet;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

/**
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

        // 加载PopupWindow布局
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.action_sheet_popupwindow, null);

        // 创建PopupWindow对象
        mPopupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);

        // 设置点击事件
        contentView.findViewById(R.id.id_rootView).setOnClickListener(dismissClickListener);
        contentView.findViewById(R.id.btn_cancel).setOnClickListener(dismissClickListener);

        // 设置动画
        contentView.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.anim_alpha));
        mPopupView = (LinearLayout) contentView.findViewById(R.id.ll_popup);
        mPopupView.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.anim_translate_popup_in));

        // 设置数据适配器
        class MyAdapter extends BaseAdapter {
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
                        callback.onClick(itemStr, position);
                        onDismiss();
                    }
                });
                return convertView;
            }

            class ViewHolder {
                Button btn_item;
            }
        }
        mListView = (ListView) contentView.findViewById(R.id.id_listview);
        mListView.setAdapter(new MyAdapter());
        
//        show PopupWindow
        mPopupWindow.showAtLocation(targetView, Gravity.BOTTOM, 0, 0);
    }

    View.OnClickListener dismissClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onDismiss();
        }
    };

    protected void onDismiss() {
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
        }
    }

    public interface OnDZYActionSheetListener {
        void onClick(String text, int position);
    }
}
