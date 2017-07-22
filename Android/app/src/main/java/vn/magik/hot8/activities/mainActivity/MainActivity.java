package vn.magik.hot8.activities.mainActivity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.magik.hot8.R;
import vn.magik.hot8.adapters.ViewPagerAdapter;
import vn.magik.hot8.constants.Globals;
import vn.magik.hot8.fragments.questFragment.QuestFragment;
import vn.magik.hot8.fragments.settingFragment.SettingFragment;
import vn.magik.hot8.fragments.profileFragment.ProfileFragment;
import vn.magik.hot8.otherHandle.HandlePermission;
import vn.magik.hot8.otherHandle.InAppProducts;

public class MainActivity extends AppCompatActivity implements HandlePermission.CallbackRequestPermision {
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    private MenuItem prevMenuItem;
    private MainPresenter mainPresenter;
    private HandlePermission handlePermission;
    public InAppProducts inAppProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        inAppProducts = new InAppProducts(MainActivity.this);
        handlePermission = new HandlePermission(this, this);
        mainPresenter = new MainPresenter(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        viewPager.addOnPageChangeListener(onPageChangeListener);
        setupViewPager(viewPager);
        viewPager.setCurrentItem(1);
    }


    BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.action_summary:
                            viewPager.setCurrentItem(0);
                            break;
                        case R.id.action_quest:
                            viewPager.setCurrentItem(1);
                            break;
                        case R.id.action_setting:
                            viewPager.setCurrentItem(2);
                            break;
                    }
                    return false;
                }
            };

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (prevMenuItem != null) {
                prevMenuItem.setChecked(false);
            } else {
                bottomNavigationView.getMenu().getItem(0).setChecked(false);
            }
            bottomNavigationView.getMenu().getItem(position).setChecked(true);
            prevMenuItem = bottomNavigationView.getMenu().getItem(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void setupViewPager(ViewPager viewPager) {
        QuestFragment questFragment = new QuestFragment();
        questFragment.setOnClickBuyVip(onClickBuyVip);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new ProfileFragment());
        fragments.add(questFragment);
        fragments.add(new SettingFragment());
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mainPresenter.saveConfig();
    }

    QuestFragment.OnClickBuyVip onClickBuyVip = new QuestFragment.OnClickBuyVip() {
        @Override
        public void onBuyVip(String packageVip) {
            inAppProducts.handleBuyItemFromGoogle(packageVip);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == InAppProducts.REQUEST_CODE) {
            inAppProducts.handleOnActivityResult(requestCode, resultCode, data);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        handlePermission.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    public void onRequestPermissionSuccess() {
        Globals.getIns().getConfig().setPermissionWriteFile(true);
    }

    @Override
    public void onRequestPermissionFail() {
        Globals.getIns().getConfig().setPermissionWriteFile(false);
    }
}
