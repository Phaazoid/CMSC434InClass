package makeabilitylab.umiacs.umd.edu.cmsc434inclass;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.preference.Preference;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class ClockActivity extends Activity {

    private Timer _timerCount = new Timer();
    private Calendar _calendar = Calendar.getInstance();
    private Handler _uiHandler = new Handler();
    private String _dateFormat = "mm/dd/yy";


    public ClockActivity() {
        super();
    }

    private String dayOfWeekToString(int dayOfWeek) {
        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                return "Sunday";
            case Calendar.MONDAY:
                return "Monday";
            case Calendar.TUESDAY:
                return "Tuesday";
            case Calendar.WEDNESDAY:
                return "Wednesday";
            case Calendar.THURSDAY:
                return "Thursday";
            case Calendar.FRIDAY:
                return "Friday";
            case Calendar.SATURDAY:
                return "Saturday";
            default:
                return "Invalid day of week";
        }
    }

    private String timeToString(Calendar calendar) {
        return String.format("%02d:%02d:%02d %s", calendar.get(Calendar.HOUR),
                calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND),
                calendar.get(Calendar.AM_PM) == Calendar.AM ? "am" : "pm");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        //Start the clock
        _timerCount.schedule(new TimerTask() {
            public void run() {
                _calendar = Calendar.getInstance();
                final String dateString;
                if (_dateFormat.equals("mm/dd/yy")) {
                    dateString = _calendar.get(Calendar.MONTH) + "/" +
                            _calendar.get(Calendar.DAY_OF_MONTH) + "/" +
                            _calendar.get(Calendar.YEAR);
                }
                else if (_dateFormat.equals("dd/mm/yy")) {
                    dateString = _calendar.get(Calendar.DAY_OF_MONTH) + "/" +
                            _calendar.get(Calendar.MONTH) + "/" +
                            _calendar.get(Calendar.YEAR);
                }
                else {
                    dateString = "Invalid date format";
                }
                final String timeString = timeToString(_calendar);
                final String dayString = dayOfWeekToString(_calendar.get(Calendar.DAY_OF_WEEK));
                _uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        TextView textViewDayOfWeek = (TextView)findViewById(R.id.textViewDayOfWeek);
                        textViewDayOfWeek.setText(dayString);
                        TextView textViewDate = (TextView)findViewById(R.id.textViewDate);
                        textViewDate.setText(dateString);
                        TextView textViewTime = (TextView)findViewById(R.id.textViewTime);
                        textViewTime.setText(timeString);
                    }
                });
            }
        }, 0, 1000);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.clock, menu);
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
