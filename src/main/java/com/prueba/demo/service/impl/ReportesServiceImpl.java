package com.prueba.demo.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.demo.core.inputDto.ListarQuemaProductoInputDto;
import com.prueba.demo.core.inputDto.ListarVentaInputDto;
import com.prueba.demo.core.inputDto.ProductoInicialInputDto;
import com.prueba.demo.core.inputDto.ProductoTerminadoInputDto;
import com.prueba.demo.core.model.DetalleProductoTerminado;
import com.prueba.demo.core.model.DetalleProductoVenta;
import com.prueba.demo.core.model.DetalleQuemaProducto;
import com.prueba.demo.core.model.DetalleVenta;
import com.prueba.demo.core.model.ProductoInicial;
import com.prueba.demo.core.model.ProductoTerminado;
import com.prueba.demo.core.model.QuemaProducto;
import com.prueba.demo.core.model.QuemaProductoPersona;
import com.prueba.demo.core.model.ReporteProductoInicial;
import com.prueba.demo.core.model.ReporteProductoTerminado;
import com.prueba.demo.core.model.ReporteQuemaProducto;
import com.prueba.demo.core.model.ReporteVenta;
import com.prueba.demo.core.model.Venta;
import com.prueba.demo.core.outputDto.ListaProductoInicialOutputDto;
import com.prueba.demo.core.outputDto.ListaProductoTerminadoOutputDto;
import com.prueba.demo.core.outputDto.ListaQuemaProductoOutputDto;
import com.prueba.demo.core.outputDto.ListaVentaOutputDto;
import com.prueba.demo.core.outputDto.ListarDetProductoTerminadoOutputDto;
import com.prueba.demo.core.outputDto.ListarDetProductoVentaOuputDto;
import com.prueba.demo.core.outputDto.ListarDetQuemaProductoOutputDto;
import com.prueba.demo.core.outputDto.ListarDetVentaOutputDto;
import com.prueba.demo.core.outputDto.ListarQuemaProductoPersonaOutputDto;
import com.prueba.demo.mapper.DetProductoTerminadoMapper;
import com.prueba.demo.mapper.DetProductoVentaMapper;
import com.prueba.demo.mapper.DetQuemaProductoMapper;
import com.prueba.demo.mapper.DetVentaMapper;
import com.prueba.demo.mapper.ProductoInicialMapper;
import com.prueba.demo.mapper.ProductoTerminadoMapper;
import com.prueba.demo.mapper.QuemaProductoMapper;
import com.prueba.demo.mapper.QuemaProductoPersonaMapper;
import com.prueba.demo.mapper.ReportesMapper;
import com.prueba.demo.mapper.VentaMapper;
import com.prueba.demo.service.AuxReporteService;
import com.prueba.demo.service.ReportesService;
import com.prueba.demo.support.dto.Respuesta;
import com.prueba.demo.support.dto.VariablesReporte;

