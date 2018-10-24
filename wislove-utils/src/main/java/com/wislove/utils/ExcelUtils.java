package com.wislove.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.alibaba.fastjson.JSONObject;

public class ExcelUtils {

	/**
	 * 读取导入的excel转化为一个json的list返回 在调用的时候,再根据返回的list转为list<T>去处理存库等操作
	 * 同时外部操作需要捕获处理异常,不能再向上抛出
	 * 
	 * @param inputStream
	 *            excel的输入流
	 * @param title
	 *            已知excel的表头取的英文别名,与实体类字段对应,key值列表,如表头为:名字,性别,电话。则这个list数据就为["name","sex","phone"]
	 * @return
	 * @throws InvalidFormatException
	 * @throws IOException
	 * @throws ExcelException
	 *             自定义异常
	 */
	public static List<JSONObject> importExcel(InputStream inputStream, List<String> title)
			throws Exception, IOException {
		Workbook workbook = null;
		List<JSONObject> list = new ArrayList<>();
		try {
			workbook = WorkbookFactory.create(inputStream);
			Sheet sheet = workbook.getSheetAt(0);
			int lastRowNum = sheet.getLastRowNum();
			if (lastRowNum <= 0) {
				throw new Exception("execl表没有数据");
			}

			int titleSize = title.size();// 表头的长度

			// 除去表头,从第二行开始遍历数据
			for (int i = 1; i <= lastRowNum; i++) {
				Row row = sheet.getRow(i);// 获得一行的数据
				if (row == null) {
					continue;
				}

				JSONObject jsonObject = new JSONObject();
				// 遍历表头列,填充json里面
				for (int j = 0; j < titleSize; j++) {
					if (row.getCell(j) == null) {
						jsonObject.put(title.get(j), "");
						continue;
					}

					if (row.getCell(j).getCellType() == Cell.CELL_TYPE_STRING) {
						jsonObject.put(title.get(j), row.getCell(j).getStringCellValue().replace("'", ""));
					} else if (row.getCell(j).getCellType() == Cell.CELL_TYPE_NUMERIC) {
						jsonObject.put(title.get(j), row.getCell(j).getNumericCellValue());
					}
				}
				list.add(jsonObject);
			}
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			workbook.close();
		}

		return list;
	}

	/**
	 * 导出excel表
	 * 
	 * @param workbook
	 * @param sheetNum
	 *            (sheet的位置，0表示第一个表格中的第一个sheet)
	 * @param sheetTitle
	 *            sheet的名称
	 * @param headers
	 *            表格的标题
	 * @param result
	 *            表格的数据
	 * @param out
	 *            输出流
	 * @throws Exception
	 * @return void
	 * @author 廖双龙
	 * @date 2018年10月10日 下午5:48:04
	 * @version v1.0.0
	 */
	public static void exportExcel(HSSFWorkbook workbook, int sheetNum, String sheetTitle, String[] headers,
			List<List<String>> result, OutputStream out) throws Exception {
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet();
		workbook.setSheetName(sheetNum, sheetTitle);
		// 设置表格默认列宽度为20个字节
		sheet.setDefaultColumnWidth(20);
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
		//style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		style.setAlignment(HorizontalAlignment.CENTER);//水平居中
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.BLACK.index);
		font.setFontHeightInPoints((short) 12);
		//font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);

		// 指定当单元格内容显示不下时自动换行
		style.setWrapText(true);

		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		for (int i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);

			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text.toString());
		}
		// 遍历集合数据，产生数据行
		if (result != null) {
			int index = 1;
			for (List<String> m : result) {
				row = sheet.createRow(index);
				int cellIndex = 0;
				for (String str : m) {
					HSSFCell cell = row.createCell(cellIndex);
					cell.setCellValue(str.toString());
					cellIndex++;
				}
				index++;
			}
		}
	}
	
	
	public static void main(String[] args) {
		// 一个excel多个sheet
		OutputStream out = null;
		try {
			out = new FileOutputStream("D:\\test.xls");

			List<List<String>> data = new ArrayList<List<String>>();
			for (int i = 1; i < 5; i++) {
				List<String> rowData = new ArrayList<>();
				rowData.add(String.valueOf(i));
				rowData.add("测试测试");
				data.add(rowData);
			}

			String[] headers = { "编号", "用户名" };

			HSSFWorkbook workbook = new HSSFWorkbook();
			exportExcel(workbook, 0, "表格1", headers, data, out);
			exportExcel(workbook, 1, "表格2", headers, data, out);
			exportExcel(workbook, 2, "表格3", headers, data, out);
			exportExcel(workbook, 3, "表格4", headers, data, out);

			// 将所有的数据一起写入，然后再关闭输入流。
			workbook.write(out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
