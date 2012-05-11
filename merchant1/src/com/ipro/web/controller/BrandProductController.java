package com.ipro.web.controller;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.ipro.dao.service.MasterBrandProduct;
import com.ipro.dao.service.MasterBrandProductImpl;
import com.ipro.model.bean.BrandProduct;
import com.ipro.model.domain.BrandProductForm;

/*
 * date:2012-01-31
 * author: pradoem wongkraso
 * contact : go2doem@gmail.com,destar_@hotmail.com
 * description: 
 * */

@Controller
@SessionAttributes
public class BrandProductController  {	

	//mapping url path
	@RequestMapping("/BrandProductAddForm")
	public ModelAndView doAddFromAction() {
		System.out.println("--->Show  BrandProductAddFrom.dog");		
		
		//mapping jsp file ,bean name,Object Form
		return new ModelAndView("/BrandProductAddForm", "command", new BrandProductForm());
	}
	
	
	//doAction Save  Form
	@RequestMapping(value = "/BrandProductSaveAction", method = RequestMethod.POST)
	public String doSaveAction(@ModelAttribute("BrandProductForm")
	BrandProductForm eForm, BindingResult result) {
		
		try{
				System.out.println("--->ADD  BrandProductSaveAction.dog");	
				System.out.println("BrandProduct Name:"+eForm.getbName());					
				MasterBrandProduct  bpService= new MasterBrandProductImpl();				
				BrandProduct bProduct = new BrandProduct();
				bProduct.setbName(eForm.getbName());					
				boolean isIns = bpService.insertBrandProduct(bProduct);	
				System.out.println("-->Result Insert:"+isIns);		
				if(isIns){
					//TODO :Error insert 
					return "redirect:error.dog?msg=Insert Data is Error&url=home.dog";
				}else{
					//TODO: insert success.			
					return "redirect:success.dog?msg=Successfully&url=BrandProductList.dog";
				}
		
		}catch(Exception e){
			e.printStackTrace();
			return "redirect:error.dog?msg=Insert Data is Error&url=home.dog";
		}
	}
	

	@RequestMapping("/BrandProductList")
	public ModelAndView doListAction(HttpServletRequest request,
				HttpServletResponse response)throws Exception {
			System.out.println("--->List BrandProductList.dog");
			
			MasterBrandProduct bpService = new MasterBrandProductImpl();
			List searchResults = bpService.listBrandProduct();
			System.out.println("--->Result :"+searchResults.size());
					
			//--> initialize PagedListHolder with our list, set current page defaulted to 0, and pass it to the view
			PagedListHolder pagedListHolder = new PagedListHolder(searchResults);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			
			//Log.debug("--->initialize PagedListHolder with our list");
			pagedListHolder.setPage(page);
			int pageSize = 10;
			pagedListHolder.setPageSize(pageSize);
			//mav.addObject("pagedListHolder", pagedListHolder);				
			return new ModelAndView("BrandProductListForm", "pagedListHolder",pagedListHolder);			
			//BrandProductListForm.jsp
	}
	
	
	//doAction  delete 
	@RequestMapping("/BrandProductDelete")
	public String doDeleteAction(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
		System.out.println("---> BrandProductDeleteAction.dog");
		MasterBrandProduct bpService = new MasterBrandProductImpl();
		
		//call Delete Implement	
		boolean isDel = bpService.deleteBrandProduct(Integer.parseInt(request.getParameter("pId")));   
		if(isDel){
			//TODO :Error insert 
			return "redirect:error.dog?msg=Delete Record is invalid.&url=home.dog";
		}else{
			//TODO: insert success.			
			return "redirect:success.dog?msg=Successfully&url=BrandProductList.dog";
		}		
	}
	
	
	//doAction GetData  Form
	@RequestMapping("/BrandProductRetrieveRec")
	public ModelAndView doFectchAction(HttpServletRequest request,
				HttpServletResponse response)throws Exception {
			System.out.println("---> BrandProductRetrieveRec.dog");
			MasterBrandProduct bpService = new MasterBrandProductImpl();
			
			//Get Data
			BrandProduct obj =  (BrandProduct)bpService.getBrandProduct(Integer.parseInt(request.getParameter("bId"))); 				
			if(obj == null){
				return new ModelAndView("msg_errors", "command",null);			
			}else{		
				//mapping Form
				BrandProductForm eForm = new BrandProductForm();
				eForm.setbId(obj.getbId()+"".trim());
				eForm.setbName(obj.getbName());			
				return new ModelAndView("BrandProductEditForm", "command",eForm);
			}
	}
		
	
	//doAction Update Form
	@RequestMapping(value = "/BrandProductUpdateAction", method = RequestMethod.POST)
	public String doUpateAction(@ModelAttribute("eForm")
		BrandProductForm eForm, BindingResult result)  {
		try{
		
			System.out.println("--->Update  BrandProductUpdateAction.dog");		
			MasterBrandProduct bpService = new MasterBrandProductImpl();
			BrandProduct bProduct = new BrandProduct();
			
			bProduct.setbId(Integer.parseInt(eForm.getbId()));
			bProduct.setbName(eForm.getbName());
			
			boolean isUpd = bpService.updateBrandProduct(bProduct);		
			System.out.println("--->Result UpdateRec:"+isUpd);		
			if(isUpd){
				//TODO :Update Error  
				return "redirect:error.dog?msg=Update Data is Error&url=home.dog";
			}else{
				//TODO: Update success.			
				return "redirect:success.dog?msg=Successfully&url=BrandProductList.dog";
			}			
		}catch(Exception e){
			return "redirect:error.dog?msg=Update Data is Error&url=home.dog";
		}
	}	
	
	
}
