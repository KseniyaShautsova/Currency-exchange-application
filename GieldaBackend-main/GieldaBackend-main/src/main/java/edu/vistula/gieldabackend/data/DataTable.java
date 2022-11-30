package edu.vistula.gieldabackend.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "data_table")
@Getter
@Setter
public class DataTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "earliest_noting")
    private Date earliestNoting;

    @OneToMany(mappedBy = "dataTable", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Stock> stocks = new ArrayList<>();
}
