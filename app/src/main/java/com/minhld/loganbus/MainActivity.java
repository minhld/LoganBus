package com.minhld.loganbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.Position;

public class MainActivity extends AppCompatActivity {
    private static final String STATE_MENUDRAWER = "menuDrawer";
    private MenuDrawer mMenuDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    void setupSlideMenu(int activityId) {
        // setup the menu drawer
        mMenuDrawer = MenuDrawer.attach(this, MenuDrawer.Type.BEHIND, Position.LEFT, MenuDrawer.MENU_DRAG_WINDOW);
        mMenuDrawer.setContentView(activityId);
        mMenuDrawer.setMenuView(R.layout.layout_leftmenu);
    }
}
