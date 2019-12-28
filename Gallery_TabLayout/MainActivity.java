package com.example.tabtwo;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);
            }
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initLayout();
//        init();
        // 탭 관련 코드
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
//        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.tab_2));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById((R.id.pager));
        TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //set tabselectedlistener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}

//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) { //이게 뭘까?
//
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        int id = item.getItemId();
//
//        switch (id) {
//            case R.id.action_done:
//                selectDone();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//
//    /**
//     * 레이아웃 초기화
//     */
//    private void initLayout() {
//
//        recyclerGallery = (RecyclerView) findViewById(R.id.recyclerGallery);
//    }
//
//
//    /**
//     * 데이터 초기화
//     */
//    private void init() {
//
//        //갤러리 리사이클러뷰 초기화
//        initRecyclerGallery();
//    }
//
//
//    /**
//     * 갤러리 아미지 데이터 초기화
//     */
//    private List<PhotoVO> initGalleryPathList() {
//
//        mGalleryManager = new GalleryManager(getApplicationContext());
//        //return mGalleryManager.getDatePhotoPathList(2015, 9, 19);
//        return mGalleryManager.getAllPhotoPathList();
//    }
//
//
//    /**
//     * 확인 버튼 선택 시
//     */
//    private void selectDone() {
//
//        List<PhotoVO> selectedPhotoList = galleryAdapter.getSelectedPhotoList();
//        for (int i = 0; i < selectedPhotoList.size(); i++) {
//            Log.i("", ">>> selectedPhotoList   :  " + selectedPhotoList.get(i).getImgPath());
//        }
//    }
//
//
//    /**
//     * 갤러리 리사이클러뷰 초기화
//     */
//    private void initRecyclerGallery() {
//
//        galleryAdapter = new GalleryAdapter(MainActivity.this, initGalleryPathList(), R.layout.item_photo);
//        galleryAdapter.setOnItemClickListener(mOnItemClickListener);
//        recyclerGallery.setAdapter(galleryAdapter);
//        recyclerGallery.setLayoutManager(new GridLayoutManager(this, 4));
//        recyclerGallery.setItemAnimator(new DefaultItemAnimator());
////        recyclerGallery.addItemDecoration(new GridDividerDecoration(getResources(), R.drawable.divider_recycler_gallery));
//    }
//
//
//    /**
//     * 리사이클러뷰 아이템 선택시 호출 되는 리스너
//     */
//    private OnItemClickListener mOnItemClickListener = new OnItemClickListener() {
//
//        @Override
//        public void OnItemClick(GalleryAdapter.PhotoViewHolder photoViewHolder, int position) {
//
//            PhotoVO photoVO = galleryAdapter.getmPhotoList().get(position);
//
//            if(photoVO.isSelected()){
//                photoVO.setSelected(false);
//            }else{
//                photoVO.setSelected(true);
//            }
//
//            galleryAdapter.getmPhotoList().set(position,photoVO);
//            galleryAdapter.notifyDataSetChanged();
//
//        }
//    };
//
//}
