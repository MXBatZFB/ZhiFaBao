package com.zfb.zhifabao.common.widget.cyclerview;

public interface AdapterCallback<Data> {
  void updata(Data data, RecyclerAdapter.ViewHolder<Data> holder);
}
