package cl.accenture.programatufuturo.proyectofinal.inventario.dao;


import cl.accenture.programatufuturo.proyectofinal.inventario.exception.SinConexionException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Excel {
    public static void main(String[] args) {
        crearExcel();
        leer();
        cargar();

    }


    public static void crearExcel() {
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("inventario");
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("Nombre");
        row.createCell(1).setCellValue("Caracteristicas");
        row.createCell(2).setCellValue("CantidadMin");
        row.createCell(3).setCellValue("CantidadMax");
        row.createCell(4).setCellValue("Precio");
        row.createCell(5).setCellValue("Marca");
        row.createCell(6).setCellValue("Categoria");

        try {
            FileOutputStream fileout = new FileOutputStream("Excel.xlsx");
            book.write(fileout);
            fileout.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void cargar(){
        Conexion con = new Conexion();
        PreparedStatement ps;

        try {
            Connection conn = con.obtenerConnection();
            FileInputStream file = new FileInputStream(new File("ruta en la que esta el archivo"));

            XSSFWorkbook wb = new XSSFWorkbook(file);
            XSSFSheet sheet = wb.getSheetAt(0);

            int numFilas = sheet.getLastRowNum();
            for (int a =1;a <= numFilas; a++){
                Row fila = sheet.getRow(a);

                ps = conn.prepareStatement("INSERT INTO Producto(Nombre,Caracteristicas,Cantidad min, Cantidad Max, Precio, Marca, Categoria) VALUES (?,?,?,?,?,?,?) ");
                ps.setString(1,fila.getCell(0).getStringCellValue());
                ps.setString(2,fila.getCell(1).getStringCellValue());
                ps.setDouble(3,fila.getCell(2).getNumericCellValue());
                ps.setDouble(4,fila.getCell(3).getNumericCellValue());
                ps.setDouble(5,fila.getCell(4).getNumericCellValue());
                ps.setString(6,fila.getCell(5).getStringCellValue());
                ps.setString(7,fila.getCell(6).getStringCellValue());
            }
            conn.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SinConexionException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void leer(){
        try {
            FileInputStream file = new FileInputStream(new File("ruta en la que esta el archivo"));

            XSSFWorkbook wb = new XSSFWorkbook(file);
            XSSFSheet sheet = wb.getSheetAt(0);

            int numFilas = sheet.getLastRowNum();
            for (int a =0;a <= numFilas; a++){
                Row fila = sheet.getRow(a);
                int numCols = fila.getLastCellNum();

                for(int b = 0; b < numCols; b++){
                    Cell celda = fila.getCell(b);

                    switch (celda.getCellTypeEnum().toString()){
                        case "NUMERIC":
                            System.out.print(celda.getNumericCellValue() + " ");
                            break;
                        case "STRING":
                            System.out.print(celda.getStringCellValue() + " ");
                            break;
                        case "FORMULA":
                            System.out.print(celda.getCellFormula() + " ");
                            break;
                    }
                }
                System.out.println();

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void reporte(){
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("Productos");

        try {
            InputStream is = new FileInputStream("src\\main\\java\\cl.accenture.programatufuturo.proyectofinal.inventario\\img\\tienda.jpg");
            byte[] bytes = IOUtils.toByteArray(is);
            int imgIndex = book.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
            is.close();

            CreationHelper help = book.getCreationHelper();
            Drawing draw = sheet.createDrawingPatriarch();

            ClientAnchor anchor = help.createClientAnchor();
            anchor.setCol1(0);
            anchor.setRow1(1);
            Picture Pict = draw.createPicture(anchor,imgIndex);
            Pict.resize(1,3);

            CellStyle tituloEstilo = book.createCellStyle();
            tituloEstilo.setAlignment(HorizontalAlignment.CENTER);
            tituloEstilo.setVerticalAlignment(VerticalAlignment.CENTER);
            Font fuenteTitulo = book.createFont();
            fuenteTitulo.setFontName("Arial");
            fuenteTitulo.setBold(true);
            fuenteTitulo.setFontHeightInPoints((short)14);
            tituloEstilo.setFont(fuenteTitulo);

            Row filaTitulo = sheet.createRow(1);
            Cell celdaTitulo = filaTitulo.createCell(1);
            celdaTitulo.setCellStyle(tituloEstilo);
            celdaTitulo.setCellValue("Reporte de Productos");
            sheet.addMergedRegion(new CellRangeAddress(1,2,1,3));

            String[] cabecera = new String[]{"idProducto","Nombre","Caracteristicas","CantidadMin","CantidadMax","Precio","marca","categoria"};

            CellStyle headerStyle = book.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);

            Row filaEncabezados = sheet.createRow(7);
            for(int i=0; i< cabecera.length;i++){
                Cell celdaEncabezado = filaEncabezados.createCell(i);
                celdaEncabezado.setCellStyle(headerStyle);
                celdaEncabezado.setCellValue(cabecera[i]);
            }

            Conexion con = new Conexion();
            PreparedStatement ps;
            ResultSet rs;
            Connection conn = con.obtenerConnection();

            int numfilaDatos = 8;

            CellStyle datosEstilos = book.createCellStyle();
            datosEstilos.setBorderBottom(BorderStyle.THIN);
            datosEstilos.setBorderLeft(BorderStyle.THIN);
            datosEstilos.setBorderRight(BorderStyle.THIN);

            ps = conn.prepareStatement("SELECT idProducto, Nombre, Caracteristicas, CantidadMin, CantidadMax, Precio, marca, categoria, stock");
            rs = ps.executeQuery();

            int numCol = rs.getMetaData().getColumnCount();

            while(rs.next())
            {
                Row filaDatos = sheet.createRow(numfilaDatos);

                for(int a = 0; a<numCol; a++){

                    Cell CeldaDatos = filaDatos.createCell(a);
                    CeldaDatos.setCellStyle(datosEstilos);
                    if(a==1||a==4||a==5||a==6){
                        CeldaDatos.setCellValue(rs.getDouble(a+1));
                    } else{
                        CeldaDatos.setCellValue(rs.getString(a+1));

                    }


                }

            }
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);
            sheet.autoSizeColumn(6);
            sheet.autoSizeColumn(7);

            sheet.setZoom(150);





            FileOutputStream fileOut = new FileOutputStream("ReporteProductos.xlsx");
            book.write(fileOut);
            fileOut.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (SinConexionException e) {
            e.printStackTrace();
        }
    }

}