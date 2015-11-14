package com.minhld.loganbus;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.minhld.supports.GTFSLoader;
import com.minhld.supports.Route;

import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.Position;

public class MainActivity extends AppCompatActivity {
    private static final String STATE_MENUDRAWER = "menuDrawer";

    private MenuDrawer mMenuDrawer;
    private LinearLayout routeList;
    private SlideMenuClickListener menuClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // add the side menu
        setupSlideMenu(R.layout.activity_main);

        // add route items into the left menu
        buildRouteList();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        try {
            if (mMenuDrawer != null){
                mMenuDrawer.restoreState(savedInstanceState.getParcelable(STATE_MENUDRAWER));
            }
        } catch (Exception e) { }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                mMenuDrawer.toggleMenu();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    /**
     * setup the slide left menu
     *
     * @param activityId
     */
    void setupSlideMenu(int activityId) {
        // setup the menu drawer
        mMenuDrawer = MenuDrawer.attach(this, MenuDrawer.Type.BEHIND, Position.LEFT, MenuDrawer.MENU_DRAG_WINDOW);
        mMenuDrawer.setContentView(activityId);
        mMenuDrawer.setMenuView(R.layout.layout_leftmenu);

        menuClickListener = new SlideMenuClickListener();
    }

    void buildRouteList(){
        routeList = (LinearLayout)findViewById(R.id.routeMenuList);

        TextView subItemText;
        int childItemHeight = (int)getResources().getDimension(R.dimen.menu_child_item_height);
        float textSize = 15;
        int leftMargin12 = (int)getResources().getDimension(R.dimen.menu_category_left_margin_12);
        int height = 0;

        // ------ adding all the sub routes ------
        for (Route routeItem : GTFSLoader.routes){
			subItemText = new TextView(this);
            subItemText.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, childItemHeight));
            subItemText.setBackgroundResource(R.drawable.menu_main_item_bg);
            subItemText.setPadding(leftMargin12, 0, 0, 0);
            subItemText.setGravity(Gravity.CENTER_VERTICAL);
            subItemText.setText(routeItem.longName);
            subItemText.setTextSize(textSize);
            subItemText.setTextColor(Color.WHITE);
            subItemText.setOnClickListener(new RouteClickListener(routeItem));
            height += childItemHeight;
            routeList.addView(subItemText);
        }
        routeList.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height));
    }

    private class SlideMenuClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.settingButton: {

                    break;
                }
                case R.id.aboutButton:{

                    break;
                }
                default:{

                }
            }
            // close the menu
            mMenuDrawer.toggleMenu();
        }

    }


    /**
     * This class handles tap events in the whole screen
     */
    private class RouteClickListener implements View.OnClickListener {
        private Route route;

        public RouteClickListener(Route route) {
            this.route = route;
        }

        @Override
        public void onClick(View v) {

            // close the menu
            mMenuDrawer.toggleMenu();
        }

    }
}
