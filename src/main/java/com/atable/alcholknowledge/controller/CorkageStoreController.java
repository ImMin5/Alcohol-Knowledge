package com.atable.alcholknowledge.controller;

import com.atable.alcholknowledge.model.CorkageInfo;
import com.atable.alcholknowledge.model.CorkageStore;
import com.atable.alcholknowledge.service.CorkageInfoService;
import com.atable.alcholknowledge.service.CorkageStoreService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/api/corkage-store/list")
    @ResponseBody
    public String ckStoreListToJson() throws JsonProcessingException {
        List<CorkageStore> stores = corkageStoreService.findCkStores();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "";
        json = objectMapper.writeValueAsString(stores);

        return json;
    }

    @GetMapping("/corkage-store/new")
    public String createForm(@RequestParam Long id, Model model) {
        CorkageInfo ckInfo = corkageInfoService.findOne(id).get();
        model.addAttribute("ckInfo", ckInfo);
        return "corkage/createCkStoreForm";
    }

    @PostMapping("/corkage-store/new")
    public String create(CorkageStoreForm ckStoreForm) {
        CorkageStore corkageStore = new CorkageStore();

        corkageStore.setRequiredValue(ckStoreForm.getName(), ckStoreForm.getAddr());
        corkageStore.setDesc(ckStoreForm.getDesc());
        corkageStore.setDateUpdate(LocalDateTime.now());

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
