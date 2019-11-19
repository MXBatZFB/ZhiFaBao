package com.zfb.zhifabao.common.factory.data;

import com.zfb.zhifabao.common.factory.Factory;
import com.zfb.zhifabao.common.factory.R;
import com.zfb.zhifabao.common.model.FileInfo;
import com.zfb.zhifabao.common.utils.FileUtil;

import java.io.File;
import java.util.LinkedList;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class LocalHelper {

    /**
     * 非递归
     *
     * @param path
     */
    public void scanDirNoRecursion(String path, DataSource.Callback callback) {
        LinkedList list = new LinkedList();
        LinkedList<FileInfo> fileInfoLinkedList = new LinkedList<>();
        File dir = new File(path);
        File[] file = dir.listFiles();
        if (file == null) return;
        for (int i = 0; i < file.length; i++) {
            if (file[i].isDirectory())
                list.add(file[i]);
            else {
                System.out.println(file[i].getAbsolutePath());
            }
        }
        File tmp;
        while (!list.isEmpty()) {
            tmp = (File) list.removeFirst();//首个目录
            if (tmp.isDirectory()) {
                file = tmp.listFiles();
                if (file == null)
                    continue;
                for (int i = 0; i < file.length; i++) {
                    if (file[i].isDirectory())
                        list.add(file[i]);//目录则加入目录列表，关键
                    else {
                        if (file[i].getName().endsWith(".doc") || file[i].getName().endsWith(".docx")) {
                            FileInfo document = FileUtil.getFileInfoFromFile(new File(file[i].getAbsolutePath()));
                            fileInfoLinkedList.add(document);
                        }
                    }
                }
            } else {
                System.out.println(tmp);
            }
        }
        if (fileInfoLinkedList.size() > 0) {
            callback.onDataLoaded(fileInfoLinkedList);
        } else {
            callback.onDtaNotAvailable(Factory.app().getString(R.string.data_error));
        }
    }
}
