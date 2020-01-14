package com.zfb.zhifabao.flags.assess;

import android.annotation.SuppressLint;
import android.graphics.Color;
import androidx.annotation.NonNull;
import com.google.android.material.appbar.AppBarLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;
import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.Common;
import com.zfb.zhifabao.common.app.Application;
import com.zfb.zhifabao.common.app.PresenterFragment;
import com.zfb.zhifabao.common.factory.model.api.account.FractionResultModel;
import com.zfb.zhifabao.common.factory.model.api.account.ResModel;
import com.zfb.zhifabao.common.factory.model.api.assess.SubmitResultModel;
import com.zfb.zhifabao.common.factory.model.api.consultation.TestBean;
import com.zfb.zhifabao.common.factory.presenter.assess.SubmitResultContract;
import com.zfb.zhifabao.common.factory.presenter.assess.SubmitResultPresenter;
import com.zfb.zhifabao.common.widget.cyclerview.RecyclerAdapter;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

@SuppressLint("ValidFragment")
public class AssessFragment extends PresenterFragment<SubmitResultContract.Presenter> implements Common.Constance, SubmitResultContract.View {
    @BindView(R.id.appbar)
    AppBarLayout appBarLayout;
    @BindView(R.id.tv_question_body)
    TextView tv_body;
    @BindView(R.id.tv_number)
    TextView tv_number;
    @BindView(R.id.rv_result)
    RecyclerView rv_result;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn_next_question)
    net.qiujuer.genius.ui.widget.Button btnSubmit;
    private Adapter mAdapter;
    private TestBean mTestBean;
    private int mNumber = 1;
    private int mAssessType;

    public AssessFragment(TestBean testBean, int mType) {
        mTestBean = testBean;
        mAssessType = mType;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        mAdapter = new Adapter();
        rv_result.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_result.setAdapter(mAdapter);
        mAdapter.replace(mTestBean.getTitleList().get(mNumber - 1).getOptionList());
        mAdapter.setListener(new RecyclerAdapter.AdapterListenerImpl<TestBean.TitleListBean.OptionListBean>() {
            @Override
            public void onItemClick(RecyclerAdapter.ViewHolder holder, TestBean.TitleListBean.OptionListBean answersBean, int position) {
                List<TestBean.TitleListBean.OptionListBean> beans = mAdapter.getItems();
                for (TestBean.TitleListBean.OptionListBean bean : beans) {
                    if (bean.getContent().equals(answersBean.getContent())) {
                        answersBean.setSelected(true);
                    } else {
                        bean.setSelected(false);
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
        });
        tv_body.setText(mTestBean.getTitleList().get(mNumber - 1).getContent());
        tv_number.setText(String.format("第%d，", mNumber) + String.format("总共%d题", mTestBean.getTitleList().size()));

        switch (mAssessType) {
            case ASSESS_TYPE_LAW:
                setHeadBackground(R.drawable.head_backgroud_lan);
                tvTitle.setText("法律测试");
                break;
            case ASSESS_TYPE_FUNCTION:
                tvTitle.setText("职能测试");
                setHeadBackground(R.drawable.head_backgroud_lv);
                break;
            case ASSESS_TYPE_PSYCHOLOGY:
                setHeadBackground(R.drawable.head_backgroud_hong);
                tvTitle.setText("心理测试");
                break;
            default:
                setHeadBackground(R.drawable.head_backgroud_lv);
                break;
        }
    }

    private void setHeadBackground(int resourceId) {
        Glide.with(getActivity()).load(resourceId).into(new ViewTarget<View, GlideDrawable>(appBarLayout) {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                this.view.setBackground(resource.getCurrent());
            }
        });
    }

    @OnClick(R.id.btn_next_question)
    void nextQuestion() {
        boolean haveSelected = checkHaveSelected();
        if (haveSelected) {
            mNumber++;
            if (mNumber < mTestBean.getTitleList().size()) {
                mAdapter.replace(mTestBean.getTitleList().get(mNumber - 1).getOptionList());
                tv_body.setText(mTestBean.getTitleList().get(mNumber - 1).getContent());
                tv_number.setText(String.format("第%d，", mNumber) + String.format("总共%d题", mTestBean.getTitleList().size()));
            } else {
                btnSubmit.setText("提交");
                btnSubmit.setEnabled(false);
                List<TestBean.TitleListBean> beans = mTestBean.getTitleList();
                SubmitResultModel model = new SubmitResultModel();
                model.setPaperId(mTestBean.getPaperId());
                if (tvTitle.getText().toString().trim().equals("心理测试")) {
                    model.setType("2");
                } else if (tvTitle.getText().toString().trim().equals("职能测试")) {
                    model.setType("1");
                } else if (tvTitle.getText().toString().trim().equals("法律测试")) {
                    model.setType("0");
                }
                List<String> beanList = new ArrayList<>();
                for (TestBean.TitleListBean questionsBean : beans) {
                    for (TestBean.TitleListBean.OptionListBean bean : questionsBean.getOptionList()) {
                        if (bean.isSelected()) {
                          beanList.add(bean.getOptionId());
                        }
                    }
                }
                model.setOptionIdList(beanList);
                model.setEmployee(new SubmitResultModel.EmployeeBean(mTestBean.getEmployeeBean().getIdCard(),mTestBean.getEmployeeBean().getName()));
                mPresenter.submitResult(model);
            }
        } else {
            Application.showToast("请选择答案哦！");
        }

    }

    private boolean checkHaveSelected() {
        List<TestBean.TitleListBean.OptionListBean> beans = mTestBean.getTitleList().get(mNumber - 1).getOptionList();
        for (TestBean.TitleListBean.OptionListBean bean : beans) {
            if (bean.isSelected()) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_functional;
    }

    @OnClick(R.id.im_back)
    void onBack() {
        getActivity().finish();
    }

    @Override
    public void onSubmitComplete(ResModel model) {
        Application.showToast(model.getMsg());
    }

    @Override
    protected SubmitResultContract.Presenter initPresenter() {
        return new SubmitResultPresenter(this);
    }

    class Adapter extends RecyclerAdapter<TestBean.TitleListBean.OptionListBean> {
        @Override
        protected int getItemViewType(int viewtype, TestBean.TitleListBean.OptionListBean answersBean) {
            return R.layout.cell_question_answer;
        }

        @Override
        protected ViewHolder<TestBean.TitleListBean.OptionListBean> onCreateViewHolder(View root, int viewType) {
            return new TestQuestionHolder(root);
        }
    }

    class TestQuestionHolder extends RecyclerAdapter.ViewHolder<TestBean.TitleListBean.OptionListBean> {
        TextView tvAnswer;
        ImageView imSelected;

        public TestQuestionHolder(@NonNull View itemView) {
            super(itemView);
            tvAnswer = itemView.findViewById(R.id.tv_answer);
            imSelected = itemView.findViewById(R.id.im_ok);
        }

        @Override
        protected void onBind(TestBean.TitleListBean.OptionListBean answersBean) {
            tvAnswer.setText(String.format(answersBean.getAnswer() + "%s" + answersBean.getContent(), "："));
            if (answersBean.isSelected()) {
                tvAnswer.setPressed(true);
                tvAnswer.setTextColor(Color.parseColor("#007AF0"));
                imSelected.setVisibility(View.VISIBLE);
            } else {
                tvAnswer.setPressed(false);
                tvAnswer.setTextColor(Color.parseColor("#B2BACB"));
                imSelected.setVisibility(View.INVISIBLE);
            }
        }
    }
}
