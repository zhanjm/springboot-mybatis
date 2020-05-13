package com.lazy.mybatis.strategy.address;

import com.lazy.mybatis.common.AddressConstants;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * 采集省份的策略
 */
public class ProvinceStrategy extends AbstractAddressStrategy {

    public ProvinceStrategy(String level){
        super(level);
    }

    @Override
    public void analysisDoc(Document doc, String pid,String path) {
        addressList = new ArrayList<>();
        //List<String> list = new ArrayList<>() ;
        clazz = AddressConstants.PROVINCE_TR;
        Elements tds = analysisTd(doc);
        tds.forEach(td->{
            String text = td.text();
            if(StringUtils.isNotEmpty(text)){
                String href = td.select("a").attr("href");
                String id = href.substring(0, href.indexOf("."))+"0000000000";
                if(StringUtils.isNotEmpty(href)){
                    href = path+href;
                }
                setAddress(id,text,pid,href);
                //list.add(href);
            }
        });
    }
}
