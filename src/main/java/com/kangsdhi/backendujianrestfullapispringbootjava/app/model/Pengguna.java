package com.kangsdhi.backendujianrestfullapispringbootjava.app.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pengguna")
public class Pengguna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NISN", nullable = true, unique = true)
    private Long NISN;

    @Column(name = "nama", nullable = false)
    private String nama;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

//    @ManyToOne
//    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "pengguna_role", joinColumns = @JoinColumn(name = "pengguna_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<RolePengguna> rolePengguna = new HashSet<RolePengguna>();

    @ManyToOne
    @JoinColumn(name = "kelas_id", referencedColumnName = "id", nullable = true)
    private Kelas kelas;

    public Pengguna() {

    }

    public Pengguna(Long NISN, String nama, String email, String password, RolePengguna rolePengguna, Kelas kelas){
        this.NISN = NISN;
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.rolePengguna.add(rolePengguna);
        this.kelas = kelas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNISN() {
        return NISN;
    }

    public void setNISN(Long NISN) {
        this.NISN = NISN;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RolePengguna> getRolePengguna() {
        return rolePengguna;
    }

    public void setRolePengguna(RolePengguna rolePengguna) {
        this.rolePengguna.add(rolePengguna);
    }

    public Kelas getKelas() {
        return kelas;
    }

    public void setKelas(Kelas kelas) {
        this.kelas = kelas;
    }

    @Override
    public String toString(){
        return "Pengguna [id="+id+", NISN="+NISN+", nama="+nama+", email="+email+", password="+password+",]";
    }
}
