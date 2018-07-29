package estate.common.util;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 应泽林 on 18-1-24.
 * excel解析类
 */
public class ExcelParse
{

    private List<Integer> errorRowIndex;

    /**
     * 解析excel文件
     * @param path
     * @throws Exception
     */
    public List<Map<String,String>> parseExcel(String path) throws Exception
    {
        Workbook wb = WorkbookFactory.create(new File(path));
        errorRowIndex=new ArrayList<>();
        List<Map<String,String>> result= new ArrayList<>();

        for (int sheetIndex=0;sheetIndex<wb.getNumberOfSheets();sheetIndex++)
        {
            //获取一张工作表
            Sheet sheet=wb.getSheetAt(sheetIndex);
            for (int rowIndex=1; rowIndex<=sheet.getLastRowNum();rowIndex++)
            {
                //获取一行数据
                try
                {
                    Row row=sheet.getRow(rowIndex);
                    Map<String, String> rowData = new HashMap<>();
                    for (int cellIndex = 0; cellIndex < row.getLastCellNum(); cellIndex++)
                    {
                        //获取一格数据
                        row.getCell(cellIndex).setCellType(Cell.CELL_TYPE_STRING);
                        rowData.put(sheet.getRow(0).getCell(cellIndex).getStringCellValue(),
                                row.getCell(cellIndex).getStringCellValue());

                    }
                    result.add(rowData);
                }
                catch (Exception e)
                {
                    errorRowIndex.add(rowIndex);
                }

            }
        }
        return result;
    }

    /**
     * 解析excel文件
     * @param file
     * @throws Exception
     */
    public List<Map<String,String>> parseExcel(File file) throws Exception
    {
        Workbook wb = WorkbookFactory.create(file);
        errorRowIndex=new ArrayList<>();
        List<Map<String,String>> result= new ArrayList<>();

        for (int sheetIndex=0;sheetIndex<wb.getNumberOfSheets();sheetIndex++)
        {
            //获取一张工作表
            Sheet sheet=wb.getSheetAt(sheetIndex);
            for (int rowIndex=1; rowIndex<=sheet.getLastRowNum();rowIndex++)
            {
                //获取一行数据
                try
                {
                    Row row=sheet.getRow(rowIndex);
                    Map<String, String> rowData = new HashMap<>();
                    for (int cellIndex = 0; cellIndex < row.getLastCellNum(); cellIndex++)
                    {
                        //获取一格数据
                        row.getCell(cellIndex).setCellType(Cell.CELL_TYPE_STRING);
                        rowData.put(sheet.getRow(0).getCell(cellIndex).getStringCellValue(),
                                row.getCell(cellIndex).getStringCellValue());

                    }
                    result.add(rowData);
                }
                catch (Exception e)
                {
                    errorRowIndex.add(rowIndex);
                }

            }
        }
        return result;
    }

    /**
     * 解析excel文件
     * @param inputStream
     * @throws Exception
     */
    public List<Map<String,String>> parseExcel(InputStream inputStream) throws Exception
    {
        Workbook wb = WorkbookFactory.create(inputStream);
        errorRowIndex=new ArrayList<>();
        List<Map<String,String>> result= new ArrayList<>();

        for (int sheetIndex=0;sheetIndex<wb.getNumberOfSheets();sheetIndex++)
        {
            //获取一张工作表
            Sheet sheet=wb.getSheetAt(sheetIndex);
            for (int rowIndex=1; rowIndex<=sheet.getLastRowNum();rowIndex++)
            {
                //获取一行数据
                try
                {
                    Row row=sheet.getRow(rowIndex);
                    Map<String, String> rowData = new HashMap<>();
                    for (int cellIndex = 0; cellIndex < sheet.getRow(0).getLastCellNum(); cellIndex++)
                    {
                        //获取一格数据
                        if (row.getCell(cellIndex)==null)
                        {
                            rowData.put(sheet.getRow(0).getCell(cellIndex).getStringCellValue(), "");
                        }
                        else
                        {
                            row.getCell(cellIndex).setCellType(Cell.CELL_TYPE_STRING);
                            rowData.put(sheet.getRow(0).getCell(cellIndex).getStringCellValue(), row.getCell
                                    (cellIndex).getStringCellValue());
                        }
                    }
                    result.add(rowData);
                }
                catch (Exception e)
                {
                    errorRowIndex.add(rowIndex);
                }

            }
        }
        return result;
    }

    /**
     * 返回解析失败的数据
     */
    public List<Integer> getErrors()
    {
        return this.errorRowIndex;
    }

}
