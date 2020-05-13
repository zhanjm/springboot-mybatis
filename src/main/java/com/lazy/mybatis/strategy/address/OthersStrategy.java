package com.lazy.mybatis.strategy.address;

import com.lazy.mybatis.common.AddressConstants;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OthersStrategy extends AbstractAddressStrategy {
    private static Map<String,String> map = new HashMap<>();

    static{
        map.put("2",AddressConstants.CITY_TR);
        map.put("3",AddressConstants.COUNTY_TR);
        map.put("4",AddressConstants.TOWN_TR);
    }

    protected OthersStrategy(String level) {
        super(level);
    }

    @Override
    public void analysisDoc(Document doc, String pid,String path) {
        addressList = new ArrayList<>();
        //List<String> list = new ArrayList<>() ;
        clazz = map.get(LEVEL);
        Elements tds = analysisTd(doc);
        int row = tds.size() / 2;
        for(int i=0;i < row;i++){
            String id = tds.get(2 * i).text();
            String href = tds.get(2 * i).select("a").attr("href");
            if(StringUtils.isNotEmpty(href)){
                href = path+href;
            }
            String name = tds.get(2 * i+1).text();
            setAddress(id,name,pid,href);
            //list.add(href);
        }
    }


}
