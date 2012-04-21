package com.example.proceeds;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Calendar;

public class Results extends Activity {

	/* Called when the activity is first created. */

	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.results);
		Bundle extras = getIntent().getExtras();
		if (extras == null) {
			return;
		}
		
		//Calc & set commission
		float commissionRate = extras.getFloat("commissionRate");
		float sellingPrice = extras.getFloat("sellingPrice");
		float commission = commissionRate * sellingPrice;
		TextView text1 = (TextView) findViewById(R.id.res_commission_decimal);
		final String commissionOut = Float.toString(commission);
		text1.setText(commissionOut);
		
		//Calc & set conveyance fee
		/* Easy example */ 
		String county = extras.getString("county");
			TextView conveyance= (TextView) findViewById(R.id.res_conveyance_fee_decimal);
		float countyRate = getCountyRate(county);
		float conveyanceFee = getConveyanceFee(countyRate, sellingPrice);
		conveyance.setText(Float.toString(conveyanceFee));
		
		String paidThrough = extras.getString("paidThrough");
		String hoaFreq = extras.getString("hoaFreq");
		String hoaFee = extras.getString("hoaFee");
		String closingDate = extras.getString("closingDate");
		String FirstMortgage = extras.getString("FirstMortgage");
		String SecondMortgage = extras.getString("SecondMortgage");
		String otherLiens = extras.getString("otherLines");
		String otherRealtor = extras.getString("otherRealtor");
		String gasLine = extras.getString("gasLine");
		String homeWarranty = extras.getString("homeWarranty");
		

	}
	
	public float getCountyRate(String county){
		/* Get the county rates, as per the email sent to me */

		float rate = (float) 0.0;
		if (county.equals("Delaware")){
			rate = (float) 0.30;
		}
		else if (county.equals("Franklin") || county.equals("Licking") || county.equals("Union")){
			rate = (float) 0.20;
		}
		else if (county.equals("Fairfield") || county.equals("Other")){
			rate = (float) 0.40;
		}
		return rate;
			
	}
	
	public float getConveyanceFee(float countyRate, float sellingPrice){
		/* For every hundred of sale price, charge the county rate */
		float tempPrice = Math.round(sellingPrice / 100) * 100;
		if (sellingPrice > tempPrice){
			tempPrice = tempPrice + 100;
		}
		tempPrice = tempPrice / 100;
		return countyRate * tempPrice;
		
	}
};