package project.git.com.edittextinput.filter;

import android.text.InputType;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.EditText;

/**
 * 字母或者数字 组合
 */
public class EditLetterOrNumberFilter extends BaseFilter {

    private int MAX_LENGTH = 10;

    public EditLetterOrNumberFilter(EditText editText, int maxLength) {
        super(editText, InputType.TYPE_TEXT_VARIATION_PASSWORD);
        //最有一个参数也可以直接写:0123456789qwertyuioplkjhgfdsazxcvbnmQWERTYUIOPLKJHGFDSAZXCVBNM
        //其实这没必要,因为在过滤器中也做了字母和数字的判断,只有一些比较特殊的字符或者比较少的字母 可以写在这里
        MAX_LENGTH = maxLength;
    }

    public EditLetterOrNumberFilter(EditText editText) {
        this(editText, 10);
    }


    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        String sourceText = source.toString();
        String destText = dest.toString();

        //验证删除等按键
        if (TextUtils.isEmpty(sourceText)) {
            return "";
        }

        if (!FilterUtils.isInputOnlyLetterOrNumberFormat(destText + sourceText, MAX_LENGTH)) {
            return "";
        }

        return sourceText;
    }
}
