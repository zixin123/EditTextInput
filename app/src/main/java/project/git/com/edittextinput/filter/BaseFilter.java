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
    public BaseFilter(EditText editText, final int inputType, final String acceptedChars) {

        if (!TextUtils.isEmpty(acceptedChars)) {
            editText.setKeyListener(new DigitsKeyListener() {

                @Override
                public int getInputType() {
                    return inputType;
                }

                @Override
                protected char[] getAcceptedChars() {
                    return acceptedChars.toCharArray();
                }
            });
        } else {
            editText.setInputType(inputType);
        }
    }

    public BaseFilter(EditText editText, int inputType) {
        editText.setInputType(inputType);
    }

    public BaseFilter(EditText editText, final String acceptedChars) {
        if (!TextUtils.isEmpty(acceptedChars)) {
            editText.setKeyListener(DigitsKeyListener.getInstance(acceptedChars));//只是控制输入的内容,排序/格式 控制不了
        }
    }


    /**
     * 在子类中重写这个方法,
     * 1.可以控制输入的数量,
     * 2.完善输入的内容
     * 3.通过正则控制输入的格式
     *
     * @param source 新输入的字符串
     * @param start  新输入的字符串起始下标，一般为0
     * @param end    新输入的字符串终点下标，一般为source长度-1
     * @param dest   输入之前文本框内容
     * @param dstart 原内容起始坐标，一般为0
     * @param dend   原内容终点坐标，一般为dest长度-1
     * @return 输入内容
     */
    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        return null;
    }
}
