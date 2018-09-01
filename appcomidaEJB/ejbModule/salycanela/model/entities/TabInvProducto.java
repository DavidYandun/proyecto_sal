package salycanela.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the tab_inv_productos database table.
 * 
 */
@Entity
@Table(name="tab_inv_productos")
@NamedQuery(name="TabInvProducto.findAll", query="SELECT t FROM TabInvProducto t")
public class TabInvProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TAB_INV_PRODUCTOS_INVIDPROD_GENERATOR", sequenceName="TAB_INV_PRODUCTOS_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TAB_INV_PRODUCTOS_INVIDPROD_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer invidprod;

	@Column(length=200)
	private String invdescripcionprod;

	@Column(length=50)
	private String invnombreprod;

	@Column(precision=10, scale=2)
	private BigDecimal invpreciocompraprod;

	@Column(precision=10, scale=2)
	private BigDecimal invprecioventaprod;

	private Integer invstockprod;

	//bi-directional many-to-one association to TabCpDetalleFacturasProveed
	@OneToMany(mappedBy="tabInvProducto")
	private List<TabCpDetalleFacturasProveed> tabCpDetalleFacturasProveeds;

	//bi-directional many-to-one association to TabInvCategoriaProducto
	@ManyToOne
	@JoinColumn(name="invidcatprod")
	private TabInvCategoriaProducto tabInvCategoriaProducto;

	//bi-directional many-to-one association to TabInvMedida
	@ManyToOne
	@JoinColumn(name="cpidmedida")
	private TabInvMedida tabInvMedida;

	public TabInvProducto() {
	}

	public Integer getInvidprod() {
		return this.invidprod;
	}

	public void setInvidprod(Integer invidprod) {
		this.invidprod = invidprod;
	}

	public String getInvdescripcionprod() {
		return this.invdescripcionprod;
	}

	public void setInvdescripcionprod(String invdescripcionprod) {
		this.invdescripcionprod = invdescripcionprod;
	}

	public String getInvnombreprod() {
		return this.invnombreprod;
	}

	public void setInvnombreprod(String invnombreprod) {
		this.invnombreprod = invnombreprod;
	}

	public BigDecimal getInvpreciocompraprod() {
		return this.invpreciocompraprod;
	}

	public void setInvpreciocompraprod(BigDecimal invpreciocompraprod) {
		this.invpreciocompraprod = invpreciocompraprod;
	}

	public BigDecimal getInvprecioventaprod() {
		return this.invprecioventaprod;
	}

	public void setInvprecioventaprod(BigDecimal invprecioventaprod) {
		this.invprecioventaprod = invprecioventaprod;
	}

	public Integer getInvstockprod() {
		return this.invstockprod;
	}

	public void setInvstockprod(Integer invstockprod) {
		this.invstockprod = invstockprod;
	}

	public List<TabCpDetalleFacturasProveed> getTabCpDetalleFacturasProveeds() {
		return this.tabCpDetalleFacturasProveeds;
	}

	public void setTabCpDetalleFacturasProveeds(List<TabCpDetalleFacturasProveed> tabCpDetalleFacturasProveeds) {
		this.tabCpDetalleFacturasProveeds = tabCpDetalleFacturasProveeds;
	}

	public TabCpDetalleFacturasProveed addTabCpDetalleFacturasProveed(TabCpDetalleFacturasProveed tabCpDetalleFacturasProveed) {
		getTabCpDetalleFacturasProveeds().add(tabCpDetalleFacturasProveed);
		tabCpDetalleFacturasProveed.setTabInvProducto(this);

		return tabCpDetalleFacturasProveed;
	}

	public TabCpDetalleFacturasProveed removeTabCpDetalleFacturasProveed(TabCpDetalleFacturasProveed tabCpDetalleFacturasProveed) {
		getTabCpDetalleFacturasProveeds().remove(tabCpDetalleFacturasProveed);
		tabCpDetalleFacturasProveed.setTabInvProducto(null);

		return tabCpDetalleFacturasProveed;
	}

	public TabInvCategoriaProducto getTabInvCategoriaProducto() {
		return this.tabInvCategoriaProducto;
	}

	public void setTabInvCategoriaProducto(TabInvCategoriaProducto tabInvCategoriaProducto) {
		this.tabInvCategoriaProducto = tabInvCategoriaProducto;
	}

	public TabInvMedida getTabInvMedida() {
		return this.tabInvMedida;
	}

	public void setTabInvMedida(TabInvMedida tabInvMedida) {
		this.tabInvMedida = tabInvMedida;
	}

}