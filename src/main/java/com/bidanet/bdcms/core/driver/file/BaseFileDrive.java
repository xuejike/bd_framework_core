package com.bidanet.bdcms.core.driver.file;

import com.bidanet.bdcms.core.common.SpringWebTool;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

/**
 * Created by xuejike on 2016-06-03.
 */

public abstract class BaseFileDrive {
    protected String tempPath="/temp";

    protected String getTempPath(){
        String realPath = SpringWebTool.getRealPath(tempPath);
        File file = new File(realPath);
        if (!file.exists()){
            file.mkdirs();
        }
        return realPath;
    }

    protected String uploadTempFile(InputStream in,String name) throws IOException {
        File file = new File(getTempPath() + "/" + new Date().getTime()+name);
        FileUtils.copyInputStreamToFile(in,file);
        return file.getPath();
    }

    protected String createRandomName(String fileName) {
        String extName = getFileExt(fileName);
        byte[] bytes = Base64.encodeBase64(UUID.randomUUID().toString().getBytes());
        String s = new String(bytes);
        return s + extName;
    }

    private String getFileExt(String fileName) {
        int i = fileName.lastIndexOf(".");
        String extName="";
        if (i>=0){
            extName = fileName.substring(i);
        }
        return extName;
    }
}
