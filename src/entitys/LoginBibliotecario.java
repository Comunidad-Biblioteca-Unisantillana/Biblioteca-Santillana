/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitys;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Storkolm
 */
@Entity
@Table(name = "login_bibliotecario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LoginBibliotecario.findAll", query = "SELECT l FROM LoginBibliotecario l")
    , @NamedQuery(name = "LoginBibliotecario.findByCodloginbibliotecario", query = "SELECT l FROM LoginBibliotecario l WHERE l.codloginbibliotecario = :codloginbibliotecario")
    , @NamedQuery(name = "LoginBibliotecario.findByCodpassword", query = "SELECT l FROM LoginBibliotecario l WHERE l.codpassword = :codpassword")})
public class LoginBibliotecario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codloginbibliotecario")
    private String codloginbibliotecario;
    @Basic(optional = false)
    @Column(name = "codpassword")
    private String codpassword;
    @JoinColumn(name = "idbibliotecario", referencedColumnName = "idbibliotecario")
    @ManyToOne(optional = false)
    private Bibliotecario_1 idbibliotecario;

    public LoginBibliotecario() {
    }

    public LoginBibliotecario(String codloginbibliotecario) {
        this.codloginbibliotecario = codloginbibliotecario;
    }

    public LoginBibliotecario(String codloginbibliotecario, String codpassword) {
        this.codloginbibliotecario = codloginbibliotecario;
        this.codpassword = codpassword;
    }

    public String getCodloginbibliotecario() {
        return codloginbibliotecario;
    }

    public void setCodloginbibliotecario(String codloginbibliotecario) {
        this.codloginbibliotecario = codloginbibliotecario;
    }

    public String getCodpassword() {
        return codpassword;
    }

    public void setCodpassword(String codpassword) {
        this.codpassword = codpassword;
    }

    public Bibliotecario_1 getIdbibliotecario() {
        return idbibliotecario;
    }

    public void setIdbibliotecario(Bibliotecario_1 idbibliotecario) {
        this.idbibliotecario = idbibliotecario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codloginbibliotecario != null ? codloginbibliotecario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoginBibliotecario)) {
            return false;
        }
        LoginBibliotecario other = (LoginBibliotecario) object;
        if ((this.codloginbibliotecario == null && other.codloginbibliotecario != null) || (this.codloginbibliotecario != null && !this.codloginbibliotecario.equals(other.codloginbibliotecario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitysRecursos.LoginBibliotecario[ codloginbibliotecario=" + codloginbibliotecario + " ]";
    }
    
}
