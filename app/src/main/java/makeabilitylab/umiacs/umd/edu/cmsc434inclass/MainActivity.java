package makeabilitylab.umiacs.umd.edu.cmsc434inclass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends Activity {

    // This is just a dummy counter that counts upwards. We'll display
    // the current count value in a TextView. Note: I like to prefix my member variables
    // by the underscore character--this is just a convention, others use 'm'. For example,
    // 'mCounter = 0' Either way, I think prefixing your member variables makes your code more
    // readable and cuts down on accidental scoping errors
    private int _counter = 0;

    // Timers schedule one-shot or recurring tasks for execution.
    // http://developer.android.com/reference/java/util/Timer.html
    private Timer _timerCount = new Timer();

    // There are two main uses for a Handler: (1) to schedule messages and runnables to be
    // executed as some point in the future; and (2) to enqueue an action to be performed
    // on a different thread than your own. We are using it for #2. To enqueue an action
    // from the Timer thread to the UI thread.
    // http://developer.android.com/reference/android/os/Handler.html
    private Handler _uiHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This is hooked up in the XML. See 'android:onClick="onButtonHelloWorld"'
     * @param view
     */
    public void onButtonHelloWorld(View view){
        TextView textView = (TextView)findViewById(R.id.textViewHelloWorld);

        if(textView.getText().equals("Hello world!")){
            textView.setText("Goodbye world!");
            System.out.println("Switching to Goodbye World");
            Log.d("Hey","Switching to Goodbye World");
        }else{
            textView.setText("Hello world!");
        }
    }

    /**
     * This is hooked up in the XML. See 'android:onClick="onButtonStartTimer"'
     * @param view
     */
    public void onButtonStartTimer(View view){
        // The "view" in this case is the view that triggered the event
        // We disable the start timer button so that it cannot be triggered more than once
        view.setEnabled(false);

        // The code below would also work to disable the button
        // I include it here for illustrative purposes
        //View buttonView = findViewById(R.id.buttonStartTimer);
        //view.setEnabled(false);

        // We are using a few anonymous classes here; which makes the code a little more
        // complex looking than it really is. The schedule method of Timer takes in a TimerTask,
        // followed by a long delay (in milliseconds) and a long period (in milliseconds)
        _timerCount.schedule(new TimerTask() {

            @Override
            public void run() {
                _counter++;

                // Use the handler to marshal/invoke the Runnable code on the UI thread
                _uiHandler.post(new Runnable(){
                    @Override
                    public void run(){
                        TextView textView = (TextView)findViewById(R.id.textViewCounter);
                        textView.setText(_counter + "");
                    }
                });
            }
        }, 0, 1000);
    }

    public void onButtonGoToClock(View view) {
        Intent intent = new Intent(this, ClockActivity.class);
        startActivity(intent);
    }

}
