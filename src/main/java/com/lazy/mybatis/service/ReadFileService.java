package com.lazy.mybatis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class ReadFileService {

    public void readFile(){

        File file = new File("F:\\zjmdownload");

        //boolean directory = file.isDirectory();
        readFile(file);

    }

    public void readFile(File file){
        if(file.isDirectory()){
            log.info("=========="+file.getPath());
            File[] files = file.listFiles();
            BlockingQueue<Runnable> bq = new ArrayBlockingQueue<Runnable>(10000);// 创建数组型缓冲等待队列
            // ThreadPoolExecutor:创建自定义线程池，池中保存的线程数为3，允许最大的线程数为6
            ThreadPoolExecutor tpe = new ThreadPoolExecutor(10, 20, 50, TimeUnit.MILLISECONDS, bq);
            for (final File file1 : files) {
                //readFile(file1);
                /*tpe.execute(new Runnable() {
                    @Override
                    public void run() {
                        readFile(file1);
                    }
                });*/
                //相当于重写runnable接口的run方法
                tpe.execute(()-> readFile(file1) );
            }
        }else{
            //log.info("=========="+file.getPath());
        }
    }


}
