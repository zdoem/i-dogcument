package com.ipro.web.controller;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.ipro.dao.service.MasterBrandProduct;
import com.ipro.dao.service.MasterBrandProductImpl;
import com.ipro.dao.service.MasterTypeProduct;
import com.ipro.dao.service.MasterTypeProductImpl;
import com.ipro.model.bean.TypeProduct;
import com.ipro.model.domain.TypeProductForm;

/*
 * date:2012-04-24
 * author: pradoem wongkraso
 * contact : go2doem@gmail.com,destar_@hotmail.com
 * description: Controller Servlet  krub. mapping  View jsp,Model and call Service.
 * */

@Controller
public class TypeProductController {
	//*********Show all parameter All by pass Request query.
	private static void EchoParamRQ(HttpServletRequest request){
		String paramNames = "";
		System.out.println("---------[ Parameter List] ------------");
		for(Enumeration e = request.getParameterNames();e.hasMoreElements(); ){
			paramNames = (String)e.nextElement();
			System.out.println(paramNames+" = "+request.getParameter(paramNames));
		}		
		System.out.println("---------- [Parameter List] -----------");		
	}
	
	
	//*********mapping url path
	@RequestMapping("/TypeProductAddForm")
	public ModelAndView doAddFormAction() {
		System.out.println("--->Show  TypeProductAddForm.dog");				
		//mapping jsp file ,bean name,Object Form
		return new ModelAndView("/TypeProductAddForm", "command", new TypeProductForm());
	}
	
	//********Save to table TypeProduct
	//********TypeProductSaveAction
	@RequestMapping(value = "/TypeProductSaveAction", method = RequestMethod.POST)
	public String doSaveAction(@ModelAttribute("TypeProductForm")
	TypeProductForm eForm, BindingResult result) {	
		try{
				System.out.println("--->ADD  TypeProductSaveAction.dog");	
				System.out.println("TypeProduct Name:"+eForm.getTypeName());					
				MasterTypeProduct  typeService= new MasterTypeProductImpl();								
				TypeProduct obj = new TypeProduct();
				obj.setTypeName(eForm.getTypeName());					
				
				boolean isIns = typeService.insertTypeProduct(obj);	
				System.out.println("-->Result Insert:"+isIns);		
				if(isIns){
					//TODO :Error insert 
					return "redirect:error.dog?msg=Insert Data is Error&url=home.dog";
				}else{
					//TODO: insert success.			
					return "redirect:success.dog?msg=Successfully&url=TypeProductList.dog";
				}		
		}catch(Exception e){
			e.printStackTrace();
			return "redirect:error.dog?msg=Insert Data is Error&url=home.dog";
		}
	}
	
	
    //*********doAction Update Form
	@RequestMapping(value = "/TypeProductUpdateAction", method = RequestMethod.POST)
	public String doUpateAction(@ModelAttribute("eForm")
			TypeProductForm eForm, BindingResult result)  {
			try{			
				System.out.println("--->Update  TypeProductUpdateAction.dog");		
				MasterTypeProduct tService = new MasterTypeProductImpl();
				
				TypeProduct obj = new TypeProduct();
				obj.setTypeId(eForm.getTypeId());
				obj.setTypeName(eForm.getTypeName());
							
				boolean isUpd = tService.updateTypeProduct(obj);	
				System.out.println("--->Result UpdateRec:"+isUpd);		
				if(isUpd){
					//TODO :Update Error  
					return "redirect:error.dog?msg=Update Data is Error&url=home.dog";
				}else{
					//TODO: Update success.			
					return "redirect:success.dog?msg=Successfully&url=TypeProductList.dog";
				}			
			}catch(Exception e){
				return "redirect:error.dog?msg=Update Data is Error&url=home.dog";
			}
		}	
	
	//*************doAction GetData  Form
	@RequestMapping("/TypeProductRetrieve")
	public ModelAndView doFectchAction(HttpServletRequest request,
					HttpServletResponse response)throws Exception {
				System.out.println("---> TypeProductRetrieve.dog");
				MasterTypeProduct tService = new MasterTypeProductImpl();
				
				//Get Data
				TypeProduct obj = (TypeProduct)tService.getTypeProduct(Integer.parseInt(request.getParameter("tId"))); 				
				if(obj == null){
					return new ModelAndView("msg_errors", "command",null);			
				}else{		
					//mapping Form
					TypeProductForm eForm = new TypeProductForm();
					eForm.setTypeId(obj.getTypeId());
					eForm.setTypeName(obj.getTypeName());							
					return new ModelAndView("TypeProductEditForm", "command",eForm);
			}
		}

	//**********doAction  delete 
		@RequestMapping("/TypeProductDelete")
		public String doDeleteAction(HttpServletRequest request,
				HttpServletResponse response)throws Exception {
			System.out.println("---> TypeProductDelete.dog");
			MasterTypeProduct tService = new MasterTypeProductImpl();
			
			//call Delete Implement	
			boolean isDel = tService.deleteTypeProduct(Integer.parseInt(request.getParameter("tId")));   
			if(isDel){
				//TODO :Error insert 
				return "redirect:error.dog?msg=Delete Record is invalid.&url=home.dog";
			}else{
				//TODO: insert success.			
				return "redirect:success.dog?msg=Successfully&url=TypeProductList.dog";
			}		
		}
	
		//---------ListAction
		@RequestMapping("/TypeProductList")
		public ModelAndView doListAction(HttpServletRequest request,
					HttpServletResponse response)throws Exception {
				System.out.println("--->List TypeProductList.dog");
				
				MasterTypeProduct tService = new MasterTypeProductImpl();
				List searchResults = tService.listTypeProduct();
				System.out.println("--->Result :"+searchResults.size());
						
				//--> initialize PagedListHolder with our list, set current page defaulted to 0, and pass it to the view
				PagedListHolder pagedListHolder = new PagedListHolder(searchResults);
				int page = ServletRequestUtils.getIntParameter(request, "p", 0);
				
				//Log.debug("--->initialize PagedListHolder with our list");
				pagedListHolder.setPage(page);
				int pageSize = 10;
				pagedListHolder.setPageSize(pageSize);
				//mav.addObject("pagedListHolder", pagedListHolder);				
				return new ModelAndView("TypeProductListForm", "pagedListHolder",pagedListHolder);			
				//mapping file viewer = "TypeProductListForm.jsp"
		}
	
}
