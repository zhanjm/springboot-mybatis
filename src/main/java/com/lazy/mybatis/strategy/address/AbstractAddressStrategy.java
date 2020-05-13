package com.lazy.mybatis.strategy.address;

import com.lazy.mybatis.entity.AdministrativeDivision;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;

public abstract class AbstractAddressStrategy implements  IAddressStrategy{
    protected List<AdministrativeDivision> addressList ;//= new ArrayList<>();
    protected final String LEVEL;
    public String clazz;
   // protected final  String PID ;

    protected AbstractAddressStrategy(String level){
        this.LEVEL = level;
        //this.PID = pid;
    }

    public Elements analysisTd(Document doc){
        if(StringUtils.isEmpty(clazz))
            throw new IllegalArgumentException("clazz不能为空！");
        Elements elements = doc.select(clazz);
        Elements tds = new Elements();
        elements.forEach(element ->tds.addAll(element.select("td")));
        return tds;
    }

    protected void setAddress(String id,String name,String pid,String href){
        AdministrativeDivision address = new AdministrativeDivision();
        address.setId(id);
        address.setName(name);
        address.setPid(pid);
        address.setLevel(LEVEL);
        if(StringUtils.isNotEmpty(href))
        address.setUrl(href);
        addressList.add(address);
    }

    @Override
    public List<AdministrativeDivision> getAddressList(){
        return this.addressList;
    }



}