@Service
public class ReportesServiceImpl implements ReportesService {
	private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);

	

	@Autowired
	private AuxReporteService auxReporteService;

	@Autowired
	private ProductoInicialMapper productoInicialMapper;

	@Autowired
	private ProductoTerminadoMapper productoTerminadoMapper;

	@Autowired
	private DetProductoTerminadoMapper detProductoTerminadoMapper;

	@Autowired
	private QuemaProductoMapper quemaProductoMapper;

	@Autowired
	private DetQuemaProductoMapper detQuemaProductoMapper;

	@Autowired
	private QuemaProductoPersonaMapper quemaProductoPersonaMapper;

	@Autowired
	private VentaMapper ventaMapper;

	@Autowired
	private DetVentaMapper detVentaMapper;

	@Autowired
	private DetProductoVentaMapper detProductoVentaMapper;

	@Autowired
	private ReportesMapper reportesMapper;

	@Override
	public Respuesta<?> listarProductoInicialExcel(ProductoInicialInputDto param) throws Exception {

	
			ProductoInicial listProductoInicial = new ProductoInicial();
			listProductoInicial.setIdProductoInicial(param.getIdProductoInicial());
			listProductoInicial.setFechaInicio(param.getFechaInicio());
			listProductoInicial.setFechaFin(param.getFechaFin());
			listProductoInicial.setPrensa(param.getPrensa());
			listProductoInicial.setTipoLadrillo(param.getTipoLadrillo());
			List<ProductoInicial> listaProductoInicial = productoInicialMapper
					.getListarProductoInicial(listProductoInicial);

			List<ListaProductoInicialOutputDto> listaInicial = new ArrayList<>();
			if (!listaProductoInicial.isEmpty()) {
				ListaProductoInicialOutputDto lista = new ListaProductoInicialOutputDto();
				for (ProductoInicial element : listaProductoInicial) {

					lista = new ListaProductoInicialOutputDto();
					lista.setIdProductoInicial(element.getIdProductoInicial());
					lista.setFechaRegistro(element.getFechaRegistro());
					lista.setFechaRegistroDesc(element.getFechaRegistroDesc());
					lista.setPrensaDesc(element.getPrensaDesc());
					lista.setCantidadProducido(element.getCantidadProducido());
					lista.setCantidadEstimada(element.getCantidadEstimada());
					lista.setActivo(element.getActivo());
					lista.setDescripcionActivo(element.getDescripcionActivo());
					lista.setTipoLadrillo(element.getTipoLadrillo());
					lista.setPrensa(element.getPrensa());
					lista.setCodigoProductoInicial(element.getCodigoProductoInicial());
					lista.setTipoLadrilloDesc(element.getTipoLadrilloDesc());
					listaInicial.add(lista);
				}
				// Creamos nuestro libro de excel
				HSSFWorkbook workbook = new HSSFWorkbook();
				// Le damos color al fondo
				HSSFFont blueFont = workbook.createFont();
			blueFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
    
			HSSFCellStyle style = workbook.createCellStyle();
			HSSFCellStyle style2 = workbook.createCellStyle();

				style.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);
				style.setBorderTop(XSSFCellStyle.BORDER_MEDIUM);
				style.setBorderRight(XSSFCellStyle.BORDER_MEDIUM);
				style.setBorderLeft(XSSFCellStyle.BORDER_MEDIUM);

				style2.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);
				style2.setBorderTop(XSSFCellStyle.BORDER_MEDIUM);
				style2.setBorderRight(XSSFCellStyle.BORDER_MEDIUM);
				style2.setBorderLeft(XSSFCellStyle.BORDER_MEDIUM);
				style2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

				style.setFont(blueFont);

				// centrar
				style.setAlignment(style.ALIGN_CENTER);
				style2.setAlignment(style2.ALIGN_CENTER);

				// iniciamos variables
				int numeroColumna = 6;
				int tamano = 5000;

		
				CellRangeAddress cellRangeAddress = new CellRangeAddress(1, 1, 2, 5);

				// Creamos una hoja de calculo llama "Reporte" en dicho libro de excel
				HSSFSheet sheet = workbook.createSheet("Reporte Producto Inicial");

				sheet.setColumnWidth(numeroColumna, tamano);

				sheet.addMergedRegion(cellRangeAddress);

				CreationHelper helper = workbook.getCreationHelper();

				// Creates the top-level drawing patriarch.
				Drawing drawing = sheet.createDrawingPatriarch();

	
				ClientAnchor anchor = helper.createClientAnchor();
			
				anchor.setCol1(0); // Column B
				anchor.setRow1(0); // Row 3 abajo
				anchor.setCol2(0); // Column C
				anchor.setRow2(1); // Row 4
				double scale = 0.1;

				// sheet.addMergedRegion(new CellRangeAddress(1,1,1,3));
				Row fila = sheet.createRow(1);
				Cell celda = fila.createCell(2);
				celda.setCellValue("REPORTE PRODUCTO INICIAL");

				// combinar y centrar
				final int borderMediumDashed = CellStyle.BORDER_MEDIUM;
				RegionUtil.setBorderTop(borderMediumDashed, cellRangeAddress, sheet, sheet.getWorkbook());
				RegionUtil.setBorderBottom(borderMediumDashed, cellRangeAddress, sheet, sheet.getWorkbook());
				RegionUtil.setBorderLeft(borderMediumDashed, cellRangeAddress, sheet, sheet.getWorkbook());
				RegionUtil.setBorderRight(borderMediumDashed, cellRangeAddress, sheet, sheet.getWorkbook());
				celda.setCellStyle(style);

				// sheet.addMergedRegion(cellRangeAddress);
				if (param.getFechaInicio() != null && param.getFechaFin() != null) {
					fila = sheet.createRow(3);
					celda = fila.createCell(0);
					celda.setCellValue("Fecha Inicio: " + ' ' + param.getFechaInicio().toString());

					fila = sheet.createRow(4);
				 celda = fila.createCell(0);
				 celda.setCellValue("Fecha Fin: " + ' ' + param.getFechaFin().toString());
				}

				if (param.getPrensa() != null) {

					fila = sheet.createRow(5);
					celda = fila.createCell(0);
					celda.setCellValue("Prensa: " + ' ' + param.getPrensaDesc());
				}

				if (param.getTipoLadrillo() != null) {
					fila = sheet.createRow(6);
					celda = fila.createCell(0);
					celda.setCellValue("Tipo Ladrillo: " + ' ' + param.getTipoLadrilloDesc());
				}
					
				// iniciamos variables

				// Un arreglo con los nombres de los meses del año

				fila = sheet.createRow(7);
				String[] meses = { "", "Codigo", "Fecha Registro", "Prensa", "Tipo Ladrillo", "Cantidad Producida",
						"Cantidad Estimada", "Diferencia" };

				for (int i = 1; i < meses.length; i++) {

					celda = fila.createCell(i);
					// Indicamos que valor debe tener
					celda.setCellValue(meses[i]);
					celda.setCellStyle(style);
					sheet.setColumnWidth(i, tamano);

				}

				int numeroFila = 8;
				for (ListaProductoInicialOutputDto getListaProductoInicial : listaInicial) {
					fila = sheet.createRow(numeroFila);

					celda = fila.createCell(1);
					celda.setCellValue(getListaProductoInicial.getCodigoProductoInicial());
					celda.setCellStyle(style2);

					celda = fila.createCell(2);
					celda.setCellValue(getListaProductoInicial.getFechaRegistroDesc());
					celda.setCellStyle(style2);

					celda = fila.createCell(3);
					celda.setCellValue(getListaProductoInicial.getPrensaDesc());
					celda.setCellStyle(style2);

					celda = fila.createCell(4);
					celda.setCellValue(getListaProductoInicial.getTipoLadrilloDesc());
					celda.setCellStyle(style2);

					celda = fila.createCell(5);
					celda.setCellValue(getListaProductoInicial.getCantidadProducido());
					celda.setCellStyle(style2);

					celda = fila.createCell(6);
					celda.setCellValue(getListaProductoInicial.getCantidadEstimada());
					celda.setCellStyle(style2);

					celda = fila.createCell(7);
					celda.setCellValue(getListaProductoInicial.getCantidadEstimada() - getListaProductoInicial.getCantidadProducido());
					celda.setCellStyle(style2);

						

					numeroFila++;

				}

				autoSizeColumns(workbook);

				byte[] fileContent = null;

				// Ahora almacenaremos el archivo en disco
				try {

					File archivo = File.createTempFile("formatoProgramaInversiones", ".xls");

					FileOutputStream out = new FileOutputStream(archivo);
					workbook.write(out);
					out.close();
					fileContent = Files.readAllBytes(archivo.toPath());

				} catch (IOException e) {
					System.err.println("ERROR AL CREAR EL ARCHIVO!");
					e.printStackTrace();
				}

				System.out.println("Reporte generado");

				Respuesta resp = new Respuesta<>();
				resp.setSuccess(true);
				resp.setMessage("Se listo correctamente los datos");
				resp.setDato(fileContent);
				// resp.setDato(listaInicial);
				return resp;
			} else {
				Respuesta resp = new Respuesta<>();
				resp.setSuccess(false);
				resp.setMessage("No se encontró registros");
				return resp;
			}

		

	}

	public void autoSizeColumns(Workbook workbook) {
		int numberOfSheets = workbook.getNumberOfSheets();
		for (int i = 0; i < numberOfSheets; i++) {
			Sheet sheet = workbook.getSheetAt(i);
			if (sheet.getPhysicalNumberOfRows() > 0) {
				Row row = sheet.getRow(sheet.getFirstRowNum());
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					int columnIndex = cell.getColumnIndex();
					sheet.autoSizeColumn(columnIndex);
				}
			}
		}
	}

	@Override
	@Transactional
	public Respuesta<?> listarProductoTerminadoExcel(ProductoTerminadoInputDto param) throws Exception {

		
			ProductoTerminado productoTerminado = new ProductoTerminado();
			productoTerminado.setIdProductoTerminado(param.getIdProductoTerminado());
			productoTerminado.setFechaInicio(param.getFechaInicio());
			productoTerminado.setFechaFin(param.getFechaFin());
			productoTerminado.setHorno(param.getHorno());

			List<ProductoTerminado> listaProductoTerminado = productoTerminadoMapper
					.listarProductoTerminado(productoTerminado);


			List<ListaProductoTerminadoOutputDto> lista = new ArrayList<>();


			if (listaProductoTerminado != null && !listaProductoTerminado.isEmpty()) {
				ListaProductoTerminadoOutputDto e = new ListaProductoTerminadoOutputDto();

				for (ProductoTerminado element : listaProductoTerminado) {
					e = new ListaProductoTerminadoOutputDto();
					e.setIdProductoTerminado(element.getIdProductoTerminado());
					e.setDescHorno(element.getDescHorno());
					e.setHorno(element.getHorno());
					e.setFechaRegistro(element.getFechaRegistro());
					e.setDescFechaRegistro(element.getDescFechaRegistro());
					e.setTotal(element.getTotal());
					e.setPaquete(element.getPaquete());
					// e.setDescStock(element.getDescStock());
					e.setDescripcionActivo(element.getDescripcionActivo());
					// e.setCrudo(element.getCrudo());
					e.setActivo(element.getActivo());
					e.setCodigo(element.getCodigo());

					DetalleProductoTerminado detalle = new DetalleProductoTerminado();
					detalle.setIdProductoTerminado(element.getIdProductoTerminado());
					List<DetalleProductoTerminado> listaDetalle = detProductoTerminadoMapper
							.listarDetProductoTerminado(detalle);

					if (listaDetalle != null && !listaDetalle.isEmpty()) {
						ListarDetProductoTerminadoOutputDto d = new ListarDetProductoTerminadoOutputDto();
						List<ListarDetProductoTerminadoOutputDto> detalle2 = new ArrayList<>();
						for (DetalleProductoTerminado element2 : listaDetalle) {
							d = new ListarDetProductoTerminadoOutputDto();

							d.setIdDetalleProductoTerminado(element2.getIdDetalleProductoTerminado());
							d.setIdProductoTerminado(element2.getIdProductoTerminado());
							d.setCodigoLadrillo(element2.getCodigoLadrillo());
							d.setDescripcionTipoLadrillo(element2.getDescripcionTipoLadrillo());
							d.setCodigoEstado(element2.getCodigoEstado());
							d.setDescripcionEstado(element2.getDescripcionEstado());
							d.setDescripcionActivo(element2.getDescripcionActivo());
							d.setActivo(element2.getActivo());
							d.setCantidadCrudo(element2.getCantidadCrudo());
							d.setCrudo(element2.getCrudo());
							d.setTotal(element2.getTotal());
							d.setCantidadPaquete(element2.getCantidadPaquete());
							d.setIdVenta(element2.getIdVenta());
							detalle2.add(d);
						}

						e.setLista(detalle2);

					}

					lista.add(e);

				}

				// Creamos nuestro libro de excel
				HSSFWorkbook workbook = new HSSFWorkbook();
				// Le damos color al fondo
				HSSFFont blueFont = workbook.createFont();

				blueFont.setBoldweight(Font.BOLDWEIGHT_NORMAL);

				HSSFCellStyle style = workbook.createCellStyle();
				HSSFCellStyle style2 = workbook.createCellStyle();

				style.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);
				style.setBorderTop(XSSFCellStyle.BORDER_MEDIUM);
				style.setBorderRight(XSSFCellStyle.BORDER_MEDIUM);
				style.setBorderLeft(XSSFCellStyle.BORDER_MEDIUM);

				style2.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);
				style2.setBorderTop(XSSFCellStyle.BORDER_MEDIUM);
				style2.setBorderRight(XSSFCellStyle.BORDER_MEDIUM);
				style2.setBorderLeft(XSSFCellStyle.BORDER_MEDIUM);
				style2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

				style.setFont(blueFont);

				// centrar
				style.setAlignment(style.ALIGN_CENTER);
				style2.setAlignment(style2.ALIGN_CENTER);

				// iniciamos variables
				int numeroColumna = 6;
				int tamano = 8000;

				// Creamos una hoja de calculo llama "Reporte" en dicho libro de excel
				HSSFSheet sheet = workbook.createSheet("Reporte Producto Terminado");

				sheet.setColumnWidth(numeroColumna, tamano);

				CreationHelper helper = workbook.getCreationHelper();

				// Creates the top-level drawing patriarch.
				Drawing drawing = sheet.createDrawingPatriarch();

				// Create an anchor that is attached to the worksheet
				ClientAnchor anchor = helper.createClientAnchor();
				// set top-left corner for the image
				anchor.setCol1(0); // Column B
				anchor.setRow1(0); // Row 3 abajo
				anchor.setCol2(0); // Column C
				anchor.setRow2(1); // Row 4
				double scale = 0.1;

				// sheet.addMergedRegion(new CellRangeAddress(1,1,1,3));
				Row fila = sheet.createRow(1);
				Cell celda = fila.createCell(3);
				celda.setCellValue("REPORTE PRODUCTO TERMINADO");
				// combinar y centrar
				final int borderMediumDashed = CellStyle.BORDER_MEDIUM;

				celda.setCellStyle(style);
				// sheet.addMergedRegion(cellRangeAddress);
				if (param.getFechaInicio() != null && param.getFechaFin() != null) {
				DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				fila = sheet.createRow(3);
				celda = fila.createCell(0);
				celda.setCellValue("Fecha Inicio: " + ' ' + formatter.format(param.getFechaInicio()));

				fila = sheet.createRow(4);
				celda = fila.createCell(0);
				celda.setCellValue("Fecha Fin: " + ' ' + formatter.format(param.getFechaFin()));


				}

				if (param.getHorno() != null) {
				fila = sheet.createRow(5);
				celda = fila.createCell(0);
				celda.setCellValue("Horno: " + ' ' + param.getDescHorno());
				}
	

				fila = sheet.createRow(6);
				String[] meses = { "", "Codigo", "Horno", "Fecha Registro", "Paquete", "Contenido" };

				for (int i = 1; i < meses.length; i++) {
					// Creamos una fila en la posicion indicada por el contador del ciclo

					// Creamos la celda para el nombre del mes, en la primera posicion de la fila
					celda = fila.createCell(i);
					// Indicamos que valor debe tener
					celda.setCellValue(meses[i]);
					celda.setCellStyle(style);
					sheet.setColumnWidth(i, tamano);

				}

				CellRangeAddress cellRangeAddress2 = null;
				int numeroFila = 7;
				int numeroFila2 = 7;
				String ladrillos = "ladrillos";

				for (ListaProductoTerminadoOutputDto getListaProductoTerminado : lista) {
					numeroFila2 = numeroFila;
					fila = sheet.createRow(numeroFila);

					celda = fila.createCell(1);
					celda.setCellValue(getListaProductoTerminado.getCodigo());
					celda.setCellStyle(style2);

					celda = fila.createCell(2);
					celda.setCellValue(getListaProductoTerminado.getDescHorno());
					celda.setCellStyle(style2);

					celda = fila.createCell(3);
					celda.setCellValue(getListaProductoTerminado.getDescFechaRegistro());
					celda.setCellStyle(style2);

					celda = fila.createCell(4);
					celda.setCellValue(getListaProductoTerminado.getPaquete());
					celda.setCellStyle(style2);


					Integer numero = 0;
					for (ListarDetProductoTerminadoOutputDto listaDetalle : getListaProductoTerminado.getLista()) {
						if (numero != 0) {
							fila = sheet.createRow(numeroFila);
						}

						celda = fila.createCell(5);
						celda.setCellValue(listaDetalle.getTotal().toString() + ' ' + ladrillos + ' '
								+ listaDetalle.getDescripcionTipoLadrillo() + ' '
								+ listaDetalle.getDescripcionEstado());
						celda.setCellStyle(style2);

					cellRangeAddress2 = new CellRangeAddress(numeroFila2, getListaProductoTerminado.getLista().size() + numeroFila2 - 1, 1, 1);
					sheet.addMergedRegion(cellRangeAddress2);
					borderRegionUtil(cellRangeAddress2, sheet, workbook);

					cellRangeAddress2 = new CellRangeAddress(numeroFila2, getListaProductoTerminado.getLista().size() + numeroFila2 - 1, 2, 2);
					sheet.addMergedRegion(cellRangeAddress2);
					borderRegionUtil(cellRangeAddress2, sheet, workbook);

					cellRangeAddress2 = new CellRangeAddress(numeroFila2, getListaProductoTerminado.getLista().size() + numeroFila2 - 1, 3, 3);
					sheet.addMergedRegion(cellRangeAddress2);
					borderRegionUtil(cellRangeAddress2, sheet, workbook);

					cellRangeAddress2 = new CellRangeAddress(numeroFila2, getListaProductoTerminado.getLista().size() + numeroFila2 - 1, 4, 4);
					sheet.addMergedRegion(cellRangeAddress2);
					borderRegionUtil(cellRangeAddress2, sheet, workbook);


					
					numeroFila++;
					numero++;

				}

				}

				autoSizeColumns(workbook);

				byte[] fileContent = null;

				// Ahora almacenaremos el archivo en disco
				try {

					File archivo = File.createTempFile("formatoProgramaInversiones", ".xls");

					FileOutputStream out = new FileOutputStream(archivo);
					workbook.write(out);
					out.close();
					fileContent = Files.readAllBytes(archivo.toPath());

				} catch (IOException q) {
					System.err.println("ERROR AL CREAR EL ARCHIVO!");
					q.printStackTrace();
				}

				System.out.println("Reporte generado");

				Respuesta resp = new Respuesta<>();
				resp.setSuccess(true);
				resp.setMessage("Se listó correctamente");
				resp.setDato(fileContent);
				return resp;
			} else {
				Respuesta resp = new Respuesta<>();
				resp.setSuccess(false);
				resp.setMessage("No se encontró registros");
				return resp;
			}
		

	}

		
	
		

	@Override
	public Respuesta<?> listarQuemaProductoExcel(ListarQuemaProductoInputDto param) throws Exception {
		QuemaProducto quemaProducto = new QuemaProducto();
		quemaProducto.setIdQuemaProducto(param.getIdQuemaProducto());
		quemaProducto.setFechaInicio(param.getFechaInicio());
		quemaProducto.setFechaFin(param.getFechaFin());
		quemaProducto.setHorno(param.getHorno());

		List<QuemaProducto> listaQuemaProducto = quemaProductoMapper.listarQuemaProducto(quemaProducto);

		List<ListaQuemaProductoOutputDto> lista = new ArrayList<>();

		if (listaQuemaProducto != null && !listaQuemaProducto.isEmpty()) {
			ListaQuemaProductoOutputDto e = new ListaQuemaProductoOutputDto();
			for (QuemaProducto element : listaQuemaProducto) {
				e = new ListaQuemaProductoOutputDto();
				e.setIdQuemaProducto(element.getIdQuemaProducto());
				e.setDescHorno(element.getDescHorno());
				e.setDescFechaRegistro(element.getDescFechaRegistro());
				e.setCantidadPaquete(element.getCantidadPaquete());
				e.setObservacion(element.getObservacion());
				e.setDescripcionEstado(element.getDescripcionEstado());
				e.setCodigo(element.getCodigo());
				e.setActivo(element.getActivo());
				e.setHorno(element.getHorno());
				e.setFechaRegistro(element.getFechaRegistro());
				e.setDescFechaInicio(element.getDescFechaInicio());
				e.setDescFechaFin(element.getDescFechaFin());

				DetalleQuemaProducto detalle = new DetalleQuemaProducto();
				detalle.setIdQuemaProducto(element.getIdQuemaProducto());
				List<DetalleQuemaProducto> listaDetalle = detQuemaProductoMapper.listarDetQuemaProducto(detalle);

				if (listaDetalle != null && !listaDetalle.isEmpty()) {
					ListarDetQuemaProductoOutputDto d = new ListarDetQuemaProductoOutputDto();
					for (DetalleQuemaProducto element2 : listaDetalle) {
						d = new ListarDetQuemaProductoOutputDto();
						if (element2.getTipo() == 1) {
							d.setIdDetalleQuemaProducto(element2.getIdDetalleQuemaProducto());
							d.setIdQuemaProducto(element2.getIdQuemaProducto());
							d.setLado(element2.getLado());
							d.setDescLado(element2.getDescLado());
							d.setActivo(element2.getActivo());
							d.setFechaDetalle(element2.getFechaDetalle());
							d.setTipo(element2.getTipo());
							e.setInicio(d);
						}

						if (element2.getTipo() == 2) {
							d.setIdDetalleQuemaProducto(element2.getIdDetalleQuemaProducto());
							d.setIdQuemaProducto(element2.getIdQuemaProducto());
							d.setLado(element2.getLado());
							d.setDescLado(element2.getDescLado());
							d.setActivo(element2.getActivo());
							d.setFechaDetalle(element2.getFechaDetalle());
							d.setTipo(element2.getTipo());
							e.setFin(d);
						}
					}
				}

				QuemaProductoPersona quemaProductoPersona = new QuemaProductoPersona();
				quemaProductoPersona.setIdQuemaProducto(element.getIdQuemaProducto());
				List<QuemaProductoPersona> listaQuemaProductoPersona = quemaProductoPersonaMapper
						.listarQuemaProductoPersona(quemaProductoPersona);

				List<ListarQuemaProductoPersonaOutputDto> listaPersona = new ArrayList<>();

				if (listaQuemaProductoPersona != null && !listaQuemaProductoPersona.isEmpty()) {
					ListarQuemaProductoPersonaOutputDto per = new ListarQuemaProductoPersonaOutputDto();
					for (QuemaProductoPersona element3 : listaQuemaProductoPersona) {
						per = new ListarQuemaProductoPersonaOutputDto();
						per.setIdQuemaProductoPersona(element3.getIdQuemaProductoPersona());
						per.setIdQuemaProducto(element3.getIdQuemaProducto());
						per.setDni(element3.getDni());
						per.setApellidoPaterno(element3.getApellidoPaterno());
						per.setApellidoMaterno(element3.getApellidoMaterno());
						per.setNombres(element3.getNombres());
						per.setActivo(element3.getActivo());
						per.setTipoPersona(element3.getTipoPersona());
						listaPersona.add(per);
					}

					e.setListaPersona(listaPersona);
				}

				lista.add(e);
			}

			// Creamos nuestro libro de excel
			HSSFWorkbook workbook = new HSSFWorkbook();
			// Le damos color al fondo
			HSSFFont blueFont = workbook.createFont();

			blueFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
			//blueFont.setFontHeightInPoints((short)24.5);

			HSSFCellStyle style = workbook.createCellStyle();
			HSSFCellStyle style2 = workbook.createCellStyle();

			style.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);
			style.setBorderTop(XSSFCellStyle.BORDER_MEDIUM);
			style.setBorderRight(XSSFCellStyle.BORDER_MEDIUM);
			style.setBorderLeft(XSSFCellStyle.BORDER_MEDIUM);

			style2.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);
			style2.setBorderTop(XSSFCellStyle.BORDER_MEDIUM);
			style2.setBorderRight(XSSFCellStyle.BORDER_MEDIUM);
			style2.setBorderLeft(XSSFCellStyle.BORDER_MEDIUM);
			style2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

			style.setFont(blueFont);

			// centrar
			style.setAlignment(style.ALIGN_CENTER);
			style2.setAlignment(style2.ALIGN_CENTER);

			// iniciamos variables
			int numeroColumna = 6;
			int tamano = 8000;

			// CellRangeAddress cellRangeAddress = new CellRangeAddress(1, 1, 1, 3);//
			// Merges the cells

			// Creamos una hoja de calculo llama "Reporte" en dicho libro de excel
			HSSFSheet sheet = workbook.createSheet("Reporte Quema Producto");

			sheet.setColumnWidth(numeroColumna, tamano);

			CreationHelper helper = workbook.getCreationHelper();

			ClientAnchor anchor = helper.createClientAnchor();
			// set top-left corner for the image
			anchor.setCol1(0); // Column B
			anchor.setRow1(0); // Row 3 abajo
			anchor.setCol2(0); // Column C
			anchor.setRow2(1); // Row 4
			double scale = 0.1;

		
			Row fila = sheet.createRow(1);
			Cell celda = fila.createCell(4);
			celda.setCellValue("REPORTE QUEMA PRODUCTO");
			
			final int borderMediumDashed = CellStyle.BORDER_MEDIUM;

			celda.setCellStyle(style);
 

			if (param.getFechaInicio() != null && param.getFechaFin() != null) {
				DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				String strFechaInicio = formatter.format(param.getFechaInicio());
				String strFechaFin = formatter.format(param.getFechaFin());

				fila = sheet.createRow(2);
				celda = fila.createCell(1);
				celda.setCellValue("Fecha Inicio: " + strFechaInicio);

				fila = sheet.createRow(3);
				celda = fila.createCell(1);
				celda.setCellValue("Fecha Fin: " + strFechaFin);
			}

			if (param.getHorno() != null) {
				fila = sheet.createRow(4);
				celda = fila.createCell(1);
				celda.setCellValue("Horno: " + param.getDescHorno());	
			}


			fila = sheet.createRow(6);
			String[] meses = { "", "Fecha Registro", "HORNO", "Cantidad Paquete", "Fecha Inicio Quema",
					"Fecha Fin Quema", "Observación", "Personas" };

			for (int i = 1; i < meses.length; i++) {

				celda = fila.createCell(i);

				celda.setCellValue(meses[i]);
				celda.setCellStyle(style);
				sheet.setColumnWidth(i, tamano);

			}
 
			CellRangeAddress cellRangeAddress2 = null;
			 
			int numeroFila = 7;
			int numeroFila2 = 7;
			for (ListaQuemaProductoOutputDto element : lista) {
				numeroFila2 = numeroFila;
				fila = sheet.createRow(numeroFila);

				celda = fila.createCell(1);
				celda.setCellValue(element.getDescFechaRegistro());		
				celda.setCellStyle(style2);

				celda = fila.createCell(2);
				celda.setCellValue(element.getDescHorno());			
				celda.setCellStyle(style2);

				celda = fila.createCell(3);
				celda.setCellValue(element.getCantidadPaquete());		
				celda.setCellStyle(style2);

				celda = fila.createCell(4);
				celda.setCellValue(element.getDescFechaInicio());	
				celda.setCellStyle(style2);			

				celda = fila.createCell(5);
				celda.setCellValue(element.getDescFechaFin());
				celda.setCellStyle(style2);
			
				celda = fila.createCell(6);
				celda.setCellValue(element.getObservacion());
				celda.setCellStyle(style2);
				

				Integer numero = 0;
				for (ListarQuemaProductoPersonaOutputDto persona : element.getListaPersona()) {

					if (numero != 0) {
						fila = sheet.createRow(numeroFila);
					}

	

					celda = fila.createCell(7);
					celda.setCellValue(persona.getNombres() + " " + persona.getApellidoPaterno() + " "
							+ persona.getApellidoMaterno());
					celda.setCellStyle(style2);

					

					numeroFila++;
					numero++;
				}


				cellRangeAddress2 = new CellRangeAddress(numeroFila2, element.getListaPersona().size() + numeroFila2 - 1, 1, 1);
				sheet.addMergedRegion(cellRangeAddress2);
				borderRegionUtil(cellRangeAddress2, sheet, workbook);

				cellRangeAddress2 = new CellRangeAddress(numeroFila2, element.getListaPersona().size() + numeroFila2 - 1, 2, 2);
				sheet.addMergedRegion(cellRangeAddress2);
				borderRegionUtil(cellRangeAddress2, sheet, workbook);

				cellRangeAddress2 = new CellRangeAddress(numeroFila2, element.getListaPersona().size() + numeroFila2 - 1, 3, 3);
				sheet.addMergedRegion(cellRangeAddress2);
				borderRegionUtil(cellRangeAddress2, sheet, workbook);

				cellRangeAddress2 = new CellRangeAddress(numeroFila2, element.getListaPersona().size() + numeroFila2 - 1, 4, 4);
				sheet.addMergedRegion(cellRangeAddress2);
				borderRegionUtil(cellRangeAddress2, sheet, workbook);

				cellRangeAddress2 = new CellRangeAddress(numeroFila2, element.getListaPersona().size() + numeroFila2 - 1, 5, 5);
				sheet.addMergedRegion(cellRangeAddress2);
				borderRegionUtil(cellRangeAddress2, sheet, workbook);

				cellRangeAddress2 = new CellRangeAddress(numeroFila2, element.getListaPersona().size() + numeroFila2 - 1, 6, 6);
				sheet.addMergedRegion(cellRangeAddress2);
				borderRegionUtil(cellRangeAddress2, sheet, workbook);
 
			}

			autoSizeColumns(workbook);

			byte[] fileContent = null;

			// Ahora almacenaremos el archivo en disco
			try {

				File archivo = File.createTempFile("formatoProgramaInversiones", ".xls");

				FileOutputStream out = new FileOutputStream(archivo);
				workbook.write(out);
				out.close();
				fileContent = Files.readAllBytes(archivo.toPath());

			} catch (IOException o) {
				System.err.println("ERROR AL CREAR EL ARCHIVO!");
				o.printStackTrace();
			}

			System.out.println("Reporte generado");

			Respuesta resp = new Respuesta<>();
			resp.setSuccess(true);
			resp.setMessage("Se listo correctamente los datos");
			resp.setDato(fileContent);
			return resp;
		} else {
			Respuesta resp = new Respuesta<>();
			resp.setSuccess(false);
			resp.setMessage("No se encontró registros");
			return resp;
		}

	}


	private static void borderRegionUtil(CellRangeAddress region,Sheet sheet, Workbook wb){
		sheet.addMergedRegion(region);
	
		final short borderMediumDashed = CellStyle.BORDER_MEDIUM;
		RegionUtil.setBorderBottom(borderMediumDashed, region, sheet, wb);
		RegionUtil.setBorderTop(borderMediumDashed, region, sheet, wb);
		RegionUtil.setBorderLeft(borderMediumDashed, region, sheet, wb);
		RegionUtil.setBorderRight(borderMediumDashed, region, sheet, wb);
	}
	

	@Override
	public Respuesta<?> listarVentaExcel(ListarVentaInputDto param) throws Exception {
		Venta venta = new Venta();
		venta.setIdVenta(param.getIdVenta());
		venta.setFechaInicio(param.getFechaInicio());
		venta.setFechaFin(param.getFechaFin());
		venta.setEstadoVenta(param.getEstadoVenta());
		List<Venta> listaVenta = ventaMapper.listarVenta(venta);

		List<ListaVentaOutputDto> outputDto = new ArrayList<>();

		if (listaVenta != null && !listaVenta.isEmpty()) {
			ListaVentaOutputDto vent = new ListaVentaOutputDto();
			for (Venta element : listaVenta) {
				vent = new ListaVentaOutputDto();
				vent.setIdVenta(element.getIdVenta());
				vent.setCodigo(element.getCodigo());
				vent.setFechaRegistro(element.getFechaRegistro());
				vent.setTipoDocumento(element.getTipoDocumento());
				vent.setNumeroDocumento(element.getNumeroDocumento());
				vent.setMetodoPago(element.getMetodoPago());
				vent.setPendienteRecojo(element.getPendienteRecojo());
				vent.setCostoTotal(element.getCostoTotal());
				vent.setRazonSocial(element.getRazonSocial());
				vent.setNombres(element.getNombres());
				vent.setApellidoPaterno(element.getApellidoPaterno());
				vent.setApellidoMaterno(element.getApellidoMaterno());
				vent.setTipoVehiculo(element.getTipoVehiculo());
				vent.setPlacaVehiculo(element.getPlacaVehiculo());
				vent.setEstadoVenta(element.getEstadoVenta());
				vent.setObservacion(element.getObservacion());
				vent.setActivo(element.getActivo());
				vent.setDescEstadoVenta(element.getDescEstadoVenta());
				vent.setDescripcionActivo(element.getDescripcionActivo());
				vent.setDescFechaRegistro(element.getDescFechaRegistro());
				vent.setDescTipoDocumento1(element.getDescTipoDocumento1());
				vent.setDescTipoDocumento2(element.getDescTipoDocumento2());

				DetalleVenta detalleVenta = new DetalleVenta();
				detalleVenta.setIdVenta(element.getIdVenta());
				List<DetalleVenta> listaDetalle = detVentaMapper.listarDetalleVenta(detalleVenta);

				List<ListarDetVentaOutputDto> outputDto2 = new ArrayList<>();

				if (listaDetalle != null && !listaDetalle.isEmpty()) {
					ListarDetVentaOutputDto det = new ListarDetVentaOutputDto();
					for (DetalleVenta element2 : listaDetalle) {
						det = new ListarDetVentaOutputDto();
						det.setIdDetalleVenta(element2.getIdDetalleVenta());
						det.setIdVenta(element2.getIdVenta());
						det.setCantidadTotal(element2.getCantidadTotal());
						det.setPrecio(element2.getPrecio());
						det.setTipoLadrillo(element2.getTipoLadrillo());
						det.setEstado(element2.getEstado());
						det.setActivo(element2.getActivo());
						det.setDescTipoLadrillo(element2.getDescTipoLadrillo());
						det.setDescEstadoLadrillo(element2.getDescEstadoLadrillo());

						DetalleProductoVenta detalleProdVenta = new DetalleProductoVenta();
						detalleProdVenta.setIdDetalleVenta(element2.getIdDetalleVenta());
						List<DetalleProductoVenta> listaDetalleProdVenta = detProductoVentaMapper
								.listarDetalleProductoVenta1(detalleProdVenta);

						List<ListarDetProductoVentaOuputDto> ouputDto3 = new ArrayList<>();

						if (listaDetalleProdVenta != null && !listaDetalleProdVenta.isEmpty()) {
							ListarDetProductoVentaOuputDto prod = new ListarDetProductoVentaOuputDto();
							for (DetalleProductoVenta element3 : listaDetalleProdVenta) {
								prod.setIdDetalleProductoVenta(element3.getIdDetalleProductoVenta());
								prod.setIdDetalleVenta(element3.getIdDetalleVenta());
								prod.setIdDetProductoTerminado(element3.getIdDetProductoTerminado());
								prod.setUtilizado(element3.getUtilizado());
								prod.setActivo(element3.getActivo());
								ouputDto3.add(prod);
							}

							det.setListaProductoVenta(ouputDto3);
						}
						outputDto2.add(det);
					}

					vent.setListaDetalle(outputDto2);

				}

				outputDto.add(vent);
			}

			// Creamos nuestro libro de excel
			HSSFWorkbook workbook = new HSSFWorkbook();
			// Le damos color al fondo
			HSSFFont blueFont = workbook.createFont();

			blueFont.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);

			HSSFCellStyle style = workbook.createCellStyle();
			HSSFCellStyle style2 = workbook.createCellStyle();

			style.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);
			style.setBorderTop(XSSFCellStyle.BORDER_MEDIUM);
			style.setBorderRight(XSSFCellStyle.BORDER_MEDIUM);
			style.setBorderLeft(XSSFCellStyle.BORDER_MEDIUM);

			style2.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);
			style2.setBorderTop(XSSFCellStyle.BORDER_MEDIUM);
			style2.setBorderRight(XSSFCellStyle.BORDER_MEDIUM);
			style2.setBorderLeft(XSSFCellStyle.BORDER_MEDIUM);
			style2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			
			style.setFont(blueFont);

			// centrar
			style.setAlignment(style.ALIGN_CENTER);
			style2.setAlignment(style2.ALIGN_CENTER);

			// iniciamos variables
			int numeroColumna = 6;
			int tamano = 8000;

			// CellRangeAddress cellRangeAddress = new CellRangeAddress(1, 1, 1, 3);//
			// Merges the cells

			// Creamos una hoja de calculo llama "Reporte" en dicho libro de excel
			HSSFSheet sheet = workbook.createSheet("Reporte Venta");

			sheet.setColumnWidth(numeroColumna, tamano);

			CreationHelper helper = workbook.getCreationHelper();

			Drawing drawing = sheet.createDrawingPatriarch();

			ClientAnchor anchor = helper.createClientAnchor();
			
			anchor.setCol1(0);
			anchor.setRow1(0);
			anchor.setCol2(0); 
			anchor.setRow2(1); 
			double scale = 0.1;

			Row fila = sheet.createRow(1);
			Cell celda = fila.createCell(5);
			celda.setCellValue("REPORTE VENTA");
			// combinar y centrar
			final int borderMediumDashed = CellStyle.BORDER_MEDIUM;

			celda.setCellStyle(style);

			if (param.getFechaInicio() != null && param.getFechaFin() != null) {
				DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				String strFechaInicio = formatter.format(param.getFechaInicio());
				String strFechaFin = formatter.format(param.getFechaFin());

				fila = sheet.createRow(2);
				celda = fila.createCell(1);
				celda.setCellValue("Fecha Inicio: " + strFechaInicio);

				fila = sheet.createRow(3);
				celda = fila.createCell(1);
				celda.setCellValue("Fecha Fin: " + strFechaFin);

			}

			if (param.getEstadoVenta() != null) {
				fila = sheet.createRow(4);
				celda = fila.createCell(1);
				celda.setCellValue("Estado: " + param.getDescEstadoVenta());
			}

			fila = sheet.createRow(6);
			String[] meses = { "", "ID", "Fecha", "Tipo Documento", "Número Documento",
					"Nombre o Razón Social", "Total a pagar", "Estado", "Contenido" };

			for (int i = 1; i < meses.length; i++) {

				celda = fila.createCell(i);

				celda.setCellValue(meses[i]);
				celda.setCellStyle(style);
				sheet.setColumnWidth(i, tamano);

			}

			CellRangeAddress cellRangeAddress2 = null;

			int numeroFila = 7;
			int numeroFila2 = 7;
			String ladrillos = " ladrillos ";
			for (ListaVentaOutputDto element : outputDto) {

				numeroFila2 = numeroFila;
				fila = sheet.createRow(numeroFila);

				celda = fila.createCell(1);
				celda.setCellValue(element.getCodigo());
				celda.setCellStyle(style2);

				celda = fila.createCell(2);
				celda.setCellValue(element.getDescFechaRegistro());
				celda.setCellStyle(style2);

				celda = fila.createCell(3);
				celda.setCellValue(element.getDescTipoDocumento1());
				celda.setCellStyle(style2);

				celda = fila.createCell(4);
				celda.setCellValue(element.getNumeroDocumento());
				celda.setCellStyle(style2);

				celda = fila.createCell(5);
				celda.setCellValue(element.getDescTipoDocumento2());
				celda.setCellStyle(style2);

				celda = fila.createCell(6);
				celda.setCellValue("S/. " + element.getCostoTotal());
				celda.setCellStyle(style2);

				celda = fila.createCell(7);
				celda.setCellValue(element.getDescEstadoVenta());
				celda.setCellStyle(style2);


				Integer numero = 0;
				for (ListarDetVentaOutputDto detalle : element.getListaDetalle()) {
					if (numero != 0) {
						fila = sheet.createRow(numeroFila);
					}

					celda = fila.createCell(8);
					celda.setCellValue(detalle.getCantidadTotal() + ladrillos + detalle.getDescTipoLadrillo() + " "+ detalle.getDescEstadoLadrillo());
					celda.setCellStyle(style2);
					
					numeroFila++;
					numero++;
				}

				cellRangeAddress2 = new CellRangeAddress(numeroFila2, element.getListaDetalle().size() + numeroFila2 - 1, 1, 1);
				sheet.addMergedRegion(cellRangeAddress2);
				borderRegionUtil(cellRangeAddress2, sheet, workbook);

				cellRangeAddress2 = new CellRangeAddress(numeroFila2, element.getListaDetalle().size() + numeroFila2 - 1, 2, 2);
				sheet.addMergedRegion(cellRangeAddress2);
				borderRegionUtil(cellRangeAddress2, sheet, workbook);

				cellRangeAddress2 = new CellRangeAddress(numeroFila2, element.getListaDetalle().size() + numeroFila2 - 1, 3, 3);
				sheet.addMergedRegion(cellRangeAddress2);
				borderRegionUtil(cellRangeAddress2, sheet, workbook);

				cellRangeAddress2 = new CellRangeAddress(numeroFila2, element.getListaDetalle().size() + numeroFila2 - 1, 4, 4);
				sheet.addMergedRegion(cellRangeAddress2);
				borderRegionUtil(cellRangeAddress2, sheet, workbook);

				cellRangeAddress2 = new CellRangeAddress(numeroFila2, element.getListaDetalle().size() + numeroFila2 - 1, 5, 5);
				sheet.addMergedRegion(cellRangeAddress2);
				borderRegionUtil(cellRangeAddress2, sheet, workbook);

				cellRangeAddress2 = new CellRangeAddress(numeroFila2, element.getListaDetalle().size() + numeroFila2 - 1, 6, 6);
				sheet.addMergedRegion(cellRangeAddress2);
				borderRegionUtil(cellRangeAddress2, sheet, workbook);

				cellRangeAddress2 = new CellRangeAddress(numeroFila2, element.getListaDetalle().size() + numeroFila2 - 1, 7, 7);
				sheet.addMergedRegion(cellRangeAddress2);
				borderRegionUtil(cellRangeAddress2, sheet, workbook);	

			}

			autoSizeColumns(workbook);

			byte[] fileContent = null;

			// Ahora almacenaremos el archivo en disco
			try {

				File archivo = File.createTempFile("formatoProgramaInversiones", ".xls");

				FileOutputStream out = new FileOutputStream(archivo);
				workbook.write(out);
				out.close();
				fileContent = Files.readAllBytes(archivo.toPath());

			} catch (IOException o) {
				System.err.println("ERROR AL CREAR EL ARCHIVO!");
				o.printStackTrace();
			}

			System.out.println("Reporte generado");

			Respuesta resp = new Respuesta<>();
			resp.setSuccess(true);
			resp.setMessage("Se listo correctamente los datos");
			resp.setDato(fileContent);
			return resp;
		} else {
			Respuesta resp = new Respuesta<>();
			resp.setSuccess(false);
			resp.setMessage("No se encontró registros");
			return resp;
		}

	}

	@Override
	public Respuesta<?> reporteProductoInicialPdf(ProductoInicialInputDto param) throws Exception {

			String strLogo = auxReporteService.obtenerImagenOnpeEncode();

			ProductoInicial productoInicial = new ProductoInicial();
			productoInicial.setIdProductoInicial(param.getIdProductoInicial());
			productoInicial.setFechaInicio(param.getFechaInicio());
			productoInicial.setFechaFin(param.getFechaFin());
			productoInicial.setPrensa(param.getPrensa());
			productoInicial.setTipoLadrillo(param.getTipoLadrillo());
			List<ProductoInicial> lista = productoInicialMapper.getListarProductoInicial(productoInicial);

			ReporteProductoInicial reporteProductoInicial = new ReporteProductoInicial();
			List<ReporteProductoInicial.ListaProductoInicial> listaProductoInicial = new ArrayList<>();

			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

			if (param.getFechaInicio() != null && param.getFechaFin() != null) {
				reporteProductoInicial.setFechaInicio(formatter.format(param.getFechaInicio()));
				reporteProductoInicial.setFechaFin(formatter.format(param.getFechaFin()));
			}
			if (param.getPrensa() != null) {
				reporteProductoInicial.setPrensa(param.getPrensaDesc());		
			}

			if (param.getTipoLadrillo() != null) {
				reporteProductoInicial.setTipoLadrillo(param.getTipoLadrilloDesc());
			}

			reporteProductoInicial.setLogo(strLogo);
		
			if (lista != null && !lista.isEmpty()) {
				ReporteProductoInicial.ListaProductoInicial e = new ReporteProductoInicial.ListaProductoInicial();

			for (ProductoInicial element : lista) {

				e = new ReporteProductoInicial.ListaProductoInicial();
				e.setCodigo(element.getCodigoProductoInicial());
				e.setFechaRegistro(element.getFechaRegistroDesc());
				e.setPrensa(element.getPrensaDesc());
				e.setTipoLadrillo(element.getTipoLadrilloDesc());
				e.setCantidadProducida(element.getCantidadProducido());
				e.setCantidadEstimada(element.getCantidadEstimada());
				e.setDiferencia(element.getCantidadEstimada() - element.getCantidadProducido());
				listaProductoInicial.add(e);
			}
				reporteProductoInicial.setListaProductoInicial(listaProductoInicial);

		
			String print = auxReporteService.generarReporte(reporteProductoInicial, VariablesReporte.PRODUCTO_INICIAL);

			Respuesta resp = new Respuesta<>();
			resp.setSuccess(true);
			resp.setMessage("Se creo el reporte correctamente");
			resp.setDato(print);
			return resp;
		} else {
			Respuesta resp = new Respuesta<>();
			resp.setSuccess(false);
			resp.setMessage("No se encontró registros");
			return resp;
		}

	}

	@Override
	public Respuesta<?> reporteQuemaProductoPdf(ListarQuemaProductoInputDto param) throws Exception {

		String strLogo = auxReporteService.obtenerImagenOnpeEncode();

		QuemaProducto quemaProducto = new QuemaProducto();
		quemaProducto.setIdQuemaProducto(param.getIdQuemaProducto());
		quemaProducto.setFechaInicio(param.getFechaInicio());
		quemaProducto.setFechaFin(param.getFechaFin());
		quemaProducto.setHorno(param.getHorno());
		List<QuemaProducto> lista = quemaProductoMapper.listarQuemaProducto(quemaProducto);

		ReporteQuemaProducto reporteQuemaProducto = new ReporteQuemaProducto();
		List<ReporteQuemaProducto.ListaQuema> listaQuema = new ArrayList<>();

		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		if (param.getFechaInicio() != null && param.getFechaFin() != null) {
			reporteQuemaProducto.setFechaInicio(formatter.format(param.getFechaInicio()));
			reporteQuemaProducto.setFechaFin(formatter.format(param.getFechaFin()));
		}

		if (param.getHorno()!=null) {
			reporteQuemaProducto.setHorno(param.getDescHorno());
		}

		reporteQuemaProducto.setLogo(strLogo);

		if (lista != null && !lista.isEmpty()) {
			ReporteQuemaProducto.ListaQuema e = new ReporteQuemaProducto.ListaQuema();
			for (QuemaProducto element : lista) {
				e = new ReporteQuemaProducto.ListaQuema();
				e.setFechaRegistro(element.getDescFechaRegistro());
				e.setHorno(element.getDescHorno());
				e.setCantidadPaquete(element.getCantidadPaquete().toString());
				e.setFechaInicioQuema(element.getDescFechaInicio());
				e.setFechaFinQuema(element.getDescFechaFin());
				e.setObservacion(element.getObservacion());

				QuemaProductoPersona quemaProductoPersona = new QuemaProductoPersona();
				quemaProductoPersona.setIdQuemaProducto(element.getIdQuemaProducto());
				List<QuemaProductoPersona> listaQuemaProductoPersona = quemaProductoPersonaMapper
						.listarQuemaProductoPersona(quemaProductoPersona);

				if (listaQuemaProductoPersona != null && !listaQuemaProductoPersona.isEmpty()) {
					ListarQuemaProductoPersonaOutputDto per = new ListarQuemaProductoPersonaOutputDto();
					for (QuemaProductoPersona element2 : listaQuemaProductoPersona) {
						if (element2.getTipoPersona().equals("TIPPERENC")) {
							e.setPersonaEncargada(element2.getNombres() + " " + element2.getApellidoPaterno() + " "+ element2.getApellidoMaterno());
						}

						if (element2.getTipoPersona().equals("TIPPERAYU")) {
							e.setPersonaAyudante(element2.getNombres() + " " + element2.getApellidoPaterno() + " "+ element2.getApellidoMaterno());
							if (element2.getNombres().equals("") && element2.getApellidoPaterno().equals("") && element2.getApellidoMaterno().equals("")) {
								e.setPersonaAyudante("");
							}
						}

						if (element2.getTipoPersona().equals("TIPPERAYU2")) {
							e.setPersonaAyudante2(element2.getNombres() + " " + element2.getApellidoPaterno() + " "+ element2.getApellidoMaterno());
							if (element2.getNombres().equals("") && element2.getApellidoPaterno().equals("") && element2.getApellidoMaterno().equals("")) {
								e.setPersonaAyudante2("");
							}
						}

					}
				}

				listaQuema.add(e);
			}

			reporteQuemaProducto.setLista(listaQuema);

			String print = auxReporteService.generarReporte(reporteQuemaProducto, VariablesReporte.QUEMA_PRODUCTO);

			Respuesta resp = new Respuesta<>();
			resp.setSuccess(true);
			resp.setMessage("Se creo el reporte correctamente");
			resp.setDato(print);
			return resp;
		} else {
			Respuesta resp = new Respuesta<>();
			resp.setSuccess(false);
			resp.setMessage("No se encontró registros");
			return resp;
		}

	}

	@Override
	public Respuesta<?> reporteVentaPdf(ListarVentaInputDto param) throws Exception {

		String strLogo = auxReporteService.obtenerImagenOnpeEncode();

		Venta venta = new Venta();
        venta.setIdVenta(param.getIdVenta());
        venta.setFechaInicio(param.getFechaInicio());
        venta.setFechaFin(param.getFechaFin());
        venta.setEstadoVenta(param.getEstadoVenta());
        List<Venta> ouputDto = ventaMapper.listarVenta(venta);

		ReporteVenta reporteVenta = new ReporteVenta();
		List<ReporteVenta.ListaVenta> listaVenta = new ArrayList<>();

		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		if (param.getFechaInicio() != null && param.getFechaFin() != null) {
			reporteVenta.setFechaInicio(formatter.format(param.getFechaInicio()));
			reporteVenta.setFechaFin(formatter.format(param.getFechaFin()));
		}

		if (param.getEstadoVenta() != null) {
			reporteVenta.setEstado(param.getDescEstadoVenta());
		}

		reporteVenta.setLogo(strLogo);

        if (ouputDto != null && !ouputDto.isEmpty()) {
            ReporteVenta.ListaVenta e = new ReporteVenta.ListaVenta();
            for (Venta element : ouputDto) {
                e = new ReporteVenta.ListaVenta();
                e.setCodigo(element.getCodigo());
				e.setFecha(element.getDescFechaRegistro());
				e.setTipoDocumento(element.getDescTipoDocumento1());
				e.setNumeroDocumento(element.getNumeroDocumento());
				e.setDescripcion(element.getDescTipoDocumento2());
				e.setTotal(element.getCostoTotal().toString());
				e.setNTotal(element.getCostoTotal());
				e.setEstado(element.getDescEstadoVenta());
                
                DetalleVenta detalleVenta = new DetalleVenta();
                detalleVenta.setIdVenta(element.getIdVenta());
                List<DetalleVenta> listaDetalle = detVentaMapper.listarDetalleVenta(detalleVenta);

                List<ReporteVenta.Contenido> listaContenido = new ArrayList<>();

                if (listaDetalle != null && !listaDetalle.isEmpty()) {
                    ReporteVenta.Contenido c = new ReporteVenta.Contenido();
                    for (DetalleVenta element2 : listaDetalle) {
                        c = new ReporteVenta.Contenido();
						c.setCantidadTotal(element2.getCantidadTotal().toString());
						c.setTipoLadrillo(element2.getDescTipoLadrillo());
						c.setEstadoLadrillo(element2.getDescEstadoLadrillo());
						listaContenido.add(c);
                    }
                    e.setContenido(listaContenido);
                }

				listaVenta.add(e);

            }

            reporteVenta.setLista(listaVenta);

			String print = auxReporteService.generarReporte(reporteVenta, VariablesReporte.VENTA);
            
			Respuesta resp = new Respuesta<>();
			resp.setSuccess(true);
			resp.setMessage("Se creo el reporte correctamente");
			resp.setDato(print);
			return resp;
		} else {
			Respuesta resp = new Respuesta<>();
			resp.setSuccess(false);
			resp.setMessage("No se encontró registros");
			return resp;
		}

	}

