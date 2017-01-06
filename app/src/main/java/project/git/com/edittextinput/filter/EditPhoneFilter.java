package project.git.com.edittextinput.filter;

import android.text.InputType;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

/**
 * 电话过滤器
 */
public class EditPhoneFilter extends BaseFilter {


    public EditPhoneFilter(EditText editText) {
        super(editText, InputType.TYPE_CLASS_NUMBER, "0123456789");
    }

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
        return sourceText;
    }

}
