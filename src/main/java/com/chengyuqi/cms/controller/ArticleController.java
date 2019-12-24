package com.chengyuqi.cms.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.chengyuqi.cms.common.CmsError;
import com.chengyuqi.cms.common.CmsMessage;
import com.chengyuqi.cms.common.Commcont;
import com.chengyuqi.cms.entity.Article;
import com.chengyuqi.cms.entity.Comment;
import com.chengyuqi.cms.entity.Complain;
import com.chengyuqi.cms.entity.User;
import com.chengyuqi.cms.service.ArticleService;
import com.chengyuqi.utils.StringUtils;
import com.github.pagehelper.PageInfo;

@RequestMapping("article")
@Controller
public class ArticleController extends BaseController{
	
	@Autowired
	ArticleService articleService;
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("getDetail")
	@ResponseBody
	public CmsMessage getDetail(int id) {
		if(id<=0) {
			
		}
		// 获取文章详情
		Article article = articleService.getById(id);
		System.err.println(article.getUser().getUsername());
		// 不存在
		if(article==null)
			return new CmsMessage(CmsError.NOT_EXIST, "文章不存在",null);
		
		// 返回数据
		return new CmsMessage(CmsError.SUCCESS,"",article); 
		
	}
	
	
	/**
	 * 跳转到投诉页面
	 * @param request
	 * @param articleId
	 * @return
	 */
	@RequestMapping(value="complain",method=RequestMethod.GET)
	public String complain(HttpServletRequest request,int articleId) {
		Article article=articleService.getById(articleId);
		request.setAttribute("article", article);
		request.setAttribute("complain", new Complain());
		return "article/complain";
	}
	
	/**
	 * 提交投诉信息
	 * @param complain
	 * @param file
	 * @param result
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping(value="complain",method=RequestMethod.POST)
	public String complain(@ModelAttribute("complain") @Valid Complain complain,
			BindingResult result,MultipartFile file,HttpServletRequest request) throws IllegalStateException, IOException {
		if(!StringUtils.checkURL(complain.getSrcUrl())) {
			System.err.println("验证");
			result.rejectValue("srcUrl", "", "不是合法的url地址");
		}
		if(result.hasErrors()) {
			System.out.println(123123123);
			return "article/complain";
		}
		
		User loginUser  =  (User)request.getSession().getAttribute(Commcont.USER_KEY);
		
		String picUrl = this.processFile(file);
		complain.setPicture(picUrl);
		
		
		//加上投诉人
		if(loginUser!=null)
			complain.setUserId(loginUser.getId());
		else
			complain.setUserId(0);
		
		articleService.addComplian(complain);
		System.err.println("投诉成功12123123123");
		return "redirect:/article/detail?id="+complain.getArticleId();
	}
	
	

	@RequestMapping("detail")
	public String detail(HttpServletRequest request,int id) {
		
		Article article = articleService.getById(id);
		request.setAttribute("article", article);
		return "detail";
	}
	
	@RequestMapping("postcomment")
	@ResponseBody
	public CmsMessage postcomment(HttpServletRequest request,int articleId,String content) {
		
		User loginUser  = (User)request.getSession().getAttribute(Commcont.USER_KEY);
		
		if(loginUser==null) {
			return new CmsMessage(CmsError.NOT_LOGIN, "您尚未登录！", null);
		}
		
		Comment comment = new Comment();
		comment.setUserId(loginUser.getId());
		comment.setContent(content);
		comment.setArticleId(articleId);
		int result = articleService.addComment(comment);
		if(result > 0)
			return new CmsMessage(CmsError.SUCCESS, "成功", null);
		
		return new CmsMessage(CmsError.FAILED_UPDATE_DB, "异常原因失败，请与管理员联系", null);
		
	}
	// {articleId:'${article.id}',content:$("#co
	
		//comments?id
		@RequestMapping("comments")
		public String comments(HttpServletRequest request,int id,int page) {
			PageInfo<Comment> commentPage =  articleService.getComments(id,page);
			request.setAttribute("commentPage", commentPage);
			return "comments";
		}
	
	
		
		@RequestMapping("complains")
		public String 	complains(HttpServletRequest request,int articleId,
				@RequestParam(defaultValue="1") int page) {
			PageInfo<Complain> complianPage=   articleService.getComplains(articleId, page);
			request.setAttribute("complianPage", complianPage);
			return "article/complainslist";
		}
		
}
