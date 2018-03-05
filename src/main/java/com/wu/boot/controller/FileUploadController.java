package com.wu.boot.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class FileUploadController {

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public @ResponseBody String upload(MultipartFile file) throws Exception {
        try {
            FileUtils.writeByteArrayToFile(new File("e:/upload/" + file.getOriginalFilename()),
                    file.getBytes()); //2
            return "ok";
        } catch (IOException e) {
            e.printStackTrace();
            return "wrong";
        }
    }
    @RequestMapping(value = "/uploads",method = RequestMethod.POST)
    public @ResponseBody String uploads(MultipartFile[] files) throws Exception {
        try {
            if(files !=null && files.length>0){
                for(MultipartFile file:files){
                    FileUtils.writeByteArrayToFile(new File("e:/upload/" + file.getOriginalFilename()),
                            file.getBytes()); //2
                }
            }
            return "ok";
        } catch (IOException e) {
            e.printStackTrace();
            return "wrong";
        }
    }
}
