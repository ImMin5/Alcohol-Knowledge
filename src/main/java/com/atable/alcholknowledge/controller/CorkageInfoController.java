package com.atable.alcholknowledge.controller;

import com.atable.alcholknowledge.model.CorkageInfo;
import com.atable.alcholknowledge.service.CorkageInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Controller
public class CorkageInfoController {

    private final CorkageInfoService corkageInfoService;

    @Autowired
    public CorkageInfoController(CorkageInfoService corkageInfoService) {
        this.corkageInfoService = corkageInfoService;
    }

    @GetMapping("/corkage-info/new")
    public String createForm() {
        return "corkage/createCkInfoForm";
    }

    @PostMapping("/corkage-info/new")
    public String create(CkInfoForm ckInfoForm) {
        CorkageInfo corkageInfo = new CorkageInfo();
        corkageInfo.setAddr(ckInfoForm.getAddr());
        corkageInfo.setDesc(ckInfoForm.getDesc());

        corkageInfo.setDateCreate(LocalDateTime.now());
        corkageInfoService.register(corkageInfo);
        return "redirect:/corkage-store/list";
    }

    @GetMapping("/corkage-info/list")
    public String list(Model model) {
        List<CorkageInfo> stores = corkageInfoService.findCkInfos();
        model.addAttribute("stores", stores);
        return "corkage/ckInfoList";
    }
}
