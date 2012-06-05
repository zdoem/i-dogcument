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
import com.ipro.dao.service.MasterSupplier;
import com.ipro.dao.service.MasterSupplierImpl;
import com.ipro.model.bean.SupplierBean;
import com.ipro.model.domain.SupplierForm;


@Controller
public class SupplierController {
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
	@RequestMapping("/SupplierAddForm")
	public ModelAndView doAddFormAction() {
		System.out.println("--->Show  SupplierAddForm.dog");				
		//mapping jsp file ,bean name,Object Form
		return new ModelAndView("SupplierAddForm", "command", new SupplierForm());
	}
			
	//********Save to table 
	@RequestMapping(value = "/SupplierSaveAction", method = RequestMethod.POST)
	public String doSaveAction(@ModelAttribute("SupplierForm")
	SupplierForm eForm, BindingResult result) {	
		try{
				System.out.println("--->ADD SupplierSaveAction.dog");	
				//System.out.println("BrandProduct Name:"+eForm.getTypeName());					
				MasterSupplier sService= new MasterSupplierImpl();								
				SupplierBean obj = new SupplierBean();
				obj.setCompanyName(eForm.getCompanyName());
				obj.setContactName(eForm.getContactName());
				obj.setEmail(eForm.getEmail());
				obj.setTel(eForm.getTel());
				obj.setMobile(eForm.getMobile());
				obj.setAddress(eForm.getAddress());
				obj.setDesc(eForm.getDesc());
				
				boolean isIns = sService.insertSupplier(obj);	
				System.out.println("-->Result Insert:"+isIns);		
				if(isIns){
					//TODO :Error insert 
					return "redirect:error.dog?msg=Insert Data is Error&url=home.dog";
				}else{
					//TODO: insert success.			
					return "redirect:success.dog?msg=Successfully&url=SupplierList.dog";
				}		
			}catch(Exception e){
				e.printStackTrace();
				return "redirect:error.dog?msg=Insert Data is Error&url=home.dog";
			}
		}
			
			
	//*********doAction Update Form
	@RequestMapping(value = "/SupplierUpdateAction", method = RequestMethod.POST)
	public String doUpateAction(@ModelAttribute("eForm")
			SupplierForm eForm, BindingResult result)  {
			try{			
				System.out.println("--->Update  SupplierUpdateAction.dog");		
				MasterSupplier sService= new MasterSupplierImpl();	
						
				SupplierBean obj = new SupplierBean();
				obj.setId(eForm.getId());
				obj.setCompanyName(eForm.getCompanyName());
				obj.setContactName(eForm.getContactName());
				obj.setEmail(eForm.getEmail());
				obj.setTel(eForm.getTel());
				obj.setMobile(eForm.getMobile());
				obj.setAddress(eForm.getAddress());
				obj.setDesc(eForm.getDesc());	
									
				boolean isUpd = sService.updateSupplier(obj);	
				System.out.println("--->Result UpdateRec:"+isUpd);		
				if(isUpd){
					//TODO :Update Error  
					return "redirect:error.dog?msg=Update Data is Error&url=home.dog";
				}else{
					//TODO: Update success.			
					return "redirect:success.dog?msg=Successfully&url=SupplierList.dog";
				}			
			}catch(Exception e){
				return "redirect:error.dog?msg=Update Data is Error&url=home.dog";
		}
	}	
			
	//*************doAction GetData  Form
	@RequestMapping("/SupplierRetrieve")
	public ModelAndView doFectchAction(HttpServletRequest request,
				HttpServletResponse response)throws Exception {
				System.out.println("---> SupplierRetrieve.dog");
				MasterSupplier sService= new MasterSupplierImpl();	
				
				//Get Data
				SupplierBean obj = sService.getSupplier(request.getParameter("sId")); 				
				if(obj == null){
					return new ModelAndView("msg_errors", "command",null);			
				}else{		
					//mapping Form
					SupplierForm eForm = new SupplierForm();
					eForm.setId(obj.getId());
					eForm.setCompanyName(obj.getCompanyName());
					System.out.println("-->obj.getTel():"+obj.getTel());
					eForm.setContactName(obj.getContactName());
					eForm.setEmail(obj.getEmail());
					eForm.setTel(obj.getTel());
					eForm.setMobile(obj.getMobile());
					eForm.setAddress(obj.getAddress());
					eForm.setDesc(obj.getDesc());

				return new ModelAndView("SupplierEditForm", "command",eForm);
			}
		}

		//**********doAction  delete 
		@RequestMapping("/SupplierDelete")
		public String doDeleteAction(HttpServletRequest request,
				HttpServletResponse response)throws Exception {
				System.out.println("---> SupplierDelete.dog");
				MasterSupplier sService= new MasterSupplierImpl();	
					
				//call Delete Implement	
				boolean isDel = sService.deleteSupplier(request.getParameter("sId"));   
				if(isDel){
					//TODO :Error insert 
					return "redirect:error.dog?msg=Delete Record is invalid.&url=home.dog";
				}else{
					//TODO: insert success.			
					return "redirect:success.dog?msg=Successfully&url=SupplierList.dog";
				}		
		}
			
		//---------ListAction
		@RequestMapping("/SupplierList")
		public ModelAndView doListAction(HttpServletRequest request,
							HttpServletResponse response)throws Exception {
					System.out.println("--->List SupplierList.dog");
						
					MasterSupplier sService= new MasterSupplierImpl();	
					List searchResults = sService.listSupplier();
					System.out.println("--->Result :"+searchResults.size());
								
					//--> initialize PagedListHolder with our list, set current page defaulted to 0, and pass it to the view
					PagedListHolder pagedListHolder = new PagedListHolder(searchResults);
					int page = ServletRequestUtils.getIntParameter(request, "p", 0);
						
					//Log.debug("--->initialize PagedListHolder with our list");
					pagedListHolder.setPage(page);
					int pageSize = 10;
					pagedListHolder.setPageSize(pageSize);
					//mav.addObject("pagedListHolder", pagedListHolder);				
					return new ModelAndView("SupplierListForm", "pagedListHolder",pagedListHolder);			
					//SupplierListForm.jsp
			}	
}
