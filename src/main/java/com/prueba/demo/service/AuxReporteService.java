package com.prueba.demo.service;

import java.io.File;

import net.sf.jasperreports.engine.JasperPrint;

public interface AuxReporteService {

    public String generarReporte(Object objecto, String rutaReporte) throws Exception;
	public String obtenerImagenOnpeEncode();
	public JasperPrint obtenerJasperPrint(Object objecto, String rutaReporte) throws Exception;
	public String generarPdfEncode(byte[] pdfByte);
	public File generarReportePdf(Object objecto, String rutaReporte) throws Exception;
    
}
