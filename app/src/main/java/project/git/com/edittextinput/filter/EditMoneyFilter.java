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

        //已经输入小数点的情况下，只能输入数字
        if (destText.contains(POINTER)) {
            if (!FilterUtils.isMoneyFormat(source.toString())) {
                return "";
            } else {
                if (POINTER.equals(source)) {  //只能输入一个小数点
                    return "";
                }
            }

            //验证小数点精度，保证小数点后只能输入两位
            int index = destText.indexOf(POINTER);
            int length = dend - index;

            if (length > POINTER_LENGTH) {
                return dest.subSequence(dstart, dend);
            }
        } else {
            //没有输入小数点的情况下，只能输入小数点和数字，但首位不能输入小数点和0
            if (!FilterUtils.isMoneyFormat(source.toString())) {
                return "";
            } else {
                if ((POINTER.equals(source) || (destText.equals(ZERO) && ZERO.equals(source))) && TextUtils.isEmpty(destText)) {
                    return "";
                }
            }
        }

        //验证输入金额的大小
        double sumText = Double.parseDouble(destText + sourceText);

        if (sumText > MAX_VALUE) {
            return dest.subSequence(dstart, dend);
        }
        CharSequence c = dest.subSequence(dstart, dend) + sourceText;

        return c;
    }
}
