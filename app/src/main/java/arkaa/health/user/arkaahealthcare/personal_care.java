package arkaa.health.user.arkaahealthcare;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class personal_care extends AppCompatActivity {

    private int alliedService ;
    private TextView textView;
    private ImageView imageView;


    private Calendar myCalendar;
    private DatePickerDialog.OnDateSetListener date;
    private EditText edittext;
    private EditText edittext1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_care);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        textView = findViewById(R.id.personal_care_text);
        imageView = findViewById(R.id.personal_care_image);



        alliedService = getIntent().getIntExtra("type",0);
        if(alliedService == 2){
            textView.setText(getResources().getString(R.string.icu));
//            imageView.setBackgroundResource(R.drawable.icu);
            imageView.setImageResource(R.drawable.icu);


        }
        else if(alliedService == 3){
            textView.setText(R.string.mobility);
//            imageView.setBackgroundResource(R.drawable.nutrition);
            imageView.setImageResource(R.drawable.nutrition);


        }
        else if (alliedService ==4){
            textView.setText(getResources().getString(R.string.mobility));
//            imageView.setBackgroundResource(R.drawable.wheelchair);
            imageView.setImageResource(R.drawable.wheelchair);

        }


        //edittext

        //dropdown
        String[] languages1 = { "9.00-9.30 am","10.00-11.00 am","12.30-01.30 pm","05.00-5.30 pm","07.30-08.30 pm"};

        //Create Array Adapter
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(personal_care.this,android.R.layout.select_dialog_singlechoice, languages1);
        //Find TextView control
        final AutoCompleteTextView acTextView1 = (AutoCompleteTextView) findViewById(R.id.time_slot);
        //Set the number of characters the user must type before the drop down list is shown
        acTextView1.setThreshold(0);
        //Set the adapter
        acTextView1.setAdapter(adapter1);

        acTextView1.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    acTextView1.showDropDown();

            }
        });

        acTextView1.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                acTextView1.showDropDown();
                return false;
            }
        });







        //dropdown

        myCalendar = Calendar.getInstance();

        edittext= findViewById(R.id.select_date);

        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };


        edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(personal_care.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        //calender2

        myCalendar = Calendar.getInstance();

        edittext1= findViewById(R.id.date_of_birth);

        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };


        edittext1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(personal_care.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



        //calender2


        //edittext







    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edittext1.setText(sdf.format(myCalendar.getTime()));
    }







}
