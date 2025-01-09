/*
 * Decompiled with CFR 0.152.
 */
package usman.springboot.sftwrlabs.sftwrlabsbackend.service;

import java.util.List;
import java.util.Map;

public interface ISlackService {
    public void sendMessageToSlackWithBlocks(String var1, List<Map<String, Object>> var2);

    public List<Map<String, Object>> buildSlackMessageBlocks(String var1);
}
