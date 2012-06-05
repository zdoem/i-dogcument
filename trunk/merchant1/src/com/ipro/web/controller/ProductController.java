package com.ipro.web.controller;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ipro.dao.service.GenerateAutoID;
import com.ipro.dao.service.MasterBrandProduct;
import com.ipro.dao.service.MasterBrandProductImpl;
import com.ipro.dao.service.MasterProduct;
import com.ipro.dao.service.MasterProductImpl;
import com.ipro.dao.service.MasterTypeProduct;
import com.ipro.dao.service.MasterTypeProductImpl;
import com.ipro.model.bean.BrandProduct;
import com.ipro.model.bean.ProductBean;
import com.ipro.model.bean.TypeProduct;
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
		public ModelAndView doAddFormAction(HttpServletRequest request) throws Exception {
			HttpSession session = request.getSession();
			System.out.println("--->Show  ProductAddForm.dog");
			
			/*******************************
			 * for BrandProductDDL
			 ********************************* */
			MasterBrandProduct bpService = new MasterBrandProductImpl();
			ArrayList bResult = bpService.listBrandProduct();
			BrandProduct  brand = new BrandProduct();//From Bean OBJ

 			Map<String,String> mapBrand = new LinkedHashMap<String,String>();		
			if(bResult!=null && bResult.size()>0){
				Iterator it = bResult.iterator();
				while(it.hasNext()){
					brand =(BrandProduct)it.next();
					mapBrand.put(brand.getbId()+"".trim(),brand.getbName());
				}
			}
			session.setAttribute("bProductListDDL", mapBrand);	
			
			/*******************************
			 * for typeProduct DDL
			 ********************************* */
			MasterTypeProduct tService = new MasterTypeProductImpl();
			ArrayList tResult = tService.listTypeProduct();

			TypeProduct  typePro = new TypeProduct();		
			 Map<String,String> mapType = new LinkedHashMap<String,String>();		
			 if(tResult!=null && tResult.size()>0){
				Iterator it = tResult.iterator();
				while(it.hasNext()){
					typePro =(TypeProduct)it.next();
					mapType.put(typePro.getTypeId()+"".trim(),typePro.getTypeName());
				}
			}
			session.setAttribute("tProductListDDL", mapType);				
			//mapping jsp file ,bean name,Object Form
			return new ModelAndView("ProductAddForm", "command", new ProductForm());
		}
			
		//********Save to table TypeProduct
		//********TypeProductSaveAction
		@RequestMapping(value = "/ProductSaveAction", method = RequestMethod.POST)
		public String doSaveAction(@ModelAttribute("ProductForm")
		ProductForm eForm, BindingResult result) {	
			try{
					System.out.println("--->ADD ProductSaveAction.dog");	
					//System.out.println("BrandProduct Name:"+eForm.getTypeName());					
					MasterProduct  pService= new MasterProductImpl();								
					
					ProductBean obj = new ProductBean();
					obj.setProductName(eForm.getProductName());
					obj.setBrandProductId(eForm.getBrandProductIdDDL());
					obj.setTypeProductId(eForm.getTypeProductIdDDL());
					obj.setPriceCost(eForm.getPriceCost());
					obj.setPriceSale(eForm.getPriceSale());
					obj.setProductItem(eForm.getProductItem());
					obj.setProductDateStart(eForm.getProductDateStart());
					obj.setProductDateExpire(eForm.getProductDateExpire());
					obj.setProductDesc(eForm.getProductDesc());
					
					obj.setProductId(GenerateAutoID.getAutoIdFromTable("product_id", "product"));
											
					boolean isIns = pService.insertProduct(obj);
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
				ProductForm eForm, BindingResult result)  {
					try{			
						System.out.println("--->Update  ProductUpdateAction.dog");		
						MasterProduct  pService= new MasterProductImpl();	
						
						ProductBean obj = new ProductBean();
						obj.setProductName(eForm.getProductName());
						obj.setBrandProductId(eForm.getBrandProductIdDDL());
						obj.setTypeProductId(eForm.getTypeProductIdDDL());
						obj.setPriceCost(eForm.getPriceCost());
						obj.setPriceSale(eForm.getPriceSale());
						obj.setProductItem(eForm.getProductItem());
						obj.setProductDateStart(eForm.getProductDateStart());
						obj.setProductDateExpire(eForm.getProductDateExpire());
						obj.setProductDesc(eForm.getProductDesc());
						obj.setProductId(eForm.getProductId());
									
						boolean isUpd = pService.updateProduct(obj);	
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
					HttpSession session = request.getSession();
					System.out.println("---> ProductRetrieve.dog");
					MasterProduct  pService= new MasterProductImpl();	
					
					ProductBean obj = new ProductBean();						
					//Get Data
					obj = pService.getProduct(request.getParameter("pId")); 				
					if(obj == null){
						return new ModelAndView("msg_errors", "command",null);			
					}else{						
							/*******************************
							 * for BrandProductDDL
							 ********************************* */
							MasterBrandProduct bpService = new MasterBrandProductImpl();
							ArrayList bResult = bpService.listBrandProduct();
							BrandProduct  brand = new BrandProduct();//From Bean OBJ	
				 			Map<String,String> mapBrand = new LinkedHashMap<String,String>();		
							if(bResult!=null && bResult.size()>0){
								Iterator it = bResult.iterator();
								while(it.hasNext()){
									brand =(BrandProduct)it.next();
									mapBrand.put(brand.getbId()+"".trim(),brand.getbName());
								}
							}
							session.setAttribute("bProductListDDL", mapBrand);	
							
							/*******************************
							 * for typeProduct DDL
							 ********************************* */
							MasterTypeProduct tService = new MasterTypeProductImpl();
							ArrayList tResult = tService.listTypeProduct();	
							TypeProduct  typePro = new TypeProduct();		
							 Map<String,String> mapType = new LinkedHashMap<String,String>();		
							 if(tResult!=null && tResult.size()>0){
								Iterator it = tResult.iterator();
								while(it.hasNext()){
									typePro =(TypeProduct)it.next();
									mapType.put(typePro.getTypeId()+"".trim(),typePro.getTypeName());
								}
							}
							session.setAttribute("tProductListDDL", mapType);				
		
							//*********mapping Form
							ProductForm eForm = new ProductForm();
							eForm.setProductId(obj.getProductId());
							eForm.setProductName(obj.getProductName());
							eForm.setBrandProductIdDDL(obj.getBrandProductId());
							eForm.setTypeProductIdDDL(obj.getTypeProductId());
							eForm.setPriceCost(obj.getPriceCost());
							eForm.setPriceSale(obj.getPriceSale());
							eForm.setProductItem(obj.getProductItem());
							eForm.setProductDateStart(obj.getProductDateStart());
							eForm.setProductDateExpire(obj.getProductDateExpire());
							eForm.setProductDesc(obj.getProductDesc());						
						
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
				boolean isDel = pService.deleteProduct(request.getParameter("pId"));   
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
				List searchResults = pService.listProduct();
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
				//ProductListForm.jsp
		}

}
