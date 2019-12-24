package com.chengyuqi.cms.service;

import java.util.List;

import javax.validation.Valid;

import com.chengyuqi.cms.entity.Article;
import com.chengyuqi.cms.entity.Category;
import com.chengyuqi.cms.entity.Channel;
import com.chengyuqi.cms.entity.Comment;
import com.chengyuqi.cms.entity.Complain;
import com.chengyuqi.cms.entity.Link;
import com.chengyuqi.cms.entity.Slide;
import com.github.pagehelper.PageInfo;

public interface ArticleService {

	PageInfo<Article> listByUser(Integer id, int page);

	int delete(int id);

	List<Channel> getChannels();
	List<Category> getCategorisByCid(int cid);

	int add(Article article);

	Article getById(int id);

	int update(Article article, Integer id);

	PageInfo<Article> list(int status, int page);

	int setHot(int id, int status);

	Article getInfoById(int id);

	int setCheckStatus(int id, int status);

	PageInfo<Comment> getComments(int articleId, int page);

	int addComment(Comment comment);

	List<Category> getCategoriesByChannelId(int channleId);

	PageInfo<Article> getArticles(int channleId, int catId, int page);

	List<Article> lastList();

	List<Slide> getSlides();

	PageInfo<Article> hotList(int page);

	List<Link> getLink(int page);

	boolean addLink(Link link);

	int addComplian( Complain complain);

	PageInfo<Complain> getComplains(int articleId, int page);

}
