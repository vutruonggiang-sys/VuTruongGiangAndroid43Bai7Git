package com.example.vutruonggiangadroid43buoi6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.vutruonggiangadroid43buoi6.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ISave {

    String butSave,tvWork,tvFormFile,tvFormDefaults,tvHour,tvDay,tvTag,tvWeek,edTitle,edDescription;
    String butTune,butCancel,butOK;
    SavePresenter savePresenter;
    Calendar calendar;
    int hour,min,day,mon,ye;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);

        calendar=Calendar.getInstance();
        hour=calendar.get(Calendar.HOUR);
        min=calendar.get(Calendar.MINUTE);
        binding.hours.setText(hour+":"+min);
        day=calendar.get(Calendar.DAY_OF_MONTH);
        mon=calendar.get(Calendar.MONTH)+1;
        ye=calendar.get(Calendar.YEAR);
        binding.day.setText(day+"/"+mon+"/"+ye);

        tvFormDefaults=binding.tvFormDefaults.getText()+"";
        tvFormFile=binding.tvFormFile.getText()+"";
        tvTag=binding.tags.getText()+"";
        tvDay=binding.day.getText()+"";
        tvHour=binding.hours.getText()+"";
        tvWeek=binding.weeks.getText()+"";
        tvWork=binding.tvwork.getText()+"";
        edTitle=binding.edTitle.getText()+"";
        edDescription=binding.edDescription.getText()+"";


        savePresenter=new SavePresenter(this);
        binding.tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog=new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Thông Báo")
                        .setMessage("Bạn có muốn lưu không")
                        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                savePresenter.onSave(1);
                                tvFormDefaults=binding.tvFormDefaults.getText()+"";
                                tvFormFile=binding.tvFormFile.getText()+"";
                                tvTag=binding.tags.getText()+"";
                                tvDay=binding.day.getText()+"";
                                tvHour=binding.hours.getText()+"";
                                tvWeek=binding.weeks.getText()+"";
                                tvWork=binding.tvwork.getText()+"";
                                edTitle=binding.edTitle.getText()+"";
                                edDescription=binding.edDescription.getText()+"";
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                binding.edTitle.setText(edTitle);
                                binding.edDescription.setText(edDescription);
                                binding.hours.setText(tvHour);
                                binding.day.setText(tvDay);
                                binding.tvwork.setText(tvWork);
                                binding.tags.setText(tvTag);
                                binding.weeks.setText(tvWeek);
                                binding.tvFormFile.setText(tvFormFile);
                                binding.tvFormDefaults.setText(tvFormDefaults);
                                savePresenter.onSave(0);
                            }
                        })
                        .create();
                alertDialog.show();
            }
        });

        binding.day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            calendar.set(year,month,dayOfMonth);
                            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
                            binding.day.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                },ye,mon,day);
                datePickerDialog.show();
            }
        });

        binding.hours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.time.setVisibility(View.VISIBLE);
                binding.butOK.setVisibility(View.VISIBLE);
                binding.butCancel1.setVisibility(View.VISIBLE);
                binding.time.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                    @Override
                    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                        hour=hourOfDay;
                        min=minute;
                    }
                });
                binding.butOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        binding.time.setVisibility(View.GONE);
                        binding.hours.setText(hour+":"+min);
                        binding.butCancel1.setVisibility(View.GONE);
                        binding.butOK.setVisibility(View.GONE);
                    }
                });

                binding.butCancel1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        binding.time.setVisibility(View.GONE);
                        binding.butCancel1.setVisibility(View.GONE);
                        binding.butOK.setVisibility(View.GONE);
                    }
                });
            }

        });


        binding.tvFormDefaults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] mang={"Nexus Tune","Winphone Tune","Peep Tune","Nokia Tune","etc"};
                List<String> listDS=new ArrayList<>();
                AlertDialog alertDialog=new AlertDialog.Builder(MainActivity.this)
                        .setSingleChoiceItems(mang, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(listDS.size()==0)
                                listDS.add(mang[which]);
                                else{
                                    listDS.remove(0);
                                    listDS.add(mang[which]);
                                }
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(listDS.get(0).equals("etc")==false)
                                binding.tvFormDefaults.setText(listDS.get(0));
                            }
                        }).create();
                alertDialog.show();
            }
        });

        binding.tags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] mang={"Family","Android","Game","VTC","Friends"};
                boolean[] booleans={false,false,false,false,false};
                List<String> listDS=new ArrayList<>();
                AlertDialog alertDialog=new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Choose tag:")
                        .setMultiChoiceItems(mang,booleans, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if(isChecked){
                                    listDS.add(mang[which]);
                                }
                            }
                        }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String tag="";
                                for(int i=0;i<listDS.size();i++){
                                    if(i!=(listDS.size()-1))
                                    tag=tag+listDS.get(i)+",";
                                    else tag=tag+listDS.get(i);
                                }
                                binding.tags.setText(tag);
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).create();
                alertDialog.show();
            }
        });

        binding.weeks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] mang={"Month","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
                boolean[] booleans={false,false,false,false,false,false,false};
                List<String> listDS=new ArrayList<>();
                AlertDialog alertDialog=new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Choose weekdays:")
                        .setMultiChoiceItems(mang,booleans, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if(isChecked){
                                    listDS.add(mang[which]);
                                }
                            }
                        }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String tag="";
                                for(int i=0;i<listDS.size();i++){
                                    if(i!=(listDS.size()-1))
                                        tag=tag+listDS.get(i).substring(0,2)+",";
                                    else tag=tag+listDS.get(i).substring(0,2);
                                }
                                binding.weeks.setText(tag);
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).create();
                alertDialog.show();
            }
        });


        binding.tvwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] mang={"Word","Friend","Family"};
                AlertDialog alertDialog=new AlertDialog.Builder(MainActivity.this)
                        .setItems(mang, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                binding.tvwork.setText(mang[which]+"     |^");
                            }
                        })
                        .create();
                alertDialog.show();
            }
        });

        binding.butTune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvFormDefaults.setVisibility(View.VISIBLE);
                binding.tvFormFile.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onSaveSuccessful(String mess) {
        Toast.makeText(this,mess,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNotSave(String mess) {
        Toast.makeText(this,mess,Toast.LENGTH_LONG).show();
    }
}