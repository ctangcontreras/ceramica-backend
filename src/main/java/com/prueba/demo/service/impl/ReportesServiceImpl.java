package com.prueba.demo.service.impl;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.constraints.Size;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.demo.core.inputDto.ProductoInicialInputDto;
import com.prueba.demo.core.inputDto.ProductoTerminadoInputDto;
import com.prueba.demo.core.model.DetalleProductoTerminado;
import com.prueba.demo.core.model.ProductoInicial;
import com.prueba.demo.core.model.ProductoTerminado;
import com.prueba.demo.core.outputDto.ListaProductoInicialOutputDto;
import com.prueba.demo.core.outputDto.ListaProductoTerminadoOutputDto;
import com.prueba.demo.core.outputDto.ListarDetProductoTerminadoOutputDto;
import com.prueba.demo.mapper.DetProductoTerminadoMapper;
import com.prueba.demo.mapper.ProductoInicialMapper;
import com.prueba.demo.mapper.ProductoTerminadoMapper;
import com.prueba.demo.service.ReportesService;
import com.prueba.demo.support.dto.Respuesta;


@Service
public class ReportesServiceImpl implements ReportesService {
	private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);


	@Autowired
	private ProductoInicialMapper productoInicialMapper;

	@Autowired
	private ProductoTerminadoMapper productoTerminadoMapper;

	@Autowired
	private DetProductoTerminadoMapper detProductoTerminadoMapper;
 

  @Override
	public Respuesta<?> listarProductoInicialExcel(ProductoInicialInputDto param) throws Exception {
			ProductoInicial listProductoInicial = new ProductoInicial();
			listProductoInicial.setIdProductoInicial(param.getIdProductoInicial());
			listProductoInicial.setFechaInicio(param.getFechaInicio());
			listProductoInicial.setFechaFin(param.getFechaFin());
			listProductoInicial.setPrensa(param.getPrensa());
			listProductoInicial.setTipoLadrillo(param.getTipoLadrillo());
			List<ProductoInicial> listaProductoInicial = productoInicialMapper.getListarProductoInicial(listProductoInicial);
		
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
			//Le damos color al fondo
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

			style.setFont(blueFont);

			 //centrar
			 style.setAlignment(style.ALIGN_CENTER);
			 style2.setAlignment(style2.ALIGN_CENTER);
			
		   //iniciamos variables
			 int numeroColumna = 6;
			 int tamano = 5000;

			 //CellRangeAddress cellRangeAddress = new CellRangeAddress(1, 1, 1, 3);// Merges the cells
			 CellRangeAddress cellRangeAddress = new CellRangeAddress(1, 1, 2, 5);
         
			 // Creamos una hoja de calculo llama "Reporte" en dicho libro de excel
			 HSSFSheet sheet = workbook.createSheet("Reporte Producto Inicial");
			
			 sheet.setColumnWidth( numeroColumna, tamano);
			 
			
			 sheet.addMergedRegion(cellRangeAddress);

			 //Resource resource = new ClassPathResource("assets/logoEmpresa.jpeg"); 
     		//InputStream inputStream = resource.getInputStream();

			// byte[] bytes = IOUtils.toByteArray(inputStream);

			 //int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
				//inputStream.close();
				
				//Returns an object that handles instantiating concrete classes
				CreationHelper helper = workbook.getCreationHelper();
			
				//Creates the top-level drawing patriarch.
				Drawing drawing = sheet.createDrawingPatriarch();
			
				//Create an anchor that is attached to the worksheet
				ClientAnchor anchor = helper.createClientAnchor();
				//set top-left corner for the image
				anchor.setCol1(0); //Column B
				anchor.setRow1(0); //Row 3 abajo
				anchor.setCol2(0); //Column C
				anchor.setRow2(1); //Row 4
				double scale = 0.1;
				
				//Creates a picture
				//Picture pict = drawing.createPicture(anchor,pictureIdx);
				//Reset the image to the original size
				//pict.resize(scale);

				
				// sheet.addMergedRegion(new CellRangeAddress(1,1,1,3));
				Row filaTitulo =sheet.createRow(1);
				Cell  celda = filaTitulo.createCell(2);
				celda.setCellValue("REPORTE PRODUCTO INICIAL");
				//combinar y centrar
				final int borderMediumDashed = CellStyle.BORDER_MEDIUM;
				RegionUtil.setBorderTop(borderMediumDashed,cellRangeAddress, sheet,sheet.getWorkbook());
				RegionUtil.setBorderBottom(borderMediumDashed,cellRangeAddress, sheet,sheet.getWorkbook());
				RegionUtil.setBorderLeft(borderMediumDashed,cellRangeAddress, sheet,sheet.getWorkbook());
				RegionUtil.setBorderRight(borderMediumDashed,cellRangeAddress, sheet,sheet.getWorkbook());
				
			  
			  celda.setCellStyle(style);
			  
			  Row filaTitulo2 =sheet.createRow(2);
				Cell  celda2 = filaTitulo2.createCell(0);
				celda2.setCellValue("Fecha Inicio");
				
				int numeroFila2 =6;
				Row filaTitulo3 =sheet.createRow(3);
				Cell  celda3 = filaTitulo3.createCell(0);
				celda3.setCellValue("Fecha Fin");
				Row filaData2=sheet.createRow(numeroFila2);
				Cell fechaFin = filaData2.createCell(1);
				fechaFin.setCellValue(param.getFechaFin());
				
			   // sheet.addMergedRegion(cellRangeAddress);
			 
			
				//iniciamos variables
			   
				
				// Un arreglo con los nombres de los meses del año
			   
				Row filaReporte =sheet.createRow(5);
				String[] meses = {"","Codigo", "Fecha Registro", "Prensa", "Tipo Ladrillo", "Cantidad Producida", "Cantidad Estimada", "Diferencia"};
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

				int numeroFila =6;
     for(ListaProductoInicialOutputDto getListaProductoInicial : listaInicial ) {
    	 Row filaData=sheet.createRow(numeroFila);
    	
    	
    	
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
    	 diferencia.setCellValue(getListaProductoInicial.getCantidadEstimada() -  getListaProductoInicial.getCantidadProducido());
    	 
    	 codigo.setCellStyle(style2);
    	 fechaRegistro.setCellStyle(style2);
    	 prensa.setCellStyle(style2);
    	 tipoLadrillo.setCellStyle(style2);
    	 cantidadProducida.setCellStyle(style2);
    	 cantidadEstimada.setCellStyle(style2);
		 diferencia.setCellStyle(style2);
    	 
    	 
    	 numeroFila ++;
    	 
     	}

		 autoSizeColumns(workbook);

			 byte[] fileContent =null;
     
			 // Ahora almacenaremos el archivo en disco
			 try {
		
				 File archivo = File.createTempFile("formatoProgramaInversiones",".xls");
		
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
            //resp.setDato(listaInicial);
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

		List<ProductoTerminado> listaProductoTerminado = productoTerminadoMapper.listarProductoTerminado(productoTerminado);

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
				//e.setDescStock(element.getDescStock());
				e.setDescripcionActivo(element.getDescripcionActivo());
				//e.setCrudo(element.getCrudo());
				e.setActivo(element.getActivo());
				e.setCodigo(element.getCodigo());
				
				DetalleProductoTerminado detalle = new DetalleProductoTerminado();
				detalle.setIdProductoTerminado(element.getIdProductoTerminado());
				List<DetalleProductoTerminado> listaDetalle = detProductoTerminadoMapper.listarDetProductoTerminado(detalle);

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
			//Le damos color al fondo
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

			style.setFont(blueFont);

			 //centrar
			 style.setAlignment(style.ALIGN_CENTER);
			 style2.setAlignment(style2.ALIGN_CENTER);
			
		   //iniciamos variables
			 int numeroColumna = 6;
			 int tamano = 5000;
			 
			 //CellRangeAddress cellRangeAddress = new CellRangeAddress(1, 1, 1, 3);// Merges the cells
			/*  CellRangeAddress cellRangeAddress = new CellRangeAddress(6,7,1,1); */
			
			 // Creamos una hoja de calculo llama "Reporte" en dicho libro de excel
			 HSSFSheet sheet = workbook.createSheet("Reporte Producto Terminado");
			
			 sheet.setColumnWidth( numeroColumna, tamano);
			 
			
			 /* sheet.addMergedRegion(cellRangeAddress); */
			
			
			 //Resource resource = new ClassPathResource("assets/logoEmpresa.jpeg"); 
     		//InputStream inputStream = resource.getInputStream();

			// byte[] bytes = IOUtils.toByteArray(inputStream);

			 //int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
				//inputStream.close();
				
				//Returns an object that handles instantiating concrete classes
				CreationHelper helper = workbook.getCreationHelper();
			
				//Creates the top-level drawing patriarch.
				Drawing drawing = sheet.createDrawingPatriarch();
			
				//Create an anchor that is attached to the worksheet
				ClientAnchor anchor = helper.createClientAnchor();
				//set top-left corner for the image
				anchor.setCol1(0); //Column B
				anchor.setRow1(0); //Row 3 abajo
				anchor.setCol2(0); //Column C
				anchor.setRow2(1); //Row 4
				double scale = 0.1;
				
				//Creates a picture
				//Picture pict = drawing.createPicture(anchor,pictureIdx);
				//Reset the image to the original size
				//pict.resize(scale);

				// sheet.addMergedRegion(new CellRangeAddress(1,1,1,3));
				Row filaTitulo =sheet.createRow(1);
				Cell  celda = filaTitulo.createCell(2);
				celda.setCellValue("REPORTE PRODUCTO TERMINADO");
				//combinar y centrar
				final int borderMediumDashed = CellStyle.BORDER_MEDIUM;
				/* RegionUtil.setBorderTop(borderMediumDashed,cellRangeAddress, sheet,sheet.getWorkbook());
				RegionUtil.setBorderBottom(borderMediumDashed,cellRangeAddress, sheet,sheet.getWorkbook());
				RegionUtil.setBorderLeft(borderMediumDashed,cellRangeAddress, sheet,sheet.getWorkbook());
				RegionUtil.setBorderRight(borderMediumDashed,cellRangeAddress, sheet,sheet.getWorkbook()); */

				
			  
			  celda.setCellStyle(style);
			   // sheet.addMergedRegion(cellRangeAddress);
			 
			
				//iniciamos variables
			   
				
				// Un arreglo con los nombres de los meses del año
			   
				Row filaReporte =sheet.createRow(5);
				String[] meses = {"","Codigo", "Horno", "Fecha Registro", "Paquete", "Contenido"};
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

				int numeroFila =6;
				String ladrillos = "ladrillos";
				
     for(ListaProductoTerminadoOutputDto getListaProductoTerminado : lista ) {
				CellRangeAddress cellRangeAddress = new CellRangeAddress(numeroFila, getListaProductoTerminado.getLista().size() + numeroFila - 1,1,1);
			
				sheet.addMergedRegion(cellRangeAddress);
				RegionUtil.setBorderTop(borderMediumDashed,cellRangeAddress, sheet,sheet.getWorkbook());
				RegionUtil.setBorderBottom(borderMediumDashed,cellRangeAddress, sheet,sheet.getWorkbook());
				RegionUtil.setBorderLeft(borderMediumDashed,cellRangeAddress, sheet,sheet.getWorkbook());
				RegionUtil.setBorderRight(borderMediumDashed,cellRangeAddress, sheet,sheet.getWorkbook());
				sheet.addMergedRegion(cellRangeAddress);
		for (ListarDetProductoTerminadoOutputDto listaDetalle : getListaProductoTerminado.getLista()) {
    	 Row filaData=sheet.createRow(numeroFila);
    	
    	Cell codigo = filaData.createCell(1);
    	codigo.setCellValue(getListaProductoTerminado.getCodigo());
    	
    	 Cell horno = filaData.createCell(2);
    	 horno.setCellValue(getListaProductoTerminado.getDescHorno());

		 Cell fechaRegistro = filaData.createCell(3);
    	 fechaRegistro.setCellValue(getListaProductoTerminado.getDescFechaRegistro());
    	 
    	 Cell paquete = filaData.createCell(4);
    	 paquete.setCellValue(getListaProductoTerminado.getPaquete());

		 Cell contenido = filaData.createCell(5);
    	 contenido.setCellValue(listaDetalle.getTotal() + ' ' + ladrillos + ' ' + listaDetalle.getDescripcionTipoLadrillo() + ' ' +listaDetalle.getDescripcionEstado());

    	 codigo.setCellStyle(style2);
		 horno.setCellStyle(style2);
    	 fechaRegistro.setCellStyle(style2);
    	 paquete.setCellStyle(style2);
		 contenido.setCellStyle(style2); 
		 
		
		}
    	 
    	 numeroFila ++;
    	 
     	}

		 autoSizeColumns(workbook);

			 byte[] fileContent =null;
     
			 // Ahora almacenaremos el archivo en disco
			 try {
		
				 File archivo = File.createTempFile("formatoProgramaInversiones",".xls");
		
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