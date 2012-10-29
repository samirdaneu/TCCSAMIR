package br.com.sgpc.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.sgpc.dao.impl.FornecedorDaoImpl;
import br.com.sgpc.model.Fornecedor;

@FacesConverter(value = "fornecedorConverter", forClass = Fornecedor.class)
public class FornecedorConverter implements Converter {

	@Override  
    public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {  
        if(value != null && !value.isEmpty()) {  
            return new FornecedorDaoImpl().buscarPorID(Integer.parseInt(value));  
        }  
        return null;  
    }	
	
	@Override    
	public String getAsString(FacesContext context, UIComponent component,
			Object object) throws ConverterException {
		if (object != null && object instanceof Fornecedor) {
			return ((Fornecedor) object).getId().toString();
		}
		return null;
	}
}