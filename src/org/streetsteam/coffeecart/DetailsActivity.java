package org.streetsteam.coffeecart;

import org.streetstream.coffeecart.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.Button;
import android.widget.TextView;

public class DetailsActivity extends ActionBarActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details);
		TextView t1 = (TextView)findViewById(R.id.textView8);
		t1.setText(getIntent().getExtras().get("name").toString());
		TextView t2 = (TextView)findViewById(R.id.textView2);
		t2.setText(getIntent().getExtras().get("start").toString());
		TextView t4 = (TextView)findViewById(R.id.textView6);
		t4.setText(getIntent().getExtras().get("venue").toString());
		TextView t5 = (TextView)findViewById(R.id.textView7);
		t5.setText(getIntent().getExtras().get("capacity").toString());
		TextView t9 = (TextView)findViewById(R.id.textView10);
		t9.setText(getIntent().getExtras().get("description").toString());
		
		getActionBar().setDisplayShowHomeEnabled(true);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.details_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
	    // Respond to the action bar's Up/Home button
	    case android.R.id.home:
	        NavUtils.navigateUpFromSameTask(this);
	        return true;
	    case R.id.menu_schedule:
	    	/*Intent backtoMain = new Intent(getApplicationContext(),MainActivity.class);
        	DetailsActivity.this.startActivity(backtoMain);*/
	    	return true;
	    }
	    return super.onOptionsItemSelected(item);
	}
}
