package cn.ucai.superwechat.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import cn.ucai.superwechat.SuperWeChatModel;
import cn.ucai.superwechat.R;
import com.hyphenate.easeui.widget.EaseTitleBar;

public class SetServersActivity extends BaseActivity {

    EditText restEdit;
    EditText imEdit;
    EaseTitleBar titleBar;

    SuperWeChatModel superWeChatModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_servers);

        restEdit = (EditText) findViewById(R.id.et_rest);
        imEdit = (EditText) findViewById(R.id.et_im);
        titleBar = (EaseTitleBar) findViewById(R.id.title_bar);

        superWeChatModel = new SuperWeChatModel(this);
        if(superWeChatModel.getRestServer() != null)
            restEdit.setText(superWeChatModel.getRestServer());
        if(superWeChatModel.getIMServer() != null)
            imEdit.setText(superWeChatModel.getIMServer());
        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        if(!TextUtils.isEmpty(restEdit.getText()))
            superWeChatModel.setRestServer(restEdit.getText().toString());
        if(!TextUtils.isEmpty(imEdit.getText()))
            superWeChatModel.setIMServer(imEdit.getText().toString());
        super.onBackPressed();
    }
}
