package com.ivanovsuper.actionitembadge;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int mNotifCount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setNotifCount(mNotifCount+1);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        showBadge(menu, R.id.badge, R.layout.badge_counter_layout);
        showBadge(menu, R.id.icon_badge, R.layout.badge_counter_layout_circle);

        return super.onCreateOptionsMenu(menu);
    }

    private void showBadge(Menu menu, int itemId, int actionLayoutId) {
        MenuItem item = menu.findItem(itemId);
        MenuItemCompat.setActionView(item, actionLayoutId);
        View layout = MenuItemCompat.getActionView(item);
        TextView notifCount=null;
        if(layout instanceof ViewGroup) {
            notifCount = (TextView) layout.findViewById(R.id.badge_count);
        } else if(layout instanceof TextView) {
            notifCount = (TextView) layout;
        }
        if(notifCount!=null) {
            if(mNotifCount==0)
                notifCount.setVisibility(View.GONE);
            else
                notifCount.setVisibility(View.VISIBLE);
                notifCount.setText(String.valueOf(mNotifCount));
        }
        layout.setOnClickListener(this);
    }

    private void setNotifCount(int count){
        mNotifCount = count;
        supportInvalidateOptionsMenu();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.badge:
            case R.id.icon_badge:
                Snackbar.make(getWindow().getDecorView(), "Count is:"+mNotifCount, Snackbar.LENGTH_SHORT).show();
                return true;
            default:
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.badge_count:
                Snackbar.make(getWindow().getDecorView(), "Count is:"+mNotifCount, Snackbar.LENGTH_SHORT).show();
                break;
        }
    }
}
