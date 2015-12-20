package com.lms.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.lms.dao.AttachmentDao;
import com.lms.dao.LeadHeaderDao;
import com.lms.daoImpl.AttachmentDaoImpl;
import com.lms.daoImpl.LeadHeaderDaoImpl;
import com.lms.model.Attachment;
import com.lms.model.LeadHeader;

public class CostSummary extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private LeadHeaderDao leadHeaderDao;
	private AttachmentDao attachmentDao;
	String cmnts="";
	String leadID="";
	File uploadedFile;
	Map<String,String> obj_map = new LinkedHashMap<String,String>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CostSummary() {
        super();
        leadHeaderDao=new LeadHeaderDaoImpl();
		attachmentDao=new AttachmentDaoImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String createdDate=dateFormat.format(cal.getTime());

		// process only if its multipart content
		String comments=request.getParameter("comments");
		System.out.println("Hello India");
		
		String fileName="";
		Attachment attachment=new Attachment();
		LeadHeader leadHeader=new LeadHeader();
		
		if (isMultipart) {
			// Create a factory for disk-based file items
			FileItemFactory factory = new DiskFileItemFactory();

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				// Parse the request
				List<FileItem> multiparts = upload.parseRequest(request);

				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						
						 fileName = item.getName();
	                     fileName=Math.random()+fileName;
	                     String root = getServletContext().getRealPath("/");
	                     File path = new File(root + "/uploads");
	                     if (!path.exists()) {
								boolean status = path.mkdirs();
	                     }

	                     uploadedFile = new File(path + "/" + fileName);
	                     System.out.println(uploadedFile);
	                     item.write(uploadedFile);
	                     
	                     
					}
					else if (item.isFormField()) {
					    String name = item.getFieldName();
					    String value = item.getString();
					    if(name.equalsIgnoreCase("comments"))
					    {
					    	cmnts=item.getString();
					    }
					    if(name.equalsIgnoreCase("leadID"))
					    {
					    	leadID=item.getString();
					    }
					    
					    
					}else{}
					
					
					
				}
				
				
				
				attachment.setDOC_NAME(fileName);
				attachment.setDOC_URL("uploads/"+fileName);
				attachment.setDOC_CONTENT_TYPE("");
				attachment.setDOC_CREATED_BY("admin");
				attachment.setLEAD_ID(leadID);
				attachment.setDOC_VERSION("1.0");
				attachment.setDOC_CREATED_DATE(createdDate);
				attachment.setDOC_TYPE("COSTSHEET");
				System.out.println(attachment.toString());
				attachmentDao.addAttachments(attachment);
				
				try
				{
					Map<String,String> obj_map = new LinkedHashMap<String,String>();
					obj_map=getIndex(uploadedFile);
					System.out.println("Got "+obj_map);
					///Without Tax For Lead Time Validation
					String DG_BASIC_VALUE=(String)obj_map.get("DG_BASIC_VALUE");
					String LOW_SIDE_SUPPLY_BASIC=(String)obj_map.get("LOW_SIDE_SUPPLY_BASIC");
					String LOW_SIDE_LABOUR_BASIC=(String)obj_map.get("LOW_SIDE_LABOUR_BASIC");
					String DG_PERCENTAGE_MARGIN=(String)obj_map.get("DG_PERCENTAGE_MARGIN");
					String LOW_SIDE_PERCENTAGE_MARGIN=(String)obj_map.get("LOW_SIDE_PERCENTAGE_MARGIN");
					String TOTAL_MARGIN=(String)obj_map.get("TOTAL_MARGIN");
					String PROJECT_TOTAL=(String)obj_map.get("PROJECT_TOTAL");
					
					//With Tax while Indent Time Validation
					
					String DG_SELLING_VALUE=(String)obj_map.get("DG_SELLING_VALUE");
					String LOW_SIDE_SELLING_VALUE=(String)obj_map.get("LOW_SIDE_SELLING_VALUE");
					String PROJECT_SELLING_TOTAL=(String)obj_map.get("PROJECT_SELLING_TOTAL");
					
					
					
					
				
					LeadHeader lHeader=new LeadHeader();
					lHeader.setLEAD_ID(leadID);
					
					lHeader.setDG_ED_VALUE(DG_BASIC_VALUE);
					lHeader.setLOW_SIDE_SUPPLY_BASIC(Double.parseDouble(LOW_SIDE_SUPPLY_BASIC));
					lHeader.setLOW_SIDE_LABOUR_BASIC(Double.parseDouble(LOW_SIDE_LABOUR_BASIC));
					lHeader.setPROJECT_TOTAL(Double.parseDouble(PROJECT_TOTAL));
					lHeader.setDG_BASIC_MARGIN(DG_PERCENTAGE_MARGIN);
					lHeader.setLOW_SIDE_MARGIN(Double.parseDouble(LOW_SIDE_PERCENTAGE_MARGIN));
					lHeader.setPROJECT_MARGIN(Double.parseDouble(TOTAL_MARGIN));
					
					lHeader.setDG_SELLING_VALUE(DG_SELLING_VALUE);
					lHeader.setLOW_SIDE_SELLING_VALUE(LOW_SIDE_SELLING_VALUE);
					lHeader.setPROJECT_SELLING_VALUE(PROJECT_SELLING_TOTAL);
					
					
					
					
					System.out.println("Cost "+lHeader);
					leadHeaderDao.updateLeadCostDetails(lHeader);
					
					//lHeader.set
					
				} catch (Exception e)
				{
					e.printStackTrace();
				}
				
			} 
			catch (Exception e) 
			{
			  System.out.println("File upload failed");
			}
		}
	}
	
	public static Map getIndex(File uploadedFile ) throws Exception
	{
		FileInputStream fis = new FileInputStream(uploadedFile);
		
		System.out.println("Uploaded File is "+uploadedFile);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet s = wb.getSheetAt(2);
		
		Iterator ite = s.rowIterator();
		
		ArrayList<String> al=new ArrayList<String>();
		al.add("GRAND TOTAL");
		al.add("LOCAL TAX");
		al.add("SERVICE TAX");
		al.add("TOTAL SELLING");
		al.add("% MARGIN");
		Map<String,String> obj_map = new LinkedHashMap<String,String>();
		
		int GRAND_TOTAL=0,LOCAL_TAXE=0,SERVICE_TAX=0,TOTAL_SELLING=0,PR_MARGIN=0;
		
		BigDecimal  UP_GRAND_TOTAL=new BigDecimal(0),
				LOW_GRAND_TOTAL=new BigDecimal(0),
				
				UP_LOCAL_TAXE=new BigDecimal(0),
				LOW_LOCAL_TAXE=new BigDecimal(0),
				
				LOW_SERVICE_TAX=new BigDecimal(0),
				
				UP_TOTAL_SELLING=new BigDecimal(0),
				LOW_TOTAL_SELLING=new BigDecimal(0),
				
				UP_PR_MARGIN=new BigDecimal(0),
				LOW_PR_MARGIN=new BigDecimal(0);
		
		while (ite.hasNext())
		{
			Row r = (Row) ite.next();

			int patchColumn = -1;
			int cn=1;
			Cell c = r.getCell(cn);
			if (c == null || c.getCellType() == Cell.CELL_TYPE_BLANK)
			{
				continue;
			}
			
			if (c.getCellType() == Cell.CELL_TYPE_STRING)
			{
				String text = c.getStringCellValue().trim();
				Iterator itr=al.iterator();
				
				while(itr.hasNext())
				{  
					String listvalue=(String) itr.next();
					
					if (text.contains(listvalue))
					{
						patchColumn = cn;
						
						Cell c2 = r.getCell(cn);
						BigDecimal lonVal =new BigDecimal(0);
							
						if(c2.getRichStringCellValue().toString().contains("GRAND TOTAL"))
						{
							if(GRAND_TOTAL==0)
							{
								Cell c_grandtotal = r.getCell(patchColumn + 3);
								
								if (c_grandtotal.getCellType() == Cell.CELL_TYPE_FORMULA)
								{
									switch (c_grandtotal.getCachedFormulaResultType())
									{
										case Cell.CELL_TYPE_NUMERIC:
											
											Double e1Val = c_grandtotal.getNumericCellValue();
											lonVal = new BigDecimal(e1Val.toString());
//											lonVal = e1Val.doubleValue();
											break;
											
										case Cell.CELL_TYPE_STRING:
											
											break;
									}
								}
								
								UP_GRAND_TOTAL=lonVal;
								
								GRAND_TOTAL++;
							}
							else if(GRAND_TOTAL==1)
							{
								Cell c_grandtotal = r.getCell(patchColumn + 4);
								
								if (c_grandtotal.getCellType() == Cell.CELL_TYPE_FORMULA)
								{
									switch (c_grandtotal.getCachedFormulaResultType())
									{
										case Cell.CELL_TYPE_NUMERIC:
											
											Double e1Val = c_grandtotal.getNumericCellValue();
											lonVal = new BigDecimal(e1Val.toString());
//											lonVal = e1Val.doubleValue();
											break;
											
										case Cell.CELL_TYPE_STRING:
											
											break;
									}
								}
								
								LOW_GRAND_TOTAL=lonVal;
								GRAND_TOTAL++;
							}
						}
						else
						{
							Cell c1 = r.getCell(patchColumn + 5);
							
							if (c1.getCellType() == Cell.CELL_TYPE_FORMULA)
							{
								switch (c1.getCachedFormulaResultType())
								{
									case Cell.CELL_TYPE_NUMERIC:
										
										Double e1Val = c1.getNumericCellValue();
										lonVal = new BigDecimal(e1Val.toString());
//										lonVal = e1Val.doubleValue();
										
										break;
									case Cell.CELL_TYPE_STRING:
										
										break;
								}
							}
							
							if(c2.getRichStringCellValue().toString().contains("LOCAL TAX"))
							{
								if(LOCAL_TAXE==0)
								{
									UP_LOCAL_TAXE=lonVal;
									
									LOCAL_TAXE++;
								}
								else
								{
									LOW_LOCAL_TAXE=LOW_LOCAL_TAXE.add(lonVal);
								}
							}
							else if(c2.getRichStringCellValue().toString().contains("SERVICE TAX"))
							{
								if(SERVICE_TAX==0)
								{
									SERVICE_TAX++;
								}
								else if(SERVICE_TAX==1)
								{
									LOW_SERVICE_TAX=lonVal;
								}
							}
							else if(c2.getRichStringCellValue().toString().contains("TOTAL SELLING"))
							{
								if(TOTAL_SELLING==0)
								{
									UP_TOTAL_SELLING=lonVal;
									
									TOTAL_SELLING++;
								}
								else if(TOTAL_SELLING==1)
								{
									LOW_TOTAL_SELLING=lonVal;
								}
							}
							else if(c2.getRichStringCellValue().toString().contains("% MARGIN"))
							{
								if(PR_MARGIN==0)
								{
									UP_PR_MARGIN=lonVal;
									
									PR_MARGIN++;
								}
								else if(PR_MARGIN==1)
								{
									LOW_PR_MARGIN=lonVal;
								}
							}
						}
					}
				}
			}
		}
		
		String DG_BASIC_VALUE="",LOW_SIDE_SUPPLY_BASIC="",LOW_SIDE_LABOUR_BASIC="";

		BigDecimal PROJECT_TOTAL=new BigDecimal(0);
		
		DG_BASIC_VALUE=UP_GRAND_TOTAL.subtract(UP_LOCAL_TAXE)+"";
		LOW_SIDE_SUPPLY_BASIC=LOW_GRAND_TOTAL.subtract(LOW_LOCAL_TAXE)+"";
		LOW_SIDE_LABOUR_BASIC=LOW_GRAND_TOTAL.subtract(LOW_SERVICE_TAX)+"";
		
		PROJECT_TOTAL=PROJECT_TOTAL.add(UP_GRAND_TOTAL.subtract(UP_LOCAL_TAXE)).add(LOW_GRAND_TOTAL.subtract(LOW_LOCAL_TAXE)).add(LOW_GRAND_TOTAL.subtract(LOW_SERVICE_TAX));
		
		obj_map.put("DG_BASIC_VALUE",UP_GRAND_TOTAL.subtract(UP_LOCAL_TAXE)+"");
		obj_map.put("LOW_SIDE_SUPPLY_BASIC",LOW_GRAND_TOTAL.subtract(LOW_LOCAL_TAXE)+"");
		obj_map.put("LOW_SIDE_LABOUR_BASIC",LOW_GRAND_TOTAL.subtract(LOW_SERVICE_TAX)+"");
		obj_map.put("DG_PERCENTAGE_MARGIN",UP_PR_MARGIN+"");
		obj_map.put("LOW_SIDE_PERCENTAGE_MARGIN",LOW_PR_MARGIN+"");
		obj_map.put("TOTAL_MARGIN",UP_PR_MARGIN.add(LOW_PR_MARGIN)+"");
		obj_map.put("DG_SELLING_VALUE",UP_TOTAL_SELLING+"");
		obj_map.put("LOW_SIDE_SELLING_VALUE",LOW_TOTAL_SELLING+"");
		obj_map.put("PROJECT_SELLING_TOTAL",UP_TOTAL_SELLING.add(LOW_TOTAL_SELLING)+"");
		obj_map.put("PROJECT_TOTAL",PROJECT_TOTAL+"");
		
		System.out.println("Map Value----->"+obj_map);
		
		return obj_map;
		
	}

	

}
