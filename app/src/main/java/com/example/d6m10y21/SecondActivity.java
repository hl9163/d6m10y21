package com.example.d6m10y21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import static com.example.d6m10y21.R.layout.support_simple_spinner_dropdown_item;

public class SecondActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    static String []st1= new String[20];
    ListView lv;
    TextView indexTv, tv2;
    double move,sum,firstNum;
    boolean mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        lv = (ListView) findViewById(R.id.list);
        indexTv = (TextView) findViewById(R.id.textViewIndex);
        tv2 = (TextView) findViewById(R.id.textView2);



        Intent gi = getIntent();
        mode = gi.getBooleanExtra("mode",true);
        move = gi.getDoubleExtra("sirMove",0);
        firstNum = gi.getDoubleExtra("firstNum",0);
        tv2.setText("X1="+firstNum+", d="+move);

        if (mode == true){
            InvoiceSeries(firstNum,move,20);
        }else{
            GP(firstNum,move,20);
        }

        lv.setOnItemClickListener(this);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


        ArrayAdapter<String> adp = new ArrayAdapter<>(this, support_simple_spinner_dropdown_item,st1);
        lv.setAdapter(adp);




    }
    public static void InvoiceSeries(double first,double d,double n){
        if (n == 1){
            st1[0] = String.valueOf(first);
            return;
        }
        double place = first+(n-1)*d;
        st1[(int) (st1.length-1-(st1.length-n))] = String.valueOf(place);
        InvoiceSeries(first, d, n - 1);

    }
    public static void GP(double first, double d, double n){
        if (n == 1){
            st1[0] = String.valueOf(first);
            return;
        }
        double place = first*Math.pow(d,n-1);
        st1[(int) (st1.length-1-(st1.length-n))] = String.valueOf(place);
        GP(first, d, n - 1);
    }
    static double sumOfGP(double a, double r, int n) {
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum = sum + a;
            a = a * r;
        }


        return sum;
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mode = true){
            String place = st1[(int) id];
            double num = Double.valueOf(place);
            sum = ((id + 1) * (firstNum + num)) / 2;
        }else{
                sum =sumOfGP(firstNum,move,position+1);

        }
        Log.w("SecondActivity",String.valueOf(sum));
        String massage = "index: "+(id+1)+" sum: "+sum;
        indexTv.setText(massage);


    }

    public void ret(View view) {
        finish();
    }
}