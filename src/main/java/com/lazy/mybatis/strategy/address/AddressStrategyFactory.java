package com.lazy.mybatis.strategy.address;

import com.lazy.mybatis.common.AddressConstants;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

public class AddressStrategyFactory {

    private static Map<String,IAddressStrategy> strategyMap = new HashMap<>();


    static{
        strategyMap.put(AddressConstants.PROVINCE_TABLE,new ProvinceStrategy(AddressConstants.PROVINCE_LV));
        strategyMap.put(AddressConstants.CITY_TABLE,new OthersStrategy(AddressConstants.CITYE_LV));
        strategyMap.put(AddressConstants.COUNTY_TABLE,new OthersStrategy(AddressConstants.COUNTY_LV));
        strategyMap.put(AddressConstants.TOWN_TABLE,new OthersStrategy(AddressConstants.TOWN_LV));
        strategyMap.put(AddressConstants.VILLAGE_TABLE,new VillageStrategy(AddressConstants.VILLAGE_LV));
    }


    public static IAddressStrategy getStrategy(Document doc){
        for (Map.Entry<String, IAddressStrategy> entry : strategyMap.entrySet()) {
            //if(doc.hasClass(entry.getKey()))
            Elements tables = doc.select("table");
            for (Element table : tables) {
                if(table.hasClass(entry.getKey()))
                    return entry.getValue();
            }
        }
        //throw new IllegalArgumentException("没有对应的策略"+doc);
        return null;
    }





}
