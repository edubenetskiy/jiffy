package space.banka.jiffy.webapp.pages;

import org.jsfr.json.compiler.JsonPathCompiler;
import space.banka.jiffy.webapp.i18n.Messages;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class JsonPathValidator implements Validator<String> {

    @Inject
    Messages messages;

    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
        try {
            JsonPathCompiler.compile(value);
        } catch (Exception exception) {
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary(messages.get("messages.illegalJsonPathQuery"));
            throw new ValidatorException(message);
        }
    }
}
