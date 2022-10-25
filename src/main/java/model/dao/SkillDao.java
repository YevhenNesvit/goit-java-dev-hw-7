package model.dao;

import jakarta.persistence.*;

@Entity(name = "Skill")
@Table(name = "skills")
public class SkillDao {
    Integer skillId;
    String name;
    String skillLevel;

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
}
