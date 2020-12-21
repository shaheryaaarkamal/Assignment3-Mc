package com.shaheryaar.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;


public class MainActivity2 extends AppCompatActivity {
    TextView timerText,TimerCounter,QuestionCouter,QuestionDisplay;
    Button Op1,Op2,Op3,Op4,next;
    CountDownTimer tm;
    View yet;
    boolean isClicked=false;
    String clickedButtonText;
    int counter,questionIndex;
    MainActivity3 obj;
    int correctCount=0,wrongCounter;
    ArrayList<MainActivity3> myList= new ArrayList<MainActivity3>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        timerText=findViewById(R.id.TimeLeftTextView);
        TimerCounter=findViewById(R.id.timerView);
        QuestionCouter=findViewById(R.id.questionCounterView);
        QuestionDisplay=findViewById(R.id.questionView);
        Op1=findViewById(R.id.option1);
        Op2=findViewById(R.id.option2);
        Op3=findViewById(R.id.option3);
        Op4=findViewById(R.id.option4);
        next=findViewById(R.id.next);

        myList.add(new MainActivity3("Who is the Founder of Pakistan?","Quaid-e-Azam","Salahudin","AllamaIqbal","MaulanaShaukatAli","Quaid-e-Azam"));
        myList.add(new MainActivity3("Entomology is the science that studies?","Behavior of human beings","The origin and history of technical and scientific terms","Insects","The formation of rocks","Insects"));
        myList.add(new MainActivity3("Windows Boot key is?","F1","F10","F2","F12","F10"));
        myList.add(new MainActivity3("Who give the idea of Pakistan?","Quaid-e-Azam","MolanaShokatAli","Choudhry Rehmat Ali","AllamaIqbal","Choudhry Rehmat Ali"));
        myList.add(new MainActivity3("2+2=?","5","4","6","3","4"));
        myList.add(new MainActivity3("Potato was introduced to Europe by","Spanish","Dutch","Portuguese","Germans","Spanish"));
        myList.add(new MainActivity3("Poverty Line means?","The line of demarcation between the rich and poor","The stage at which there is a leveling down between the rich and the poor","The lowest level in the ladder of economic prosperity","The minimum level of per capita consumption expenditure","The minimum level of per capita consumption expenditure"));
        myList.add(new MainActivity3("Private investment is otherwise called as","induced investment","foreign institutional investment","oreign direct investment","autonomous investment","induced investment"));
        myList.add(new MainActivity3("Vowels consists of ______ alphabets ?","4","3","5","7","5"));
        myList.add(new MainActivity3("When is the independence day of pakistan?","6-September","15-August","None","14-August","14-August"));
        Collections.shuffle(myList);
        counter=0;
        TimerCounter.setText("" +12);
        setData(counter);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isClicked==true)
                {
                    Log.i("bool", "onClick: "+isClicked);
                    ClickNext(view,clickedButtonText,questionIndex);
                }
                else
                {
                    Log.i("bool", "else: "+isClicked);
                    ClickNext(yet,"Nothing",0);
                }


            }
        });

        /////////////Timer/////////////
        tm= new CountDownTimer(10*1000,1000)
        {
            @Override
            public void onTick(long l) {
                TimerCounter.setText(String.format("%d", l / 1000));
            }

            @Override
            public void onFinish() {
                Toast.makeText(MainActivity2.this, "Time Over", Toast.LENGTH_SHORT).show();

                ClickNext(yet,"Nothing Selected",0);
            }
        };
    }
    ///////Display_Data///////
    public  void setData(final int val)
    {
        final MainActivity3 obj=myList.get(val);
        QuestionCouter.setText((val+1)+"/"+myList.size());
        if (tm!=null)
        {
            tm.start();
        }
        QuestionDisplay.setText("#"+(val+1)+" "+obj.getQuestion());
        Op1.setText(obj.getOpt1());
        Op2.setText(obj.getOpt2());
        Op3.setText(obj.getOpt3());
        Op4.setText(obj.getOpt4());


        Op1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isClicked=true;
                clickedButtonText = Op1.getText().toString();
                questionIndex = val;

            }
        });

        Op2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isClicked=true;
                clickedButtonText=Op2.getText().toString();
                questionIndex=val;

            }
        });

        Op3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isClicked=true;
                clickedButtonText=Op3.getText().toString();
                questionIndex=val;
            }
        });

        Op4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isClicked=true;
                clickedButtonText=Op4.getText().toString();
                questionIndex=val;
            }
        });

    }
    ////////New Intent/////
    public void newActivity(View view,int val,int total,int wrong)
    {
        Intent intent = new Intent(this,ResultActivity2.class);
        String result="Your Result is\n  "+"Correct: "+val+"/"+total+"\n"+"Wrong: "+wrong+"/"+total;
        intent.putExtra("Result",result);
        startActivity(intent);

    }
    //////Next Click Activity//////
    public void ClickNext(View view,String clickedButtonText,int questionIndex)
    {
        final MainActivity3 obj1=myList.get(questionIndex);

        if(counter<(myList.size()-1))
        {
            if(clickedButtonText.equals(obj1.getAnswer())) {
                correctCount++;
            }
            else {
                wrongCounter++;
            }

            tm.cancel();
            isClicked=false;
            counter++;
            setData(counter);
            if(counter==(myList.size()-1)){
                next.setText("Finish");
            }
        }

        else
        {

            if(clickedButtonText.equals(obj1.getAnswer()))
            {
                correctCount++;
            }
            else {
                wrongCounter++;
            }
            tm.cancel();

            Toast.makeText(MainActivity2.this, "Questions are completed", Toast.LENGTH_SHORT).show();
            newActivity(view,correctCount,myList.size(),wrongCounter);
        }
    }

}