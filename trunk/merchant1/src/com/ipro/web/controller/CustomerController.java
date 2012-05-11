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
import com.ipro.dao.service.GenerateAutoID;
import com.ipro.dao.service.MasterCustomer;
import com.ipro.dao.service.MasterCustomerImpl;
import com.ipro.model.bean.CustomerBean;
import com.ipro.model.domain.CustomerForm;

/*
 * date:2012-04-24
 * author: pradoem wongkraso
 * contact : go2doem@gmail.com,destar_@hotmail.com
 * description: Controller Servlet  krub. mapping  View jsp,Model and call Service.
 * */

@Controller
public class CustomerController {
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
		@RequestMapping("/CustomerAddForm")
		public ModelAndView doAddFormAction() {
			System.out.println("--->Show  CustomerAddForm.dog");				
			//mapping jsp file ,bean name,Object Form
			return new ModelAndView("CustomerAddForm", "command", new CustomerForm());
		}
		
		//********Save to table TypeProduct
		//********TypeProductSaveAction
		@RequestMapping(value = "/CustomerSaveAction", method = RequestMethod.POST)
		public String doSaveAction(@ModelAttribute("CustomerForm")
		CustomerForm eForm, BindingResult result) {	
			try{
					System.out.println("--->ADD CustomerSaveAction.dog");	
					//System.out.println("BrandProduct Name:"+eForm.getTypeName());					
					MasterCustomer  cService= new MasterCustomerImpl();								
					CustomerBean  obj = new CustomerBean();
					obj.setCusFname(eForm.getfName());
					obj.setCusLname(eForm.getlName());
					obj.setCusTel(eForm.getTel());
					obj.setCusMobile(eForm.getMobile());
					obj.setCusEmail(eForm.getEmail());
					obj.setCusAddress(eForm.getAddress());
					obj.setCusComment(eForm.getComment());
					obj.setCusId(GenerateAutoID.getAutoIdFromTable("cus_id", "customers"));					
					
					System.out.println("------->> AutoCustID:"+obj.getCusId());
					
					boolean isIns = cService.insertCustomers(obj);	
					System.out.println("-->Result Insert:"+isIns);		
					if(isIns){
						//TODO :Error insert 
						return "redirect:error.dog?msg=Insert Data is Error&url=home.dog";
					}else{
						//TODO: insert success.			
						return "redirect:success.dog?msg=Successfully&url=CustomerList.dog";
					}		
			}catch(Exception e){
				e.printStackTrace();
				return "redirect:error.dog?msg=Insert Data is Error&url=home.dog";
			}
		}
		
		
	    //*********doAction Update Form
		@RequestMapping(value = "/CustomerUpdateAction", method = RequestMethod.POST)
		public String doUpateAction(@ModelAttribute("eForm")
				CustomerForm eForm, BindingResult result)  {
				try{			
					System.out.println("--->Update  CustomerUpdateAction.dog");		
					MasterCustomer cService = new MasterCustomerImpl();
					
					CustomerBean obj = new CustomerBean();
					obj.setCusId(eForm.getId());
					obj.setCusFname(eForm.getfName());
					obj.setCusLname(eForm.getlName());
					obj.setCusTel(eForm.getTel());
					obj.setCusMobile(eForm.getMobile());
					obj.setCusEmail(eForm.getEmail());
					obj.setCusAddress(eForm.getAddress());
					obj.setCusComment(eForm.getComment());
								
					boolean isUpd = cService.updateCustomers(obj);	
					System.out.println("--->Result UpdateRec:"+isUpd);		
					if(isUpd){
						//TODO :Update Error  
						return "redirect:error.dog?msg=Update Data is Error&url=home.dog";
					}else{
						//TODO: Update success.			
						return "redirect:success.dog?msg=Successfully&url=CustomerList.dog";
					}			
				}catch(Exception e){
					return "redirect:error.dog?msg=Update Data is Error&url=home.dog";
				}
			}	
		
		//*************doAction GetData  Form
		@RequestMapping("/CustomerRetrieve")
		public ModelAndView doFectchAction(HttpServletRequest request,
						HttpServletResponse response)throws Exception {
					System.out.println("---> CustomerRetrieve.dog");
					MasterCustomer cService = new MasterCustomerImpl();
					
					//Get Data
					CustomerBean obj = cService.getCustomers(request.getParameter("cId")); 				
					if(obj == null){
						return new ModelAndView("msg_errors", "command",null);			
					}else{		
						//mapping Form
						CustomerForm eForm = new CustomerForm();
						eForm.setId(obj.getCusId());
						eForm.setfName(obj.getCusFname());
						eForm.setlName(obj.getCusLname());
						eForm.setTel(obj.getCusTel());
						eForm.setMobile(obj.getCusMobile());
						eForm.setEmail(obj.getCusEmail());
						eForm.setAddress(obj.getCusAddress());
						eForm.setFlag(obj.getCusFlag());
						eForm.setComment(obj.getCusComment());
						
						return new ModelAndView("CustomerEditForm", "command",eForm);
						//mapping CustomerEditForm.jsp file 
					}
			}

		//**********doAction  delete 
		@RequestMapping("/CustomerDelete")
		public String doDeleteAction(HttpServletRequest request,
					HttpServletResponse response)throws Exception {
				System.out.println("---> CustomerDelete.dog");
				MasterCustomer cService = new MasterCustomerImpl();				
				//call Delete Implement	
				System.out.println("--CID:"+request.getParameter("cId"));
				boolean isDel = cService.deleteCustomers(request.getParameter("cId"));
				if(isDel){
					//TODO :Error insert 
					return "redirect:error.dog?msg=Delete Record is invalid.&url=home.dog";
				}else{
					//TODO: insert success.			
					return "redirect:success.dog?msg=Successfully&url=CustomerList.dog";
				}		
			}
		
			//---------ListAction
		@RequestMapping("/CustomerList")
		public ModelAndView doListAction(HttpServletRequest request,
						HttpServletResponse response)throws Exception {
					System.out.println("--->List CustomerList.dog");
					
					MasterCustomer cService = new MasterCustomerImpl();
					List searchResults = cService.listCustomers();
					System.out.println("--->Result :"+searchResults.size());
							
					//--> initialize PagedListHolder with our list, set current page defaulted to 0, and pass it to the view
					PagedListHolder pagedListHolder = new PagedListHolder(searchResults);
					int page = ServletRequestUtils.getIntParameter(request, "p", 0);
					
					//Log.debug("--->initialize PagedListHolder with our list");
					pagedListHolder.setPage(page);
					int pageSize = 10;
					pagedListHolder.setPageSize(pageSize);
					//mav.addObject("pagedListHolder", pagedListHolder);				
					return new ModelAndView("CustomerListForm", "pagedListHolder",pagedListHolder);			
					//CustomerListForm.jsp
			}

}
