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

import com.ipro.dao.service.MasterProduct;
import com.ipro.dao.service.MasterProductImpl;
import com.ipro.model.bean.ProductBean;
import com.ipro.model.domain.CustomerForm;
import com.ipro.model.domain.ProductForm;

@Controller
public class ProductController {
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
			@RequestMapping("/ProductAddForm")
			public ModelAndView doAddFormAction() {
				System.out.println("--->Show  ProductAddForm.dog");				
				//mapping jsp file ,bean name,Object Form
				return new ModelAndView("/ProductAddForm", "command", new ProductForm());
			}
			
			//********Save to table TypeProduct
			//********TypeProductSaveAction
			@RequestMapping(value = "/ProductSaveAction", method = RequestMethod.POST)
			public String doSaveAction(@ModelAttribute("ProductForm")
			CustomerForm eForm, BindingResult result) {	
				try{
						System.out.println("--->ADD ProductSaveAction.dog");	
						//System.out.println("BrandProduct Name:"+eForm.getTypeName());					
						MasterProduct  pService= new MasterProductImpl();								
						//TypeProduct obj = new TypeProduct();
						//obj.setTypeName(eForm.getTypeName());					
						
						boolean isIns = false;// bpService.insertBrandProduct(bProduct);	
						System.out.println("-->Result Insert:"+isIns);		
						if(isIns){
							//TODO :Error insert 
							return "redirect:error.dog?msg=Insert Data is Error&url=home.dog";
						}else{
							//TODO: insert success.			
							return "redirect:success.dog?msg=Successfully&url=ProductList.dog";
						}		
				}catch(Exception e){
					e.printStackTrace();
					return "redirect:error.dog?msg=Insert Data is Error&url=home.dog";
				}
			}
			
			
		    //*********doAction Update Form
			@RequestMapping(value = "/ProductUpdateAction", method = RequestMethod.POST)
			public String doUpateAction(@ModelAttribute("eForm")
					CustomerForm eForm, BindingResult result)  {
					try{			
						System.out.println("--->Update  ProductUpdateAction.dog");		
						MasterProduct  pService= new MasterProductImpl();	
						
						ProductBean obj = new ProductBean();
						//obj.setTypeId(eForm.getTypeId());
						//obj.setTypeName(eForm.getTypeName());
									
						boolean isUpd = false;// bpService.updateBrandProduct(obj);		
						System.out.println("--->Result UpdateRec:"+isUpd);		
						if(isUpd){
							//TODO :Update Error  
							return "redirect:error.dog?msg=Update Data is Error&url=home.dog";
						}else{
							//TODO: Update success.			
							return "redirect:success.dog?msg=Successfully&url=ProductList.dog";
						}			
					}catch(Exception e){
						return "redirect:error.dog?msg=Update Data is Error&url=home.dog";
					}
				}	
			
			//*************doAction GetData  Form
			@RequestMapping("/ProductRetrieve")
			public ModelAndView doFectchAction(HttpServletRequest request,
							HttpServletResponse response)throws Exception {
						System.out.println("---> ProductRetrieve.dog");
						MasterProduct  pService= new MasterProductImpl();	
						
						ProductBean obj = null;
						
						//Get Data
						//TypeProduct obj = null;// (BrandProduct)bpService.getBrandProduct(Integer.parseInt(request.getParameter("bId"))); 				
						if(obj == null){
							return new ModelAndView("msg_errors", "command",null);			
						}else{		
							//mapping Form
							CustomerForm eForm = new CustomerForm();
							//eForm.setTypeId(obj.getTypeId());
							//eForm.setTypeName(obj.getTypeName());							
							return new ModelAndView("ProductEditForm", "command",eForm);
						}
				}

			//**********doAction  delete 
				@RequestMapping("/ProductDelete")
				public String doDeleteAction(HttpServletRequest request,
						HttpServletResponse response)throws Exception {
					System.out.println("---> ProductDelete.dog");
					MasterProduct  pService= new MasterProductImpl();	
					
					//call Delete Implement	
					boolean isDel = false;// bpService.deleteBrandProduct(Integer.parseInt(request.getParameter("pId")));   
					if(isDel){
						//TODO :Error insert 
						return "redirect:error.dog?msg=Delete Record is invalid.&url=home.dog";
					}else{
						//TODO: insert success.			
						return "redirect:success.dog?msg=Successfully&url=ProductList.dog";
					}		
				}
			
				//---------ListAction
				@RequestMapping("/ProductList")
				public ModelAndView doListAction(HttpServletRequest request,
							HttpServletResponse response)throws Exception {
						System.out.println("--->List ProductList.dog");
						
						MasterProduct  pService= new MasterProductImpl();	
						List searchResults = null;//bpService.listBrandProduct();
						System.out.println("--->Result :"+searchResults.size());
								
						//--> initialize PagedListHolder with our list, set current page defaulted to 0, and pass it to the view
						PagedListHolder pagedListHolder = new PagedListHolder(searchResults);
						int page = ServletRequestUtils.getIntParameter(request, "p", 0);
						
						//Log.debug("--->initialize PagedListHolder with our list");
						pagedListHolder.setPage(page);
						int pageSize = 10;
						pagedListHolder.setPageSize(pageSize);
						//mav.addObject("pagedListHolder", pagedListHolder);				
						return new ModelAndView("ProductListForm", "pagedListHolder",pagedListHolder);			
						//BrandProductListForm.jsp
				}

}
