package com.scoding.ecoorder.adapter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.scoding.ecoorder.config.FeignConfiguration;
import com.scoding.ecoorder.rest.dto.EcoPointDTO;


@FeignClient(name="ecoPoint", url = "http://k8s-ecomarke-ecomarke-58be675e99-1138815434.ap-northeast-2.elb.amazonaws.com", configuration = {FeignConfiguration.class})
public interface EcoPointClient {

    @GetMapping("/ecoPoint/{memberId}")
    ResponseEntity<EcoPointDTO> getEcoPointList(@PathVariable("memberId") Long memberId);
    
}
