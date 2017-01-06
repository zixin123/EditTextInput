package project.git.com.edittextinput.filter;

import android.text.InputType;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.EditText;

/**
 * Created by yang on 16/3/30.
 * 密码过滤器,英文,数字,符号,6-20位
 */
public class EditPasswordFilter extends BaseFilter {

    private int MAX_LENGTH = 20;

    public EditPasswordFilter(EditText editText) {
        super(editText, InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }


    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        String sourceText = source.toString();
        String destText = dest.toString();

        //验证删除等按键
        if (TextUtils.isEmpty(sourceText)) {
            return "";
        }
        if (!FilterUtils.isInputPasswordFormat(destText + sourceText)) {
            return "";
        }

        if ((destText + sourceText).length() > MAX_LENGTH) {
            return "";
        }
        return dest.subSequence(dstart, dend) + sourceText;
    }
}
