package com.chengyuqi.cms.controller;



import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.chengyuqi.cms.common.Commcont;
import com.chengyuqi.cms.entity.Article;
import com.chengyuqi.cms.entity.Category;
import com.chengyuqi.cms.entity.Channel;
import com.chengyuqi.cms.entity.User;
import com.chengyuqi.cms.service.ArticleService;
import com.chengyuqi.cms.service.UserService;
import com.chengyuqi.utils.FileUtils;
import com.chengyuqi.utils.HtmlUtils;
import com.chengyuqi.utils.StringUtils;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Value("${upload.path}")
	String picRootPath;
	
	@Value("${pic.path}")
	String picUrl;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping(value="rejister", method=RequestMethod.GET)
	public String toRejister(HttpServletRequest request) {
		User user = new User();
		request.setAttribute("user", user);
		return "/user/rejister";
	}
	
	@RequestMapping("logout")
	public String home(HttpServletRequest request,HttpServletResponse response) {
		request.getSession().removeAttribute(Commcont.USER_KEY);
		
		Cookie cookieUserName = new Cookie("username", "");
		cookieUserName.setPath("/");
		cookieUserName.setMaxAge(0);// 立即过期
		response.addCookie(cookieUserName);
		Cookie cookiePassword = new Cookie("password", "");
		cookiePassword.setPath("/");
		cookiePassword.setMaxAge(0);// 立即过期
		response.addCookie(cookiePassword);
		
		return "redirect:/";
	}
	
	/**
	 * 删除个人中心的文章
	 * @return
	 */
	@RequestMapping("deletearticle")
	@ResponseBody
	public boolean deletearticle(int id) {
		
		int result= articleService.delete(id);
		
		return result>0;
		
	}
	
	
	@RequestMapping(value="rejister",method=RequestMethod.POST)
	public String rejister(@Valid @ModelAttribute("user")User user,BindingResult result,HttpServletRequest request) {
		
		//JSR303校验，有错误直接返回注册页面
		if(result.hasErrors()) {
			return "/user/rejister";
		}
		
		//进行唯一校验
		//提取user对象中的username属性
		String username = user.getUsername();
		//将提取出来的usernmae传入，并且返回一个User对象
		User getUser =userService.getUserByUserName(username);
		//判断这个user对象，如果这个对象不为空，则用户已经存在，返回给前台错误信息
		if(getUser!=null) {
			result.rejectValue("username", "", "用户名已存在，请重新输入");
			return "/user/rejister";
		}
		
		//手动验证，利用工具类，进行验证密码是否全为数字,如果全为数字则返回true
		if(StringUtils.isNumber(user.getPassword())) {
			result.rejectValue("passwordd","","密码不可以是纯数字");
			return "/user/rejister";
		}
		
		//进行注册，返回一个int类型，进行判断是否注册成功
		int i=userService.rejister(user);
		//判断如果i小于1的话则说明没有成功加进去
		if(i<1) {
			request.setAttribute("err", "注册失败，请稍后重试");
			return "/user/rejister";
		}
		
		return "redirect:login";
		
	}
	/**
	 * 名字的唯一性检查
	 * @param username
	 * @return
	 */
	@ResponseBody
	@RequestMapping("checkname")
	public boolean checkname(String username) {
		User getUser =userService.getUserByUserName(username);
		return getUser==null;
	}
	
	/**
	 * 进入登录的页面
	 * @return
	 */
	@RequestMapping(value="login" ,method=RequestMethod.GET)
	public String login() {
		return "/user/login";
	}
	
	/**
	 * 登录方法
	 * @param request
	 * @param user
	 * @param m
	 * @return
	 */
	@RequestMapping(value="login" ,method=RequestMethod.POST)
	public String login(HttpServletRequest request,User user,Model m,HttpServletResponse response) {
		//接受前台传来的用户名和密码传到业务层接口，返回一个user对象
		String pwd=new String(user.getPassword());
		User getUser = userService.login(user);
		//利用返回的对象进行判断，如果不为空则说明登录正确
		if(getUser==null) {
			m.addAttribute("err", "用户名或密码错误");
			return "/user/login";
		}
		//登录成功后将对象存入session中
		request.getSession().setAttribute(Commcont.USER_KEY, getUser);
		
		//保存用户的用户名和密码
		Cookie cookieUserName = new Cookie("username",user.getUsername());
		cookieUserName.setPath("/");
		cookieUserName.setMaxAge(10*24*3600);//保存十天
		response.addCookie(cookieUserName);
		Cookie cookiePassword = new Cookie("password", pwd);
		cookiePassword.setPath("/");
		cookiePassword.setMaxAge(10*24*3600);//保存十天
		response.addCookie(cookiePassword);
		
		//判断登录者的身份，如果是管理员则跳转到管理员界面，反之则进入到用户中心
		if(getUser.getRole()==Commcont.USER_ADMIN) {
			return "/admin/index";
		}
		
		return "redirect:home";
	}
	
	@RequestMapping("home")
	public String home() {
		return "/user/home";
	}
	
	@RequestMapping("articles")
	public String articles(HttpServletRequest request,@RequestParam(defaultValue="1") int page) {
		User loginUser = (User)request.getSession().getAttribute(Commcont.USER_KEY);
		System.err.println(loginUser);
		PageInfo<Article> articlePage = articleService.listByUser(loginUser.getId(),page);
		request.setAttribute("articlePage", articlePage);
		return "article/list";
	}
	
	
	/**
	 * 跳转到发布文章的页面
	 * @return
	 */
	@RequestMapping("postArticle")
	public String postArticle(HttpServletRequest request) {	
		List<Channel> channels= articleService.getChannels();
		request.setAttribute("channels", channels);
		return "/article/post";
	}
	
	/**
	 *  获取分类
	 * @param request
	 * @param cid
	 * @return
	 */
	@RequestMapping("getCategoris")
	@ResponseBody
	public List<Category>  getCategoris(int cid) {	
		List<Category> categoris = articleService.getCategorisByCid(cid);
		return categoris;
	}
	
	
	@RequestMapping(value = "postArticle",method=RequestMethod.POST)
	@ResponseBody
	public boolean postArticle(HttpServletRequest request, Article article,MultipartFile file) {
		String picUrl;
		try {
			// 处理上传文件
			picUrl = processFile(file);
			article.setPicture(picUrl);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//当前用户是文章的作者
		User loginUser = (User)request.getSession().getAttribute(Commcont.USER_KEY);
		article.setUserId(loginUser.getId());
		
		return articleService.add(article)>0;
	}
	
	/**
	 * 
	 * @param file
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	private String processFile(MultipartFile file) throws IllegalStateException, IOException {
		
		if(file.isEmpty()) 
			return "";
		
		// 判断目标目录时间否存在
		//picRootPath + ""
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String subPath = sdf.format(new Date());
		//图片存放的路径
		File path= new File(picRootPath+"/" + subPath);
		//路径不存在则创建
		if(!path.exists())
			path.mkdirs();
		
		//计算新的文件名称
		String suffixName = FileUtils.getSuffixName(file.getOriginalFilename());
		
		//随机生成文件名
		String fileName = UUID.randomUUID().toString() + suffixName;
		//文件另存
		file.transferTo(new File(picRootPath+"/" + subPath + "/" + fileName));
		return  subPath + "/" + fileName;
		
	}
	
	/**
	 * 跳转到修改文章的页面
	 * @return
	 */
	@RequestMapping(value="updateArticle",method=RequestMethod.GET)
	public String updateArticle(HttpServletRequest request,int id) {	
		System.err.println(id);
		//获取栏目
		List<Channel> channels= articleService.getChannels();
		request.setAttribute("channels", channels);
		
		//获取文章
		Article article = articleService.getById(id);
		System.err.println("aaaaa"+article.getPicture());
		User loginUser = (User)request.getSession().getAttribute(Commcont.USER_KEY);
		if(loginUser.getId() != article.getUserId()) {
			// todo 准备做异常处理的！！
		}
		request.setAttribute("article", article);
		request.setAttribute("content1",  HtmlUtils.htmlspecialchars(article.getContent()));
		
		
		return "/article/update";
	}
	
	/**
	 * 接受修改文章的页面提交的数据
	 * @return
	 */
	@RequestMapping(value="updateArticle",method=RequestMethod.POST)
	@ResponseBody
	public  boolean  updateArticle(HttpServletRequest request,Article article,MultipartFile file) {
		System.out.println("aarticle is  "  + article);
		String picUrl;
		try {
			picUrl = processFile(file);
			// 处理上传文件
			if(StringUtils.haveValue(picUrl)) {
				article.setPicture(picUrl);
			}
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//当前用户是文章的作者
		User loginUser = (User)request.getSession().getAttribute(Commcont.USER_KEY);
		//article.setUserId(loginUser.getId());
		int updateREsult  = articleService.update(article,loginUser.getId());
		return updateREsult>0;
	}
	
	/**
	 * 
	 * @param username
	 * @return
	 */
	/*@RequestMapping("deletearticle")
	@ResponseBody
	public boolean deleteArticle(int id) {
		int result  = articleService.delete(id);
		return result > 0;
	}*/
}
