package cs4720.cs.virginia.edu.coreskills;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**

 Assignment Notes: This activity should pull data from a REST JSON API based upon
 the course requested in the EditText.  The courses are those from a previous semester.
 We used the Retrofit library to do this, since making network calls in Android
 otherwise require actions on a separate thread.  If you wish to use this library,
 some helper code is provided in the LousListAPI classes.

 Retrofit References:
 http://square.github.io/retrofit/
 https://github.com/marksherriff/LousListRESTAPI

 Native JSON Parsing:
 http://www.ssaurel.com/blog/learn-to-consume-a-rest-web-service-and-parse-json-result-in-android/

 */

public class WebServiceActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://stardock.cs.virginia.edu/louslist/Courses/view/";

    EditText courseEditText;
    TextView courseNameTextView;
    TextView instructorTextView;
    TextView locationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service);

        courseEditText = (EditText) findViewById(R.id.courseEditText);
        courseNameTextView = (TextView) findViewById(R.id.courseNameTextView);
        instructorTextView = (TextView) findViewById(R.id.instructorTextView);
        locationTextView = (TextView) findViewById(R.id.locationTextView);
    }

    public void downloadData(View view) {

        // Add your code here to download the data and update the screen

    }
}
