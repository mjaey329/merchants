package com.xzjh.passbook.imports;

import com.xzjh.passbook.entity.Merchants;
import com.xzjh.passbook.vo.CreateMerchantsRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Slf4j
@Service
public class MultipartImport {

    public boolean upload() throws Exception{

        File file = new File("D://1.txt");
        System.out.println(file.getName()+"____"+file.canRead());

        return true;
    }

    public boolean creatWorkbook() throws Exception {

//        Workbook wb = new HSSFWorkbook();
//        Sheet sheet = wb.createSheet("1 sheet");
//        Row row = sheet.createRow(2);
//
//
//        createCell(wb, row, (short)0, HSSFCellStyle.ALIGN_CENTER, HSSFCellStyle.VERTICAL_BOTTOM);//既在中间又在下边
//                 createCell(wb, row, (short)1, HSSFCellStyle.ALIGN_FILL, HSSFCellStyle.VERTICAL_CENTER);//要充满屏幕又要中间
//                 createCell(wb, row, (short)2, HSSFCellStyle.ALIGN_LEFT, HSSFCellStyle.VERTICAL_TOP);//既在右边又在上边
//                 createCell(wb, row, (short)3, HSSFCellStyle.ALIGN_RIGHT, HSSFCellStyle.VERTICAL_TOP);//既在右边又在上边
//
//        FileOutputStream fileOut = new FileOutputStream("d:\\test.xls");
//        wb.write(fileOut);
//        fileOut.close();

        File f = new File("D:\\test.xls");
        FileInputStream fis = new FileInputStream(f);
        Workbook workbook = WorkbookFactory.create(fis);
        Sheet sheet = workbook.getSheetAt(0);

        Cell row = sheet.getRow(1).getCell(1);
        System.out.println(sheet.getSheetName() +"_________"+row);

        return true;
    }

    /**
     * <h1>读取当前工作簿</h1>
     * @param Mufile
     * @return
     * @throws Exception
     */
    public List<Merchants> readXls(MultipartFile Mufile) throws Exception{
        File file = File.createTempFile(Mufile.getName(),"xlsx");
        Mufile.transferTo(file);
        FileInputStream fis = new FileInputStream(file);
        Workbook workbook = WorkbookFactory.create(fis);
        System.out.println("当前工作簿有 "+  workbook.getNumberOfSheets() + "个 Sheet。");
        Sheet sheet = workbook.getSheetAt(0);
        HashMap<String,Integer> merchantsMaps = new HashMap<String, Integer>();

        for (int i = 0;i <= sheet.getRow(0).getLastCellNum();i++){
            Object key = sheet.getRow(0).getCell(i);
            if (key == null){
                continue;
            }
            merchantsMaps.put(String.valueOf(key),i);
        }

        int nameIndex = merchantsMaps.getOrDefault("商户名称",-1);
        int logIndex = merchantsMaps.getOrDefault("商户 logo",-1);
        int businessIndex = merchantsMaps.getOrDefault("商户营业执照",-1);
        int phoneIndex = merchantsMaps.getOrDefault("商户的联系电话",-1);
        int addressIndex = merchantsMaps.getOrDefault("商户地址",-1);


       List<Merchants> merchants = new ArrayList<>();

        for (int i = 1;i<=sheet.getLastRowNum();i++){

                Row row = sheet.getRow(i);
                Merchants merchant = new Merchants();
                merchant.setName(String.valueOf(row.getCell(nameIndex)));
                merchant.setLogoUrl(String.valueOf(row.getCell(logIndex)));
                merchant.setBusinessLicenseUrl(String.valueOf(row.getCell(businessIndex)));
                merchant.setPhone(String.valueOf(row.getCell(phoneIndex)));
                merchant.setAddress(String.valueOf(row.getCell(addressIndex)));
                merchants.add(merchant);

        }



        fis.close();
        return merchants;
    }



    /**
           * 创建一个单元格并为其设定指定的对其方式
           * @param wb 工作簿
          * @param row 行
           * @param column  列
          * @param halign  水平方向对其方式
           * @param valign  垂直方向对其方式
           */
    private static void createCell(Workbook wb,Row row,short column,short halign,short valign){
                 Cell cell=row.createCell(column);  // 创建单元格
               cell.setCellValue(new HSSFRichTextString("Align It"));  // 设置值

                 CellStyle cellStyle=wb.createCellStyle(); // 创建单元格样式

                cellStyle.setAlignment(halign);  // 设置单元格水平方向对其方式
                 cellStyle.setVerticalAlignment(valign); // 设置单元格垂直方向对其方式

                 cell.setCellStyle(cellStyle); // 设置单元格样式
            }



}
