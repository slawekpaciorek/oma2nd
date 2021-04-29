package com.oma.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@ConfigurationProperties(prefix = "file")
@Entity
@Table(name = "merchant_documents")
public
class DocumnentStorageProperties {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "Nazwa")
        private Integer nazwa;
    @Column(name = "Cena netto")
        private Integer cenaNetto;
    @Column(name = "Indeks katalogowy")
        private String indexKatalogowy;
    @Column(name = "Indeks handlowy")
        private String indeksHandlowy;
    @Column(name = "Kategoria")
        private String kategoria;
}
