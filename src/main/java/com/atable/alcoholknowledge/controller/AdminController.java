package com.atable.alcoholknowledge.controller;


import com.atable.alcoholknowledge.dto.CorkageInfoForm;
import com.atable.alcoholknowledge.dto.CorkageStoreForm;
import com.atable.alcoholknowledge.model.CorkageInfo;
import com.atable.alcoholknowledge.model.CorkageStore;
import com.atable.alcoholknowledge.model.WineInfo;
import com.atable.alcoholknowledge.service.CorkageInfoService;
import com.atable.alcoholknowledge.service.CorkageStoreService;
import com.atable.alcoholknowledge.service.WineInfoService;
import com.atable.alcoholknowledge.model.WineInfo;
import com.atable.alcoholknowledge.service.WineInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class AdminController {
    private final WineInfoService wineInfoService;
    private final CorkageInfoService corkageInfoService;
    private final CorkageStoreService corkageStoreService;
    private final String id ="admin";
    private final String pw ="1234";

    @Autowired
    public AdminController(WineInfoService wineInfoService,
                           CorkageInfoService corkageInfoService,
                           CorkageStoreService corkageStoreService){
        this.wineInfoService = wineInfoService;
        this.corkageStoreService = corkageStoreService;
        this.corkageInfoService = corkageInfoService;
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

    /* Corkage Admin Controller */
    @GetMapping("/admin/corkage-info/new")
    public String createForm() {
        return "corkage/createCkInfoForm";
    }

    @PostMapping("/admin/corkage-info/new")
    public String create(CorkageInfoForm ckInfoForm) {
        CorkageInfo corkageInfo = new CorkageInfo();
        corkageInfo.setAddr(ckInfoForm.getAddr());
        corkageInfo.setDesc(ckInfoForm.getDesc());
        corkageInfo.setDateCreate(LocalDateTime.now());
        corkageInfo.setName(ckInfoForm.getName());
        corkageInfoService.register(corkageInfo);
        return "redirect:/admin/corkage-info/list";
    }

    @GetMapping("/admin/corkage-info/list")
    public String corkageInfolist(Model model) {
        List<CorkageInfo> stores = corkageInfoService.findCkInfos();
        model.addAttribute("stores", stores);
        return "corkage/ckInfoList";
    }


    @GetMapping("/admin/corkage-store/new")
    public String createForm(@RequestParam Long id, Model model) {
        CorkageInfo ckInfo = corkageInfoService.findOne(id).get();
        model.addAttribute("ckInfo", ckInfo);
        return "corkage/createCkStoreForm";
    }

    @PostMapping("/admin/corkage-store/new")
    public String create(CorkageStoreForm ckStoreForm) {
        CorkageStore corkageStore = new CorkageStore();

        corkageStore.setRequiredValue(ckStoreForm.getName(), ckStoreForm.getAddr());
        corkageStore.setDesc(ckStoreForm.getDesc());
        corkageStore.setDateUpdate(LocalDateTime.now());

        corkageStoreService.register(corkageStore);
        return "redirect:/admin/corkage-info/list";
    }

    @GetMapping("/admin/corkage-store/list")
    public String corkageStoreList(Model model) {
        List<CorkageStore> stores = corkageStoreService.findCkStores();
        model.addAttribute("stores", stores);

        return "corkage/ckStoreList";
    }
}
