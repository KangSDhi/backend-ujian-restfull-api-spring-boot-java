package com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.pengguna;

import com.kangsdhi.backendujianrestfullapispringbootjava.app.repository.role.RolePengguna;
import jakarta.persistence.*;

@Entity
@Table(name = "pengguna")
public class Pengguna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NISN", nullable = true, unique = true)
    private Integer NISN;

    @Column(name = "nama", nullable = false)
    private String nama;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

//    @Column(name = "role_id", nullable = false)
//    private Long role_id;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private RolePengguna rolePengguna;

    @Column(name = "kelas_id", nullable = true)
    private Long kelas_id;

    public Pengguna() {

    }

    public Pengguna(Integer NISN, String nama, String email, String password, RolePengguna rolePengguna, Long kelas_id){
        this.NISN = NISN;
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.rolePengguna = rolePengguna;
        this.kelas_id = kelas_id;
    }

    public Long getId() {
        return id;
    }

    public Integer getNISN() {
        return NISN;
    }

    public void setNISN(Integer NISN) {
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

    public RolePengguna getRolePengguna() {
        return rolePengguna;
    }

    public void setRolePengguna(RolePengguna rolePengguna) {
        this.rolePengguna = rolePengguna;
    }

    public Long getKelas_id() {
        return kelas_id;
    }

    public void setKelas_id(Long kelas_id) {
        this.kelas_id = kelas_id;
    }

    @Override
    public String toString(){
        return "Pengguna [id="+id+", NISN="+NISN+", nama="+nama+", email="+email+", password="+password+", role_id="+rolePengguna.getId().toString()+", kelas_id="+kelas_id+"]";
    }
}
