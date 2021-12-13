package com.atable.alcholknowledge.controller;

import com.atable.alcholknowledge.model.CorkageInfo;
import com.atable.alcholknowledge.model.CorkageStore;
import com.atable.alcholknowledge.service.CorkageInfoService;
import com.atable.alcholknowledge.service.CorkageStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CorkageStoreController {

    private final CorkageStoreService corkageStoreService;
    private final CorkageInfoService corkageInfoService;

    @Autowired
    public CorkageStoreController(CorkageStoreService corkageStoreService, CorkageInfoService corkageInfoService) {
        this.corkageStoreService = corkageStoreService;
        this.corkageInfoService = corkageInfoService;
    }

    @GetMapping("/corkage-store/new")
    public String createForm(@RequestParam Long id, Model model) {
        CorkageInfo ckInfo = corkageInfoService.findOne(id).get();
        model.addAttribute("ckInfo", ckInfo);
        return "corkage/createCkStoreForm";
    }

    @PostMapping("/corkage-store/new")
    public String create(CorkageStore ckStoreForm) {
        CorkageStore corkageStore = new CorkageStore();

        corkageStore.setRequiredValue(ckStoreForm.getName(), ckStoreForm.getAddr());
        corkageStore.setDesc(ckStoreForm.getDesc());
        corkageStore.setIsChecked(corkageStore.getIsChecked() + 1);
        corkageStoreService.register(corkageStore);

        return "redirect:/corkage-info/list";
    }

    @GetMapping("/corkage-store/list")
    public String list(Model model) {
        List<CorkageStore> stores = corkageStoreService.findCkStores();
        model.addAttribute("stores", stores);

        return "corkage/ckStoreList";
    }
}
