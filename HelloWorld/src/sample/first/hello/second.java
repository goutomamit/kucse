package sample.first.hello;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class second extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final Button button = (Button) findViewById(R.id.Button01);
        button.setOnClickListener(new OnClickListener() 
        {
            public void onClick(View v)
            
            {
            	Intent myIntent=new Intent(second.this,first.class);
            	second.this.startActivity(myIntent);
            	
            	
            }
            
        
            
        });
            
            
    }
}