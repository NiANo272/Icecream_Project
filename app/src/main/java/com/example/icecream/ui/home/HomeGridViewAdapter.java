package com.example.icecream.ui.home;


import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.io.File;
import java.sql.Array;
import java.util.ArrayList;

import com.example.icecream.R;
import com.example.icecream.ui.home.HomeItemInfo;

import org.w3c.dom.Text;

public class HomeGridViewAdapter extends BaseAdapter {
    String TAG = HomeFragment.class.getSimpleName();
    ArrayList<HomeItemInfo> item = new ArrayList<HomeItemInfo>();

    @Override
    public int getCount() { return item.size(); }

    @Override
    public Object getItem(int i) { return item.get(i);  }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void addItem(HomeItemInfo add_item) { item.add(add_item); }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup){
        final Context context = viewGroup.getContext();
        final HomeItemInfo myitem = item.get(i);

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_icon, viewGroup, false);

            TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
            TextView tv_category = (TextView) view.findViewById(R.id.tv_category);
            ImageView iv_icon = (ImageView) view.findViewById(R.id.iv_icon);

            tv_name.setText(myitem.getName());
            tv_category.setText(myitem.getCategory());
            iv_icon.setImageResource(myitem.getResId());

            Log.d(TAG, "getView() - [ "+i+" ] "+myitem.getName());
        }
        else{
            View view_1 = new View(context);
            view_1 = (View) view;
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, myitem.getBuyYear() + "\n" + myitem.getBuyMonth() + "\n" + myitem.getBuyDay(), Toast.LENGTH_LONG).show();

                //팝업 생성
               // HomeItemDialog homeItemDialog = HomeItemDialog.getInstance();
                //homeItemDialog.show(homeItemDialog.getChildFragmentManager(), HomeItemDialog.TAG_EVENT_DIALOG);
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                PopupMenu pop = new PopupMenu(view.getContext(), view);
                pop.getMenuInflater().inflate(R.menu.gridview_popup,pop.getMenu());

                pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        File file = new File("/data/data/com.example.icecream/files/"+myitem.getName()+".txt");
                        file.delete();

                        item.remove(i);
                        HomeGridViewAdapter.this.notifyDataSetChanged();

                        return false;
                    }
                });
                pop.show();

                return true;
            }
        });
//        View finalView = view;
//        view.setOnLongClickListener(new View.OnLongClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.N)
//            @Override
//
//            public boolean onLongClick(View v) {
//                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
//
//                ClipData dragData = new ClipData(
//                        (CharSequence) v.getTag(),
//                        new String[] { ClipDescription.MIMETYPE_TEXT_PLAIN },
//                        item);
//
//                View.DragShadowBuilder myShadow = new MyDragShadowBuilder(finalView);
//
//                v.startDragAndDrop(dragData,  // The data to be dragged
//                        myShadow,  // The drag shadow builder
//                        null,      // No need to use local data
//                        0          // Flags (not currently used, set to 0)
//                );
//
//
//
//                return true;
//            }
//        });

        return view;
    }

    private static class MyDragShadowBuilder extends View.DragShadowBuilder {

        private static Drawable shadow;

        public MyDragShadowBuilder(View v) {

            // Stores the View parameter.
            super(v);

            // Creates a draggable image that fills the Canvas provided by the system.
            shadow = new ColorDrawable(Color.LTGRAY);
        }
        @Override
        public void onProvideShadowMetrics (Point size, Point touch) {

            // Defines local variables
            int width, height;

            // Set the width of the shadow to half the width of the original View.
            width = getView().getWidth() / 2;

            // Set the height of the shadow to half the height of the original View.
            height = getView().getHeight() / 2;

            // The drag shadow is a ColorDrawable. This sets its dimensions to be the
            // same as the Canvas that the system provides. As a result, the drag shadow
            // fills the Canvas.
            shadow.setBounds(0, 0, width, height);

            // Set the size parameter's width and height values. These get back to the
            // system through the size parameter.
            size.set(width, height);

            // Set the touch point's position to be in the middle of the drag shadow.
            touch.set(width / 2, height / 2);
        }

        // Defines a callback that draws the drag shadow in a Canvas that the system
        // constructs from the dimensions passed to onProvideShadowMetrics().
        @Override
        public void onDrawShadow(Canvas canvas) {

            // Draw the ColorDrawable on the Canvas passed in from the system.
            shadow.draw(canvas);
        }
    }
    }
