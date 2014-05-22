package com.time.tomato.entity;

import java.io.Serializable;

/**
 * 土豆实体类
 */
public class TomatoEntity implements Serializable {
	private static final long serialVersionUID = 7715082767404898634L;
	/** 土豆id */
	public Integer id;
	/** 土豆内容 */
	public String content;
	/** 土豆是否置顶 */
	public Long isTop;
	/** 土豆是否完成 */
	public Long isFinished;
	/** 土豆是否重要 */
	public Long isImportant;

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

	public Long getIsTop() {
		return isTop;
	}

	public void setIsTop(Long isTop) {
		this.isTop = isTop;
	}

	public Long getIsFinished() {
		return isFinished;
	}

	public void setIsFinished(Long isFinished) {
		this.isFinished = isFinished;
	}

	public Long getIsImportant() {
		return isImportant;
	}

	public void setIsImportant(Long isImportant) {
		this.isImportant = isImportant;
	}

}
