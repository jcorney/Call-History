package com.example.callhistory;

import java.util.Date;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		Cursor myCursor = managedQuery(CallLog.Calls.CONTENT_URI, null, null,

		null, null);

		int number = myCursor.getColumnIndex(CallLog.Calls.NUMBER);

		int date = myCursor.getColumnIndex(CallLog.Calls.DATE);

		int duration = myCursor.getColumnIndex(CallLog.Calls.DURATION);

		int type = myCursor.getColumnIndex(CallLog.Calls.TYPE);

		StringBuilder sb = new StringBuilder();

		while (myCursor.moveToNext()) {

			String phnumber = myCursor.getString(number);
			String callduration = myCursor.getString(duration);
			String calltype = myCursor.getString(type);
			String calldate = myCursor.getString(date);
			Date d = new Date(Long.valueOf(calldate));

			String callTypeStr = "";

			switch (Integer.parseInt(calltype)) {

			case CallLog.Calls.OUTGOING_TYPE:
				callTypeStr = "Outgoing";
				break;

			case CallLog.Calls.INCOMING_TYPE:
				callTypeStr = "Incoming";
				break;

			case CallLog.Calls.MISSED_TYPE:
				callTypeStr = "Missed";
				break;

			}

			sb.append("Phone number " + phnumber);
			sb.append(System.getProperty("line.separator"));
			sb.append("Call duration " + callduration);
			sb.append(System.getProperty("line.separator"));
			sb.append("Call type " + callTypeStr);
			sb.append(System.getProperty("line.separator"));
			sb.append("Call date " + d);
			sb.append(System.getProperty("line.separator"));
			sb.append("---------------------------");
			sb.append(System.getProperty("line.separator"));

		}

		TextView callDetails = (TextView) findViewById(R.id.text_CallHistory);

		callDetails.setText(sb.toString());

	}

}
