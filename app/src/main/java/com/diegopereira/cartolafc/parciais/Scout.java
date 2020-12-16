package com.diegopereira.cartolafc.parciais;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Scout{

	@SerializedName("FS")
	@Expose
	private String fS;

	@SerializedName("PI")
	@Expose
	private String pI;

	@SerializedName("FC")
	@Expose
	private String fC;

	@SerializedName("DS")
	@Expose
	private String dS;

	@SerializedName("CA")
	@Expose
	private String cA;

	@SerializedName("DD")
	@Expose
	private String dD;

	@SerializedName("GS")
	@Expose
	private String gS;

	@SerializedName("FF")
	@Expose
	private String fF;

	@SerializedName("I")
	@Expose
	private String I;

	@SerializedName("FD")
	@Expose
	private String fD;

	@SerializedName("A")
	@Expose
	private String A;

	@SerializedName("FT")
	@Expose
	private String fT;

	@SerializedName("SG")
	@Expose
	private String sG;

	@SerializedName("CV")
	@Expose
	private String cV;

	@SerializedName("G")
	@Expose
	private String G;

	@SerializedName("PP")
	@Expose
	private String pP;

	@SerializedName("DP")
	@Expose
	private String dP;

	public Scout() {
		fS = null;
	}

	public void setFS(String fS){
		this.fS = fS;
	}

	public String getFS(){
		return fS;
	}

	public void setPI(String pI){
		this.pI = pI;
	}

	public String getPI(){
		return pI;
	}

	public void setFC(String fC){
		this.fC = fC;
	}

	public String getFC(){
		return fC;
	}

	public void setDS(String dS){
		this.dS = dS;
	}

	public String getDS(){
		return dS;
	}

	public void setCA(String cA){
		this.cA = cA;
	}

	public String getCA(){
		return cA;
	}

	public void setDD(String dD){
		this.dD = dD;
	}

	public String getDD(){
		return dD;
	}

	public void setGS(String gS){
		this.gS = gS;
	}

	public String getGS(){
		return gS;
	}

	public void setFF(String fF){
		this.fF = fF;
	}

	public String getFF(){
		return fF;
	}

	public void setI(String I){
		this.I = I;
	}

	public String getI(){
		return I;
	}

	public void setFD(String fD){
		this.fD = fD;
	}

	public String getFD(){
		return fD;
	}

	public void setA(String A){
		this.A = A;
	}

	public String getA(){
		return A;
	}

	public void setFT(String fT){
		this.fT = fT;
	}

	public String getFT(){
		return fT;
	}

	public void setSG(String sG){
		this.sG = sG;
	}

	public String getSG(){
		return sG;
	}

	public void setCV(String cV){
		this.cV = cV;
	}

	public String getCV(){
		return cV;
	}

	public void setG(String G){
		this.G = G;
	}

	public String getG(){
		return G;
	}

	public void setPP(String pP){
		this.pP = pP;
	}

	public String getPP(){
		return pP;
	}

	public void setDP(String dP){
		this.dP = dP;
	}

	public String getDP(){
		return dP;
	}


	@Override
 	public String toString(){
		return 

			"FS: " + fS  + " " +
			"PI: " + pI  + " " +
			"FC: " + fC  + " " +
			"DS: " + dS  + " " +
			"CA: " + cA  + " " +
			"DD: " + dD  + " " +
			"GS: " + gS  + " " +
			"FF: " + fF  + " " +
			"I: " + I  +  " " +
			"FD: " + fD  + " " +
			"A: " + A  +  " " +
			"FT: " + fT  + " " +
			"SG: " + sG  + " " +
			"CV: " + cV  + " " +
			"G: " + G  +  " " +
			"PP: " + pP  + " " +
			"DP: " + dP
			;
		}



}