package com.example.d6m10y21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    Switch sw;
    EditText moves,firstnum;
    boolean mode = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sw = (Switch) findViewById(R.id.switch2);
        moves = (EditText) findViewById(R.id.editTextNumberDecimal2);
        firstnum = (EditText) findViewById(R.id.editTextNumberDecimal);

        moves.setText("");
        firstnum.setText("");


    }

    public void go(View view) {
        if(sw.isChecked()){
            moves.setHint("Enter the difference");
            mode = true;
        }
        else{
            moves.setHint("Enter the multiples");
            mode = false;
        }
    }

    public void move(View view) {
        String st = moves.getText().toString();
        double sirMove = Double.parseDouble(st);

        String st2 = firstnum.getText().toString();
        double firstNum = Double.parseDouble(st2);

        Intent si = new Intent(this,SecondActivity.class);
        si.putExtra("mode",mode);
        si.putExtra("sirMove",sirMove);
        si.putExtra("firstNum",firstNum);

        startActivity(si);



    }
}