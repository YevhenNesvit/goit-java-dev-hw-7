package model.dao;

import jakarta.persistence.*;

import java.util.Set;

@Entity(name = "Skill")
@Table(name = "skills")
public class SkillDao {
    private Integer skillId;
    private String name;
    private String skillLevel;
    private Set<DeveloperDao> developers;

    public SkillDao() {

    }

    @Id
    @Column(name = "skill_id")
    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "skill_level")
    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }

    @ManyToMany(mappedBy = "skills")
    public Set<DeveloperDao> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<DeveloperDao> developers) {
        this.developers = developers;
    }
}
