package ru.mephi.kafedra.domain.data.entities

import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * @author nivanov
 * on 23.04.17.
 */

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = ["site_id", "relative_path"]))
class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @NotNull
    @Size(min = 1, max = 100)
    String title

    @Size(max = 500)
    String description

    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "relative_path")
    String relativePath

    @ManyToOne
    Page parentPage

    @ManyToOne
    Site site

    @NotNull
    @OneToMany
    Set<JsEventListener> jsEventListeners

    @NotNull
    @OneToMany
    Set<Attribute> attributes

    @OneToOne
    @NotNull
    Component rootComponent
}
