package com.kodigo.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cuenta")
public class Cuenta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCuenta;

	@Column(name = "numero_cuenta", nullable = false, length = 10)
	private String numeroCuenta;

	@Column(name = "tipo_cuenta", nullable = false, length = 3)
	private String tipoCuenta;
	
	@Column(name = "saldo_inicial", nullable = false)
	private BigDecimal saldoInicial;
	
	@Column(name = "saldo_disponible", nullable = false)
	private BigDecimal saldoDisponible;
	
	@Column(name = "estado", nullable = false)
	private Boolean estado;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente" , nullable = false, foreignKey = @ForeignKey(name = "fk_cuenta_cliente"))
	private Cliente cliente;

}
