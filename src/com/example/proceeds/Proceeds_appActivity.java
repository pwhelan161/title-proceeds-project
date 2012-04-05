package com.example.proceeds;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Proceeds_appActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Spinner spinner = (Spinner) findViewById(R.id.realtor_commissions_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
        		 this, R.array.realtor_commissions_prompt, 
        		 android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        		
    };
    
    public void calculateProceeds(View button){
    	final EditText selling_price = (EditText) findViewById(R.id.selling_price);
    	final EditText first_mortgage = (EditText) findViewById(R.id.first_mortgage_decimal);
    	final EditText second_mortgage = (EditText) findViewById(R.id.second_mortgage_decimal);
    	final EditText other_liens = (EditText) findViewById(R.id.other_liens_decimal);
    	final Spinner commissions = (Spinner) findViewById(R.id.realtor_commissions_spinner);
    	String commission_rate = commissions.getSelectedItem().toString();
    	
    	String sellingPriceS = selling_price.getText().toString();
    	float sellingPrice = Float.valueOf(sellingPriceS);
    	
    	String firstMortgageS = first_mortgage.getText().toString();
    	float firstMortgage = Float.valueOf(firstMortgageS);
    	
    	String secondMortgageS = second_mortgage.getText().toString();
    	float secondMortgage = Float.valueOf(secondMortgageS);
    	
    	String otherLiensS = other_liens.getText().toString();
    	float otherLiens = Float.valueOf(otherLiensS);
    	
    	float commissionRate = Float.valueOf(commission_rate);
    	
    	float proceeds = sellingPrice + firstMortgage + secondMortgage + otherLiens
    			+ commissionRate;
    	
    	String proceedsStr = String.valueOf(proceeds);/*
    	updateStr = Context.getResources().getString()*/
    	
    	TextView tv = (TextView)findViewById(R.id.calculatedProceeds);
    	tv.setText(proceedsStr);
    	
    	
    };
}