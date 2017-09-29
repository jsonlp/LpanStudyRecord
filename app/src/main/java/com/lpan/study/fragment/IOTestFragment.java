package com.lpan.study.fragment;

import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lpan.study.context.AppContext;
import com.lpan.study.fragment.base.BaseFragment;
import com.lpan.study.utils.FileUtils;
import com.test.lpanstudyrecord.R;

import java.io.File;

/**
 * Created by lpan on 2017/1/16.
 */

public class IOTestFragment extends BaseFragment implements View.OnClickListener {

    private static final String CACHE_FILE_NAME = "friend_list.json";

    private static final String CONTENT = "原标题：中方警告特朗普：一个中国原则不可谈判\n" +
            "\n" +
            "核心提示：中国外交部发言人陆慷说，一个中国原则是中美关系的政治基础，是不可谈判的。\n" +
            "\n" +
            "参考消息网1月16日报道 美媒称，在美国当选总统特朗普声称一个中国政策——将近40年来，该政策一直是中美关系的基础——可以谈判后，中国外交部几乎毫不掩饰地对其进行了斥责。\n" +
            "\n" +
            "据美国《华尔街日报》网站1月14日报道，特朗普13日在接受该报记者采访时说，这一长期政策——根据这一政策，美国同意在外交上不承认台湾——是悬而未决的。特朗普说，他不承诺奉行这一政策，除非看到北京在贸易和汇率等问题上取得进步。\n" +
            "\n" +
            "他说：“一切都在谈判之中，包括一个中国政策。”\n" +
            "\n" +
            "中国外交部发言人陆慷说，一个中国原则是中美关系的政治基础，是不可谈判的。";

    private TextView mTextView1, mTextView2, mTextView3;

    private EditText mEditText;

    private File mFile;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_io_test;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mTextView1 = (TextView) view.findViewById(R.id.text1);
        mTextView2 = (TextView) view.findViewById(R.id.text2);
        mTextView3 = (TextView) view.findViewById(R.id.text3);
        mEditText= (EditText) view.findViewById(R.id.edit1);


        mTextView1.setOnClickListener(this);
        mTextView2.setOnClickListener(this);
        mEditText.setOnClickListener(this);


    }

    @Override
    protected void initData() {
        super.initData();

//        FileUtils.writeTextFile(CONTENT,getCacheFile().getAbsolutePath());
//        FileUtils.saveContent(CONTENT, CACHE_FILE_NAME);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text1:
                String s = FileUtils.readTextFile(getCacheFile().getAbsolutePath());
                mTextView3.setText(s);
                break;

            case R.id.text2:

                break;

            case R.id.edit1:
                Toast.makeText(AppContext.getContext(),"1111",Toast.LENGTH_SHORT).show();
                break;

        }
    }

    public File getCacheFile() {
        if (mFile == null) {
            mFile = new File(Environment.getExternalStorageDirectory(), "/" + "Jiemoapp" + "/" + "videos/" + CACHE_FILE_NAME);
            mFile.setWritable(true);
        }
        return mFile;
    }

}