@Override
	public Respuesta<?> reporteProductoTerminadoPdf(ProductoTerminadoInputDto param) throws Exception {

		String strLogo = auxReporteService.obtenerImagenOnpeEncode();

		ProductoTerminado productoTerminado = new ProductoTerminado();
		productoTerminado.setIdProductoTerminado(param.getIdProductoTerminado());
        productoTerminado.setFechaInicio(param.getFechaInicio());
        productoTerminado.setFechaFin(param.getFechaFin());
		productoTerminado.setHorno(param.getHorno());
		List<ProductoTerminado> productoTerminadoOutput = productoTerminadoMapper.listarProductoTerminado(productoTerminado);

		ReporteProductoTerminado reporteProductoTerminado = new ReporteProductoTerminado();
		List<ReporteProductoTerminado.ListaProductoTerminado> lista = new ArrayList<>();

		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		if (param.getFechaInicio() != null && param.getFechaFin() != null) {
			reporteProductoTerminado.setFechaInicio(formatter.format(param.getFechaInicio()));
			reporteProductoTerminado.setFechaFin(formatter.format(param.getFechaFin()));
		}

		if (param.getHorno() != null) {
			reporteProductoTerminado.setHorno(param.getDescHorno());
		}

		reporteProductoTerminado.setLogo(strLogo);

        if (productoTerminadoOutput != null && !productoTerminadoOutput.isEmpty()) {
            ReporteProductoTerminado.ListaProductoTerminado e = new ReporteProductoTerminado.ListaProductoTerminado();
            for (ProductoTerminado element : productoTerminadoOutput) {
                e = new ReporteProductoTerminado.ListaProductoTerminado();
                e.setCodigo(element.getCodigo());
				e.setHorno(element.getDescHorno());
				e.setFechaRegistro(element.getDescFechaRegistro());
				e.setPaquete(element.getPaquete().toString());

                DetalleProductoTerminado detalleProductoTerminado = new DetalleProductoTerminado();
                detalleProductoTerminado.setIdProductoTerminado(element.getIdProductoTerminado());
                List<DetalleProductoTerminado> listaDetalle = detProductoTerminadoMapper.listarDetProductoTerminado(detalleProductoTerminado);

                List<ReporteProductoTerminado.Contenido> listaContenido = new ArrayList<>();

                if (listaDetalle != null && !listaDetalle.isEmpty()) {
                    ReporteProductoTerminado.Contenido c = new ReporteProductoTerminado.Contenido();
                    for (DetalleProductoTerminado element2 : listaDetalle) {
                        c = new ReporteProductoTerminado.Contenido();
						c.setCantidadTotal(element2.getTotal().toString());
						c.setTipoLadrillo(element2.getDescripcionTipoLadrillo());
						c.setEstadoLadrillo(element2.getDescripcionEstado());
						listaContenido.add(c);
                    }
                    e.setContenido(listaContenido);
                }

				lista.add(e);

            }

            reporteProductoTerminado.setLista(lista);

			String print = auxReporteService.generarReporte(reporteProductoTerminado, VariablesReporte.PRODUCTO_TERMINADO);
            
			Respuesta resp = new Respuesta<>();
			resp.setSuccess(true);
			resp.setMessage("Se creo el reporte correctamente");
			resp.setDato(print);
			return resp;
		} else {
			Respuesta resp = new Respuesta<>();
			resp.setSuccess(false);
			resp.setMessage("No se encontró registros");
			return resp;
		}

	}


}