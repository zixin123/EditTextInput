package project.git.com.edittextinput.filter;

import android.text.InputType;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.EditText;

/**
 *名字过滤器,只能由汉子组成.
 */
public class EditNameFilter extends BaseFilter {

    private int MAX_LENGTH = 5;//默认不长度不大于5

    public EditNameFilter(EditText editText, int length) {
        super(editText, InputType.TYPE_CLASS_TEXT);
        MAX_LENGTH = length;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        String sourceText = source.toString();
        String destText = dest.toString();

        //验证删除等按键
        if (TextUtils.isEmpty(sourceText)) {
            return "";
        }

        if (!FilterUtils.isInputNameFormat(destText + sourceText, MAX_LENGTH)) {
            return "";
        }
        return sourceText;
    }
}
