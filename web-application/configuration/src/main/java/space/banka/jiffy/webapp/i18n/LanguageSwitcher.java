package space.banka.jiffy.webapp.i18n;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Locale;

@Named
@SessionScoped
public class LanguageSwitcher implements Serializable {

    private Locale locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();

    public Locale getLocale() {
        return locale;
    }

    public void switchLanguage(String languageCode) {
        this.locale = new Locale(languageCode);
        refreshView();
    }

    private void refreshView() {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }
}
