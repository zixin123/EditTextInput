package project.git.com.edittextinput.filter;

import android.text.InputType;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.EditText;

/**
 * 邮箱
 */
public class EditEmailFilter extends BaseFilter {

    public EditEmailFilter(EditText editText) {
        super(editText, InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }


    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        String sourceText = source.toString();
        String destText = dest.toString();

        //验证删除等按键
        if (TextUtils.isEmpty(sourceText)) {
            return "";
        }

        if (!FilterUtils.isInputEmailFormat(destText + sourceText)) {
            return "";
        }

        return sourceText;
    }
}
