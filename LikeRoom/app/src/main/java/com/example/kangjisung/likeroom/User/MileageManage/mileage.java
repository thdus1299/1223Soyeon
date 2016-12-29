package com.example.kangjisung.likeroom.User.MileageManage;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.kangjisung.likeroom.R;
import com.example.kangjisung.likeroom.SQLiteDatabaseControl.DatabaseHelper;
import com.example.kangjisung.likeroom.SQLiteDatabaseControl.LocalHostDatabaseManager;

/////////////////////////////////////마일리지 적립 클래스
public class mileage extends Activity {

    Button Plus;  //적립버튼
    Button[] Btn=new Button[12];  //버튼배열
    ImageButton CloBtn; //닫기 버튼
    int[] numBtn={R.id.Num0,R.id.Num1,R.id.Num2,R.id.Num3,R.id.Num4,R.id.Num5,R.id.Num6,R.id.Num7,R.id.Num8,R.id.Num9,R.id.Num10}; //버튼 id 배열
    EditText NumView;
    int i;
    DatabaseHelper databaseHelperTest;
    LocalHostDatabaseManager localHostDatabaseManager;
    String testDatabaseName = "ShopkeeperDatabase.db";
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_mileage);
        Intent mil = getIntent(); ////user클래스에서 눌렀던 사용자의 이름을 가져옴
        final String name = mil.getExtras().getString("name");  //가져온 사용자의 이름을 넣음

        NumView = (EditText) findViewById(R.id.NumView);
/////////////////////버튼만들기
        for (i = 0; i <= 10; i++) {
            Btn[i] = (Button) findViewById(numBtn[i]);
        }
        for (i = 0; i <= 10; i++) {
            final int index;
            index = i;
            Btn[index].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String a;
                    a = NumView.getText().toString()
                            + Btn[index].getText().toString();
                    NumView.setText(a);
                }
            });
        }

        CloBtn = (ImageButton) findViewById(R.id.BtnClose);
        CloBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ////////마일리지 적립 버튼
        Plus = (Button) findViewById(R.id.PlusBtn);
        Plus.setOnClickListener(new View.OnClickListener(){
            int point;
            public void onClick(View v) {
                databaseHelperTest = new DatabaseHelper(getApplicationContext(), testDatabaseName);
                localHostDatabaseManager = new LocalHostDatabaseManager(getApplicationContext(), getApplicationInfo().dataDir + "/databases/", testDatabaseName);

                sqLiteDatabase = localHostDatabaseManager.OpenSQLiteDatabase();
                point = Integer.parseInt(NumView.getText().toString());
                point=(int)(point*0.3);
                sqLiteDatabase.execSQL("insert into `포인트` (`고유회원등록번호`,`포인트`,`포인트갱신날짜`) values ((select `고유회원등록번호` from `회원정보` where `이름`=\""+name+"\",\""+point+"\",(select date('now')));");
                Toast.makeText(getApplicationContext(), name+"에게"+point+"빵이 적립되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

}
