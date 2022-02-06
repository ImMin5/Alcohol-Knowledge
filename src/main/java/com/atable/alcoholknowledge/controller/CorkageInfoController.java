package com.atable.alcoholknowledge.controller;

import com.atable.alcoholknowledge.dto.CorkageInfoForm;
import com.atable.alcoholknowledge.model.CorkageInfo;
import com.atable.alcoholknowledge.service.CorkageInfoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class CorkageInfoController {

    private final CorkageInfoService corkageInfoService;

    @Autowired
    public CorkageInfoController(CorkageInfoService corkageInfoService) {
        this.corkageInfoService = corkageInfoService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/api/corkage-info/list")
    public String ckInfoListToJson() throws JsonProcessingException {
        List<CorkageInfo> stores = corkageInfoService.findCkInfos();
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(stores);
        }
        catch (Exception e) {
            System.out.println("error : " + e);
        }
        return json;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("api/corkage-info/new")
    public ResponseEntity<String> createCkInfoFromJson(@RequestBody CorkageInfoForm ckInfoForm) throws JsonProcessingException {
        CorkageInfo corkageInfo = new CorkageInfo();
        corkageInfo.setAddr(ckInfoForm.getAddr());
        corkageInfo.setDesc(ckInfoForm.getDesc());
        corkageInfo.setDateCreate(LocalDateTime.now());
        corkageInfo.setName(ckInfoForm.getName());
        long id = corkageInfoService.register(corkageInfo);
        if (id == -1L) {
            return ResponseEntity.accepted()
                    .headers(new HttpHeaders())
                    .body("이미 접수 요청된 장소입니다.");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(ckInfoForm));
        /*
        To-do: Uri needed with status:created
        URI uri = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(CorkageStoreController.class)
                        .ckStoreListToJson(0, 10))
                .toUri();
         */
        return ResponseEntity.ok()
                .headers(new HttpHeaders())
                .body("");
    }
}
