package fr.diginamic.formation.security.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/** Classe gérant la structure d'une utilisateur */
@Entity
@Table(name = "users")
public class User implements UserDetails {

    /** Variable auto-générée en base de donnnée afin d'identifier un utilisateur */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** Variable contenant le nom et prénom d'un utilisateur */
    @Column(nullable = false)
    private String fullName;

    /** Variable contenant le mail d'un utilisateur */
    @Column(unique = true, length = 100, nullable = false)
    private String email;

    /** Variable contenant le mot de passe de l'utilisateur */
    @Column(nullable = false)
    private String password;

    /** Variable contenant la date de création de l'utilisateur */
    @CreationTimestamp
    @Column(name = "created_ad")
    private Date createdAt;

    /** Variable contenant la date de mise à jour de l'utilisateur */
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    /** Constructeur vide pour jpa
     *
     */
    public User() {
    }

    /** Constructeur contenant les parametre pour créer un utilisateur
     *
     * @param fullName
     * @param email
     * @param password
     */
    public User(String fullName, String email, String password) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String getUsername() {
        return "";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return "";
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
