package com.claro.sccweb.form;

import java.util.Date;

public class DisponibilizacaoPacotesPreForm extends BaseForm {

	private String cdEOTClaro;
	private String cdEOTLD;
	private String cdPacoteMinuto;
	private Date dtInicio;
	private Date dtFim;
	private Date dtInicioProcExterna;
	private Date dtFimProcExterna;
	public String getCdEOTClaro() {
		return cdEOTClaro;
	}
	public void setCdEOTClaro(String cdEOTClaro) {
		this.cdEOTClaro = cdEOTClaro;
	}
	public String getCdEOTLD() {
		return cdEOTLD;
	}
	public void setCdEOTLD(String cdEOTLD) {
		this.cdEOTLD = cdEOTLD;
	}
	public String getCdPacoteMinuto() {
		return cdPacoteMinuto;
	}
	public void setCdPacoteMinuto(String cdPacoteMinuto) {
		this.cdPacoteMinuto = cdPacoteMinuto;
	}
	public Date getDtInicio() {
		return dtInicio;
	}
	public void setDtInicio(Date dtInicio) {
		this.dtInicio = dtInicio;
	}
	public Date getDtFim() {
		return dtFim;
	}
	public void setDtFim(Date dtFim) {
		this.dtFim = dtFim;
	}
	public Date getDtInicioProcExterna() {
		return dtInicioProcExterna;
	}
	public void setDtInicioProcExterna(Date dtInicioProcExterna) {
		this.dtInicioProcExterna = dtInicioProcExterna;
	}
	public Date getDtFimProcExterna() {
		return dtFimProcExterna;
	}
	public void setDtFimProcExterna(Date dtFimProcExterna) {
		this.dtFimProcExterna = dtFimProcExterna;
	}
	
	
}
