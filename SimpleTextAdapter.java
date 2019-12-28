package com.example.test1;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class SimpleTextAdapter extends RecyclerView.Adapter<SimpleTextAdapter.ViewHolder> {

    private ArrayList<PhoneBook> mData = null ;

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1,textView2 ;

        ViewHolder(View itemView) {
            super(itemView) ;

            // 뷰 객체에 대한 참조. (hold strong reference)
            textView1 = itemView.findViewById(R.id.text1) ;
            textView2 = itemView.findViewById(R.id.text2) ;
//            itemView.setOnClickListener(new View.onClickListener(){
//                @Override
//                public void onClick(View v){
//                    int pos=getAdapterPosition();
//                    if(pos != RecyclerView.NO_POSITION){
//
//                    }
//                }
//            });

        }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    SimpleTextAdapter(ArrayList<PhoneBook> list) {
        mData = list ;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public SimpleTextAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.recyclerview_item, parent, false) ;
        SimpleTextAdapter.ViewHolder vh = new SimpleTextAdapter.ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(SimpleTextAdapter.ViewHolder holder, int position) {
        final PhoneBook phoneBook = mData.get(position);
        holder.textView1.setText(phoneBook.getName());
        holder.textView2.setText(phoneBook.getNumber());

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) holder.itemView.getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        //int deviceWidth = displayMetrics.widthPixels;  // 핸드폰의 가로 해상도를 구함.
        double deviceHeight = displayMetrics.heightPixels;  // 핸드폰의 세로 해상도를 구함.
        deviceHeight = deviceHeight / 8.8;
        //int deviceWidth = (int) (deviceHeight * 1.5);  // 세로의 길이를 가로의 길이의 1.5배로 하고 싶었다.
        holder.itemView.getLayoutParams().height = (int)deviceHeight;  // 아이템 뷰의 세로 길이를 구한 길이로 변경
        holder.itemView.requestLayout(); // 변경 사항 적용

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

            }
        });
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size() ;
    }


}

