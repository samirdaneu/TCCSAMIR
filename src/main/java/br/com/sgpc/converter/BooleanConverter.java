package br.com.sgpc.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "booleanConverter", forClass = boolean.class)
public class BooleanConverter implements Converter {

    protected static final String DEFAULT_BUNDLE = "mensagem";

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {
        
        return getMessage("label_sim",context).equals(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
        
        if (value == null){
            value = (Boolean) false;
        }
        
        return ((Boolean) value) ? getMessage("label_sim",context) : getMessage("label_nao", context);
    }

    private String getMessage(String messageKey, FacesContext context) {        
        return context.getApplication().getResourceBundle(context, DEFAULT_BUNDLE).getString(messageKey);
    }
}
