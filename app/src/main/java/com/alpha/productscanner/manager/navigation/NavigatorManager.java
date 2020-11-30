package com.alpha.productscanner.manager.navigation;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.alpha.productscanner.R;
import com.alpha.productscanner.ui.detail_product.DetailProductFragment;
import com.alpha.productscanner.ui.home.HomeFragment;
import com.alpha.productscanner.ui.list_products.ListProductFragment;
import com.alpha.productscanner.ui.paypal.PayPalFragment;

public class NavigatorManager implements FragmentManager.OnBackStackChangedListener {

    private final static String LOG_TAG = NavigatorManager.class.getName();
    private Context mContext;
    private String tag;
    private FragmentManager mFragmentManager;
    private Fragment fragment;
    private NavigationListener mNavigationListener;
    private Toolbar mToolbar;
    private ActionBar mActionBar;
    private BottomNavigationView bottomNavigationView;
    Activity activity;

    public static final int F_HOME = 1;
    public static final int F_LIST_PRODUCT = 2;
    public static final int F_DETAIL_PRODUCT = 3;
    public static final int F_PAYPAL = 4;


    public interface NavigationListener {
        void onBackstackChange();
    }

    public void initNavigation(Context context, FragmentManager fragmentManager, NavigationListener navigationListener) {
        this.mContext = context;
        this.mFragmentManager = fragmentManager;
        this.mNavigationListener = navigationListener;
        this.mFragmentManager.addOnBackStackChangedListener(this);
    }

    public void initNavigation(Context context, FragmentManager fragmentManager, Toolbar toolbar, ActionBar supportActionBar, BottomNavigationView bottomNavigationView, NavigationListener navigationListener) {
        this.activity=activity;
        this.mContext = context;
        this.mFragmentManager = fragmentManager;
        this.mToolbar = toolbar;
        this.mActionBar = supportActionBar;
        this.bottomNavigationView = bottomNavigationView;
        this.mNavigationListener = navigationListener;
        this.mFragmentManager.addOnBackStackChangedListener(this);
    }

    public synchronized void navigation(int idNavigation, Bundle bundle) {


        switch (idNavigation) {
            case F_HOME:
                fragment = HomeFragment.newInstance();
                //openAsRoot(fragment, fragment.getTag());
                open(fragment, fragment.getTag());

                break;
            case F_LIST_PRODUCT:
                fragment = ListProductFragment.newInstance(bundle);
                open(fragment, fragment.getTag());
                break;
            case F_DETAIL_PRODUCT:
                fragment = DetailProductFragment.newInstance(bundle);
                open(fragment, fragment.getTag());
                break;
            case F_PAYPAL:


                fragment =  PayPalFragment.newInstance(bundle);
                open(fragment, fragment.getTag());
                break;



            default:
                break;
        }
    }

    public void setToolbarTitle(String title) {
        if (mToolbar != null) {
            mToolbar.setTitle(title);
        }
    }

    public void showActionBarAndBottonNavigation(boolean visible) {
        try {

            if (mActionBar != null && bottomNavigationView != null) {
                if (visible) {
                    mActionBar.show();
                    bottomNavigationView.setVisibility(View.VISIBLE);
                } else {
                    mActionBar.hide();
                    bottomNavigationView.setVisibility(View.GONE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openAsRoot(Fragment fragment, String tag) {
        popAllFragments();
        open(fragment, tag);
    }

    private void open(Fragment fragment, String tag) {

        if (mFragmentManager != null) {
            mFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment, tag).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack(fragment.toString()).commit();
            setToolbarTitle(tag);
        }
        int level = getNavStackEntryCount();

    }

    private void popAllFragments() {
        int backStackCount = getNavStackEntryCount();
        if (backStackCount >= 1) {

            for (int f = 0; f < backStackCount; f++) {
                int backStackId = mFragmentManager.getBackStackEntryAt(f).getId();
                mFragmentManager.popBackStack(backStackId, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        }
    }

    public void navigateBack(Activity activity) {
        int navigateCount = getNavStackEntryCount();

        if (navigateCount == 0) {
            activity.finish();
        } else {
            mFragmentManager.popBackStackImmediate();
        }
    }

    public int getNavStackEntryCount() {
        return (mFragmentManager != null) ? mFragmentManager.getBackStackEntryCount() : 0;
    }

    public boolean isRootFragmentVisible() {
        return mFragmentManager.getBackStackEntryCount() <= 1;
    }

    @Override
    public void onBackStackChanged() {
        if (mNavigationListener != null) {
            mNavigationListener.onBackstackChange();
        }
    }

}
