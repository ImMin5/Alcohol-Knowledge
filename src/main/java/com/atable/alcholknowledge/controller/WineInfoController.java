package com.atable.alcholknowledge.controller;

import com.atable.alcholknowledge.dto.WineInfoDto;
import com.atable.alcholknowledge.dto.WineInfoForm;
import com.atable.alcholknowledge.model.WineInfo;
import com.atable.alcholknowledge.service.WineInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.List;

@RestController
public class WineInfoController {
    private final WineInfoService wineInfoService;

    @Autowired
    public WineInfoController(WineInfoService wineInfoService){
        this.wineInfoService = wineInfoService;
    }

    @GetMapping("/")
    public String list(Model model){
        List<WineInfoDto> wineInfos = wineInfoService.findWineInfos();
        model.addAttribute("wineInfos" , wineInfos);
        return "home";

    }

    //와인정보 생성 api
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/api/wineinfo")
    @ResponseBody
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
    @ResponseBody
    public String findWineInfosAll() {
        List<WineInfoDto> wineInfos = wineInfoService.findWineInfos();
        ObjectMapper mapper = new ObjectMapper();
        String jsonList = "";

        try {
            jsonList = mapper.writeValueAsString(wineInfos);
        } catch (Exception e) {
            System.out.println("error : " + e);
        }

        return jsonList;
    }

    //와인정보 페이지네이션 api
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "api/wineinfo/pagination")
    @ResponseBody
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
    @GetMapping(value = "api/wineinfo/pagination/word")
    @ResponseBody
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
    @GetMapping(value = "api/wineinfo/search")
    @ResponseBody
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
    //와인정보 삭제 api
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping(value = "api/wineinfo")
    @ResponseBody
    public String deleteWineInfo(@RequestParam String pk){
        return wineInfoService.deleteWineInfoById(Long.parseLong(pk));
    }

    //test용 api
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/api/test")
    @ResponseBody
    public String test(@RequestBody String wineInfoForm){
        //System.out.println(wineInfoForm.getNameEng());
        System.out.println(wineInfoForm);
        return "form";
    }
}