package project.git.com.edittextinput.filter;

import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
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
     * 输入时,监听电话号码格式
     *
     * @param phoneNumber
     * @return
     */
    public static boolean isInputPhoneFormat(String phoneNumber) {
        String regular = "^1[0-9]{0,10}$";
        return match(regular, phoneNumber);
    }

    /**
     * 电话号码正则表达式
     *
     * @param phoneNumber
     * @return
     */
    public static boolean regularPhone(String phoneNumber) {
        String regular = "^1[0-9]{10}$";
        return match(regular, phoneNumber);
    }

    /**
     * 输入时,监听金额格式
     *
     * @param money
     * @param count 小数点后省略几位
     * @return
     */
    public static boolean isInputMoneyFormat(String money, int count) {
        StringBuffer sb = new StringBuffer();
        sb.append("^");
        //第一种,0开头
        sb.append("(0(\\.\\d{0,").append(count).append("})?)").append("|");
        //第二种非零开头
        sb.append("([1-9]\\d*(\\.\\d{0,").append(count).append("})?)");
        sb.append("$");
        String regular = sb.toString();
        boolean result = match(regular, money);
        Log.d("FilterUtils", "money=" + money + "-->" + result + "-->" + regular);
        return result;
    }

    /**
     * 金额正则表达式
     *
     * @param money
     * @param count
     * @return
     */
    public static boolean regularMoney(String money, int count) {
        StringBuffer sb = new StringBuffer();
        sb.append("^");
        //第一种,0开头
        sb.append("(0(\\.\\d{1,").append(count).append("})?)").append("|");
        //第二种非零开头
        sb.append("([1-9]\\d*(\\.\\d{1,").append(count).append("})?)");
        sb.append("$");
        String regular = sb.toString();
        boolean result = match(regular, money);
        Log.d("FilterUtils", "money=" + money + "-->" + result + "-->" + regular);
        return result;
    }


    /**
     * 输入时,是否是纯中文
     *
     * @param name
     * @return
     */
    public static boolean isInputNameFormat(String name) {
        String regular = "^[\u4e00-\u9fa5]*$";
        return match(regular, name);
    }

    /**
     * 输入时,是否仅字母或数字组合
     *
     * @param name
     * @return
     */
    public static boolean isInputOnlyLetterOrNumberFormat(String name) {
        String regular = "^[a-zA-Z0-9]+$";
        return match(regular, name);
    }

    /**
     * 输入时,密码格式
     *
     * @param str
     * @return
     */
    public static boolean isInputPasswordFormat(String str) {
        String regular = "[a-zA-Z0-9!@#$%^&*()-+=~:()><,.'?\"]+$";
        return match(regular, str);
    }

    /**
     * 输入时,邮箱格式监听
     *
     * @param str
     * @return
     */
    public static boolean isInputEmailFormat(String str) {
        //my my@  my@qq my@qq. my@qq.com
        String regular = "^(\\w+)|(\\w+@)|(\\w+@\\w+)|(\\w+@\\w+.)|(\\w+@\\w+.\\w+)$";
        return match(regular, str);
    }

    /**
     * 邮箱的正则表达式
     *
     * @param str
     * @return
     */
    public static boolean regularEmail(String str) {
        // my@qq.com
        String regular = "^\\w+@\\w+.\\w+$";
        return match(regular, str);
    }

    /**
     * 输入时,身份证判断, 身份证分为15位和18位(最后一位是数字或者字母X,x)
     *
     * @param idNumber
     * @return
     */
    public static boolean isInputIdNumberFormat(String idNumber) {
        String regular = "^(\\d{0,15})$|^(\\d{0,17})$|^(\\d{17}[\\d|X|x])$";
        return match(regular, idNumber);
    }

    /**
     * 身份证正则表达式
     *
     * @param idNumber
     * @return
     */
    public static boolean regularIdNumber(String idNumber) {
        String regular = "^(\\d{15})$|^(\\d{17}[\\d|X|x])$";
        return match(regular, idNumber);
    }


}
