package br.com.sgpc.webservice;

public class Teste {
	
	public static void main(String[] args) {
		
		new GrepCep().getGrepCepHttpSoap11Endpoint().obterEnderecoCep("08080660", "201201029306055PPT7OW0GSFTNRH2ZKSEL").getResponseDescription();
	}
}
