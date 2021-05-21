package ar.edu.unju.fi.tp4.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import static java.time.temporal.ChronoUnit.DAYS;

import java.time.Duration;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;


@Entity
@Table(name = "CLIENTES")
@Component
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cli_codigo")
	private int codigo;
	@Column(name = "cli_tipoDocumento")
	private String tipoDocumento;
	@Column(name = "cli_nroDocumento")
	private int nroDocumento;
	@Column(name = "cli_nombreApellido")
	private String nombreApellido;
	@Column(name = "cli_mail")
	private String mail;
	@Column(name = "cli_password")
	private String password;
	@Column(name = "cli_fechaNacimiento")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate fechaNacimiento;
	@Column(name = "cli_edad")
	private int edad;
	@Column(name = "cli_codigoAreaTelefono")
	private int codigoAreaTelefono;
	@Column(name = "cli_nroTelefono")
	private int nroTelefono;
	@Column(name = "cli_fechaUltimaCompra")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate fechaUltimaCompra;
	
	
	public Cliente() {
		
	}


	public Cliente(String tipoDocumento, int nroDocumento, String nombreApellido, String mail, String password,
			LocalDate fechaNacimiento, int edad, int codigoAreaTelefono, int nroTelefono, LocalDate fechaUltimaCompra) {
		super();
		this.tipoDocumento = tipoDocumento;
		this.nroDocumento = nroDocumento;
		this.nombreApellido = nombreApellido;
		this.mail = mail;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
		this.edad = edad;
		this.codigoAreaTelefono = codigoAreaTelefono;
		this.nroTelefono = nroTelefono;
		this.fechaUltimaCompra = fechaUltimaCompra;
	}

	

	public int getCodigo() {
		return codigo;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public String getTipoDocumento() {
		return tipoDocumento;
	}


	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}


	public int getNroDocumento() {
		return nroDocumento;
	}


	public void setNroDocumento(int nroDocumento) {
		this.nroDocumento = nroDocumento;
	}


	public String getNombreApellido() {
		return nombreApellido;
	}


	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	public int getEdad() {
		LocalDate today = LocalDate.now();
		Period per=Period.between(fechaNacimiento, today);
		edad=per.getYears();
		return edad;
	}


	public void setEdad(int edad) {
		this.edad = edad;
	}


	public int getCodigoAreaTelefono() {
		return codigoAreaTelefono;
	}


	public void setCodigoAreaTelefono(int codigoAreaTelefono) {
		this.codigoAreaTelefono = codigoAreaTelefono;
	}


	public int getNroTelefono() {
		return nroTelefono;
	}


	public void setNroTelefono(int nroTelefono) {
		this.nroTelefono = nroTelefono;
	}


	public LocalDate getFechaUltimaCompra() {
		return fechaUltimaCompra;
	}


	public void setFechaUltimaCompra(LocalDate fechaUltimaCompra) {
		this.fechaUltimaCompra = fechaUltimaCompra;
	}


	@Override
	public String toString() {
		return "Cliente [tipoDocumento=" + tipoDocumento + ", nroDocumento=" + nroDocumento + ", nombreApellido="
				+ nombreApellido + ", mail=" + mail + ", password=" + password + ", fechaNacimiento=" + fechaNacimiento
				+ ", edad=" + edad + ", codigoAreaTelefono=" + codigoAreaTelefono + ", nroTelefono=" + nroTelefono
				+ ", fechaUltimaCompra=" + fechaUltimaCompra + "]";
	}

   /**
    * 
    Obtiene tiempo desde la ultima fecha de compra
    */
	
    public String obtenerUltimaCompra() {
    	String fecha="";
		LocalDate today = LocalDate.now();
		Period per =Period.between(fechaUltimaCompra, today);
		fecha="Anios: "+per.getYears()+'|'+" Meses: "+per.getMonths()+'|'+" Dias: "+per.getDays();
		return fecha;
    }
	/**
	 * Obtiene dias transcurridos
	 * @return
	 */
    
    public int obtenerFechaNacimiento() {
    	
    	// Usando ChronoUnit
    	LocalDate today = LocalDate.now();
        int dias = (int) DAYS.between(fechaNacimiento, today); 	
    	return dias;
    }
    /**
     * Obtiene tiempo para proximo cummpleaños
     * @return
     */
	
     public String obtenerCumpleanio() {
		
		String fecha="";
		
		LocalDate hoy = LocalDate.now();
		int anio = 0;
		
		if(hoy.getMonthValue()<fechaNacimiento.getMonthValue()) {
			anio=hoy.getYear();
		}else {
			if(hoy.getMonthValue()==fechaNacimiento.getMonthValue()) {
				if(hoy.getDayOfMonth()<fechaNacimiento.getDayOfMonth()) {
					anio=hoy.getYear();
				}
			}else {
				anio=hoy.getYear()+1;
			}
		}
		
		
        LocalDate proximoCumple=LocalDate.of(anio, fechaNacimiento.getMonth(), fechaNacimiento.getDayOfMonth());
		
	    Period periodo =Period.between(hoy, proximoCumple);
		
		fecha="Anios: "+periodo.getYears()+'|'+" Meses: "+periodo.getMonths()+'|'+" Dias: "+periodo.getDays();
		
		LocalDateTime horaHoy=LocalDateTime.now();
		
		LocalDateTime horasProximoCumple=LocalDateTime.of(anio, fechaNacimiento.getMonth(), fechaNacimiento.getDayOfMonth(), 0, 0, 0);
		
		Duration duracion=Duration.between(horaHoy, horasProximoCumple);
		
		fecha=fecha+'-'+" Hora: "+duracion.toHoursPart()+'|'+" Min: "+duracion.toMinutesPart()+'|'+" Seg: "+duracion.toSecondsPart();
		
		return fecha;
		
	}
	
}
