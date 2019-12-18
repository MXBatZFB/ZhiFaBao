package com.zfb.zhifabao.common.widget.cyclerview;


import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zfb.zhifabao.common.R;
import com.zfb.zhifabao.common.model.FileInfo;
import com.zfb.zhifabao.common.utils.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class FileItemView extends RecyclerView {
    private static final int LOADER_ID = 0X0200;
    private FileItemView.Adapter mAdapter = new FileItemView.Adapter();
    private LoaderCallback mLoaderCallback = new LoaderCallback();
    private ProgressDialog mProgressDialog;

    public FileItemView(@NonNull Context context) {
        super(context);
        init();
    }

    public FileItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FileItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setLayoutManager(new LinearLayoutManager(getContext()));
        setAdapter(mAdapter);
    }

    public void setup(LoaderManager loaderManager, ProgressDialog progressDialog) {
        loaderManager.initLoader(LOADER_ID, null, mLoaderCallback);
        mProgressDialog = progressDialog;
    }

    private void updateSource(List<FileInfo> infoList) {
        mAdapter.replace(infoList);
        mProgressDialog.dismiss();
    }

    private class Adapter extends RecyclerAdapter<FileInfo> {

        @Override
        protected int getItemViewType(int viewType, FileInfo fileInfo) {
            return R.layout.cell_folder_file_info_item;
        }

        @Override
        protected ViewHolder<FileInfo> onCreateViewHolder(View root, int viewType) {
            return new FileItemView.ViewHolder(root);
        }
    }

    private class ViewHolder extends RecyclerAdapter.ViewHolder<FileInfo> {
        TextView tv_content;
        TextView tv_size;
        TextView tv_time;
        ImageView iv_cover;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_content = itemView.findViewById(R.id.tv_content);
            tv_size = itemView.findViewById(R.id.tv_size);
            tv_time = itemView.findViewById(R.id.tv_time);
            iv_cover = itemView.findViewById(R.id.iv_cover);
        }

        @Override
        protected void onBind(FileInfo fileInfo) {
            tv_content.setText(fileInfo.getFileName());
            tv_size.setText(FileUtil.FormatFileSize(fileInfo.getFileSize()));
            tv_time.setText(fileInfo.getTime());
            Glide.with(getContext()).load(R.mipmap.word).fitCenter().into(iv_cover);
        }
    }

    private class LoaderCallback implements LoaderManager.LoaderCallbacks<Cursor> {

        private final String[] FILE_PROJECTION = new String[]{
                MediaStore.Files.FileColumns._ID,
                MediaStore.Files.FileColumns.MIME_TYPE,
                MediaStore.Files.FileColumns.SIZE,
                MediaStore.Files.FileColumns.DATE_MODIFIED,
                MediaStore.Files.FileColumns.DATA
        };


        @NonNull
        @Override
        public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle bundle) {
            String select = "(" + MediaStore.Files.FileColumns.DATA + " LIKE '%.doc'" + " or " + MediaStore.Files.FileColumns.DATA + " LIKE '%.docx'" + ")";
            if (id == LOADER_ID) {
                return new CursorLoader(getContext(),
                        MediaStore.Files.getContentUri("external"),
                        FILE_PROJECTION,
                        select,
                        null,
                        null);
            }
            //noinspection ConstantConditions
            return null;
        }

        @Override
        public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {

            ArrayList<FileInfo> fileInfoList = new ArrayList<>();
            if (data != null) {
                Log.e("delong", "ColumnCount>>>>>>>>>>>>>" + data.getColumnCount());
                int count = data.getCount();
                if (count > 0) {
                    data.moveToFirst();
                    int columnIndexOrThrow_DATA = data.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA);
                    do {
                        String filePath = data.getString(columnIndexOrThrow_DATA);
                        FileInfo fileInfo = FileUtil.getFileInfoFromFile(new File(filePath));
                        fileInfoList.add(fileInfo);
                    } while (data.moveToNext());
                }
            }
            updateSource(fileInfoList);
        }

        @Override
        public void onLoaderReset(@NonNull Loader<Cursor> loader) {
            updateSource(null);
        }
    }
}
