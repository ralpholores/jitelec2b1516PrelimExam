package tech.sidespell.prelimexam;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private RadioButton mIncrement,mDecrement;
    private SeekBar mSeekBar;
    private TextView mTextView,mSeekValue;
    private ToggleButton mToggle;

    private int seekValue;
    private long timeRemaining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mIncrement = (RadioButton) findViewById(R.id.inc);
        mDecrement = (RadioButton) findViewById(R.id.dec);
        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        mTextView = (TextView) findViewById(R.id.textView);
        mSeekValue = (TextView) findViewById(R.id.seekValue);


        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                progress = progress / 10;
                progress = progress * 10;
                mSeekValue.setText(String.valueOf(progress));
                seekValue = progress;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final Handler handler = new Handler();

        mToggle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                    if(mIncrement.isSelected())
                    {
                        mTextView.setText(seekValue);


                        Runnable runnable = new Runnable() {
                            @Override
                            public void run() {
                                timeRemaining += seekValue;
                                mTextView.setText(timeRemaining + "");

                                handler.postDelayed(this, seekValue);

                            }
                        };

                        handler.postDelayed(runnable, seekValue);
                    }
                    else if(mDecrement.isSelected())
                    {
                        mTextView.setText(seekValue);


                        Runnable runnable = new Runnable() {
                            @Override
                            public void run() {
                                timeRemaining -= seekValue;
                                mTextView.setText(timeRemaining + "");

                                handler.postDelayed(this, seekValue);

                            }
                        };

                        handler.postDelayed(runnable, seekValue);
                    }
                }

        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
