/*
 * Decompiled with CFR 0.152.
 */
package usman.springboot.sftwrlabs.sftwrlabsbackend.service;

import usman.springboot.sftwrlabs.sftwrlabsbackend.model.PromotionalContent;

public interface IPromotionalContentService {
    public PromotionalContent getLatestContent();

    public PromotionalContent saveContent(PromotionalContent var1);
}
