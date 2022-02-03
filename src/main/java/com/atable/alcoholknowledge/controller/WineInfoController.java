package com.atable.alcoholknowledge.controller;

import com.atable.alcoholknowledge.dto.WineInfoDto;
import com.atable.alcoholknowledge.dto.WineInfoForm;
import com.atable.alcoholknowledge.model.ResponseVo;
import com.atable.alcoholknowledge.model.WineInfo;
import com.atable.alcoholknowledge.service.WineInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.List;

@RestController
public class WineInfoController {
    private final WineInfoService wineInfoService;

    @Autowired
    public WineInfoController(WineInfoService wineInfoService){
        this.wineInfoService = wineInfoService;
    }


    //와인정보 생성 api
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/api/wineinfo")
    public String createWineInfo(@RequestBody WineInfoForm form){
        try{
            System.out.println(form.getNameEng());
            System.out.println(form.getNameKor());
            System.out.println(form.getPrice());
            System.out.println(form.getVintage());
            System.out.println(form.getSizeBottle());
            form.setDateCreated(new Timestamp(System.currentTimeMillis()));
            WineInfo wineInfo = new WineInfo(form);
            System.out.println(wineInfoService.submit(wineInfo) + " made wineinfo");
        }
        catch(Exception e){
            System.out.println("error : "+ e);
            return "fail to make wineinfo";
        }
        finally {
            return "good";
        }
    }

    //와인정보 조회 api
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value="/api/wineinfo")
    public String findWineInfosAll() {
        List<WineInfoDto> wineInfos = wineInfoService.findWineInfoDtos();
        ObjectMapper mapper = new ObjectMapper();
        String jsonList = "";
        try {
            jsonList = mapper.writeValueAsString(wineInfos);
        } catch (Exception e) {
            System.out.println("error : " + e);
        }

        return jsonList;
    }

    //와인정보 삭제 api
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping(value = "/api/wineinfo")
    public ResponseVo deleteWineInfo(@RequestParam String pk)  {
        //String msg = wineInfoService.deleteWineInfoById(Long.parseLong(pk));
        ResponseVo rs = null;
        try{

            rs = new ResponseVo(200,"good","data");
        }
        catch( Exception e ){
            System.out.println(e);
        }
        finally {
            return rs;
        }
    }


    //와인정보 페이지네이션 api
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/api/wineinfo/pagination")
    public String findWineInfosPage(@RequestParam String pageIndex ,@RequestParam String pageSize){
        List<WineInfoDto> wineInfos = wineInfoService.findWineInfosPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
        ObjectMapper mapper = new ObjectMapper();
        String jsonList = "";

        try{
            jsonList = mapper.writeValueAsString(wineInfos);
        }catch (Exception e){
            System.out.println("error : "+ e);
        }
        return jsonList;
    }

    //와인검색 페이지네이션
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/api/wineinfo/pagination/word")
    public String findByWordWineInfosPage(@RequestParam String pageIndex ,@RequestParam String pageSize ,@RequestParam String word){
        List<WineInfoDto> wineInfos = wineInfoService.findByWordWineInfosPage(Integer.parseInt(pageIndex), Integer.parseInt(pageSize),word);
        ObjectMapper mapper = new ObjectMapper();
        String jsonList = "";

        try{
            jsonList = mapper.writeValueAsString(wineInfos);
        }catch (Exception e){
            System.out.println("error : "+ e);
        }
        return jsonList;
    }


    //와인정보 검색 api
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/api/wineinfo/search")
    public String findWineInfos(@RequestParam String word){

        System.out.println("search function in");
        List<WineInfoDto> wineInfos = wineInfoService.findWineInfosByWord(word);
        ObjectMapper mapper = new ObjectMapper();
        String jsonList = "";


        try {
            jsonList = mapper.writeValueAsString(wineInfos);
        } catch (Exception e) {
            System.out.println("error : " + e);
        }

        return jsonList;

    }

    //test용 api
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/api/test")
    public String test(@RequestBody String wineInfoForm){
        //System.out.println(wineInfoForm.getNameEng());
        System.out.println(wineInfoForm);
        return "form";
    }
}