package ru.web.project.model.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * @author nivanov
 *         on 03.11.16.
 */

@Entity
public class PageNode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(max = 30)
    private String relativeToParent;
    @NotNull
    @Size(min = 1, max = 150)
    private String title;
    @Size(max = 2000)
    private String description;
    @ManyToOne(optional = false)
    private Site site;
    @OneToMany(orphanRemoval = true)
    private Set<PageNode> childNodes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRelativeToParent() {
        return relativeToParent;
    }

    public void setRelativeToParent(String relativeToParent) {
        this.relativeToParent = relativeToParent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<PageNode> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(Set<PageNode> childNodes) {
        this.childNodes = childNodes;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }
}
