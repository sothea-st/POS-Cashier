package com.example.pos.system.domain.general;



import jakarta.persistence.*;

@Entity
@Table(name = "pos_file")
public class FileStore {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long iden;
    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Lob
    @Column(name = "byte_data")
    @Basic(fetch = FetchType.EAGER) // Change to EAGER
    private byte[] byteData;

    public FileStore() {
    }

    public FileStore(String id ,String name, String type, byte[] data) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.byteData = data;
    }

    public String getId() {
        return id;
    }
    public void setId(String id){this.id = id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return byteData;
    }

    public void setData(byte[] data) {
        this.byteData = data;
    }


}
