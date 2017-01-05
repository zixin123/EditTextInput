package project.git.com.edittextinput.filter;

import android.text.InputType;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

/**
 * Created by yang on 16/3/30.
 * 金额,小数点后面只能输入两位小数
 */
public class EditMoneyFilter extends BaseFilter {

    //输入的最大金额
    private double MAX_VALUE = Double.MAX_VALUE;
    //小数点后的位数
    private int POINTER_LENGTH = 2;

    private static final String POINTER = ".";

    private static final String ZERO = "0";

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


    /**
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
            return dest.subSequence(dstart, dend);
        }
        CharSequence c = dest.subSequence(dstart, dend) + sourceText;

        return c;
    }
}
