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

		if (param.getFechaInicio() == null && param.getFechaFin() == null) {

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

				style.setFont(blueFont);

				// centrar
				style.setAlignment(style.ALIGN_CENTER);
				style2.setAlignment(style2.ALIGN_CENTER);

				// iniciamos variables
				int numeroColumna = 6;
				int tamano = 5000;

				// CellRangeAddress cellRangeAddress = new CellRangeAddress(1, 1, 1, 3);//
				// Merges the cells
				CellRangeAddress cellRangeAddress = new CellRangeAddress(1, 1, 2, 5);

				// Creamos una hoja de calculo llama "Reporte" en dicho libro de excel
				HSSFSheet sheet = workbook.createSheet("Reporte Producto Inicial");

				sheet.setColumnWidth(numeroColumna, tamano);

				sheet.addMergedRegion(cellRangeAddress);

				// Resource resource = new ClassPathResource("assets/logoEmpresa.jpeg");
				// InputStream inputStream = resource.getInputStream();

				// byte[] bytes = IOUtils.toByteArray(inputStream);

				// int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
				// inputStream.close();

				// Returns an object that handles instantiating concrete classes
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

				// Creates a picture
				// Picture pict = drawing.createPicture(anchor,pictureIdx);
				// Reset the image to the original size
				// pict.resize(scale);

				// sheet.addMergedRegion(new CellRangeAddress(1,1,1,3));
				Row filaTitulo = sheet.createRow(1);
				Cell celda = filaTitulo.createCell(2);
				celda.setCellValue("REPORTE PRODUCTO INICIAL");

				// combinar y centrar
				final int borderMediumDashed = CellStyle.BORDER_MEDIUM;
				RegionUtil.setBorderTop(borderMediumDashed, cellRangeAddress, sheet, sheet.getWorkbook());
				RegionUtil.setBorderBottom(borderMediumDashed, cellRangeAddress, sheet, sheet.getWorkbook());
				RegionUtil.setBorderLeft(borderMediumDashed, cellRangeAddress, sheet, sheet.getWorkbook());
				RegionUtil.setBorderRight(borderMediumDashed, cellRangeAddress, sheet, sheet.getWorkbook());
				celda.setCellStyle(style);

				// sheet.addMergedRegion(cellRangeAddress);

				/*
				 * Row filaTitulo2 =sheet.createRow(3);
				 * Cell celda2 = filaTitulo2.createCell(0);
				 * celda2.setCellValue("Fecha Inicio: " + ' ' +
				 * param.getFechaInicio().toString());
				 * 
				 * Row filaTitulo3 =sheet.createRow(4);
				 * Cell celda3 = filaTitulo3.createCell(0);
				 * celda3.setCellValue("Fecha Fin: " + ' ' + param.getFechaFin().toString());
				 */

				// iniciamos variables

				// Un arreglo con los nombres de los meses del año

				Row filaReporte = sheet.createRow(6);
				String[] meses = { "", "Codigo", "Fecha Registro", "Prensa", "Tipo Ladrillo", "Cantidad Producida",
						"Cantidad Estimada", "Diferencia" };
				// Este ciclo ira creando las filas, una por cada mes del año
				// la variable posicion indicara en que fila nos encontramos, mientras que mes
				// selecionara el nombre del mes.
				// posicion comienza en 1 para compensar que ya usarmos la fila 0 para
				// poner los dias

				for (int i = 1; i < meses.length; i++) {
					// Creamos una fila en la posicion indicada por el contador del ciclo

					// Creamos la celda para el nombre del mes, en la primera posicion de la fila
					Cell celdaMes = filaReporte.createCell(i);
					// Indicamos que valor debe tener
					celdaMes.setCellValue(meses[i]);
					celdaMes.setCellStyle(style);
					sheet.setColumnWidth(i, tamano);

				}

				int numeroFila = 7;
				for (ListaProductoInicialOutputDto getListaProductoInicial : listaInicial) {
					Row filaData = sheet.createRow(numeroFila);

					Cell codigo = filaData.createCell(1);
					codigo.setCellValue(getListaProductoInicial.getCodigoProductoInicial());

					Cell fechaRegistro = filaData.createCell(2);
					fechaRegistro.setCellValue(getListaProductoInicial.getFechaRegistroDesc());

					Cell prensa = filaData.createCell(3);
					prensa.setCellValue(getListaProductoInicial.getPrensaDesc());

					Cell tipoLadrillo = filaData.createCell(4);
					tipoLadrillo.setCellValue(getListaProductoInicial.getTipoLadrilloDesc());

					Cell cantidadProducida = filaData.createCell(5);
					cantidadProducida.setCellValue(getListaProductoInicial.getCantidadProducido());

					Cell cantidadEstimada = filaData.createCell(6);
					cantidadEstimada.setCellValue(getListaProductoInicial.getCantidadEstimada());

					Cell diferencia = filaData.createCell(7);
					diferencia.setCellValue(getListaProductoInicial.getCantidadEstimada()
							- getListaProductoInicial.getCantidadProducido());

					codigo.setCellStyle(style2);
					fechaRegistro.setCellStyle(style2);
					prensa.setCellStyle(style2);
					tipoLadrillo.setCellStyle(style2);
					cantidadProducida.setCellStyle(style2);
					cantidadEstimada.setCellStyle(style2);
					diferencia.setCellStyle(style2);

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

		} else {

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

				// CellRangeAddress cellRangeAddress = new CellRangeAddress(1, 1, 1, 3);//
				// Merges the cells
				CellRangeAddress cellRangeAddress = new CellRangeAddress(1, 1, 2, 5);

				// Creamos una hoja de calculo llama "Reporte" en dicho libro de excel
				HSSFSheet sheet = workbook.createSheet("Reporte Producto Inicial");

				sheet.setColumnWidth(numeroColumna, tamano);

				sheet.addMergedRegion(cellRangeAddress);

				// Resource resource = new ClassPathResource("assets/logoEmpresa.jpeg");
				// InputStream inputStream = resource.getInputStream();

				// byte[] bytes = IOUtils.toByteArray(inputStream);

				// int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
				// inputStream.close();

				// Returns an object that handles instantiating concrete classes
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

				// Creates a picture
				// Picture pict = drawing.createPicture(anchor,pictureIdx);
				// Reset the image to the original size
				// pict.resize(scale);

				// sheet.addMergedRegion(new CellRangeAddress(1,1,1,3));
				Row filaTitulo = sheet.createRow(1);
				Cell celda = filaTitulo.createCell(2);
				celda.setCellValue("REPORTE PRODUCTO INICIAL");

				// combinar y centrar
				final int borderMediumDashed = CellStyle.BORDER_MEDIUM;
				RegionUtil.setBorderTop(borderMediumDashed, cellRangeAddress, sheet, sheet.getWorkbook());
				RegionUtil.setBorderBottom(borderMediumDashed, cellRangeAddress, sheet, sheet.getWorkbook());
				RegionUtil.setBorderLeft(borderMediumDashed, cellRangeAddress, sheet, sheet.getWorkbook());
				RegionUtil.setBorderRight(borderMediumDashed, cellRangeAddress, sheet, sheet.getWorkbook());
				celda.setCellStyle(style);

				// sheet.addMergedRegion(cellRangeAddress);

				Row filaTitulo2 = sheet.createRow(3);
				Cell celda2 = filaTitulo2.createCell(0);
				celda2.setCellValue("Fecha Inicio: " + ' ' + param.getFechaInicio().toString());

				Row filaTitulo3 = sheet.createRow(4);
				Cell celda3 = filaTitulo3.createCell(0);
				celda3.setCellValue("Fecha Fin: " + ' ' + param.getFechaFin().toString());

				// iniciamos variables

				// Un arreglo con los nombres de los meses del año

				Row filaReporte = sheet.createRow(6);
				String[] meses = { "", "Codigo", "Fecha Registro", "Prensa", "Tipo Ladrillo", "Cantidad Producida",
						"Cantidad Estimada", "Diferencia" };
				// Este ciclo ira creando las filas, una por cada mes del año
				// la variable posicion indicara en que fila nos encontramos, mientras que mes
				// selecionara el nombre del mes.
				// posicion comienza en 1 para compensar que ya usarmos la fila 0 para
				// poner los dias

				for (int i = 1; i < meses.length; i++) {
					// Creamos una fila en la posicion indicada por el contador del ciclo

					// Creamos la celda para el nombre del mes, en la primera posicion de la fila
					Cell celdaMes = filaReporte.createCell(i);
					// Indicamos que valor debe tener
					celdaMes.setCellValue(meses[i]);
					celdaMes.setCellStyle(style);
					sheet.setColumnWidth(i, tamano);

				}

				int numeroFila = 7;
				for (ListaProductoInicialOutputDto getListaProductoInicial : listaInicial) {
					Row filaData = sheet.createRow(numeroFila);

					Cell codigo = filaData.createCell(1);
					codigo.setCellValue(getListaProductoInicial.getCodigoProductoInicial());

					Cell fechaRegistro = filaData.createCell(2);
					fechaRegistro.setCellValue(getListaProductoInicial.getFechaRegistroDesc());

					Cell prensa = filaData.createCell(3);
					prensa.setCellValue(getListaProductoInicial.getPrensaDesc());

					Cell tipoLadrillo = filaData.createCell(4);
					tipoLadrillo.setCellValue(getListaProductoInicial.getTipoLadrilloDesc());

					Cell cantidadProducida = filaData.createCell(5);
					cantidadProducida.setCellValue(getListaProductoInicial.getCantidadProducido());

					Cell cantidadEstimada = filaData.createCell(6);
					cantidadEstimada.setCellValue(getListaProductoInicial.getCantidadEstimada());

					Cell diferencia = filaData.createCell(7);
					diferencia.setCellValue(getListaProductoInicial.getCantidadEstimada()
							- getListaProductoInicial.getCantidadProducido());

					codigo.setCellStyle(style2);
					fechaRegistro.setCellStyle(style2);
					prensa.setCellStyle(style2);
					tipoLadrillo.setCellStyle(style2);
					cantidadProducida.setCellStyle(style2);
					cantidadEstimada.setCellStyle(style2);
					diferencia.setCellStyle(style2);

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

		if (param.getFechaInicio() == null && param.getFechaFin() == null) {

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
							d.setNro(element2.getNro());
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

		

				HSSFCellStyle style = workbook.createCellStyle();
				HSSFCellStyle style2 = workbook.createCellStyle();

				style.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);
				style.setBorderTop(XSSFCellStyle.BORDER_MEDIUM);
				style.setBorderRight(XSSFCellStyle.BORDER_MEDIUM);
				style.setBorderLeft(XSSFCellStyle.BORDER_MEDIUM);

			blueFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
    


				style2.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);
				style2.setBorderTop(XSSFCellStyle.BORDER_MEDIUM);
				style2.setBorderRight(XSSFCellStyle.BORDER_MEDIUM);
				style2.setBorderLeft(XSSFCellStyle.BORDER_MEDIUM);
				style2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

				style.setFont(blueFont);

				// centrar
				style.setAlignment(style.ALIGN_CENTER);
				style2.setAlignment(style2.ALIGN_CENTER);
				style2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

				// iniciamos variables
				int numeroColumna = 6;
				int tamano = 8000;

				HSSFSheet sheet = workbook.createSheet("Reporte Producto Terminado");

				sheet.setColumnWidth(numeroColumna, tamano);

				CreationHelper helper = workbook.getCreationHelper();

				Drawing drawing = sheet.createDrawingPatriarch();

				ClientAnchor anchor = helper.createClientAnchor();
				// set top-left corner for the image
				anchor.setCol1(0); // Column B
				anchor.setRow1(0); // Row 3 abajo
				anchor.setCol2(0); // Column C
				anchor.setRow2(1); // Row 4
				double scale = 0.1;

				// Creates a picture
				// Picture pict = drawing.createPicture(anchor,pictureIdx);
				// Reset the image to the original size
				// pict.resize(scale);

				// sheet.addMergedRegion(new CellRangeAddress(1,1,1,3));
				Row filaTitulo = sheet.createRow(1);
				Cell celda = filaTitulo.createCell(3);
				celda.setCellValue("REPORTE PRODUCTO TERMINADO");
				// combinar y centrar
				final int borderMediumDashed = CellStyle.BORDER_MEDIUM;

				celda.setCellStyle(style);
				// sheet.addMergedRegion(cellRangeAddress);

				Row filaReporte = sheet.createRow(5);
				String[] meses = { "", "Codigo", "Horno", "Fecha Registro", "Paquete", "Contenido" };

				for (int i = 1; i < meses.length; i++) {

					Cell celdaMes = filaReporte.createCell(i);
					// Indicamos que valor debe tener
					celdaMes.setCellValue(meses[i]);
					celdaMes.setCellStyle(style);
					sheet.setColumnWidth(i, tamano);

				}

				int numeroFila = 6;
				String ladrillos = "ladrillos";

				for (ListaProductoTerminadoOutputDto getListaProductoTerminado : lista) {

					Row filaData = sheet.createRow(numeroFila);

					Cell codigo = filaData.createCell(1);
					codigo.setCellValue(getListaProductoTerminado.getCodigo());

					Cell horno = filaData.createCell(2);
					horno.setCellValue(getListaProductoTerminado.getDescHorno());

					Cell fechaRegistro = filaData.createCell(3);
					fechaRegistro.setCellValue(getListaProductoTerminado.getDescFechaRegistro());

					Cell paquete = filaData.createCell(4);
					paquete.setCellValue(getListaProductoTerminado.getPaquete());

					codigo.setCellStyle(style2);
					horno.setCellStyle(style2);
					fechaRegistro.setCellStyle(style2);
					paquete.setCellStyle(style2);

					Integer numero = 0;
					for (ListarDetProductoTerminadoOutputDto listaDetalle : getListaProductoTerminado.getLista()) {
						if (numero != 0) {
							filaData = sheet.createRow(numeroFila);
						}

						Cell contenido = filaData.createCell(5);
						contenido.setCellValue(listaDetalle.getTotal().toString() + ' ' + ladrillos + ' '
								+ listaDetalle.getDescripcionTipoLadrillo() + ' '
								+ listaDetalle.getDescripcionEstado());
						CellRangeAddress cellRangeAddress = new CellRangeAddress(numeroFila,
								getListaProductoTerminado.getLista().size() + numeroFila - 1, 1, 1);
						CellRangeAddress cellRangeAddress2 = new CellRangeAddress(numeroFila,
								getListaProductoTerminado.getLista().size() + numeroFila - 1, 2, 2);
						CellRangeAddress cellRangeAddress3 = new CellRangeAddress(numeroFila,
								getListaProductoTerminado.getLista().size() + numeroFila - 1, 3, 3);
						CellRangeAddress cellRangeAddress4 = new CellRangeAddress(numeroFila,
								getListaProductoTerminado.getLista().size() + numeroFila - 1, 4, 4);

						RegionUtil.setBorderTop(borderMediumDashed, cellRangeAddress, sheet, sheet.getWorkbook());
						RegionUtil.setBorderBottom(borderMediumDashed, cellRangeAddress, sheet, sheet.getWorkbook());
						RegionUtil.setBorderLeft(borderMediumDashed, cellRangeAddress, sheet, sheet.getWorkbook());
						RegionUtil.setBorderRight(borderMediumDashed, cellRangeAddress, sheet, sheet.getWorkbook());
						sheet.addMergedRegion(cellRangeAddress);

						RegionUtil.setBorderTop(borderMediumDashed, cellRangeAddress2, sheet, sheet.getWorkbook());
						RegionUtil.setBorderBottom(borderMediumDashed, cellRangeAddress2, sheet, sheet.getWorkbook());
						RegionUtil.setBorderLeft(borderMediumDashed, cellRangeAddress2, sheet, sheet.getWorkbook());
						RegionUtil.setBorderRight(borderMediumDashed, cellRangeAddress2, sheet, sheet.getWorkbook());
						sheet.addMergedRegion(cellRangeAddress2);

						RegionUtil.setBorderTop(borderMediumDashed, cellRangeAddress3, sheet, sheet.getWorkbook());
						RegionUtil.setBorderBottom(borderMediumDashed, cellRangeAddress3, sheet, sheet.getWorkbook());
						RegionUtil.setBorderLeft(borderMediumDashed, cellRangeAddress3, sheet, sheet.getWorkbook());
						RegionUtil.setBorderRight(borderMediumDashed, cellRangeAddress3, sheet, sheet.getWorkbook());
						sheet.addMergedRegion(cellRangeAddress3);

						RegionUtil.setBorderTop(borderMediumDashed, cellRangeAddress4, sheet, sheet.getWorkbook());
						RegionUtil.setBorderBottom(borderMediumDashed, cellRangeAddress4, sheet, sheet.getWorkbook());
						RegionUtil.setBorderLeft(borderMediumDashed, cellRangeAddress4, sheet, sheet.getWorkbook());
						RegionUtil.setBorderRight(borderMediumDashed, cellRangeAddress4, sheet, sheet.getWorkbook());
						sheet.addMergedRegion(cellRangeAddress4);

						contenido.setCellStyle(style2);
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
		} else {

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
							d.setNro(element2.getNro());
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

				// CellRangeAddress cellRangeAddress = new CellRangeAddress(1, 1, 1, 3);//
				// Merges the cells
				/* CellRangeAddress cellRangeAddress = new CellRangeAddress(6,7,1,1); */

				// Creamos una hoja de calculo llama "Reporte" en dicho libro de excel
				HSSFSheet sheet = workbook.createSheet("Reporte Producto Terminado");

				sheet.setColumnWidth(numeroColumna, tamano);

				/* sheet.addMergedRegion(cellRangeAddress); */

				// Resource resource = new ClassPathResource("assets/logoEmpresa.jpeg");
				// InputStream inputStream = resource.getInputStream();

				// byte[] bytes = IOUtils.toByteArray(inputStream);

				// int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
				// inputStream.close();

				// Returns an object that handles instantiating concrete classes
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
				Row filaTitulo = sheet.createRow(1);
				Cell celda = filaTitulo.createCell(3);
				celda.setCellValue("REPORTE PRODUCTO TERMINADO");
				// combinar y centrar
				final int borderMediumDashed = CellStyle.BORDER_MEDIUM;

				celda.setCellStyle(style);
				// sheet.addMergedRegion(cellRangeAddress);
				DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				Row filaTitulo2 = sheet.createRow(3);
				Cell celda2 = filaTitulo2.createCell(0);
				celda2.setCellValue("Fecha Inicio: " + ' ' + formatter.format(param.getFechaInicio()));
				// String celda2 = formatter.format(param.getFechaInicio());

				Row filaTitulo3 = sheet.createRow(4);
				Cell celda3 = filaTitulo3.createCell(0);
				celda3.setCellValue("Fecha Fin: " + ' ' + formatter.format(param.getFechaFin()));

				// iniciamos variables

				// Un arreglo con los nombres de los meses del año

				Row filaReporte = sheet.createRow(6);
				String[] meses = { "", "Codigo", "Horno", "Fecha Registro", "Paquete", "Contenido" };

				for (int i = 1; i < meses.length; i++) {
					// Creamos una fila en la posicion indicada por el contador del ciclo

					// Creamos la celda para el nombre del mes, en la primera posicion de la fila
					Cell celdaMes = filaReporte.createCell(i);
					// Indicamos que valor debe tener
					celdaMes.setCellValue(meses[i]);
					celdaMes.setCellStyle(style);
					sheet.setColumnWidth(i, tamano);

				}

				int numeroFila = 7;
				String ladrillos = "ladrillos";

				for (ListaProductoTerminadoOutputDto getListaProductoTerminado : lista) {

					Row filaData = sheet.createRow(numeroFila);

					Cell codigo = filaData.createCell(1);
					codigo.setCellValue(getListaProductoTerminado.getCodigo());

					Cell horno = filaData.createCell(2);
					horno.setCellValue(getListaProductoTerminado.getDescHorno());

					Cell fechaRegistro = filaData.createCell(3);
					fechaRegistro.setCellValue(getListaProductoTerminado.getDescFechaRegistro());

					Cell paquete = filaData.createCell(4);
					paquete.setCellValue(getListaProductoTerminado.getPaquete());

					codigo.setCellStyle(style2);
					horno.setCellStyle(style2);
					fechaRegistro.setCellStyle(style2);
					paquete.setCellStyle(style2);

					Integer numero = 0;
					for (ListarDetProductoTerminadoOutputDto listaDetalle : getListaProductoTerminado.getLista()) {
						if (numero != 0) {
							filaData = sheet.createRow(numeroFila);
						}

						Cell contenido = filaData.createCell(5);
						contenido.setCellValue(listaDetalle.getTotal().toString() + ' ' + ladrillos + ' '
								+ listaDetalle.getDescripcionTipoLadrillo() + ' '
								+ listaDetalle.getDescripcionEstado());
						CellRangeAddress cellRangeAddress = new CellRangeAddress(numeroFila,
								getListaProductoTerminado.getLista().size() + numeroFila - 1, 1, 1);
						CellRangeAddress cellRangeAddress2 = new CellRangeAddress(numeroFila,
								getListaProductoTerminado.getLista().size() + numeroFila - 1, 2, 2);
						CellRangeAddress cellRangeAddress3 = new CellRangeAddress(numeroFila,
								getListaProductoTerminado.getLista().size() + numeroFila - 1, 3, 3);
						CellRangeAddress cellRangeAddress4 = new CellRangeAddress(numeroFila,
								getListaProductoTerminado.getLista().size() + numeroFila - 1, 4, 4);

						RegionUtil.setBorderTop(borderMediumDashed, cellRangeAddress, sheet, sheet.getWorkbook());
						RegionUtil.setBorderBottom(borderMediumDashed, cellRangeAddress, sheet, sheet.getWorkbook());
						RegionUtil.setBorderLeft(borderMediumDashed, cellRangeAddress, sheet, sheet.getWorkbook());
						RegionUtil.setBorderRight(borderMediumDashed, cellRangeAddress, sheet, sheet.getWorkbook());
						sheet.addMergedRegion(cellRangeAddress);

						RegionUtil.setBorderTop(borderMediumDashed, cellRangeAddress2, sheet, sheet.getWorkbook());
						RegionUtil.setBorderBottom(borderMediumDashed, cellRangeAddress2, sheet, sheet.getWorkbook());
						RegionUtil.setBorderLeft(borderMediumDashed, cellRangeAddress2, sheet, sheet.getWorkbook());
						RegionUtil.setBorderRight(borderMediumDashed, cellRangeAddress2, sheet, sheet.getWorkbook());
						sheet.addMergedRegion(cellRangeAddress2);

						RegionUtil.setBorderTop(borderMediumDashed, cellRangeAddress3, sheet, sheet.getWorkbook());
						RegionUtil.setBorderBottom(borderMediumDashed, cellRangeAddress3, sheet, sheet.getWorkbook());
						RegionUtil.setBorderLeft(borderMediumDashed, cellRangeAddress3, sheet, sheet.getWorkbook());
						RegionUtil.setBorderRight(borderMediumDashed, cellRangeAddress3, sheet, sheet.getWorkbook());
						sheet.addMergedRegion(cellRangeAddress3);

						RegionUtil.setBorderTop(borderMediumDashed, cellRangeAddress4, sheet, sheet.getWorkbook());
						RegionUtil.setBorderBottom(borderMediumDashed, cellRangeAddress4, sheet, sheet.getWorkbook());
						RegionUtil.setBorderLeft(borderMediumDashed, cellRangeAddress4, sheet, sheet.getWorkbook());
						RegionUtil.setBorderRight(borderMediumDashed, cellRangeAddress4, sheet, sheet.getWorkbook());
						sheet.addMergedRegion(cellRangeAddress4);

						contenido.setCellStyle(style2);
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

			// Resource resource = new ClassPathResource("assets/logoEmpresa.jpeg");
			// InputStream inputStream = resource.getInputStream();

			// byte[] bytes = IOUtils.toByteArray(inputStream);

			// int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			// inputStream.close();

			// Returns an object that handles instantiating concrete classes
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
			Row filaTitulo = sheet.createRow(1);
			Cell celda = filaTitulo.createCell(4);
			celda.setCellValue("REPORTE QUEMA PRODUCTO");
			// combinar y centrar
			final int borderMediumDashed = CellStyle.BORDER_MEDIUM;

			celda.setCellStyle(style);

			Row filaT = null;
			Cell celdaT = null;

			if (param.getFechaInicio() != null && param.getFechaFin() != null) {
				DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				String strFechaInicio = formatter.format(param.getFechaInicio());
				String strFechaFin = formatter.format(param.getFechaFin());

				filaT = sheet.createRow(2);
				celdaT = filaT.createCell(0);
				celdaT.setCellValue("Fecha Inicio: " + strFechaInicio);

				filaT = sheet.createRow(3);
				celdaT = filaT.createCell(0);
				celdaT.setCellValue("Fecha Fin: " + strFechaFin);

			}

			// sheet.addMergedRegion(cellRangeAddress);

			Row filaReporte = sheet.createRow(5);
			String[] meses = { "", "Fecha Registro", "HORNO", "Cantidad Paquete", "Fecha Inicio Quema",
					"Fecha Fin Quema", "Observación", "Personas" };

			for (int i = 1; i < meses.length; i++) {
				// Creamos una fila en la posicion indicada por el contador del ciclo

				// Creamos la celda para el nombre del mes, en la primera posicion de la fila
				Cell celdaMes = filaReporte.createCell(i);
				// Indicamos que valor debe tener
				celdaMes.setCellValue(meses[i]);
				celdaMes.setCellStyle(style);
				sheet.setColumnWidth(i, tamano);

			}

			int numeroFila = 6;
			for (ListaQuemaProductoOutputDto element : lista) {
				Row filaData = sheet.createRow(numeroFila);

				Cell fechaRegistro = filaData.createCell(1);
				fechaRegistro.setCellValue(element.getDescFechaRegistro());

				Cell horno = filaData.createCell(2);
				horno.setCellValue(element.getDescHorno());

				Cell cantidadPaquete = filaData.createCell(3);
				cantidadPaquete.setCellValue(element.getCantidadPaquete());

				Cell descfechaInicio = filaData.createCell(4);
				descfechaInicio.setCellValue(element.getDescFechaInicio());

				Cell descfechaFin = filaData.createCell(5);
				descfechaFin.setCellValue(element.getDescFechaFin());

				Cell observacion = filaData.createCell(6);
				observacion.setCellValue(element.getObservacion());

				fechaRegistro.setCellStyle(style2);
				horno.setCellStyle(style2);
				cantidadPaquete.setCellStyle(style2);
				descfechaInicio.setCellStyle(style2);
				descfechaFin.setCellStyle(style2);
				observacion.setCellStyle(style2);

				Integer numero = 0;
				for (ListarQuemaProductoPersonaOutputDto persona : element.getListaPersona()) {

					if (numero != 0) {
						filaData = sheet.createRow(numeroFila);
					}

					Cell descPersona = filaData.createCell(7);
					descPersona.setCellValue(persona.getNombres() + " " + persona.getApellidoPaterno() + " "
							+ persona.getApellidoMaterno());

					descPersona.setCellStyle(style2);

					CellRangeAddress cellRangeAddress = new CellRangeAddress(numeroFila,
							element.getListaPersona().size() + numeroFila - 1, 1, 1);
					CellRangeAddress cellRangeAddress2 = new CellRangeAddress(numeroFila,
							element.getListaPersona().size() + numeroFila - 1, 2, 2);
					CellRangeAddress cellRangeAddress3 = new CellRangeAddress(numeroFila,
							element.getListaPersona().size() + numeroFila - 1, 3, 3);
					CellRangeAddress cellRangeAddress4 = new CellRangeAddress(numeroFila,
							element.getListaPersona().size() + numeroFila - 1, 4, 4);
					CellRangeAddress cellRangeAddress5 = new CellRangeAddress(numeroFila,
							element.getListaPersona().size() + numeroFila - 1, 5, 5);
					CellRangeAddress cellRangeAddress6 = new CellRangeAddress(numeroFila,
					element.getListaPersona().size() + numeroFila - 1, 6, 6);		

					RegionUtil.setBorderTop(borderMediumDashed, cellRangeAddress, sheet, sheet.getWorkbook());
					RegionUtil.setBorderBottom(borderMediumDashed, cellRangeAddress, sheet, sheet.getWorkbook());
					RegionUtil.setBorderLeft(borderMediumDashed, cellRangeAddress, sheet, sheet.getWorkbook());
					RegionUtil.setBorderRight(borderMediumDashed, cellRangeAddress, sheet, sheet.getWorkbook());
					sheet.addMergedRegion(cellRangeAddress);

					RegionUtil.setBorderTop(borderMediumDashed, cellRangeAddress2, sheet, sheet.getWorkbook());
					RegionUtil.setBorderBottom(borderMediumDashed, cellRangeAddress2, sheet, sheet.getWorkbook());
					RegionUtil.setBorderLeft(borderMediumDashed, cellRangeAddress2, sheet, sheet.getWorkbook());
					RegionUtil.setBorderRight(borderMediumDashed, cellRangeAddress2, sheet, sheet.getWorkbook());
					sheet.addMergedRegion(cellRangeAddress2);

					RegionUtil.setBorderTop(borderMediumDashed, cellRangeAddress3, sheet, sheet.getWorkbook());
					RegionUtil.setBorderBottom(borderMediumDashed, cellRangeAddress3, sheet, sheet.getWorkbook());
					RegionUtil.setBorderLeft(borderMediumDashed, cellRangeAddress3, sheet, sheet.getWorkbook());
					RegionUtil.setBorderRight(borderMediumDashed, cellRangeAddress3, sheet, sheet.getWorkbook());
					sheet.addMergedRegion(cellRangeAddress3);

					RegionUtil.setBorderTop(borderMediumDashed, cellRangeAddress4, sheet, sheet.getWorkbook());
					RegionUtil.setBorderBottom(borderMediumDashed, cellRangeAddress4, sheet, sheet.getWorkbook());
					RegionUtil.setBorderLeft(borderMediumDashed, cellRangeAddress4, sheet, sheet.getWorkbook());
					RegionUtil.setBorderRight(borderMediumDashed, cellRangeAddress4, sheet, sheet.getWorkbook());
					sheet.addMergedRegion(cellRangeAddress4);

					RegionUtil.setBorderTop(borderMediumDashed, cellRangeAddress5, sheet, sheet.getWorkbook());
					RegionUtil.setBorderBottom(borderMediumDashed, cellRangeAddress5, sheet, sheet.getWorkbook());
					RegionUtil.setBorderLeft(borderMediumDashed, cellRangeAddress5, sheet, sheet.getWorkbook());
					RegionUtil.setBorderRight(borderMediumDashed, cellRangeAddress5, sheet, sheet.getWorkbook());
					sheet.addMergedRegion(cellRangeAddress5);

					RegionUtil.setBorderTop(borderMediumDashed, cellRangeAddress6, sheet, sheet.getWorkbook());
					RegionUtil.setBorderBottom(borderMediumDashed, cellRangeAddress6, sheet, sheet.getWorkbook());
					RegionUtil.setBorderLeft(borderMediumDashed, cellRangeAddress6, sheet, sheet.getWorkbook());
					RegionUtil.setBorderRight(borderMediumDashed, cellRangeAddress6, sheet, sheet.getWorkbook());
					sheet.addMergedRegion(cellRangeAddress6);

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

			// Resource resource = new ClassPathResource("assets/logoEmpresa.jpeg");
			// InputStream inputStream = resource.getInputStream();

			// byte[] bytes = IOUtils.toByteArray(inputStream);

			// int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			// inputStream.close();

			// Returns an object that handles instantiating concrete classes
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
			Row filaTitulo = sheet.createRow(1);
			Cell celda = filaTitulo.createCell(5);
			celda.setCellValue("REPORTE VENTA");
			// combinar y centrar
			final int borderMediumDashed = CellStyle.BORDER_MEDIUM;

			celda.setCellStyle(style);

			Row filaT = null;
			Cell celdaT = null;

			if (param.getFechaInicio() != null && param.getFechaFin() != null) {
				DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				String strFechaInicio = formatter.format(param.getFechaInicio());
				String strFechaFin = formatter.format(param.getFechaFin());

				filaT = sheet.createRow(2);
				celdaT = filaT.createCell(1);
				celdaT.setCellValue("Fecha Inicio: " + strFechaInicio);

				filaT = sheet.createRow(3);
				celdaT = filaT.createCell(1);
				celdaT.setCellValue("Fecha Fin: " + strFechaFin);

			}

			if (param.getEstadoVenta() != null) {
				filaT = sheet.createRow(4);
				celdaT = filaT.createCell(1);
				celdaT.setCellValue(param.getEstadoVenta().equals("ESTVENPEN") ? "Estado: Venta Pendiente"
						: "Estado: Venta Finalizada");
			}

			// sheet.addMergedRegion(cellRangeAddress);

			Row filaReporte = sheet.createRow(6);
			String[] meses = { "", "ID", "Fecha", "Tipo Documento", "Número Documento",
					"Nombre o Razón Social", "Total a pagar", "Estado", "Contenido" };

			for (int i = 1; i < meses.length; i++) {
				// Creamos una fila en la posicion indicada por el contador del ciclo

				// Creamos la celda para el nombre del mes, en la primera posicion de la fila
				Cell celdaMes = filaReporte.createCell(i);
				// Indicamos que valor debe tener
				celdaMes.setCellValue(meses[i]);
				celdaMes.setCellStyle(style);
				sheet.setColumnWidth(i, tamano);

			}

			int numeroFila = 7;
			String ladrillos = " ladrillos ";
			for (ListaVentaOutputDto element : outputDto) {

				Row filaData = sheet.createRow(numeroFila);

				Cell codigo = filaData.createCell(1);
				codigo.setCellValue(element.getCodigo());

				Cell fechaRegistro = filaData.createCell(2);
				fechaRegistro.setCellValue(element.getDescFechaRegistro());

				Cell tipoDocumento = filaData.createCell(3);
				tipoDocumento.setCellValue(element.getDescTipoDocumento1());

				Cell numeroDocumento = filaData.createCell(4);
				numeroDocumento.setCellValue(element.getNumeroDocumento());

				Cell nombreCompleto = filaData.createCell(5);
				nombreCompleto.setCellValue(element.getDescTipoDocumento2());

				Cell costoTotal = filaData.createCell(6);
				costoTotal.setCellValue("S/. " + element.getCostoTotal());

				Cell estadoVenta = filaData.createCell(7);
				estadoVenta.setCellValue(element.getDescEstadoVenta());

				codigo.setCellStyle(style2);
				fechaRegistro.setCellStyle(style2);
				tipoDocumento.setCellStyle(style2);
				numeroDocumento.setCellStyle(style2);
				nombreCompleto.setCellStyle(style2);
				costoTotal.setCellStyle(style2);
				estadoVenta.setCellStyle(style2);

				Integer numero = 0;
				for (ListarDetVentaOutputDto detalle : element.getListaDetalle()) {
					if (numero != 0) {
						filaData = sheet.createRow(numeroFila);
					}

					Cell contenido = filaData.createCell(8);
					contenido.setCellValue(detalle.getCantidadTotal() + ladrillos + detalle.getDescTipoLadrillo() + " "
							+ detalle.getDescEstadoLadrillo());

					CellRangeAddress cellRangeAddress = new CellRangeAddress(numeroFila,
							element.getListaDetalle().size() + numeroFila - 1, 1, 1);
					CellRangeAddress cellRangeAddress2 = new CellRangeAddress(numeroFila,
							element.getListaDetalle().size() + numeroFila - 1, 2, 2);
					CellRangeAddress cellRangeAddress3 = new CellRangeAddress(numeroFila,
							element.getListaDetalle().size() + numeroFila - 1, 3, 3);
					CellRangeAddress cellRangeAddress4 = new CellRangeAddress(numeroFila,
							element.getListaDetalle().size() + numeroFila - 1, 4, 4);
					CellRangeAddress cellRangeAddress5 = new CellRangeAddress(numeroFila,
							element.getListaDetalle().size() + numeroFila - 1, 5, 5);

					CellRangeAddress cellRangeAddress6 = new CellRangeAddress(numeroFila,
							element.getListaDetalle().size() + numeroFila - 1, 6, 6);

					CellRangeAddress cellRangeAddress7 = new CellRangeAddress(numeroFila,
							element.getListaDetalle().size() + numeroFila - 1, 7, 7);

					sheet.addMergedRegion(cellRangeAddress);

					RegionUtil.setBorderTop(borderMediumDashed, cellRangeAddress, sheet, sheet.getWorkbook());
					RegionUtil.setBorderBottom(borderMediumDashed, cellRangeAddress, sheet, sheet.getWorkbook());
					RegionUtil.setBorderLeft(borderMediumDashed, cellRangeAddress, sheet, sheet.getWorkbook());
					RegionUtil.setBorderRight(borderMediumDashed, cellRangeAddress, sheet, sheet.getWorkbook());
					sheet.addMergedRegion(cellRangeAddress);

					RegionUtil.setBorderTop(borderMediumDashed, cellRangeAddress2, sheet, sheet.getWorkbook());
					RegionUtil.setBorderBottom(borderMediumDashed, cellRangeAddress2, sheet, sheet.getWorkbook());
					RegionUtil.setBorderLeft(borderMediumDashed, cellRangeAddress2, sheet, sheet.getWorkbook());
					RegionUtil.setBorderRight(borderMediumDashed, cellRangeAddress2, sheet, sheet.getWorkbook());
					sheet.addMergedRegion(cellRangeAddress2);

					RegionUtil.setBorderTop(borderMediumDashed, cellRangeAddress3, sheet, sheet.getWorkbook());
					RegionUtil.setBorderBottom(borderMediumDashed, cellRangeAddress3, sheet, sheet.getWorkbook());
					RegionUtil.setBorderLeft(borderMediumDashed, cellRangeAddress3, sheet, sheet.getWorkbook());
					RegionUtil.setBorderRight(borderMediumDashed, cellRangeAddress3, sheet, sheet.getWorkbook());
					sheet.addMergedRegion(cellRangeAddress3);

					RegionUtil.setBorderTop(borderMediumDashed, cellRangeAddress4, sheet, sheet.getWorkbook());
					RegionUtil.setBorderBottom(borderMediumDashed, cellRangeAddress4, sheet, sheet.getWorkbook());
					RegionUtil.setBorderLeft(borderMediumDashed, cellRangeAddress4, sheet, sheet.getWorkbook());
					RegionUtil.setBorderRight(borderMediumDashed, cellRangeAddress4, sheet, sheet.getWorkbook());
					sheet.addMergedRegion(cellRangeAddress4);

					RegionUtil.setBorderTop(borderMediumDashed, cellRangeAddress5, sheet, sheet.getWorkbook());
					RegionUtil.setBorderBottom(borderMediumDashed, cellRangeAddress5, sheet, sheet.getWorkbook());
					RegionUtil.setBorderLeft(borderMediumDashed, cellRangeAddress5, sheet, sheet.getWorkbook());
					RegionUtil.setBorderRight(borderMediumDashed, cellRangeAddress5, sheet, sheet.getWorkbook());
					sheet.addMergedRegion(cellRangeAddress5);

					RegionUtil.setBorderTop(borderMediumDashed, cellRangeAddress6, sheet, sheet.getWorkbook());
					RegionUtil.setBorderBottom(borderMediumDashed, cellRangeAddress6, sheet, sheet.getWorkbook());
					RegionUtil.setBorderLeft(borderMediumDashed, cellRangeAddress6, sheet, sheet.getWorkbook());
					RegionUtil.setBorderRight(borderMediumDashed, cellRangeAddress6, sheet, sheet.getWorkbook());
					sheet.addMergedRegion(cellRangeAddress6);

					RegionUtil.setBorderTop(borderMediumDashed, cellRangeAddress7, sheet, sheet.getWorkbook());
					RegionUtil.setBorderBottom(borderMediumDashed, cellRangeAddress7, sheet, sheet.getWorkbook());
					RegionUtil.setBorderLeft(borderMediumDashed, cellRangeAddress7, sheet, sheet.getWorkbook());
					RegionUtil.setBorderRight(borderMediumDashed, cellRangeAddress7, sheet, sheet.getWorkbook());
					sheet.addMergedRegion(cellRangeAddress7);

					contenido.setCellStyle(style2);

					numeroFila++;
					numero++;
				}

				// numeroFila = element.getListaDetalle().size() + numeroFila - 1;

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
			ReporteProductoInicial listProductoInicial = new ReporteProductoInicial();
			listProductoInicial.setIdProductoInicial(param.getIdProductoInicial());
			listProductoInicial.setFechaInicio(param.getFechaInicio());
			listProductoInicial.setFechaFin(param.getFechaFin());
			listProductoInicial.setPrensa(param.getPrensa());
			listProductoInicial.setTipoLadrillo(param.getTipoLadrillo());
			List<ReporteProductoInicial> listaProductoInicial = reportesMapper.getReporteProductoInicial(listProductoInicial);
		
			List<ListaProductoInicialOutputDto> listaReporteInicial = new ArrayList<>();
			if (!listaProductoInicial.isEmpty()) {
				ListaProductoInicialOutputDto lista = new ListaProductoInicialOutputDto();

			for (ReporteProductoInicial element : listaProductoInicial) {
				// Date date = new
				// SimpleDateFormat("dd/mm/yyyy").parse(element.getFechaRegistroDate());
				lista = new ListaProductoInicialOutputDto();
				lista.setIdProductoInicial(element.getIdProductoInicial());
				lista.setFechaRegistroDesc(element.getFechaRegistroDesc());
				lista.setPrensaDesc(element.getPrensaDesc());
				lista.setCantidadProducido(element.getCantidadProducido());
				lista.setCantidadEstimada(element.getCantidadEstimada());
				lista.setCodigoProductoInicial(element.getCodigoProductoInicial());
				lista.setTipoLadrilloDesc(element.getTipoLadrilloDesc());
				listaReporteInicial.add(lista);
			}

			
			byte[] fileContent = null;

			// Ahora almacenaremos el archivo en disco
			try {

				File archivo = File.createTempFile("formatoProgramaInversiones", ".pdf");

				FileOutputStream out = new FileOutputStream(archivo);
				// workbook.write(out);
				out.close();
				fileContent = Files.readAllBytes(archivo.toPath());

			} catch (IOException o) {
				System.err.println("ERROR AL CREAR EL ARCHIVO!");
				o.printStackTrace();
			}

			System.out.println("Reporte generado");

			Respuesta resp = new Respuesta<>();
			resp.setSuccess(true);
			resp.setMessage("Se creo el reporte correctamente");
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
								e.setPersonaAyudante("POR DEFINIR");
							}
						}

						if (element2.getTipoPersona().equals("TIPPERAYU2")) {
							e.setPersonaAyudante2(element2.getNombres() + " " + element2.getApellidoPaterno() + " "+ element2.getApellidoMaterno());
							if (element2.getNombres().equals("") && element2.getApellidoPaterno().equals("") && element2.getApellidoMaterno().equals("")) {
								e.setPersonaAyudante2("POR DEFINIR");
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


}