package com.lazy.mybatis.controller;

import com.lazy.mybatis.service.ReadFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@Api(tags = "用多线程读取文件")
public class ReadFileController {

    @Autowired
    private ReadFileService readFileService;

    @RequestMapping(value = "/readFile",method = RequestMethod.GET)
    @ApiOperation(value="多线程读取文件", notes="多线程读取文件")
    public void readFile(){
        readFileService.readFile();

    }


    @RequestMapping(value = "/downloadFile",method = RequestMethod.GET)
    @ApiOperation(value="多线程读取文件", notes="多线程读取文件")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response,String z,String y)  {
        //response.setCharacterEncoding("UTF-8");
        //response.setContentType("multipart/form-data");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(new Date());
        //request.getHeader("USER-AGENT");
        String downloadName="导出的文件"+dateStr+".xlsx";
        try {
            downloadName = new String(downloadName.getBytes("GB2312"), "iso-8859-1");// 得到:美女.jpg
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("content-type", "text/plain");
        response.setHeader("content-type", "application/x-msdownload;");
        response.setHeader("Content-Disposition", "attachment;fileName=" + downloadName);
        response.setContentType("text/plain; charset=utf-8");
        File file = new File("F:\\导出的文件.xlsx");
        ServletOutputStream os =null;
        InputStream is = null;
        try {
            os = response.getOutputStream();
            is = new BufferedInputStream(new FileInputStream(file));
            byte[] bytes = new byte[1024];
            int len = 0;
            if((len=is.read(bytes))>0){
                os.write(bytes,0,len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
                try {
                    if(is!=null){
                    is.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            try {
                if(os!=null){
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        //readFileService.readFile();

    }


}
