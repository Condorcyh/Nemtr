package com.graduation.nemtr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component("MajorDocumentConfig")
public class MajorDocumentConfig {
    private String collectionName;
    public void setName(String name){
        this.collectionName = name;
    }
    public String mongodbCollectionName(){
        return collectionName;
    }
}
