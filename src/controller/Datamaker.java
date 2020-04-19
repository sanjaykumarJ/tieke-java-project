package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Datamaker {
	public static void main(String[] args) {
		Datamaker dm= new Datamaker();
		dm.datamaker();
	}
	

	void datamaker() {
		File file = new File("/Users/sanjaybalajawahar/eclipse-workspace/Teike data appl/src/controller/test.html");
		Document document=null;
		try {
			document = Jsoup.connect("https://www.linkedin.com/jobs/search/?f_E=1&location=India&locationId=in%3A0").get();
			 Elements search = document.select(".jobs-search-content__results");
//			 ListIterator<Element> datalist = search.listIterator();
//			 for (int i=0;i<datalist) {
//				 Element elem=search.listIterator().next();
//				 System.out.println(elem.html());
//			 }
			 List<Databean> databeanList=new ArrayList<>();
			 search.parallelStream().forEach(a->{
				Elements element= a.select("div");
				element.stream().forEach(b->{
					if(!b.getElementsByClass("listed-job-posting__title").text().isEmpty()) {
						Databean databean= new Databean();
						databean.setOrg_name(b.getElementsByClass("listed-job-posting__company").text());
						databean.setOrg_description(b.getElementsByClass("listed-job-posting__description").text());
						databean.setJob_location(b.getElementsByClass("listed-job-posting__location").text());
						databean.setJob_title(b.getElementsByClass("listed-job-posting__title").text());
						databeanList.add(databean);
					}
				});
			 });
			 Workbook workbook = new HSSFWorkbook();
			// Create a Font for styling header cells
		        Font headerFont = workbook.createFont();
		        headerFont.setBold(true);
		        headerFont.setFontHeightInPoints((short) 11);
		        headerFont.setColor(IndexedColors.BLACK1.getIndex());

		        // Create a CellStyle with the font
		        CellStyle headerCellStyle = workbook.createCellStyle();
		        headerCellStyle.setFont(headerFont);
		        headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.index);
			Sheet sheet= workbook.createSheet("linkedin");
			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			Row row = sheet.createRow(0);
			Cell cell=row.createCell(0);
			cell.setCellValue("job_title");
			cell.setCellStyle(headerCellStyle);
			Cell companyCell = row.createCell(1);
			companyCell.setCellValue("company_name");
			companyCell.setCellStyle(headerCellStyle);
			Cell locationCell = row.createCell(2);
			locationCell.setCellValue("location");
			locationCell.setCellStyle(headerCellStyle);
			Cell description= row.createCell(3);
			description.setCellValue("Description of company");
			description.setCellStyle(headerCellStyle);
			 for(int i=0;i<databeanList.size();i++) {
				 Databean data= databeanList.get(i);
				 Row rows= sheet.createRow(i+1);
				 Cell cellz=rows.createCell(0);
					cellz.setCellValue(data.getJob_title());
					Cell companyCellz = rows.createCell(1);
					companyCellz.setCellValue(data.getOrg_name());
					Cell locationCellz = rows.createCell(2);
					locationCellz.setCellValue(data.getJob_location());
					Cell descriptionCell= rows.createCell(3);
					descriptionCell.setCellValue(data.getOrg_description());
			 }
			File outputFile= new File("/Users/sanjaybalajawahar/eclipse-workspace/Teike data appl/src/controller/data-op.xls");
			FileOutputStream fos = new FileOutputStream(outputFile);
			 workbook.write(fos);
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
				}
}
