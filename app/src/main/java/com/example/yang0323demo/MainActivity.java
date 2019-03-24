package com.example.yang0323demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.anye.greendao.gen.MyUserDao;
import com.example.yang0323demo.adapter.HomeAdapter;
import com.example.yang0323demo.adapter.TwoAdapter;
import com.example.yang0323demo.bean.BannerBean;
import com.example.yang0323demo.bean.HomeBean;
import com.example.yang0323demo.mvp.presenter.Presenter;
import com.example.yang0323demo.mvp.view.IView;
import com.example.yang0323demo.network.GreenDaoManager;
import com.example.yang0323demo.network.NetWorkUtils;
import com.example.yang0323demo.user.MyUser;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IView {

    @BindView(R.id.flybanner)
    FlyBanner flybanner;
    @BindView(R.id.myrecy)
    RecyclerView myrecy;
    private Presenter presenter;
    private List<HomeBean.ResultBean> homelist;
    private MyUserDao myUserDao;
    private boolean netWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        myUserDao = GreenDaoManager.getInstance().getDaoSession().getMyUserDao();
        presenter = new Presenter(this);
        presenter.getBanner();
        presenter.getHome();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        myrecy.setLayoutManager(gridLayoutManager);

        netWork = NetWorkUtils.getNetWork(this);

        if (netWork) {
            presenter.getHome();
        }else {
            //走数据库缓存
            List<MyUser> myUsers = myUserDao.loadAll();
            if (myUsers.size() == 0) {
                Toast.makeText(this, "无数据库缓存", Toast.LENGTH_SHORT).show();
            }else {
                //获取缓存数据的列表适配器
                Toast.makeText(this, "数据库缓存成功", Toast.LENGTH_SHORT).show();
                TwoAdapter twoAdapter = new TwoAdapter(MainActivity.this, myUsers);
                myrecy.setAdapter(twoAdapter);
                twoAdapter.setOnItemClickListener(new TwoAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(String i) {
                        Toast.makeText(MainActivity.this, i, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

    @Override
    public void getPreBanner(BannerBean bannerBean) {
        List<BannerBean.ResultBean> list = bannerBean.getResult();
        ArrayList<String> imagelist = new ArrayList<>();
        for (int j = 0; j < list.size(); j++) {
            imagelist.add(list.get(j).getImageUrl());
        }
        flybanner.setImagesUrl(imagelist);
    }

    @Override
    public void getPreHome(HomeBean homeBean) {
        homelist = homeBean.getResult();
        //做缓存处理
        for (int y = 0; y<homelist.size();y++) {
            if (homelist != null) {
                MyUser myUser = new MyUser();
                myUser.setName(homelist.get(y).getName());
                myUser.setImageUrl(homelist.get(y).getImageUrl());
                myUserDao.insert(myUser);//添加一个
            }
        }
        HomeAdapter homeAdapter = new HomeAdapter(MainActivity.this, homelist);
        myrecy.setAdapter(homeAdapter);

        homeAdapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String i) {
                Toast.makeText(MainActivity.this, i, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
