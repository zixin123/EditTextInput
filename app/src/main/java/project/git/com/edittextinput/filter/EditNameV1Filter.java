package project.git.com.edittextinput.filter;

import android.text.InputType;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.EditText;

/**
 * 名字过滤器,只能由汉子组成.
 */
public class EditNameV1Filter extends BaseFilter {

    private int MAX_LENGTH = 5;//默认不长度不大于5

    public EditNameV1Filter(EditText editText, int length) {
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

        //判断是否纯中文
        if (FilterUtils.isOnlyChineseFormat(sourceText)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(destText).append(sourceText);
            if (stringBuffer.toString().length() > MAX_LENGTH) {
                String result = stringBuffer.toString().substring(dend, MAX_LENGTH);
                return result;
            } else {
                return sourceText;
            }
        } else {
            return "";
        }
    }
}
