package com.lazy.mybatis.strategy.address;

import com.lazy.mybatis.entity.AdministrativeDivision;
import org.jsoup.nodes.Document;

import java.util.List;

public interface IAddressStrategy {


    public void analysisDoc(Document doc, String pid,String path);

    public List<AdministrativeDivision> getAddressList();



}
