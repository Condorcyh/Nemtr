package com.graduation.nemtr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component("PredictDocumentConfig")
public class PredictDocumentConfig {
    private String collectionName;
    public void setName(String name){
        this.collectionName = name;
    }
    public String mongodbCollectionName(){
        return collectionName;
    }
}
