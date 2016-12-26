package ru.mephi.kafedra.data.entities.components

import ru.mephi.kafedra.data.entities.SitePage

import javax.persistence.*

/**
 * @author nivanov
 * on 26.12.16.
 */
@Entity
@Inheritance
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

    @ManyToOne
    SitePage page

    @ManyToOne
    Component parent
}
