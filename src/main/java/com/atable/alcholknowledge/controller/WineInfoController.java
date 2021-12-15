package com.atable.alcholknowledge.controller;

import com.atable.alcholknowledge.model.WineInfo;
import com.atable.alcholknowledge.service.WineInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class WineInfoController {
    private final WineInfoService wineInfoService;

    @Autowired
    public WineInfoController(WineInfoService wineInfoService){
        this.wineInfoService = wineInfoService;
    }

    @GetMapping("/wineinfo/new")
    public String createWineInfoForm(){
        return "wineinfo/createWineInfoForm";
    }

    @PostMapping("/wineinfo/new")
    public String create(WineInfoForm form){
        WineInfo wineInfo = new WineInfo();
        form.setDateCreated(new Timestamp(System.currentTimeMillis()));
        wineInfo.setInfo(form);
        System.out.println("sizeBottle : "+form.getSizeBottle());
        wineInfoService.submit(wineInfo);

        return "redirect:/";
    }

    @GetMapping("/")
    public String list(Model model){
        List<WineInfo> wineInfos = wineInfoService.findWineInfos();
        model.addAttribute("wineInfos" , wineInfos);
        return "home";

    }
}
