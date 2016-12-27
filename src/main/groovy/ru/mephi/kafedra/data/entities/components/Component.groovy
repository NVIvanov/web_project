package ru.mephi.kafedra.data.entities.components

import ru.mephi.kafedra.data.entities.SitePage

import javax.persistence.*
import javax.validation.constraints.NotNull

/**
 * @author nivanov
 * on 26.12.16.
 */
@Entity
class Component {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id
    String width, height, margin, padding, position
    Type type

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "component_id")
    Set<JsEventListener> jsEventListeners

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "component_id")
    Set<Component> children

    @NotNull
    @ManyToOne
    SitePage page

    @ManyToOne
    Component parent

    String src
    Integer borderRadius
    Integer fontSize
    String fontFamily
    String colorHex
    Boolean isHref = false
    String text
}
