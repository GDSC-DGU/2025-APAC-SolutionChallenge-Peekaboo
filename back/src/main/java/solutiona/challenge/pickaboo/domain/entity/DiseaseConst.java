package solutiona.challenge.pickaboo.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Table(name = "disease_const")
public class DiseaseConst {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ranking")
    private Long ranking;

    @Column(name = "rating")
    private Long rating;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private String type;

    @Column(name = "site")
    private String site;

    @Column(name = "reason")
    private String reason;

    @Column(name = "mild")
    private String mild;

    @Column(name = "severe")
    private String severe;

    @Column(name = "preventive")
    private String preventive;

    @Column(name = "caution")
    private String caution;


    @OneToMany(mappedBy = "diseaseConst", cascade = CascadeType.MERGE)
    private List<Drug> drugs;

    @OneToMany(mappedBy = "diseaseConst", cascade = CascadeType.MERGE)
    private List<Symptoms> symptoms;

    @JoinColumn(name = "disease_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Disease disease;
}
