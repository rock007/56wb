package sam.wb.db.entity.shuadan;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ProductAttr {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Long product_id;

	private String att_name;
	
	private String att_text;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}

	public String getAtt_name() {
		return att_name;
	}

	public void setAtt_name(String att_name) {
		this.att_name = att_name;
	}

	public String getAtt_text() {
		return att_text;
	}

	public void setAtt_text(String att_text) {
		this.att_text = att_text;
	}

	
}
