package com.time.tomato.entity;

import java.io.Serializable;

/**
 * 土豆实体类
 */
public class ToastEntity implements Serializable {
	private static final long serialVersionUID = 7715082767404898634L;
	/** 土豆id */
	public Integer id;
	/** 土豆内容 */
	public String content;
	/** 土豆是否置顶 */
	public Boolean isTop;
	/** 土豆是否完成 */
	public Boolean isFinished;
	/** 土豆是否重要 */
	public Boolean isImportant;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getIsTop() {
		return isTop;
	}

	public void setIsTop(Boolean isTop) {
		this.isTop = isTop;
	}

	public Boolean getIsFinished() {
		return isFinished;
	}

	public void setIsFinished(Boolean isFinished) {
		this.isFinished = isFinished;
	}

	public Boolean getIsImportant() {
		return isImportant;
	}

	public void setIsImportant(Boolean isImportant) {
		this.isImportant = isImportant;
	}

}
