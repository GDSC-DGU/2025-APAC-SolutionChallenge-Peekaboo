package solutiona.challenge.pickaboo.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "disease")
public class Disease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ranking")
    private Long ranking;

    @Column(name = "percnet")
    private Long percent;

    @OneToMany(mappedBy = "disease", cascade = CascadeType.MERGE)
    private List<Diagnosis> diagnoses;

    @OneToMany(mappedBy = "disease", cascade = CascadeType.MERGE)
    private List<DiseaseConst> diseaseConsts;


}
