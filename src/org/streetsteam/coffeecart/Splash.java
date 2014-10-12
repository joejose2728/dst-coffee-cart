package org.streetsteam.coffeecart;

import org.streetstream.coffeecart.R;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Splash extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		Thread splash_screen = new Thread(){		
		public void run(){
			
			try{
				sleep(2000);
			} 
			catch(Exception ex){
				
					ex.printStackTrace();
				} finally{
					
					startActivity(new Intent(getApplicationContext(),MainActivity.class));
					
				}
			
			}
		};
		splash_screen.start();
	}
}
