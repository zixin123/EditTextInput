package project.git.com.edittextinput;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.DialerKeyListener;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import project.git.com.edittextinput.filter.EditPhoneFilter;
import project.git.com.edittextinput.filter.FilterUtils;
import project.git.com.edittextinput.view.EditTextView;

public class MainActivity extends AppCompatActivity {


    private EditTextView moneyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        EditText test_edit = (EditText) findViewById(R.id.test_edit);


//        test_edit.setInputType(InputType.TYPE_CLASS_NUMBER);
        test_edit.setKeyListener(new DigitsKeyListener() {
            @Override
            public int getInputType() {
                return InputType.TYPE_CLASS_TEXT;
            }

//            @Override
//            protected char[] getAcceptedChars() {
//                return "abc".toCharArray();
//            }
        });

        test_edit.setInputType(InputType.TYPE_CLASS_NUMBER);
        if (test_edit.getInputType() == InputType.TYPE_CLASS_NUMBER) {
            test_edit.setText("0");
        } else if (test_edit.getInputType() == InputType.TYPE_CLASS_TEXT) {
            test_edit.setText("a");
        }


        String strIds = "abc";
        test_edit.setKeyListener(DigitsKeyListener.getInstance(strIds));


        findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOk();
            }
        });
    }

    private void getOk() {
        moneyView = (EditTextView) findViewById(R.id.money_view);
        String money = moneyView.getText();
        if (FilterUtils.regularMoney(money, 2)) {
            Toast.makeText(MainActivity.this, "是金额格式", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "不是金额格式", Toast.LENGTH_SHORT).show();
        }
    }


}
