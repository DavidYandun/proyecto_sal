package salycanela.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the tab_cp_detalle_facturas_proveed database table.
 * 
 */
@Entity
@Table(name="tab_cp_detalle_facturas_proveed")
@NamedQuery(name="TabCpDetalleFacturasProveed.findAll", query="SELECT t FROM TabCpDetalleFacturasProveed t")
public class TabCpDetalleFacturasProveed implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TAB_CP_DETALLE_FACTURAS_PROVEED_CPIDDETALLEFACPROV_GENERATOR", sequenceName="TAB_CP_DETALLE_FACTURAS_PROVEED_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TAB_CP_DETALLE_FACTURAS_PROVEED_CPIDDETALLEFACPROV_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer cpiddetallefacprov;

	private Integer cpcantidaddetallefacprov;

	@Column(precision=10, scale=2)
	private BigDecimal cpdescuentodetallefacprov;

	private Integer cpidmedida;

	private Boolean cpivadetallefacprov;

	@Column(precision=10, scale=2)
	private BigDecimal cptotaldetallefacprov;

	@Column(precision=10, scale=2)
	private BigDecimal cpunitariodetallefacprov;

	//bi-directional many-to-one association to TabCpFacturasProveedore
	@ManyToOne
	@JoinColumn(name="cpidfacturaprov")
	private TabCpFacturasProveedore tabCpFacturasProveedore;

	//bi-directional many-to-one association to TabInvProducto
	@ManyToOne
	@JoinColumn(name="cpinvidproducto")
	private TabInvProducto tabInvProducto;

	public TabCpDetalleFacturasProveed() {
	}

	public Integer getCpiddetallefacprov() {
		return this.cpiddetallefacprov;
	}

	public void setCpiddetallefacprov(Integer cpiddetallefacprov) {
		this.cpiddetallefacprov = cpiddetallefacprov;
	}

	public Integer getCpcantidaddetallefacprov() {
		return this.cpcantidaddetallefacprov;
	}

	public void setCpcantidaddetallefacprov(Integer cpcantidaddetallefacprov) {
		this.cpcantidaddetallefacprov = cpcantidaddetallefacprov;
	}

	public BigDecimal getCpdescuentodetallefacprov() {
		return this.cpdescuentodetallefacprov;
	}

	public void setCpdescuentodetallefacprov(BigDecimal cpdescuentodetallefacprov) {
		this.cpdescuentodetallefacprov = cpdescuentodetallefacprov;
	}

	public Integer getCpidmedida() {
		return this.cpidmedida;
	}

	public void setCpidmedida(Integer cpidmedida) {
		this.cpidmedida = cpidmedida;
	}

	public Boolean getCpivadetallefacprov() {
		return this.cpivadetallefacprov;
	}

	public void setCpivadetallefacprov(Boolean cpivadetallefacprov) {
		this.cpivadetallefacprov = cpivadetallefacprov;
	}

	public BigDecimal getCptotaldetallefacprov() {
		return this.cptotaldetallefacprov;
	}

	public void setCptotaldetallefacprov(BigDecimal cptotaldetallefacprov) {
		this.cptotaldetallefacprov = cptotaldetallefacprov;
	}

	public BigDecimal getCpunitariodetallefacprov() {
		return this.cpunitariodetallefacprov;
	}

	public void setCpunitariodetallefacprov(BigDecimal cpunitariodetallefacprov) {
		this.cpunitariodetallefacprov = cpunitariodetallefacprov;
	}

	public TabCpFacturasProveedore getTabCpFacturasProveedore() {
		return this.tabCpFacturasProveedore;
	}

	public void setTabCpFacturasProveedore(TabCpFacturasProveedore tabCpFacturasProveedore) {
		this.tabCpFacturasProveedore = tabCpFacturasProveedore;
	}

	public TabInvProducto getTabInvProducto() {
		return this.tabInvProducto;
	}

	public void setTabInvProducto(TabInvProducto tabInvProducto) {
		this.tabInvProducto = tabInvProducto;
	}

}