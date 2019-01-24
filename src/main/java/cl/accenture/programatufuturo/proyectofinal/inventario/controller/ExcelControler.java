package cl.accenture.programatufuturo.proyectofinal.inventario.controller;


import cl.accenture.programatufuturo.proyectofinal.inventario.dao.Conexion;
import cl.accenture.programatufuturo.proyectofinal.inventario.exception.SinConexionException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExcelControler {
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

}