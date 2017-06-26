package com.bidanet.bdcms.core.messageConverter;


import com.bidanet.bdcms.core.bean.FileOutput;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by avatek on 2015/12/16 0016.
 */
public class FileOutputMessageConverter extends AbstractHttpMessageConverter<FileOutput> {
    public FileOutputMessageConverter() {
        super(MediaType.ALL);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        boolean b = FileOutput.class == clazz;
        return b;
    }

    @Override
    protected FileOutput readInternal(Class<? extends FileOutput> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {

        return new FileOutput("不支持输入");
    }

    @Override
    protected void writeInternal(FileOutput fileOutput, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        InputStream inputStream = fileOutput.getInputStream();

        if (inputStream!=null){
            outputMessage.getHeaders().set("Content-Disposition","filename="+fileOutput.getFilename());
            outputMessage.getHeaders().set("Content-Type",fileOutput.getContentType());
//            IOUtils.toByteArray()
            outputMessage.getBody().write(IOUtils.toByteArray(inputStream));
//            HttpHeaders headers = outputMessage.getHeaders();
        }else{
            outputMessage.getBody().write("文件不存在".getBytes() );
        }
    }
}
