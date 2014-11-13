package makeabilitylab.umiacs.umd.edu.cmsc434inclass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;


public class CoolClockActivity extends Activity {

    private Timer _timeUpdater = new Timer();
    private Handler _uiHandler = new Handler();

    public CoolClockActivity() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cool_clock);

        System.out.println("Starting Cool Clock");
        _timeUpdater.schedule(new TimerTask() {
            @Override
            public void run() {
                _uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        CoolClockView coolClockView = (CoolClockView)findViewById(
                                R.id.coolClockView);
                        //System.out.println(Calendar.getInstance().toString());
                        coolClockView.updateTime(Calendar.getInstance());
                    }
                });
            }
        }, 0, 33);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cool_clock, menu);
        System.out.println("Create options menu");
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

}
