/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RestController
 */
package usman.springboot.sftwrlabs.sftwrlabsbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import usman.springboot.sftwrlabs.sftwrlabsbackend.model.PromotionalContent;
import usman.springboot.sftwrlabs.sftwrlabsbackend.service.IPromotionalContentService;

@RestController
@RequestMapping(value={"/api/promotional-content"})
public class PromotionalContentController {
    @Autowired
    private IPromotionalContentService promotionalContentService;

    @PostMapping(value={"/save-promo"})
    public PromotionalContent savePromotionalContent(@RequestBody PromotionalContent content) {
        return this.promotionalContentService.saveContent(content);
    }

    @GetMapping(value={"/get-promo"})
    public PromotionalContent getLatestContent() {
        return this.promotionalContentService.getLatestContent();
    }
}
