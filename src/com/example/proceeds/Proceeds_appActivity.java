package com.example.proceeds;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
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

		/* Set commission spinner */
		Spinner spinner = (Spinner) findViewById(R.id.realtor_commissions_spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.realtor_commissions_prompt,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

		/* Set county spinner */
		Spinner countySpinner = (Spinner) findViewById(R.id.county_spinner);
		ArrayAdapter<CharSequence> countyAdapter = ArrayAdapter
				.createFromResource(this, R.array.county_spinner,
						android.R.layout.simple_spinner_item);
		countyAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		countySpinner.setAdapter(countyAdapter);

		/* Set paid through spinner */
		Spinner paidThroughSpinner = (Spinner) findViewById(R.id.PaidThrough_spinner);
		ArrayAdapter<CharSequence> paidThroughAdapter = ArrayAdapter
				.createFromResource(this, R.array.PaidThrough,
						android.R.layout.simple_spinner_item);
		paidThroughAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		paidThroughSpinner.setAdapter(paidThroughAdapter);

		/* Set HOA/Condo Fees spinner */
		Spinner hoaSpinner = (Spinner) findViewById(R.id.HOACondoFeesSpinner1);
		ArrayAdapter<CharSequence> hoaAdapter = ArrayAdapter
				.createFromResource(this, R.array.HOACondoFeesSpinner1,
						android.R.layout.simple_spinner_item);
		hoaAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		hoaSpinner.setAdapter(hoaAdapter);

		// capture our View elements
		// mDateDisplay = (TextView) findViewById(R.id.closingDateValue);
		mPickDate = (TextView) findViewById(R.id.closingDateValue);
		// mPickDate = (Button) findViewById(R.id.pickDate);

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
	};

	// updates the date in the TextView
	private void updateDisplay() {
		mPickDate.setText(new StringBuilder()
				// Month is 0 based so add 1
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

	public void calculateProceeds(View button) {
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

		
		float commission = (float) (commissionRate * .01 * sellingPrice);
		String commissionS = String.valueOf(commission);

		TextView tv = (TextView) findViewById(R.id.calculatedProceeds);
		tv.setText(commissionS);
		
		setContentView(R.layout.results);

	};

}