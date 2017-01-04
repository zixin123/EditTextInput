package project.git.com.edittextinput.filter;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.widget.EditText;

/**
 * Created by yang on 2017/1/4.
 */

public class BaseFilter implements InputFilter {

    /**
     * @param editText      输入框
     * @param inputType     输入类型,这个控制的默认弹出的键盘,也会起到输入内容的限制
     * @param acceptedChars 控制可输入的字符
     */
    public BaseFilter(EditText editText, int inputType, String acceptedChars) {
//        editText.setKeyListener(new DigitsKeyListener() {
//            @Override
//            public int getInputType() {
//                return inputType;//控制输入类型和默认弹出的键盘类型
//            }
//
//            @Override
//            protected char[] getAcceptedChars() {//控制输入的内容接收的字符
//                return TextUtils.isEmpty(acceptedChars) ? super.getAcceptedChars() : acceptedChars.toCharArray();
//            }
//        });
        if (!TextUtils.isEmpty(acceptedChars)) {
            editText.setKeyListener(DigitsKeyListener.getInstance());
        }
        editText.setInputType(inputType);
    }

    public BaseFilter(EditText editText, int inputType) {
        editText.setInputType(inputType);
    }

    public BaseFilter(EditText editText, final String acceptedChars) {
        if (!TextUtils.isEmpty(acceptedChars)) {
            editText.setKeyListener(DigitsKeyListener.getInstance());
        }
    }

    public BaseFilter() {
        super();
    }


    /**
     * 在子类中重写这个方法,
     * 1.可以控制输入的数量,
     * 2.完善输入的内容
     * 3.通过正则控制输入的格式
     *
     * @param source
     * @param start
     * @param end
     * @param dest
     * @param dstart
     * @param dend
     * @return
     */
    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        return null;
    }
}
