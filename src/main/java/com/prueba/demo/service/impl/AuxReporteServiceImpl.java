package com.prueba.demo.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.prueba.demo.service.AuxReporteService;
import com.prueba.demo.support.dto.VariablesReporte;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JsonDataSource;

@Service
public class AuxReporteServiceImpl implements AuxReporteService {
	private static final Logger log = LoggerFactory.getLogger(AuxReporteServiceImpl.class);
	
	@Autowired
	ResourceLoader resourceLoader;
	
	@Override
	public String generarReporte(Object objecto, String rutaReporte) throws Exception {
		try {
			JasperPrint print = llenarReporte(objecto, rutaReporte);
			return generarPdfEncode(print);
		} catch (JRException e) {
			log.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public File generarReportePdf(Object objecto, String rutaReporte) throws Exception {
		try {
			JasperPrint print = llenarReporte(objecto, rutaReporte);
			return generarPdf(print);
		} catch (JRException e) {
			log.error(e.getMessage());
			throw e;
		}
	}
	
	private File generarPdf(JasperPrint print) {
		File pdf = null;
		try {
			byte[] pdfByte = JasperExportManager.exportReportToPdf(print);
			pdf = File.createTempFile(VariablesReporte.NAME_TEMP_REPORTE, "");
			try (OutputStream out = new FileOutputStream(pdf)) {
				out.write(pdfByte);
				out.close();
			}
			 
			 
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return pdf;
	}

	private String generarPdfEncode(JasperPrint print) {
		String codificado = null;
		try {
			byte[] pdfByte = JasperExportManager.exportReportToPdf(print);
			File pdf = File.createTempFile(VariablesReporte.NAME_TEMP_REPORTE, "");
			try (OutputStream out = new FileOutputStream(pdf)) {
				out.write(pdfByte);
				out.close();
			}
			codificado = codificado(pdf);
			pdf.delete();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return codificado;
	}
	
	@Override
	public String generarPdfEncode(byte[] pdfByte) {
		String codificado = null;
		try {
			
 			File pdf = File.createTempFile(VariablesReporte.NAME_TEMP_REPORTE, "");
			try (OutputStream out = new FileOutputStream(pdf)) {
				out.write(pdfByte);
				out.close();
			}
			codificado = codificado(pdf);
			pdf.delete();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return codificado;
	}

	private JasperPrint llenarReporte(Object objecto, String rutaReporte) throws Exception {
		InputStream resourceInputStream = null;
		try {
			Gson gson = new Gson();
			String json = gson.toJson(objecto);
			JsonDataSource dataSource = new JsonDataSource(new ByteArrayInputStream(json.getBytes((StandardCharsets.UTF_8.displayName()))));
			dataSource.setLocale(Locale.US);
			Resource resource = new ClassPathResource(VariablesReporte.NAME_TEMP_GENERAL + rutaReporte);
			resourceInputStream = resource.getInputStream();
			
			return JasperFillManager.fillReport(resourceInputStream, null, dataSource);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
		finally {
			resourceInputStream.close();
		}

	}
	
	public JasperPrint obtenerJasperPrint(Object objecto, String rutaReporte) throws Exception {
		InputStream resourceInputStream = null;
		try {
			Gson gson = new Gson();
			String json = gson.toJson(objecto);
			JsonDataSource dataSource = new JsonDataSource(new ByteArrayInputStream(json.getBytes((StandardCharsets.UTF_8.displayName()))));
			dataSource.setLocale(Locale.US);
 
			Resource resource = new ClassPathResource(VariablesReporte.NAME_TEMP_GENERAL + rutaReporte);
			resourceInputStream = resource.getInputStream();
			
			return JasperFillManager.fillReport(resourceInputStream, null, dataSource);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
		finally {
			resourceInputStream.close();
		}

	}

	public String codificado(File file) throws Exception {
		String codificado = null;
		try {
			byte[] fileArray = new byte[(int) file.length()];
			InputStream inputStream = new FileInputStream(file);
			inputStream.read(fileArray);
			codificado = Base64.encodeBase64String(fileArray);
			inputStream.close();

		} catch (UnsupportedEncodingException ex) {
			log.error(ex.getMessage());
		}
		return codificado;
	}

	@Override
	public String obtenerImagenOnpeEncode() {
		String ruta = null;
		try {
			Resource resource = new ClassPathResource(VariablesReporte.IMG);
			InputStream inputStream = resource.getInputStream();
			byte[] fileArray = IOUtils.toByteArray(inputStream);
			inputStream.read(fileArray);
			ruta = Base64.encodeBase64String(fileArray);
			inputStream.close();
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return ruta;
	}
 
}
