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

    @Column(name = "rating")
    private Long rating;

    @Column(name = "e_rating")
    private Long eRating;

    @Column(name = "name")
    private String name;

    @Column(name = "e_name")
    private String eName;

    @Column(name = "description")
    private String description;

    @Column(name = "e_description")
    private String eDescription;

    @Column(name = "type")
    private String type;

    @Column(name = "e_type")
    private String eType;

    @Column(name = "site")
    private String site;

    @Column(name = "e_site")
    private String eSite;

    @Column(name = "reason")
    private String reason;

    @Column(name = "e_reason")
    private String eReason;

    @Column(name = "mild")
    private String mild;

    @Column(name = "e_mild")
    private String eMild;

    @Column(name = "severe")
    private String severe;

    @Column(name = "e_severe")
    private String eSevere;

    @Column(name = "preventive")
    private String preventive;

    @Column(name = "e_preventive")
    private String ePreventive;

    @Column(name = "caution")
    private String caution;

    @Column(name = "e_caution")
    private String eCaution;

    @OneToMany(mappedBy = "diseaseConst", cascade = CascadeType.MERGE)
    private List<Drug> drugs;

    @OneToMany(mappedBy = "diseaseConst", cascade = CascadeType.MERGE)
    private List<Symptoms> symptoms;

    @OneToMany(mappedBy = "diseaseConst", cascade = CascadeType.MERGE)
    private List<Disease> diseases;
}
