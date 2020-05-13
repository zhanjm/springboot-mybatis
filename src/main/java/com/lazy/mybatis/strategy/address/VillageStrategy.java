package com.lazy.mybatis.strategy.address;

import com.lazy.mybatis.common.AddressConstants;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class VillageStrategy extends AbstractAddressStrategy {

    public VillageStrategy(String level){
        super(level);
    }



    @Override
    public void analysisDoc(Document doc, String pid,String path) {
        addressList = new ArrayList<>();
        clazz = AddressConstants.VILLAGE_TR;
        Elements tds = analysisTd(doc);
        int row = tds.size() / 3;
        for(int i=0;i < row;i++){
            String id = tds.get(3 * i).text();
            String name = tds.get(3 * i+2).text();
            setAddress(id,name,pid,null);
        }
    }
}
