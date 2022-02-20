package hrubos.local.calc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText num1;
    EditText num2;
    Spinner spinner;
    Button calculate;
    TextView labelResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculate = findViewById(R.id.calculate);
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        spinner = findViewById(R.id.spinner);
        labelResult = findViewById(R.id.labelResult);

        calculate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                double number1;
                double number2;
                double result = 0;

                try {
                    number1 = Double.parseDouble(num1.getText().toString());
                    number2 = Double.parseDouble(num2.getText().toString());

                    switch(spinner.getSelectedItem().toString()){
                        case "+":
                            result = number1 + number2;
                            break;
                        case "-":
                            result = number1 - number2;
                            break;
                        case "*":
                            result = number1 * number2;
                            break;
                        case "/":
                            if (number2 == 0) {
                                labelResult.setText("NULOU DĚLIT NELZE!");
                                return;
                            }
                            result = number1/number2;
                            break;
                        default:
                            labelResult.setText("Nevybrán operátor");
                            return;
                    }

                    NumberFormat nf = new DecimalFormat("#.###");
                    labelResult.setText(nf.format(result));
                } catch (NumberFormatException e) {
                    labelResult.setText("Chybné zadání...");
                }
            }
        });

        String[] operatorsArray = getResources().getStringArray(R.array.operators);
        List list = new ArrayList(Arrays.asList(operatorsArray));
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

    }
}