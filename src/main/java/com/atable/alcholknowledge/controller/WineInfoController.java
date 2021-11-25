package com.atable.alcholknowledge.controller;

import com.atable.alcholknowledge.model.WineInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.sql.Timestamp;

@Controller
public class WineInfoController {

    @GetMapping("api/wine-info")
    @ResponseBody
    public int wine(Model model){
        WineInfo winfo = new WineInfo();
        winfo.setInfo("Silver Oak","실버오크", 2018 , 100000,new Timestamp(System.currentTimeMillis()),new Date(2021,1,26) ,"dd","서울","청담 와인앤 모어","750ml");
        return winfo.getPrice();
    }

}
