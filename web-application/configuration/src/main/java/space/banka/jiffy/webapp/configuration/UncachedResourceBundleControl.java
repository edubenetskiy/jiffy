package space.banka.jiffy.webapp.configuration;

import lombok.extern.slf4j.Slf4j;

import java.util.Locale;
import java.util.ResourceBundle;

@Slf4j
public class UncachedResourceBundleControl extends ResourceBundle.Control {

    public UncachedResourceBundleControl() {
        log.warn("Do not use {} on live servers!", getClass().getName());
    }

    @Override
    public long getTimeToLive(String baseName, Locale locale) {
        return TTL_DONT_CACHE;
    }
}
