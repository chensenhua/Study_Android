package ac692x_case.jieli.com.myapplication;

import ac692x_case.jieli.com.annotlib.AutoParcel;
import ac692x_case.jieli.com.annotlib.AutoSingleton;
import ac692x_case.jieli.com.annotlib.ParamAnnot;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_text)
    TextView tvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Log.e("sen", new FirstBean().toString());


    }

    @OnClick(R.id.tv_text)
    public void onViewClicked() {
    }

    @AutoParcel
    public void test(){

    }

    @ParamAnnot("param annot")
    public void test1(){

    }
}
