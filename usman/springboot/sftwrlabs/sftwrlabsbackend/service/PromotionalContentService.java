/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 */
package usman.springboot.sftwrlabs.sftwrlabsbackend.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usman.springboot.sftwrlabs.sftwrlabsbackend.dao.IPromotionalContentRepository;
import usman.springboot.sftwrlabs.sftwrlabsbackend.model.PromotionalContent;
import usman.springboot.sftwrlabs.sftwrlabsbackend.service.IPromotionalContentService;

@Service
public class PromotionalContentService
implements IPromotionalContentService {
    @Autowired
    private IPromotionalContentRepository promotionalContentRepository;

    @Override
    public PromotionalContent getLatestContent() {
        List contents = this.promotionalContentRepository.findAll();
        if (contents.isEmpty()) {
            return null;
        }
        return (PromotionalContent)contents.get(contents.size() - 1);
    }

    @Override
    public PromotionalContent saveContent(PromotionalContent content) {
        return (PromotionalContent)this.promotionalContentRepository.save(content);
    }
}
