package com.wu.boot.converter;

import com.alibaba.fastjson.JSON;
import com.wu.boot.dto.MessageDto;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class MyMessageConverter extends AbstractHttpMessageConverter<Object> {
    public MyMessageConverter(){
        super(new MediaType("application", "wucq",Charset.forName("UTF-8")));//2
    }
    @Override
    protected boolean supports(Class<?> aClass) {
//        return MessageDto.class.isAssignableFrom(aClass) || User.class.isAssignableFrom(aClass);
        return true;
    }

    @Override
    protected Object readInternal(Class<? extends Object> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        String temp = StreamUtils.copyToString(httpInputMessage.getBody(), Charset.forName("UTF-8"));
        String[] tempArr = temp.split("-");
        if( MessageDto.class.isAssignableFrom(aClass)){
            return new MessageDto(tempArr[0], tempArr[1]);
        }
        Object object=JSON.parseObject(temp,aClass);
        return object;
    }

    @Override
    protected void writeInternal(Object object, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        if(object instanceof MessageDto){
            MessageDto messageDto1=(MessageDto)object;
            String out = messageDto1.getCode() + "-"
                    + messageDto1.getMessage();
            StreamUtils.copy(out, Charset.forName("UTF-8"), httpOutputMessage.getBody());
        }

        String string= JSON.toJSONString(object);
        httpOutputMessage.getBody().write(string.getBytes());
    }
}
