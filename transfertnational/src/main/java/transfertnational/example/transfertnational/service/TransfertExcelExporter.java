package transfertnational.example.transfertnational.service;
import java.io.IOException;
import java.util.List;
 
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import transfertnational.example.transfertnational.model.TransfertNational;
 
public class TransfertExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<TransfertNational> listTransferts;
     
    public TransfertExcelExporter(List<TransfertNational> listTransferts) {
        this.listTransferts = listTransferts;
        workbook = new XSSFWorkbook();
    }
 
 
    private void writeHeaderLine() {
        sheet = workbook.createSheet("Transferts");
         
        Row row = sheet.createRow(0);
         
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
         
        createCell(row, 0, "ID", style);      
        createCell(row, 1, "ID Agent", style);       
        createCell(row, 2, "ID Client", style);    
        createCell(row, 3, "Pièce d'identité", style);
        createCell(row, 4, "Num Gsm", style);
        createCell(row, 5, "ID Bénéficiaire", style);      
        createCell(row, 6, "ID Compte", style);       
        createCell(row, 7, "Status", style);    
        createCell(row, 8, "Code transfert", style);
        createCell(row, 9, "Montant", style);
        createCell(row, 10, "Nombre de jour", style);

         
    }
     
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
     
    private void writeDataLines() {
        int rowCount = 1;
 
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
                 
        for (TransfertNational trans : listTransferts) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             
            createCell(row, columnCount++, trans.getId(), style);
            createCell(row, columnCount++, trans.getIdAgent(), style);
            createCell(row, columnCount++, trans.getIdClient(), style);
            createCell(row, columnCount++, trans.getPi(), style);
            createCell(row, columnCount++, trans.getNumGsm(), style);
            
            createCell(row, columnCount++, trans.getIdBeneficiaire(), style);
            createCell(row, columnCount++, trans.getIdCompte(), style);
            createCell(row, columnCount++, trans.getStatus(), style);
            createCell(row, columnCount++, trans.getCodeTransfert(), style);
            createCell(row, columnCount++, trans.getMontant(), style);
            createCell(row, columnCount++, trans.getNombreJours(), style);

            
        }
    }
     
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
         
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
         
    }
}