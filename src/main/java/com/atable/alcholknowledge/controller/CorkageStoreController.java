package com.atable.alcholknowledge.controller;

import com.atable.alcholknowledge.dto.CorkageStoreForm;
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
    public String ckStoreListToJson(@RequestParam("idx") int pageIndex, @RequestParam("size") int pageSize) throws JsonProcessingException {
        List<CorkageStore> stores = corkageStoreService.findCkStores(pageIndex, pageSize);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "";
        json = objectMapper.writeValueAsString(stores);

        return json;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/api/corkage-store")
    @ResponseBody
    public String ckStoreById(@RequestParam long id) throws JsonProcessingException {
        CorkageStore corkageStore = corkageStoreService.findOne(id).get();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "";
        json = objectMapper.writeValueAsString(corkageStore);

        return json;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/api/corkage-store/search")
    @ResponseBody
    public String searchCkstore(@RequestParam String keyword,
                                @RequestParam("idx") int pageIndex,
                                @RequestParam("size") int pageSize) throws JsonProcessingException {
        List<CorkageStore> corkageStores = corkageStoreService.findByKeyWord(keyword, pageIndex, pageSize);
        if (corkageStores.isEmpty())
            return "";
        ObjectMapper objectMapper= new ObjectMapper();

        return objectMapper.writeValueAsString(corkageStores);
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
    public String list(Model model) {
        List<CorkageStore> stores = corkageStoreService.findCkStores();
        model.addAttribute("stores", stores);

        return "corkage/ckStoreList";
    }
}
