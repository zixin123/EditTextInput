package project.git.com.edittextinput.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import project.git.com.edittextinput.R;
import project.git.com.edittextinput.filter.EditMoneyFilter;
import project.git.com.edittextinput.filter.EditNameFilter;
import project.git.com.edittextinput.filter.EditPhoneFilter;
import project.git.com.edittextinput.filter.FilterUtils;

/**
 * Created by yang on 2017/1/4.
 */

public class EditTextView extends LinearLayout {
    public EditTextView(Context context) {
        this(context, null);
    }

    public EditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.EditTextView);

        int type = typedArray.getInteger(R.styleable.EditTextView_Type, -1);
        View view = View.inflate(context, R.layout.view_edittext, this);
        EditText view_edittext = (EditText) view.findViewById(R.id.view_edittext);
        if (type != -1) {
            switch (type) {
                case 1://电话号码
                    FilterUtils.setFilter(view_edittext, new EditPhoneFilter(view_edittext));
                    break;
                case 2://金额1,不限制最大值,保留两位小数
                    FilterUtils.setFilter(view_edittext, new EditMoneyFilter(view_edittext, 2));
                    break;
                case 3://金额2,限制最大值 10000,保留三位小数
                    FilterUtils.setFilter(view_edittext, new EditMoneyFilter(view_edittext, 3, 10000));
                    break;
                case 4://名字
                    FilterUtils.setFilter(view_edittext, new EditNameFilter(view_edittext));
//                    InputFilter[] inputFilter = new InputFilter[]{new EditNameFilter(view_edittext)};
//                    view_edittext.setFilters(inputFilter);
                    break;
            }
        }
        typedArray.recycle();
    }
}
