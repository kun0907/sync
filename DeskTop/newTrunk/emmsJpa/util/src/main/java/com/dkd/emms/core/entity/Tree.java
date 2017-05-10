package com.dkd.emms.core.entity;


/**
 * 生成ztree格式的树结构
 * @author SY
 *
 */

public class Tree extends BaseEntity{
	/**
	 * 显示在树中的id(仅为显示用)
	 */
	private String id; 
	/**
	 * 树状结构中的name(仅为显示用)
	 */
	private String name;
	/**
	 * 树状结构中的code(仅为显示用)
	 */
	private String code;
	/**
	 * 父节点id
	 */
	private String parentId;
	/**
	 * 节点中的点击事件触发url
	 */
	private String url;
	/**
	 * 节点中的点击事件触发页面在哪里显示
	 */
	private String target="_self";
	/**
	 * 节点图标
	 */
	private String icon;
	/**
	 * 节点所在层级
	 */
	private String level;
	/**
	 * 是否叶子节点
	 */
	private String isLeaf;
	/**
	 * 在树状结构出现checkbox时使用(复选框被选中)
	 */
	private boolean chkDisabled;
	/**
	 * 是否显示复选框
	 */
	private boolean checked;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public boolean isChkDisabled() {
		return chkDisabled;
	}
	public void setChkDisabled(boolean chkDisabled) {
		this.chkDisabled = chkDisabled;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
}
