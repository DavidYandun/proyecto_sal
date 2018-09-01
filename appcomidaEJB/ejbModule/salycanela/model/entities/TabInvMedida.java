package salycanela.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tab_inv_medidas database table.
 * 
 */
@Entity
@Table(name="tab_inv_medidas")
@NamedQuery(name="TabInvMedida.findAll", query="SELECT t FROM TabInvMedida t")
public class TabInvMedida implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TAB_INV_MEDIDAS_CPIDMEDIDA_GENERATOR", sequenceName="TAB_INV_MEDIDAS_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TAB_INV_MEDIDAS_CPIDMEDIDA_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer cpidmedida;

	@Column(nullable=false, length=50)
	private String cpnombremedida;

	//bi-directional many-to-one association to TabInvProducto
	@OneToMany(mappedBy="tabInvMedida")
	private List<TabInvProducto> tabInvProductos;

	public TabInvMedida() {
	}

	public Integer getCpidmedida() {
		return this.cpidmedida;
	}

	public void setCpidmedida(Integer cpidmedida) {
		this.cpidmedida = cpidmedida;
	}

	public String getCpnombremedida() {
		return this.cpnombremedida;
	}

	public void setCpnombremedida(String cpnombremedida) {
		this.cpnombremedida = cpnombremedida;
	}

	public List<TabInvProducto> getTabInvProductos() {
		return this.tabInvProductos;
	}

	public void setTabInvProductos(List<TabInvProducto> tabInvProductos) {
		this.tabInvProductos = tabInvProductos;
	}

	public TabInvProducto addTabInvProducto(TabInvProducto tabInvProducto) {
		getTabInvProductos().add(tabInvProducto);
		tabInvProducto.setTabInvMedida(this);

		return tabInvProducto;
	}

	public TabInvProducto removeTabInvProducto(TabInvProducto tabInvProducto) {
		getTabInvProductos().remove(tabInvProducto);
		tabInvProducto.setTabInvMedida(null);

		return tabInvProducto;
	}

}