package com.unitconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import static java.lang.Math.round;

public class MainActivity extends AppCompatActivity {
    Spinner main_spin;
    LinearLayout temp_convertor_layout, area_converter_layout,length_converter_layout;
    Button btn_convert_temp,btn_convert_area,btn_length_convert;
    RadioButton tofaranheit,tosquarefeet,toyard,tomile,tometre,tofeet;
    EditText et_temp_txt,et_area_text,et_length_text;
    TextView display_temp,display_area,display_length;
    ViewGroup parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_spin = (Spinner) findViewById(R.id.main_spin);
        temp_convertor_layout = (LinearLayout) findViewById(R.id.temp_convertor);
        area_converter_layout = (LinearLayout) findViewById(R.id.area_converter_layout);
//        temp_convertor_layout2 = (LinearLayout) findViewById(R.id.temp_convertor2);
//        parent = (ViewGroup) temp_convertor_layout.getParent();

        //for temperature converter
        btn_convert_temp = (Button) findViewById(R.id.convert);
        tofaranheit = (RadioButton) findViewById(R.id.tofaranheit);
        et_temp_txt = (EditText) findViewById(R.id.et_temp_text);
        display_temp = (TextView) findViewById(R.id.display_temp);


        //for Area converter
        btn_convert_area = (Button) findViewById(R.id.area_convert);
        et_area_text = (EditText) findViewById(R.id.et_area_text);
        tosquarefeet = (RadioButton) findViewById(R.id.tosquarefeet);
        display_area = (TextView) findViewById(R.id.display_area);
        area_converter_layout = (LinearLayout) findViewById(R.id.area_converter_layout);


        //for Length Converter
        length_converter_layout = (LinearLayout) findViewById(R.id.length_converter_layout);
        et_length_text = (EditText) findViewById(R.id.et_length_text);
        display_length = (TextView) findViewById(R.id.display_length);
        btn_length_convert = (Button) findViewById(R.id.length_convert);
        tofeet = (RadioButton) findViewById(R.id.tofeet);
        toyard = (RadioButton) findViewById(R.id.toyard);
        tometre = (RadioButton) findViewById(R.id.tometre);
        tomile = (RadioButton) findViewById(R.id.tomile);

//        temp_convertor_layout.setVisibility(View.GONE);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.main_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        main_spin.setAdapter(adapter);

        main_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        temp_convertor_layout.setVisibility(View.VISIBLE);
                        area_converter_layout.setVisibility(View.GONE);
                        length_converter_layout.setVisibility(View.GONE);

                        break;
                    case 2:
                        area_converter_layout.setVisibility(View.VISIBLE);
                        temp_convertor_layout.setVisibility(View.GONE);
                        length_converter_layout.setVisibility(View.GONE);
                        break;
//                        int index = parent.indexOfChild(temp_convertor_layout);
//                        parent.removeView(temp_convertor_layout);
//                        LayoutInflater l = getLayoutInflater();
//                        View v = l.inflate(R.layout.area,parent,false);
//                        parent.addView(v,index);
                    case 3:
                        length_converter_layout.setVisibility(View.VISIBLE);
                        temp_convertor_layout.setVisibility(View.GONE);
                        area_converter_layout.setVisibility(View.GONE);
                        break;

                    default:
                        area_converter_layout.setVisibility(View.GONE);
                        temp_convertor_layout.setVisibility(View.GONE);
                        length_converter_layout.setVisibility(View.GONE);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



                //Temperature Conversion
                btn_convert_temp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {

                            Double t = Double.parseDouble(et_temp_txt.getText().toString());
                            if (tofaranheit.isChecked()) {
                                display_temp.setText("Temperature:  " + (((t * 9) / 5) + 32) + "°F");
                            } else {
                                display_temp.setText("Temperature:  " + (((t - 32) * 5) / 9) + "°C");
                            }

                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Please enter temperature ! ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //Area Conversion Button
        btn_convert_area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Double t = Double.parseDouble(et_area_text.getText().toString());
                    if (tosquarefeet.isChecked()) {
                        display_area.setText("Square feet:  " +(t/0.09290304) + "ft2");
                    } else {
                        display_area.setText("Square metre:  " + (t*0.09290304) + "m2");
                    }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Please enter area ! ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Length Conversion Button
        btn_length_convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Double t = Double.parseDouble(et_length_text.getText().toString());
                    if (tofeet.isChecked()) {
                        display_length.setText("Feet:  " +(t/0.3048) + "ft");
                    }
                    else if(tometre.isChecked()){
                        display_length.setText("Metre:  " + (t*0.3048) + "m");
                    }
                    else if(tomile.isChecked()){
                        display_length.setText("Square metre:  " + (t* 0.00056818) + "mi");
                    }
                    else {
                        display_length.setText("Square metre:  " + (t*1760.0) + "yd");
                    }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Please enter length ! ", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
