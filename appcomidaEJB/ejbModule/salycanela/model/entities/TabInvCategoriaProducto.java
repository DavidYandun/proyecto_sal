package salycanela.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tab_inv_categoria_productos database table.
 * 
 */
@Entity
@Table(name="tab_inv_categoria_productos")
@NamedQuery(name="TabInvCategoriaProducto.findAll", query="SELECT t FROM TabInvCategoriaProducto t")
public class TabInvCategoriaProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TAB_INV_CATEGORIA_PRODUCTOS_INVIDCATPROD_GENERATOR", sequenceName="TAB_INV_CATEGORIA_PRODUCTOS_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TAB_INV_CATEGORIA_PRODUCTOS_INVIDCATPROD_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer invidcatprod;

	@Column(length=50)
	private String invnombrecatprod;

	//bi-directional many-to-one association to TabInvProducto
	@OneToMany(mappedBy="tabInvCategoriaProducto")
	private List<TabInvProducto> tabInvProductos;

	public TabInvCategoriaProducto() {
	}

	public Integer getInvidcatprod() {
		return this.invidcatprod;
	}

	public void setInvidcatprod(Integer invidcatprod) {
		this.invidcatprod = invidcatprod;
	}

	public String getInvnombrecatprod() {
		return this.invnombrecatprod;
	}

	public void setInvnombrecatprod(String invnombrecatprod) {
		this.invnombrecatprod = invnombrecatprod;
	}

	public List<TabInvProducto> getTabInvProductos() {
		return this.tabInvProductos;
	}

	public void setTabInvProductos(List<TabInvProducto> tabInvProductos) {
		this.tabInvProductos = tabInvProductos;
	}

	public TabInvProducto addTabInvProducto(TabInvProducto tabInvProducto) {
		getTabInvProductos().add(tabInvProducto);
		tabInvProducto.setTabInvCategoriaProducto(this);

		return tabInvProducto;
	}

	public TabInvProducto removeTabInvProducto(TabInvProducto tabInvProducto) {
		getTabInvProductos().remove(tabInvProducto);
		tabInvProducto.setTabInvCategoriaProducto(null);

		return tabInvProducto;
	}

}