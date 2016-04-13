package tough.john.dartsaveragecalculator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        final Button submit_button = (Button) findViewById(R.id.submitButton);
        submit_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText numberOfDartsView = (EditText) findViewById(R.id.editTextNumberOfDarts);
                String numberOfDartsString = numberOfDartsView.getText().toString();

                EditText remainingScoreView = (EditText) findViewById(R.id.editTextRemainingScore);
                String remainingScoreString = remainingScoreView.getText().toString();

                if (numberOfDartsString.matches("") || remainingScoreString.matches("")) {
                    showWarningDialog(R.string.title_alert_dialog, R.string.message_alert_empty_fields);
                } else {
                    float numberOfDarts = Integer.parseInt(numberOfDartsString);
                    float remainingScore = Integer.parseInt(remainingScoreString);

                    if (numberOfDarts < 6) {
                        showWarningDialog(R.string.title_alert_dialog, R.string.message_alert_invalid_number_of_darts);

                    } else if (remainingScore > 501 || remainingScore < 0) {
                        showWarningDialog(R.string.title_alert_dialog, R.string.message_alert_invalid_score);
                    } else {
                        final float DARTS_PER_THROW = 3;
                        final float LEG_TOTAL = 501;

                        float threeDartAverage = (( (LEG_TOTAL - remainingScore) / numberOfDarts ) * DARTS_PER_THROW );

                        TextView threeDartAverageTextView = (TextView) findViewById(R.id.textView3DartAverage);
                        threeDartAverageTextView.setText(String.format(Locale.UK, "%.2f", threeDartAverage));
                    }
                }
            }
        });

        final Button clear_button = (Button) findViewById(R.id.clearButton);
        clear_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText numberOfDartsView = (EditText) findViewById(R.id.editTextNumberOfDarts);
                EditText remainingScoreView = (EditText) findViewById(R.id.editTextRemainingScore);
                TextView threeDartAverageTextView = (TextView) findViewById(R.id.textView3DartAverage);

                numberOfDartsView.setText("");
                remainingScoreView.setText("");
                threeDartAverageTextView.setText("");

                remainingScoreView.requestFocus();
            }
        });
    }

    private void showWarningDialog(int title, int message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setMessage(message)
                .setTitle(title)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                }).show();
    }
}