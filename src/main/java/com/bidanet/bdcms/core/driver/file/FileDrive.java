package com.bidanet.bdcms.core.driver.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by xuejike on 2016-05-31.
 */
public interface FileDrive {
    /**
     * 上传文件，进行保存
     * @param file  需要保存的文件
     * @param saveName 另存为名称
     * @return 返回URL路径
     */
    String upload(File file, String saveName) throws IOException;

    /**
     * 上传文件，进行保存
     * @param file  需要保存的文件
     * @return 返回URL路径
     */
    String upload(File file) throws IOException;

    String upload(InputStream in, String oldName) throws IOException;

    String uploadToFileName(InputStream in, String newName) throws IOException;

    /**
     * 上传文件图片，进行保存
     * @param file  需要保存的文件
     * @param saveName 另存为名称
     * @return 返回URL路径
     */
    String uploadImg(File file, String saveName) throws IOException;
    /**
     * 上传文件图片，进行保存
     * @param file  需要保存的文件
     * @return 返回URL路径
     */
    String uploadImg(File file) throws IOException;

    String uploadImgToFileName(InputStream in, String saveName) throws IOException;

    String uploadImg(InputStream in, String oldName) throws IOException;

}
