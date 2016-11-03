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
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(max = 30)
    private String relativePath;
    @NotNull
    private String relativeDirectory;
    @NotNull
    @Size(min = 1, max = 150)
    private String title;
    @Size(max = 2000)
    private String description;

    @OneToMany
    private Set<PageNode> pageNodes;

    @OneToMany
    private Set<SiteDeveloper> siteDevelopers;

    @ManyToOne
    private Department department;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    public String getRelativeDirectory() {
        return relativeDirectory;
    }

    public void setRelativeDirectory(String relativeDirectory) {
        this.relativeDirectory = relativeDirectory;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<SiteDeveloper> getSiteDevelopers() {
        return siteDevelopers;
    }

    public void setSiteDevelopers(Set<SiteDeveloper> siteDevelopers) {
        this.siteDevelopers = siteDevelopers;
    }

    public Set<PageNode> getPageNodes() {
        return pageNodes;
    }

    public void setPageNodes(Set<PageNode> pageNodes) {
        this.pageNodes = pageNodes;
    }
}
