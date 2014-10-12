package org.streetsteam.coffeecart;

import org.streetstream.coffeecart.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailsActivity extends ActionBarActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details);
		TextView t1 = (TextView)findViewById(R.id.textView2);
		t1.setText(getIntent().getExtras().get("name").toString());
		TextView t2 = (TextView)findViewById(R.id.textView3);
		t2.setText(getIntent().getExtras().get("start").toString());
		TextView t4 = (TextView)findViewById(R.id.textView4);
		t4.setText(getIntent().getExtras().get("venue").toString());
		TextView t5 = (TextView)findViewById(R.id.textView5);
		t5.setText(getIntent().getExtras().get("capacity").toString());
		TextView t9 = (TextView)findViewById(R.id.textView9);
		t9.setText(getIntent().getExtras().get("description").toString());
		
		final Button button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent backtoMain = new Intent(getApplicationContext(),MainActivity.class);
            	DetailsActivity.this.startActivity(backtoMain);
            }
        });
	}
}
