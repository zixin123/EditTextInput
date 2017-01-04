package project.git.com.edittextinput.filter;

import android.text.InputFilter;
import android.text.TextUtils;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yang on 2017/1/4.
 */

public class FilterUtils {

    /**
     * 给EditFilter设置过滤器
     *
     * @param editText
     * @param filter
     */
    public static void setFilter(EditText editText, BaseFilter filter) {
        InputFilter[] filters = {filter};
        editText.setFilters(filters);
    }

    private static boolean match(String regex, String str) {
        if (TextUtils.isEmpty(str))
            return false;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 判断输入时候,监听电话号码格式
     *
     * @param phoneNumber
     * @return
     */
    public static boolean isInputPhoneFormat(String phoneNumber) {
        String regular = "1[0-9]{0,10}$";
        return match(regular, phoneNumber);
    }

    /**
     * 判断输入时候,监听金额格式
     *
     * @param money
     * @return
     */
    public static boolean isMoneyFormat(String money) {
        String regular = "([\u4e00-\u9fa5]*)|([a-zA-Z]*)";
        return match(regular, money);
    }

    /**
     * 是否是纯中文
     *
     * @param name
     * @return
     */
    public static boolean isNameFormat(String name) {
        String regular = "[\u4e00-\u9fa5]*";
        return match(regular, name);
    }
}
