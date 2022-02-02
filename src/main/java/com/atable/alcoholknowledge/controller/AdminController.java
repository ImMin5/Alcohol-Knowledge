package com.atable.alcoholknowledge.controller;


import com.atable.alcoholknowledge.model.WineInfo;
import com.atable.alcoholknowledge.service.WineInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {
    private final WineInfoService wineInfoService;
    private final String id ="admin";
    private final String pw ="1234";

    @Autowired
    public AdminController(WineInfoService wineInfoService){
        this.wineInfoService = wineInfoService;
    }


    @GetMapping("/admin")
    public String adminHome(Model model){
        return "admin_home";

    }

    @GetMapping("/admin/wineinfo")
    public String adminWineInfo(Model model){
        List<WineInfo> wineInfos = wineInfoService.findWineInfos();
        model.addAttribute("wineInfos", wineInfos);
        System.out.println("size of wineinfos : " + wineInfos.size());
        return "wineinfo/admin_wineinfo_list";
    }



}
