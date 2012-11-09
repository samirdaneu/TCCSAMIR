package br.com.sgpc.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "booleanConverter", forClass = Boolean.class)
public class BooleanConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null)
			return Boolean.FALSE;
		return "Sim".equalsIgnoreCase(value) || "True".equalsIgnoreCase(value);
	}

	public String getAsString(FacesContext context, UIComponent component,
			Object object) {
		if (object instanceof Boolean)
			return ((Boolean) object).booleanValue() ? "Sim" : "Não";
		return object.toString();
	}

}
