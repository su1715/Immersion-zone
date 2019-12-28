package com.example.test1;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TabHost;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class PhoneBook{
    private String id;
    private String name;
    private String number;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    PhoneBook(String id, String name, String number){
        this.id=id;
        this.name=name;
        this.number=number;
    }
    PhoneBook(){

    }

}

class Loader{
    public static ArrayList<PhoneBook> getData(Context context){
        ArrayList<PhoneBook> datas=new ArrayList<>();
        ContentResolver resolver=context.getContentResolver();
        Uri phoneUri= ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String proj[]={ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER};
        String sortOrder="case"+
                " when substr(" + ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+", 1,1) BETWEEN 'ㄱ' AND '힣' then 1 "+
                " when substr(" + ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+", 1,1) BETWEEN 'A' AND 'Z' then 2 "+
                " when substr(" + ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+", 1,1) BETWEEN 'a' AND 'z' then 3 "+
                " else 4 end, " + ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" COLLATE LOCALIZED ASC";
        Cursor cursor=resolver.query(phoneUri,proj,null,null,sortOrder);
        if(cursor!=null){
            while(cursor.moveToNext()){
                int index=cursor.getColumnIndex(proj[0]);
                String id=cursor.getString(index);

                index=cursor.getColumnIndex(proj[1]);
                String name=cursor.getString(index);

                index=cursor.getColumnIndex(proj[2]);
                String number=cursor.getString(index);
                String tel=number.replace('-',' ');

                PhoneBook book=new PhoneBook();
                book.setId(id);
                book.setName(name);
                book.setNumber(tel);

                datas.add(book);

            }
        }
        cursor.close();
        return datas;
    }
}

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_CONTACTS)) {

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        1);
            }
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test2);
        TabHost tabHost1 = (TabHost) findViewById(R.id.tabHost1) ;
        tabHost1.setup() ;

        // 첫 번째 Tab. (탭 표시 텍스트:"TAB 1"), (페이지 뷰:"content1")
        TabHost.TabSpec ts1 = tabHost1.newTabSpec("Tab Spec 1") ;
        ts1.setContent(R.id.content1) ;



        ArrayList<PhoneBook> phoneBooks=new ArrayList<>();
        phoneBooks=Loader.getData(this);


        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        RecyclerView recyclerView = findViewById(R.id.recycler1) ;
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), 1));
        recyclerView.setLayoutManager(new LinearLayoutManager(this)) ;

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        SimpleTextAdapter adapter = new SimpleTextAdapter(phoneBooks) ;
        recyclerView.setAdapter(adapter) ;

        ts1.setIndicator("PhoneBook") ;
        tabHost1.addTab(ts1)  ;

        // 두 번째 Tab. (탭 표시 텍스트:"TAB 2"), (페이지 뷰:"content2")
        TabHost.TabSpec ts2 = tabHost1.newTabSpec("Tab Spec 2") ;
        ts2.setContent(R.id.content2) ;
        ts2.setIndicator("Gallery") ;
        tabHost1.addTab(ts2) ;

        // 세 번째 Tab. (탭 표시 텍스트:"TAB 3"), (페이지 뷰:"content3")
        TabHost.TabSpec ts3 = tabHost1.newTabSpec("Tab Spec 3") ;
        ts3.setContent(R.id.content3) ;
        ts3.setIndicator("TAB 3") ;
        tabHost1.addTab(ts3) ;


    }
}

//class PhoneBook {
//    private String id;
//    private String name;
//    private String number;
//
//    PhoneBook(String id, String name,String number){
//        this.id=id;
//        this.name=name;
//        this.number=number;
//    }
//
//    public String getNumber() {
//        return number;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setNumber(String number) {
//        this.number = number;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//}
/*
    public List<PhoneBook> getContacts() {
        List<PhoneBook> datas = new ArrayList<>();
        ContentResolver resolver = context.getContentResolver();
        Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = {ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER};

        Cursor cursor = resolver.query(phoneUri, projection, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int idIndex = cursor.getColumnIndex(projection[0]);
                int nameIndex = cursor.getColumnIndex(projection[1]);
                int numberIndex = cursor.getColumnIndex(projection[2]);
                String id = cursor.getString(idIndex);
                String name = cursor.getString(nameIndex);
                String number = cursor.getString(numberIndex);

                PhoneBook phoneBook = new PhoneBook();
                phoneBook.setId(id);
                phoneBook.setName(name);
                phoneBook.setNumber(number);

                datas.add(phoneBook);
            }
        }
        cursor.close();
        return datas;
    }
}
*/
//        PhoneBook phone1=new PhoneBook("0","박수정","010-7183-8939");
//        PhoneBook phone2=new PhoneBook("1","백송이","010-1111-1111");
//        PhoneBook phone3=new PhoneBook("2","천송이","010-2222-2222");
//        PhoneBook phone4=new PhoneBook("3","만송이","010-3333-3333");
//        PhoneBook phone5=new PhoneBook("0","박수정","010-7183-8939");
//        PhoneBook phone6=new PhoneBook("1","백송이","010-1111-1111");
//        PhoneBook phone7=new PhoneBook("2","천송이","010-2222-2222");
//        PhoneBook phone8=new PhoneBook("3","만송이","010-3333-3333");
//        PhoneBook phone9=new PhoneBook("0","박수정","010-7183-8939");
//        PhoneBook phone10=new PhoneBook("1","백송이","010-1111-1111");
//        PhoneBook phone11=new PhoneBook("2","천송이","010-2222-2222");
//        PhoneBook phone12=new PhoneBook("3","만송이","010-3333-3333");
//
//        phoneBooks.add(phone1);
//        phoneBooks.add(phone2);
//        phoneBooks.add(phone3);
//        phoneBooks.add(phone4);
//        phoneBooks.add(phone5);
//        phoneBooks.add(phone6);
//        phoneBooks.add(phone7);
//        phoneBooks.add(phone8);
//        phoneBooks.add(phone9);
//        phoneBooks.add(phone10);
//        phoneBooks.add(phone11);
//        phoneBooks.add(phone12);
