package com.services.myretail.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.services.myretail.models.RedskyProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Repository
public class RedskyRepository {

    @Autowired
    RestTemplate restTemplate;

    private final String URL = "https://redsky-uat.perf.target.com/redsky_aggregations/v1/redsky/case_study_v1" +
            "?key={key}&tcin={tcin}";
    private final String REDSKY_KEY = "3yUxt7WltYG7MFKPp7uyELi1K40ad2ys";

    private static final Logger log = LoggerFactory.getLogger(RedskyRepository.class);

    RedskyRepository() { }

    public String getProductTitle(int productId) {
        RedskyProduct redskyProduct = getRedskyProduct (productId);
        if(redskyProduct != null)
            return redskyProduct.getItem ().getProduct_description ().getTitle ();
        return null;
    }


    public RedskyProduct getRedskyProduct (int productId) {
        RedskyProduct resdkyProduct = null;

        Map<String, String> params = new HashMap<> ();
        params.put ("key", REDSKY_KEY);
        params.put ("tcin", String.valueOf (productId));

        ResponseEntity responseEntity = null;

        log.info ("Retrieving Redsky Product : "+productId);
        try {
            responseEntity = restTemplate.getForEntity (URL, String.class, params);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode ().equals (HttpStatus.NOT_FOUND))
                return null;
        } catch (Exception e) {
            e.printStackTrace ();
            return null;
        }

        ObjectMapper mapper = new ObjectMapper ();
        try {
            JsonNode jsonNode = mapper.readTree (responseEntity.getBody ().toString ()).findPath ("product");
            resdkyProduct = mapper.treeToValue (jsonNode, RedskyProduct.class);

        } catch (JsonProcessingException e) {
            e.printStackTrace ();
        }
        log.info ("Completed retrieving  Redsky Product : "+productId);
        return resdkyProduct;
    }
}
