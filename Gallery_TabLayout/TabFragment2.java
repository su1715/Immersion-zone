package com.example.tabtwo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.tabtwo.GalleryAdapter;
//import com.example.tabtwo.


import java.util.List;
import java.util.zip.Inflater;

public class TabFragment2 extends Fragment {
    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        System.out.println("ff");

    }

    private GalleryManager mGalleryManager;
    private RecyclerView recyclerGallery;
    private GalleryAdapter galleryAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment2, null);
        setHasOptionsMenu(true);
//        initLayout();
        recyclerGallery= view.findViewById(R.id.recyclerGallery);
        init();
        return view;
    }

//


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) { //이게 뭘까?
        inflater.inflate(R.menu.menu_main, menu);
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


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

    /**
     * 레이아웃 초기화
     */
    private void initLayout() {
//        System.out.println("ff");
        recyclerGallery = (RecyclerView) getView().findViewById(R.id.recyclerGallery);
//        System.out.println("ff2");
    }

    //
//    /**
//     * 데이터 초기화
//     */
    private void init() {

        //갤러리 리사이클러뷰 초기화
        initRecyclerGallery();
    }

//

    /**
     * 갤러리 아미지 데이터 초기화
     */
    private List<PhotoVO> initGalleryPathList() {

        mGalleryManager = new GalleryManager(getActivity().getApplicationContext());
        //return mGalleryManager.getDatePhotoPathList(2015, 9, 19);
        return mGalleryManager.getAllPhotoPathList();
    }
//

    /**
     * 확인 버튼 선택 시
     */
//    private void selectDone() {
//
//        List<PhotoVO> selectedPhotoList = galleryAdapter.getSelectedPhotoList();
//        for (int i = 0; i < selectedPhotoList.size(); i++) {
//            Log.i("", ">>> selectedPhotoList   :  " + selectedPhotoList.get(i).getImgPath());
//        }
//    }



    /**
     * 갤러리 리사이클러뷰 초기화
     */
    private void initRecyclerGallery() {

        galleryAdapter = new GalleryAdapter(TabFragment2.this, initGalleryPathList(), R.layout.item_photo);
        galleryAdapter.setOnItemClickListener(mOnItemClickListener);
        recyclerGallery.setAdapter(galleryAdapter);
        recyclerGallery.setLayoutManager(new GridLayoutManager(getContext(), 4));
        recyclerGallery.setItemAnimator(new DefaultItemAnimator());
//        recyclerGallery.addItemDecoration(new GridDividerDecoration(getResources(), R.drawable.divider_recycler_gallery));
    }

//
//
//    /**
//     * 리사이클러뷰 아이템 선택시 호출 되는 리스너
//     */
    private OnItemClickListener mOnItemClickListener = new OnItemClickListener() {

        @Override
        public void OnItemClick(GalleryAdapter.PhotoViewHolder photoViewHolder, int position) { //이 함수에서 짤것은?

            PhotoVO photoVO = galleryAdapter.getmPhotoList().get(position);
//            System.out.println("ff");
            // use photovo.imgpath


////            ImageView imageview= (ImageView) v.findViewById(R.id.fullScreenImageView);
            Intent fullScreenIntent=new Intent(getContext(), FullScreenImageActivity.class);
            fullScreenIntent.putExtra("imgPath", photoVO.getImgPath());
//        Data(PhotoVO.getImgPath());
            photoViewHolder.imgPhoto.getContext().startActivity(fullScreenIntent);
            System.out.println(photoVO.getImgPath());
//        Extra("picture", position);
//            photoViewHolder.imgPhoto.getContext().startActivity(fullScreenIntent);

//            if(photoVO.isSelected()){
//                photoVO.setSelected(false);
//            }else{
//                photoVO.setSelected(true);
//            }
//
//            galleryAdapter.getmPhotoList().set(position,photoVO);
//            galleryAdapter.notifyDataSetChanged();

        }
    };

}
//
//}
