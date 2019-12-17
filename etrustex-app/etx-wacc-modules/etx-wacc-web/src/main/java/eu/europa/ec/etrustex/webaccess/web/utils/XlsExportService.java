package eu.europa.ec.etrustex.webaccess.web.utils;


import eu.europa.ec.etrustex.webaccess.web.model.UserRoleXLS;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service("xlsExportService")
public class XlsExportService {

    public Sheet buildExcelDocument(List<UserRoleXLS> userRoleXLSs) throws IOException {
        Workbook workbook = new XSSFWorkbook();


        int startRowIndex = 0;
        int startColIndex = 0;

        //Create new worksheet
        Sheet worksheet = workbook.createSheet("Party users");

        buildReport(worksheet, startRowIndex, startColIndex);

        fillReport(worksheet,startRowIndex, startColIndex, userRoleXLSs);


        Sheet sheet = workbook.getSheetAt(0);
        return sheet;
    }

    private void buildReport(Sheet worksheet, int startRowIndex, int startColIndex) {
        worksheet.setColumnWidth(0, 5000);
        worksheet.setColumnWidth(1, 10000);
        worksheet.setColumnWidth(2, 7000);
        worksheet.setColumnWidth(3, 4500);
        worksheet.setColumnWidth(4, 10000);
        worksheet.setColumnWidth(5, 5000);

        buildHeaders(worksheet, ++startRowIndex, startColIndex);

    }


    private static void buildHeaders(Sheet worksheet, int startRowIndex, int startColIndex) {
        Font font = worksheet.getWorkbook().createFont();
        font.setBold(true);
        // Create cell style for the headers
        CellStyle headerCellStyle = worksheet.getWorkbook().createCellStyle();
        headerCellStyle.setFillBackgroundColor(IndexedColors.GREY_25_PERCENT.index);
        headerCellStyle.setFillPattern(FillPatternType.NO_FILL);
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
        headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerCellStyle.setWrapText(true);
        headerCellStyle.setFont(font);
        headerCellStyle.setBorderTop(BorderStyle.THIN);
        headerCellStyle.setBorderBottom(BorderStyle.THIN);
        headerCellStyle.setBorderRight(BorderStyle.THIN);
        headerCellStyle.setBorderLeft(BorderStyle.THIN);

        // Create the column headers
        Row rowHeader = worksheet.createRow((short) startRowIndex);
        rowHeader.setHeight((short) 500);

        Cell cell1 = rowHeader.createCell(startColIndex+0);
        cell1.setCellValue("Party");
        cell1.setCellStyle(headerCellStyle);

        Cell cell2 = rowHeader.createCell(startColIndex+1);
        cell2.setCellValue("Name");
        cell2.setCellStyle(headerCellStyle);

        Cell cell3 = rowHeader.createCell(startColIndex+2);
        cell3.setCellValue("Role");
        cell3.setCellStyle(headerCellStyle);

        Cell cell4 = rowHeader.createCell(startColIndex+3);
        cell4.setCellValue("ECAS Unique ID");
        cell4.setCellStyle(headerCellStyle);

        Cell cell5 = rowHeader.createCell(startColIndex+4);
        cell5.setCellValue("Email address");
        cell5.setCellStyle(headerCellStyle);

        Cell cell6 = rowHeader.createCell(startColIndex+5);
        cell6.setCellValue("Message notification");
        cell6.setCellStyle(headerCellStyle);

    }

    private void fillReport(Sheet worksheet, int startRowIndex, int startColIndex, List<UserRoleXLS> userRoleXLSs) {
        startRowIndex +=2 ;

        UserRoleXLS userRoleXLS = null;
        for (int i =0; i < userRoleXLSs.size(); i++){
            userRoleXLS = userRoleXLSs.get(i);

            // Create a new row
            Row row = worksheet.createRow(startRowIndex + i);
            CellStyle bodyCellStyle = getCellStyle(worksheet, userRoleXLS);


            Cell cell0 = row.createCell(startColIndex+0);
            cell0.setCellValue(userRoleXLS.getPartyName());
            cell0.setCellStyle(bodyCellStyle);


            Cell cell1 = row.createCell(startColIndex+1);
            cell1.setCellValue(userRoleXLS.getUserName());
            cell1.setCellStyle(bodyCellStyle);

            Cell cell2 = row.createCell(startColIndex+2);
            cell2.setCellValue(userRoleXLS.getRoleType());
            cell2.setCellStyle(bodyCellStyle);

            Cell cell3 = row.createCell(startColIndex+3);
            cell3.setCellValue(userRoleXLS.getECASId());
            cell3.setCellStyle(bodyCellStyle);

            Cell cell4 = row.createCell(startColIndex+4);
            if (userRoleXLS.getEmail() != null && userRoleXLS.getEmail().contains(",")) {
                cell4.setCellValue(userRoleXLS.getEmail().replaceAll(",","\n"));
            } else {
                cell4.setCellValue(userRoleXLS.getEmail());
            }
            cell4.setCellStyle(bodyCellStyle);

            Cell cell5 = row.createCell(startColIndex+5);
            cell5.setCellValue(userRoleXLS.getNotificationsEnabled());
            cell5.setCellStyle(bodyCellStyle);

        }
    }

    private CellStyle getCellStyle(Sheet worksheet, UserRoleXLS userRoleXLS){
        Font fontBlackColor = worksheet.getWorkbook().createFont();
        fontBlackColor.setColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        Font fontBlueColor = worksheet.getWorkbook().createFont();
        fontBlueColor.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());

        // Create cell style for the body
        CellStyle bodyCellStyle = worksheet.getWorkbook().createCellStyle();
        bodyCellStyle.setAlignment(HorizontalAlignment.CENTER);
        bodyCellStyle.setWrapText(true);
        bodyCellStyle.setBorderTop(BorderStyle.THIN);
        bodyCellStyle.setBorderBottom(BorderStyle.THIN);
        bodyCellStyle.setBorderRight(BorderStyle.THIN);
        bodyCellStyle.setBorderLeft(BorderStyle.THIN);
        bodyCellStyle.setAlignment(HorizontalAlignment.LEFT);

        if (userRoleXLS.getRoleType() == null) {
            bodyCellStyle.setFont(fontBlueColor);
        } else {
            bodyCellStyle.setFont(fontBlackColor);
        }

        return bodyCellStyle;
    }



}
