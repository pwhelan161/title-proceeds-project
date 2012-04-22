package com.example.proceeds;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Proceeds_appActivity extends Activity {

	private TextView mDateDisplay;
	// private Button mPickDate;
	private TextView mPickDate;
	private int mYear;
	private int mMonth;
	private int mDay;

	static final int DATE_DIALOG_ID = 0;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Declaring all variables

		/* Set commission spinner */
		final Spinner spinner = (Spinner) findViewById(R.id.realtor_commissions_spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.realtor_commissions_prompt,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

		/* Set county spinner */
		final Spinner countySpinner = (Spinner) findViewById(R.id.county_spinner);
		ArrayAdapter<CharSequence> countyAdapter = ArrayAdapter
				.createFromResource(this, R.array.county_spinner,
						android.R.layout.simple_spinner_item);
		countyAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		countySpinner.setAdapter(countyAdapter);

		/* Set paid through spinner */
		final Spinner paidThroughSpinner = (Spinner) findViewById(R.id.PaidThrough_spinner);
		ArrayAdapter<CharSequence> paidThroughAdapter = ArrayAdapter
				.createFromResource(this, R.array.PaidThrough,
						android.R.layout.simple_spinner_item);
		paidThroughAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		paidThroughSpinner.setAdapter(paidThroughAdapter);

		/* Set HOA/Condo Fees spinner */
		final Spinner hoaSpinner = (Spinner) findViewById(R.id.HOACondoFeesSpinner1);
		ArrayAdapter<CharSequence> hoaAdapter = ArrayAdapter
				.createFromResource(this, R.array.HOACondoFeesSpinner1,
						android.R.layout.simple_spinner_item);
		hoaAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		hoaSpinner.setAdapter(hoaAdapter);

		/* Set the date picker & initialize it */
		// capture our View elements
		mPickDate = (TextView) findViewById(R.id.closingDateValue);

		// add a click listener to the button
		mPickDate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);
			}
		});

		// get the current date
		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);

		// display the current date (this method is below)
		updateDisplay();
		/* Finished setting & updating datepicker */

		/* Ready calculate button to go to other activity */
		Button next = (Button) findViewById(R.id.calculateButton);
		next.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				
				final EditText annualTextI = (EditText) findViewById(R.id.AnnualTax);
				float annualTax = Float.valueOf(annualTextI.getText().toString());

				final EditText feeAmountI = (EditText) findViewById(R.id.HOAFeesDollars);
				float feeAmount = Float
						.valueOf(feeAmountI.getText().toString());

				final EditText sellingPriceI = (EditText) findViewById(R.id.selling_price);
				final float sellingPrice = Float.valueOf(sellingPriceI
						.getText().toString());

				final EditText firstMortgageI = (EditText) findViewById(R.id.first_mortgage_decimal);
				float firstMortgage = Float.valueOf(firstMortgageI.getText()
						.toString());

				final EditText secondMortgageI = (EditText) findViewById(R.id.second_mortgage_decimal);
				final float secondMortgage = Float.valueOf(secondMortgageI
						.getText().toString());

				final EditText otherLiensI = (EditText) findViewById(R.id.other_liens_decimal);
				final float otherLiens = Float.valueOf(otherLiensI.getText()
						.toString());

				final EditText otherRealtorI = (EditText) findViewById(R.id.other_realtor_fees_decimal);
				float otherRealter = Float.valueOf(otherRealtorI.getText()
						.toString());

				final EditText gasLineI = (EditText) findViewById(R.id.gas_line_decimal);
				float gasLine = Float.valueOf(gasLineI.getText().toString());

				final EditText homeWarrantyI = (EditText) findViewById(R.id.home_warranty_decimal);
				float homeWarranty = Float.valueOf(homeWarrantyI.getText()
						.toString());

				final EditText sellerConcessionsI = (EditText) findViewById(R.id.seller_concessions_decimal);
				float sellerConcessions = Float.valueOf(sellerConcessionsI
						.getText().toString());

				float commissionRate = Float.valueOf(spinner.getSelectedItem()
						.toString());
				commissionRate = (float) (commissionRate * .01);
				
				final EditText hoaFeeI = (EditText) findViewById(R.id.HOAFeesDollars);
				float hoaFee = Float.valueOf(hoaFeeI.getText().toString());

				Intent myIntent = new Intent(view.getContext(), Results.class);
				myIntent.putExtra("year", mYear);
				myIntent.putExtra("month", mMonth);
				myIntent.putExtra("day", mDay);
				myIntent.putExtra("paidThrough", paidThroughSpinner
						.getSelectedItem().toString());
				myIntent.putExtra("annualTax", annualTax);
				myIntent.putExtra("county", countySpinner.getSelectedItem()
						.toString());
				myIntent.putExtra("commissionRate", commissionRate);
				myIntent.putExtra("sellingPrice", sellingPrice);
				myIntent.putExtra("hoaFee", hoaFee);
				myIntent.putExtra("hoaFreq", hoaSpinner.getSelectedItem().toString());
			
				startActivityForResult(myIntent, 0);
			}

		});

	};

	// updates the date in the TextView
	private void updateDisplay() {
		mPickDate.setText(new StringBuilder()
				// Month is 0 based so add 1 for display purposes
				.append(mMonth + 1).append("-").append(mDay).append("-")
				.append(mYear).append(" "));
	}

	// the callback received when the user "sets" the date in the dialog
	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			updateDisplay();
		}
	};

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
					mDay);
		}
		return null;
	}

}