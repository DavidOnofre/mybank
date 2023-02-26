package com.kodigo.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
@Table(name = "movimiento")
public class Movimiento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMovimiento;
	
	@Column(name = "fecha", nullable = false)
	private LocalDateTime fecha;

	@Column(name = "tipo_movimiento", nullable = false, length = 10)
	private String tipoMovimiento;
	
	@Column(name = "valor", nullable = false)
	private BigDecimal valor;
	
	@Column(name = "saldo", nullable = false)
	private BigDecimal saldo;
	
	@ManyToOne
	@JoinColumn(name = "id_cuenta" , nullable = false, foreignKey = @ForeignKey(name = "fk_movimiento_cuenta"))
	private Cuenta cuenta;

}
