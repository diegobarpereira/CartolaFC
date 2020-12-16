package com.diegopereira.cartolafc.parciais;

import com.google.gson.annotations.SerializedName;

public class Escudos{

	@SerializedName("60x60")
	private String jsonMember60x60;

	@SerializedName("45x45")
	private String jsonMember45x45;

	@SerializedName("30x30")
	private String jsonMember30x30;

	public void setJsonMember60x60(String jsonMember60x60){
		this.jsonMember60x60 = jsonMember60x60;
	}

	public String getJsonMember60x60(){
		return jsonMember60x60;
	}

	public void setJsonMember45x45(String jsonMember45x45){
		this.jsonMember45x45 = jsonMember45x45;
	}

	public String getJsonMember45x45(){
		return jsonMember45x45;
	}

	public void setJsonMember30x30(String jsonMember30x30){
		this.jsonMember30x30 = jsonMember30x30;
	}

	public String getJsonMember30x30(){
		return jsonMember30x30;
	}

	@Override
 	public String toString(){
		return 
			"Escudos{" + 
			"60x60 = '" + jsonMember60x60 + '\'' + 
			",45x45 = '" + jsonMember45x45 + '\'' + 
			",30x30 = '" + jsonMember30x30 + '\'' + 
			"}";
		}
}