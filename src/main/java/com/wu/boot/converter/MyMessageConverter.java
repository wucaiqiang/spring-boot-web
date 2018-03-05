package com.wu.boot.converter;

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
import java.util.ArrayList;
import java.util.List;

public class MyMessageConverter extends AbstractHttpMessageConverter<MessageDto> {
    private final List<Charset> availableCharsets;
    public MyMessageConverter(){
        super(new MediaType("application", "wucq",Charset.forName("UTF-8")),MediaType.ALL);//2
        this.availableCharsets = new ArrayList<Charset>(Charset.availableCharsets().values());
    }
    @Override
    protected boolean supports(Class<?> aClass) {
        return MessageDto.class.isAssignableFrom(aClass);
    }

    @Override
    protected MessageDto readInternal(Class<? extends MessageDto> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        String temp = StreamUtils.copyToString(httpInputMessage.getBody(), Charset.forName("UTF-8"));
        String[] tempArr = temp.split("-");
        return new MessageDto(tempArr[0], tempArr[1]);
    }

    @Override
    protected void writeInternal(MessageDto messageDto, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        String out = messageDto.getCode() + "-"
                + messageDto.getMessage();
        StreamUtils.copy(out, Charset.forName("UTF-8"), httpOutputMessage.getBody());
    }
}
