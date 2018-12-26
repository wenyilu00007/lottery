package com.wyl.lotterycommon.utils.excel;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelReader.class);
	private String filePath;
	private String sheetName;
	private int sheetIndex;
	private Workbook workBook;
	private Sheet sheet;
	private InputStream inStream;
	private List<String> columnHeaderList;
	// 记录每一行数据
	private List<List<String>> rowListData;
	// 记录每一行数据
	private List<Map<String, String>> rowMapData;
	private boolean isGetData;
	private int headIndex;
	private Map<String, String> currentRowMap;
	private List<String> currentRowList;

	
	public ExcelReader(InputStream is, String filePath, int sheetIndex,List<String> columnHeaderList,int headIndex) {
		this.filePath = filePath;
		this.sheetIndex = sheetIndex;
		this.inStream = is;
		this.isGetData = false;
		this.columnHeaderList = columnHeaderList;
		this.headIndex = headIndex;
		this.init();
	}

	public ExcelReader(InputStream inStream, String filePath,String sheetName,List<String> columnHeaderList,int headIndex) {
		this.filePath = filePath;
		this.sheetName = sheetName;
		this.inStream = inStream;
		this.isGetData = false;
		this.columnHeaderList = columnHeaderList;
		this.headIndex = headIndex;
		this.init();
	}

	/**
	 * 初始化
	 * 
	 * @author run 2015年9月7日
	 */
	private void init() {
		this.isEmptyFilePahtAndInStream();
		if (StringUtils.isEmpty(filePath)) {
			this.initForFileInputStream();
		} else {
			this.initForFilePath();
		}
	}

	/**
	 * 验证文件路径和文件流不能同时为空
	 * 
	 * @author run 2015年9月7日
	 */
	private void isEmptyFilePahtAndInStream() {
		if (StringUtils.isEmpty(filePath) && inStream == null) {
			throw new NullPointerException("filePah和inStream 不能同时为空 ");
		}
	}

	/**
	 * 初始化
	 * 
	 * @author run
	 * @param inStream
	 *            2015年9月7日
	 */
	private void initForFileInputStream() {
		try {
			workBook = WorkbookFactory.create(inStream);
			sheet = this.getSheet();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage(), e);
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage(), e);
		}
	}

	/**
	 * 初始化
	 * 
	 * @author run 2015年9月7日
	 */
	private void initForFilePath() {
		FileInputStream inStream = null;
		try {
			inStream = new FileInputStream(new File(filePath));
			workBook = WorkbookFactory.create(inStream);
			sheet = this.getSheet();
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage(), e);
		} finally {
			try {
				if (inStream != null) {
					inStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				LOGGER.error(e.getMessage(), e);
			}
		}
	}

	private Sheet getSheet() {
		if (StringUtils.isEmpty(sheetName)) {
			return workBook.getSheetAt(sheetIndex);
		} else {
			return workBook.getSheet(sheetName);
		}
	}

	/**
	 * cell的值
	 * 
	 * @author run
	 * @param cell
	 * @return 2015年9月7日
	 */
	private String getCellValue(Cell cell) {
		if (cell == null) {
			return null;
		}
		cell.setCellType(Cell.CELL_TYPE_STRING);
		String tempStr = "";
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC:
			tempStr = String.valueOf(cell.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_STRING:
			tempStr = cell.getStringCellValue().toString();
			break;
		case Cell.CELL_TYPE_BLANK:
			tempStr = "";
			break;
		default:
			tempStr = "";
			break;
		}
		return tempStr;
	}

	/**
	 * 分装数据进 list
	 * 
	 * @author run 2015年9月7日
	 */
	private void getSheetData() {
		rowListData = new ArrayList<List<String>>();
		rowMapData = new ArrayList<Map<String, String>>();
		if(columnHeaderList == null || columnHeaderList.size() <= 0){
			columnHeaderList = new ArrayList<String>();
		}
		int numOfRows = sheet.getLastRowNum() + 1;
		for (int rowIndex = 0; rowIndex < numOfRows; rowIndex++) {
			if (rowIndex < headIndex) {
				continue;
			}
			Row row = sheet.getRow(rowIndex);
			if (row != null) {
				setColumnHeaderList(row, rowIndex);
				this.getRowData(row, rowIndex);
			}
			if (rowIndex > headIndex) {
				rowMapData.add(currentRowMap);
			}
			rowListData.add(currentRowList);
		}
		isGetData = true;
	}

	/**
	 * header的list
	 * 
	 * @author run
	 * @param row
	 * @param rowIndex
	 *            2015年9月7日
	 */
	private void setColumnHeaderList(Row row, int rowIndex) {
		if (rowIndex != headIndex) {
			return;
		}
		if(columnHeaderList.size() > 0){
			return;
		}
		for (int j = 0; j < row.getLastCellNum(); j++) {
			Cell cell = row.getCell(j);
			String cellValue = this.getCellValue(cell);
			columnHeaderList.add(cellValue);
		}
	}

	/**
	 * 得到当前row 的数据
	 * 
	 * @author run
	 * @param row
	 * @param rowIndex
	 *            2015年9月7日
	 */
	private void getRowData(Row row, int rowIndex) {
		currentRowMap = new HashMap<String, String>();
		currentRowMap.put("rowindex", rowIndex + 1 +"");
		currentRowList = new ArrayList<String>();
		for (int cellIndex = 0; cellIndex < columnHeaderList.size(); cellIndex++) {
			Cell cell = row.getCell(cellIndex);
			String cellValue = this.getCellValue(cell);
			cellValue = cellValue == null?null:cellValue.replaceAll(" ", "").replaceAll("\t", "");
			if (rowIndex > headIndex) {
				String headCellValue = columnHeaderList.get(cellIndex);
				if (currentRowMap.containsKey(headCellValue)) {
					headCellValue = headCellValue + cellIndex;
				}
				currentRowMap.put(headCellValue, cellValue);
			}
			currentRowList.add(cellValue);
		}
	}

	/**
	 * 得到指定cell 的value
	 * 
	 * @author run
	 * @param row
	 * @param col
	 * @return 2015年9月7日
	 */
	public String getCellData(int row, int col) {
		if (row <= 0 || col <= 0) {
			return null;
		}
		this.isToGetData();
		if (rowListData.size() >= row && rowListData.get(row - 1).size() >= col) {
			return rowListData.get(row - 1).get(col - 1);
		} else {
			return null;
		}
	}

	/**
	 * 得到指定cell 的value
	 * 
	 * @author run
	 * @param row
	 * @param col
	 * @return 2015年9月7日
	 */
	public String getCellData(int row, String headerName) {
		if (row <= 0) {
			return null;
		}
		this.isToGetData();
		if (rowMapData.size() >= row
				&& rowMapData.get(row - 1).containsKey(headerName)) {
			return rowMapData.get(row - 1).get(headerName);
		} else {
			return null;
		}
	}

	/**
	 * 得到sheet所有数据 row 以list表示
	 * 
	 * @author run
	 * @return 2015年9月7日
	 */
	public List<List<String>> getRowListData() {
		this.isToGetData();
		return rowListData;
	}

	/**
	 * 得到sheet所有数据 row 以map表示
	 * 
	 * @author run
	 * @return 2015年9月7日
	 */
	public List<Map<String, String>> getRowMapData() {
		this.isToGetData();
		return rowMapData;
	}

	/**
	 * 是否需要获取数据
	 * 
	 * @author run 2015年9月7日
	 */
	private void isToGetData() {
		if (!isGetData) {
			this.getSheetData();
		}
	}

	public void setHeadIndex(int headIndex) {
		this.headIndex = headIndex;
	}

	public List<String> getColumnHeaderList() {
		return columnHeaderList;
	}

	public static void main(String[] args) {
		ExcelReader eh = new ExcelReader(null,
				"E:/downloads/客户批量下单模版 (1).xlsx", 0,null,1);
		eh.setHeadIndex(1);
		for (List<String> list : eh.getRowListData()) {
			for (String string : list) {
				System.out.print(string+",");
			}
			System.out.println();
		}

		/*
		 * for (Map<String, String> map : eh.getRowMapData()) {
		 * System.out.println
		 * ("=================================================="); for (String
		 * string : map.keySet()) {
		 * System.out.println("key:"+string+"==value:"+map.get(string)); } }
		 */
		System.out.println(eh.getCellData(1, 1));
		System.out.println(eh.getCellData(1, "test1"));
	}
}
