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
import com.ipro.dao.service.MasterUser;
import com.ipro.dao.service.MasterUserImpl;
import com.ipro.model.bean.UsersBean;
import com.ipro.model.domain.UsersForm;

@Controller
public class UsersController {
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
	@RequestMapping("/UsersAddForm")
	public ModelAndView doAddFormAction() {
			System.out.println("--->Show  UserAddForm.dog");				
			//mapping jsp file ,bean name,Object Form
			return new ModelAndView("UserAddForm", "command", new UsersForm() );
	}
			
	//********Save to table TypeProduct
	//********TypeProductSaveAction
	@RequestMapping(value = "/UsersSaveAction", method = RequestMethod.POST)
	public String doSaveAction(@ModelAttribute("UsersForm")
			UsersForm eForm, BindingResult result) {	
			try{
						System.out.println("--->ADD UsersSaveAction.dog");	
						//System.out.println("BrandProduct Name:"+eForm.getTypeName());					
						MasterUser  uService= new MasterUserImpl();								
						UsersBean obj = new UsersBean();
						obj.setfName(eForm.getfName());
						obj.setlName(eForm.getlName());
						obj.setTel(eForm.getTel());
						obj.setMobile(eForm.getMobile());
						obj.setAddress(eForm.getAddress());
						obj.setEmail(eForm.getEmail());
						obj.setCitizenId(eForm.getCitizenId());
						obj.setLogin(eForm.getUserName());
						obj.setPassword(eForm.getPassword());
						
						//Generate Auto#ID
						obj.setId(GenerateAutoID.getAutoIdFromTable("usr_id", "user2"));
						System.out.println("Generate Auto#ID :"+obj.getId());
						
						boolean isIns = uService.insertUsers(obj);	
						System.out.println("-->Result Insert:"+isIns);		
						if(isIns){
							//TODO :Error insert 
							return "redirect:error.dog?msg=Insert Data is Error&url=home.dog";
						}else{
							//TODO: insert success.			
							return "redirect:success.dog?msg=Successfully&url=UsersList.dog";
						}		
				}catch(Exception e){
					e.printStackTrace();
					return "redirect:error.dog?msg=Insert Data is Error&url=home.dog";
				}
			}
			
			
	//*********doAction Update Form
	@RequestMapping(value = "/UsersUpdateAction", method = RequestMethod.POST)
	public String doUpateAction(@ModelAttribute("eForm")
		UsersForm eForm, BindingResult result)  {
			try{			
				System.out.println("--->Update  UsersUpdateAction.dog");		
				MasterUser  uService=  new MasterUserImpl();	
						
				UsersBean obj = new UsersBean();
				obj.setId(eForm.getUsrId());
				obj.setfName(eForm.getfName());
				obj.setlName(eForm.getlName());
				obj.setTel(eForm.getTel());
				obj.setMobile(eForm.getMobile());
				obj.setEmail(eForm.getEmail());
				obj.setAddress(eForm.getAddress());
				obj.setLogin(eForm.getUserName());
				obj.setPassword(eForm.getPassword());
				obj.setCitizenId(eForm.getCitizenId());
									
				boolean isUpd =  uService.updateUsers(obj);
				System.out.println("--->Result UpdateRec:"+isUpd);		
				if(isUpd){
							//TODO :Update Error  
						return "redirect:error.dog?msg=Update Data is Error&url=home.dog";
				}else{
							//TODO: Update success.			
						return "redirect:success.dog?msg=Successfully&url=UsersList.dog";
				}			
			}catch(Exception e){
					return "redirect:error.dog?msg=Update Data is Error&url=home.dog";
			}
		}	
			
		//*************doAction GetData  Form
		@RequestMapping("/UsersRetrieve")
		public ModelAndView doFectchAction(HttpServletRequest request,
				HttpServletResponse response)throws Exception {
				System.out.println("---> UsersRetrieve.dog");
				MasterUser  uService= new MasterUserImpl();	
						
				//Get Data
				UsersBean obj =  uService.getUsers(request.getParameter("uId")); 	
				System.out.println("---- test -----"+obj);
				if(obj == null){
					return new ModelAndView("msg_errors", "command",null);			
				}else{		
					//mapping Form
					UsersForm eForm = new UsersForm();
					eForm.setUsrId(obj.getId());
					eForm.setfName(obj.getfName());
					eForm.setlName(obj.getlName());
					eForm.setTel(obj.getTel());
					eForm.setMobile(obj.getMobile());
					eForm.setEmail(obj.getEmail());
					eForm.setAddress(obj.getAddress());
					eForm.setUserName(obj.getLogin());
					eForm.setPassword(obj.getPassword());
					eForm.setActivate(obj.getActivate());
					eForm.setCitizenId(obj.getCitizenId());
					eForm.setUdate(obj.getDate());
					
					System.out.println("----->"+eForm.getPassword());
					
					return new ModelAndView("UserEditForm", "command",eForm);
				}
		}

	//**********doAction  delete 
	@RequestMapping("/UsersDelete")
	public String doDeleteAction(HttpServletRequest request,
			HttpServletResponse response)throws Exception {
			System.out.println("---> TypeProductDelete.dog");
			MasterUser  uService= new MasterUserImpl();						
			//call Delete Implement	
			boolean isDel = uService.deleteUsers(request.getParameter("uId"));   
			if(isDel){
				//TODO :Error insert 
				return "redirect:error.dog?msg=Delete Record is invalid.&url=home.dog";
			}else{
				//TODO: insert success.			
				return "redirect:success.dog?msg=Successfully&url=UsersList.dog";
			}		
	}
			
		//---------ListAction
		@RequestMapping("/UsersList")
		public ModelAndView doListAction(HttpServletRequest request,
					HttpServletResponse response)throws Exception {
				System.out.println("--->List UsersList.dog");
						
				MasterUser  uService= new MasterUserImpl();	
				List searchResults = uService.listUsers();
				System.out.println("--->Result :"+searchResults.size());
								
				//--> initialize PagedListHolder with our list, set current page defaulted to 0, and pass it to the view
				PagedListHolder pagedListHolder = new PagedListHolder(searchResults);
				int page = ServletRequestUtils.getIntParameter(request, "p", 0);
						
				//Log.debug("--->initialize PagedListHolder with our list");
				pagedListHolder.setPage(page);
				int pageSize = 10;
				pagedListHolder.setPageSize(pageSize);
				//mav.addObject("pagedListHolder", pagedListHolder);				
				return new ModelAndView("UserListForm", "pagedListHolder",pagedListHolder);			
				//UserListForm.jsp
		}	
}
