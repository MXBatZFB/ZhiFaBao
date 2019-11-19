package com.zfb.zhifabao.flags.assess;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.zfb.zhifabao.common.factory.model.api.assess.SubmitResultModel;
import com.zfb.zhifabao.common.factory.model.api.consultation.TestBean;
import com.zfb.zhifabao.common.factory.model.api.consultation.TestBean.QuestionsBean.QuestionBean.AnswersBean;
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
        mAdapter.replace(mTestBean.getQuestions().get(mNumber - 1).getQuestion().getAnswers());
        mAdapter.setListener(new RecyclerAdapter.AdapterListenerImpl<AnswersBean>() {
            @Override
            public void onItemClick(RecyclerAdapter.ViewHolder holder, AnswersBean answersBean, int position) {
                List<AnswersBean> beans = mAdapter.getItems();
                for (AnswersBean bean : beans) {
                    if (bean.getAnswerContent().equals(answersBean.getAnswerContent())) {
                        answersBean.setSelected(true);
                    } else {
                        bean.setSelected(false);
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
        });
        tv_body.setText(mTestBean.getQuestions().get(mNumber - 1).getQuestion().getQuestionBody());
        tv_number.setText(String.format("第%d，", mNumber) + String.format("总共%d题", mTestBean.getQuestions().size()));

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
            if (mNumber <= mTestBean.getQuestions().size()) {
                mAdapter.replace(mTestBean.getQuestions().get(mNumber - 1).getQuestion().getAnswers());
                tv_body.setText(mTestBean.getQuestions().get(mNumber - 1).getQuestion().getQuestionBody());
                tv_number.setText(String.format("第%d，", mNumber) + String.format("总共%d题", mTestBean.getQuestions().size()));
            } else {
                btnSubmit.setText("提交");
                btnSubmit.setEnabled(false);
                List<TestBean.QuestionsBean> beans = mTestBean.getQuestions();
                SubmitResultModel model = new SubmitResultModel();
                if (tvTitle.getText().toString().trim().equals("心理测试")) {
                    model.setCeshitype("XinLiCeShi");
                } else if (tvTitle.getText().toString().trim().equals("职能测试")) {
                    model.setCeshitype("BaoXianCongYeRenYuanCeShi");
                } else if (tvTitle.getText().toString().trim().equals("法律测试")) {
                    model.setCeshitype("JiBenFaCeShi");
                }
                List<SubmitResultModel.AnswersBean> beanList = new ArrayList<>();
                for (TestBean.QuestionsBean questionsBean : beans) {
                    SubmitResultModel.AnswersBean answersBean = new SubmitResultModel.AnswersBean();
                    answersBean.setQuestionNumber(questionsBean.getQuestion().getQuestionNumber());
                    for (AnswersBean bean : questionsBean.getQuestion().getAnswers()) {
                        if (bean.isSelected()) {
                            answersBean.setAnswerName(bean.getAnswerName());
                        }
                    }
                    beanList.add(answersBean);
                }
                model.setAnswers(beanList);
                mPresenter.submitResult(model);
            }
        } else {
            Application.showToast("请选择答案哦！");
        }

    }

    private boolean checkHaveSelected() {
        List<AnswersBean> beans = mTestBean.getQuestions().get(mNumber - 1).getQuestion().getAnswers();
        for (AnswersBean bean : beans) {
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
    public void onSubmitComplete(FractionResultModel model) {
        Toast.makeText(getActivity(), model.getSum(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected SubmitResultContract.Presenter initPresenter() {
        return new SubmitResultPresenter(this);
    }

    class Adapter extends RecyclerAdapter<AnswersBean> {
        @Override
        protected int getItemViewType(int viewtype, AnswersBean answersBean) {
            return R.layout.cell_question_answer;
        }

        @Override
        protected ViewHolder<AnswersBean> onCreateViewHolder(View root, int viewType) {
            return new TestQuestionHolder(root);
        }
    }

    class TestQuestionHolder extends RecyclerAdapter.ViewHolder<AnswersBean> {
        TextView tvAnswer;
        ImageView imSelected;

        public TestQuestionHolder(@NonNull View itemView) {
            super(itemView);
            tvAnswer = itemView.findViewById(R.id.tv_answer);
            imSelected = itemView.findViewById(R.id.im_ok);
        }

        @Override
        protected void onBind(AnswersBean answersBean) {
            tvAnswer.setText(String.format(answersBean.getAnswerName() + "%s" + answersBean.getAnswerContent(), "："));
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
