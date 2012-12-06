package br.com.sgpc.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class Teste {
	
	private String string;
	private List<String> strings;	
	
	public static void main(String[] args) {
		String string = new String();
		List<String> strings = new ArrayList<String>();
		
		string = "carro";
		strings.add(string);
		string = "00";
		System.out.println(strings.get(0));
		
		Locale locale = new Locale("pt", "BR");
		GregorianCalendar calendar = new GregorianCalendar(); 
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy",locale); 
		System.out.println(formatador.format(calendar.getTime())); 
		
		
	}

	public void setString(String string) {
		this.string = string;
	}

	public String getString() {
		return string;
	}

	public void setStrings(List<String> strings) {
		this.strings = strings;
	}

	public List<String> getStrings() {
		return strings;
	}
	
	

}
