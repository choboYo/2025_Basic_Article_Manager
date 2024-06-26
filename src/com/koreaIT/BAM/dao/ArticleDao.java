package com.koreaIT.BAM.dao;

import java.util.ArrayList;
import java.util.List;

import com.koreaIT.BAM.dto.Article;
import com.koreaIT.BAM.util.Util;

public class ArticleDao {

	private List<Article> articles;
	private int lastId;
	
	public ArticleDao() {
		this.lastId = 1;
		this.articles = new ArrayList<>();
	}

	public int getLastId() {
		return lastId;
	}
	
	public void writeArticle(int memberId, String title, String body, int viewCnt) {
		articles.add(new Article(lastId, Util.getDateStr(), memberId, title, body, viewCnt));
		lastId++;
	}
	
	public List<Article> getPrintArticles(String searchKeyword) {
		if (searchKeyword.length() > 0) {
			System.out.println("검색어 : " + searchKeyword);
			
			List<Article> printArticles = new ArrayList<>();
			
			for (Article article : articles) {
				if (article.getTitle().contains(searchKeyword)) {
					printArticles.add(article);
				}
			}
			return printArticles;
		}
		return articles;
	}
	
	public Article getArticleById(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}
		return null;
	}
	
	
	public void increaseViewCnt(Article foundArticle) {
		foundArticle.increaseViewCnt();
	}

	public void modifyArticle(Article foundArticle, String title, String body) {
		foundArticle.setTitle(title);
		foundArticle.setBody(body);
	}

	public void deleteArticle(Article foundArticle) {
		articles.remove(foundArticle);
	}

	public void setreply(Article foundReply, String text, String replyUser) {
		foundReply.setText(text);
		foundReply.setReplyUser(replyUser);
	}

	public void deleteReplyArticle(Article foundReply) {
		foundReply.setReplyUser(null);
		foundReply.setText(null);
	}

	public String getreplyUser(Article foundReply) {
		return foundReply.getReplyUser();
	}

}