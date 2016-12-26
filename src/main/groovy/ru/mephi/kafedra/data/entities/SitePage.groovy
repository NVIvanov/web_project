package ru.mephi.kafedra.data.entities

import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * @author nivanov
 * on 26.12.16.
 */
@Entity
class SitePage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id
    @NotNull
    @Size(min = 1, max = 100)
    String relativePath

    @ManyToOne
    SitePage parentPage

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "page_id")
    Set<SitePage> children

}
