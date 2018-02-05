package com.ittr.main;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class ExcelReader {

	public static Map loadExcelLines(String filePath) throws Exception {

		Map<Integer, ArrayList<Integer>> hashMapForExcel = new TreeMap<Integer,ArrayList<Integer>>();

		File excelFile = new File(filePath);
		Workbook excelWorkbook = Workbook.getWorkbook(excelFile);
		Sheet excelSheet = excelWorkbook.getSheet(0);
		int row = excelSheet.getRows();
		int col = excelSheet.getColumns();

		for (int i = 1; i < row; i++) {
			ArrayList<Integer> cellValueList = new ArrayList<Integer>();
			for (int j = 0; j < col; j++) {
				Cell excelCell = excelSheet.getCell(j, i);
				Integer cellValue = Integer.parseInt(excelCell.getContents());
				cellValueList.add(cellValue);	
			}
			hashMapForExcel.put(i,  cellValueList);
			cellValueList = null;
		}

		return hashMapForExcel;
	}
}