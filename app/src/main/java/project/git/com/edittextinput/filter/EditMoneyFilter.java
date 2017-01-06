package project.git.com.edittextinput.filter;

import android.text.InputType;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

/**
 * 金额:输入的内容,最大值,小数点后位数,
 */
public class EditMoneyFilter extends BaseFilter {

    //输入的最大金额
    private double MAX_VALUE = Double.MAX_VALUE;
    //小数点后的位数
    private int POINTER_LENGTH = 2;

    private static final String POINTER = ".";

    /**
     * 默认的保留两位小数,默认最大值为Double.MAX_VALUE
     *
     * @param editText
     */
    public EditMoneyFilter(EditText editText) {
        this(editText, 2, Double.MAX_VALUE);
    }

    /**
     * @param editText
     * @param pointCount 小数点后保留的小数个数
     */
    public EditMoneyFilter(EditText editText, int pointCount) {
        this(editText, pointCount, Double.MAX_VALUE);
    }

    /**
     * @param editText
     * @param pointCount 小数点后保留的个数
     * @param max        最大值
     */
    public EditMoneyFilter(EditText editText, int pointCount, double max) {
        super(editText, InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        POINTER_LENGTH = pointCount;
        MAX_VALUE = max;
    }


    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        String sourceText = source.toString();
        String destText = dest.toString();

        //验证删除等按键
        if (TextUtils.isEmpty(sourceText)) {
            return "";
        }

        if (!FilterUtils.isInputMoneyFormat(destText + sourceText, POINTER_LENGTH)) {
            return "";
        }

        double sumText;
        if (sourceText.equals(POINTER)) {
            sumText = Double.parseDouble(destText);
        } else {
            //验证输入金额的大小
            sumText = Double.parseDouble(destText + sourceText);
        }

        if (sumText > MAX_VALUE) {
            return "";
        }
        return sourceText;
    }
}
