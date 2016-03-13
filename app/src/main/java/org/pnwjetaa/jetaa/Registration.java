package org.pnwjetaa.jetaa;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;


public class Registration extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Button submitButton = (Button)findViewById(R.id.submit);

        final Spinner POSspinner = (Spinner)findViewById(R.id.PositionSpinner);
        String[] POSItems = new String[]{"ALT","CIR"};
        ArrayAdapter<String> POSAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,POSItems);
        POSspinner.setAdapter(POSAdapter);

        final Spinner Statespinner = (Spinner)findViewById(R.id.StateSpinner);
        String[] StateItems = new String[]{"WA","ID","MT"};
        ArrayAdapter<String> StateAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,StateItems);
        Statespinner.setAdapter(StateAdapter);

        final Spinner Yearsspinner = (Spinner)findViewById(R.id.YearsSpinner);
        String[] YearItems = new String[]{"1","2","3","4","5"};
        ArrayAdapter<String> YearAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,YearItems);
        Yearsspinner.setAdapter(YearAdapter);

        final EditText firstName = (EditText)findViewById(R.id.firstNameInput);
        final EditText lastName = (EditText)findViewById(R.id.lastNameInput);
        final EditText emailInput = (EditText)findViewById(R.id.emailInput);
        final EditText phoneInput = (EditText)findViewById(R.id.phoneInput);
        final EditText departCityInput = (EditText)findViewById(R.id.DepartureCityInput);
        final EditText departYearInput = (EditText)findViewById(R.id.DepartureYearInput);
        final EditText PlacementPref = (EditText)findViewById(R.id.PrefectureInput);
        final EditText yearsCompleteInput = (EditText)findViewById(R.id.YearCompletionInput);
        final EditText PlacementCity = (EditText)findViewById(R.id.PlacementCityInput);
        final TextView ErrorMessage = (TextView)findViewById(R.id.errorMessage);


        submitButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String first = firstName.getText().toString();
                        String last = lastName.getText().toString();
                        String email = emailInput.getText().toString();
                        String phone = phoneInput.getText().toString();
                        String state = Statespinner.getSelectedItem().toString();
                        String JETPos = POSspinner.getSelectedItem().toString();
                        String departCity = departCityInput.getText().toString();
                        String departYear = departYearInput.getText().toString();
                        String yearComplete = yearsCompleteInput.getText().toString();
                        String years = Yearsspinner.getSelectedItem().toString();
                        String placementPref = PlacementPref.getText().toString();
                        String placementCity = PlacementCity.getText().toString();

                        final RadioGroup interviews = (RadioGroup) findViewById(R.id.JETInterviewsBool);
                        final RadioGroup preDeparture = (RadioGroup) findViewById(R.id.preDepartureBool);
                        final RadioGroup recruiting = (RadioGroup) findViewById(R.id.JETRecruitingBool);
                        RadioButton InterviewInput = ((RadioButton)findViewById(interviews.getCheckedRadioButtonId()));
                        RadioButton preDepartInput = ((RadioButton)findViewById(preDeparture.getCheckedRadioButtonId()));
                        RadioButton recruitingInput = ((RadioButton)findViewById(recruiting.getCheckedRadioButtonId()));
                        FileOutputStream outputStream;
                        if(first.equals("") || last.equals("") || email.equals(""))
                        {
                            if(first.equals(""))
                            {
                                firstName.setBackgroundResource(R.drawable.red_box);
                            }
                            if(last.equals(""))
                            {
                                lastName.setBackgroundResource(R.drawable.red_box);
                            }
                            if(email.equals(""))
                            {
                                emailInput.setBackgroundResource(R.drawable.red_box);
                            }
                            ErrorMessage.setText("There is missing information.");
                            //firstName.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                        }
                        else {

                            try {


                                //new FileOutputStream(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).toString()+"/registration_info.txt").;
                                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).toString() + "/register_info.txt");

                                BufferedWriter output = new BufferedWriter(new FileWriter(file, true));
                                output.write("---------------------\n");
                                output.write("First Name: " + first + "\n");
                                output.write("Last Name: " + last + "\n");
                                output.write("Email: " + email + "\n");
                                output.write("Phone: " + phone + "\n");
                                output.write("State: " + state + "\n");
                                output.write("JET Position: " + JETPos + "\n");
                                output.write("Departure City: " + departCity + "\n");
                                output.write("Departure Year: " + departYear + "\n");
                                output.write("Year Competed JET: " + yearComplete + "\n");
                                output.write("Total Years on JET: " + years + "\n");
                                output.write("Placement Prefecture: " + placementPref + "\n");
                                output.write("Placement City: " + placementCity + "\n");
                                if (InterviewInput != null) {
                                    if (InterviewInput.isChecked()) {
                                        output.write("Willing to do Interviews: " + InterviewInput.getText().toString() + "\n");
                                        InterviewInput.setChecked(false);
                                    }

                                }
                                if (preDepartInput != null) {
                                    if (preDepartInput.isChecked()) {
                                        output.write("Willing to help with Pre-Departure: " + preDepartInput.getText().toString() + "\n");
                                        preDepartInput.setChecked(false);
                                    }
                                }
                                if (recruitingInput != null) {
                                    if (recruitingInput.isChecked()) {
                                        output.write("Willing to help with JET Recruiting: " + recruitingInput.getText().toString() + "\n");
                                        recruitingInput.setChecked(false);
                                    }
                                }

                                output.close();
                                firstName.setText("");
                                lastName.setText("");
                                emailInput.setText("");
                                phoneInput.setText("");
                                departCityInput.setText("");
                                departYearInput.setText("");
                                PlacementPref.setText("");
                                yearsCompleteInput.setText("");
                                PlacementCity.setText("");
                                ErrorMessage.setText("Submitted successfully. Thank you!");

                                firstName.setBackgroundResource(R.drawable.box);
                                lastName.setBackgroundResource(R.drawable.box);
                                emailInput.setBackgroundResource(R.drawable.box);
                                //outputStream = openFileOutput(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).toString()+"/register_info.txt");
                                //outputStream.write(first.getBytes());
                                //outputStream.flush();
                                //outputStream.close();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                        }
//                        try{
//                            Thread.sleep(4000);
//                            ErrorMessage.setText("");
//                        } catch (Exception e){
//                            e.printStackTrace();
//                        }


                    }
                });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
