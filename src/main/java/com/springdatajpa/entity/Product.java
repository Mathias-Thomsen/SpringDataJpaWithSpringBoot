package com.springdatajpa.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

//Annotations for jpa there is a standard for ORM (object-relational mapping)
@Entity


//Here we use "Lombok" there is a dependency in our pom file. We don´t need to write all boiler-code like getter and setter for all the entities.
//Just annotate like this.
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
//Change properties in the tabel.
@Table(

        name = "products", //Change the table name. Hibernate use the class name by default.
        schema = "ecommerce", //Witch schema do the table use.
        uniqueConstraints = { //Add unique constrains for the attributes.
                @UniqueConstraint(
                        name ="sku_unique",
                        columnNames = "stock_keeping_unit"
                )
        }
)

public class Product {


    //Primary Key -- ID
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE, //Use the sequence strategy as id generator.
            generator = "product_generator" //Give the generator a name.
    )
    @SequenceGenerator(
            name = "product_generator", //Specify the name of the generator
            sequenceName = "product_sequence_name", //Hibernate give the table a name by default, but you can change it like this.
            allocationSize = 1 //By default, 50. But it normal to use 1.
    )
    private Long id;


    @Column(name = "stock_keeping_unit", nullable = false) //Change the colum name and make it nullable.
    private String sku;

    @Column(nullable = false)
    private String name;

    private String description;
    private BigDecimal price;
    private boolean active;
    private String imageUrl;

    @CreationTimestamp //Hibernate automatic create a timestamp in the database
    private LocalDateTime dateCreated;

    @UpdateTimestamp //Hibernate automatic update this timestamp if there has been implemented a change in the database.
    private LocalDateTime lastUpdated;


}
