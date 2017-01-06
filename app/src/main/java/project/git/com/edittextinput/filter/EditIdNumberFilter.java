package project.git.com.edittextinput.filter;

import android.text.InputType;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.EditText;

/**
 * 身份证号,15位和18位(最后为是数字或者字母Xx)
 */
public class EditIdNumberFilter extends BaseFilter {

    public EditIdNumberFilter(EditText editText) {
        super(editText, InputType.TYPE_CLASS_PHONE,"0123456789Xx");
    }



    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        String sourceText = source.toString();
        String destText = dest.toString();

        //验证删除等按键
        if (TextUtils.isEmpty(sourceText)) {
            return "";
        }

        if (!FilterUtils.isInputIdNumberFormat(destText + sourceText)) {
            return "";
        }

        return sourceText;
    }
}
