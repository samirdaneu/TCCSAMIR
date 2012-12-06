package br.com.sgpc.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;

import br.com.sgpc.model.Fornecedor;

@FacesConverter(value = "fornecedorConverter", forClass = Fornecedor.class)
public class FornecedorConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
       
        Fornecedor fornecedor = null;
       
        if (StringUtils.isNotEmpty( value )) {   
            fornecedor = new Fornecedor();
            fornecedor.setId( Integer.valueOf( value ) );
        }
        return fornecedor;

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object object) throws ConverterException {
       
        String resultado = null;
        if (object != null && object instanceof Fornecedor) {
            resultado = ((Fornecedor) object).getId().toString();
        }
        return resultado;
        
    }
}