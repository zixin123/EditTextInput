package project.git.com.edittextinput.filter;

import android.text.InputType;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.EditText;

/**
 * 电话过滤器
 */
public class EditPhoneFilter extends BaseFilter {

    private int MAX_LENGTH = 11;

    public EditPhoneFilter(EditText editText) {
        super(editText, InputType.TYPE_CLASS_NUMBER, "0123456789");
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
        if (!FilterUtils.isInputPhoneFormat(destText + sourceText)) {
            return "";
        }
        if ((destText + sourceText).length() > MAX_LENGTH) {
            return "";
        }
        return dest.subSequence(dstart, dend) + sourceText;
    }

}
