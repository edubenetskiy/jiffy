package space.banka.jiffy.webapp.i18n;

import javax.annotation.PropertyKey;
import javax.enterprise.context.Dependent;
import javax.faces.annotation.ManagedProperty;
import javax.inject.Inject;
import java.text.MessageFormat;
import java.util.PropertyResourceBundle;

@Dependent
public class Messages {

    @Inject
    @ManagedProperty("#{messages}")
    PropertyResourceBundle messages;

    public String format(@PropertyKey String key, Object... args) {
        String pattern = get(key);
        return MessageFormat.format(pattern, args);
    }

    public String get(@PropertyKey String key) {
        return messages.getString(key);
    }
}
