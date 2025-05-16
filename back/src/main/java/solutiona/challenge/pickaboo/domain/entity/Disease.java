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
import lombok.Builder;
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

    @Column(name = "percent")
    private Long percent;


    @JoinColumn(name = "diagnosis_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Diagnosis diagnosis;

    @JoinColumn(name = "disease_const_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private DiseaseConst diseaseConst;


    @Builder
    public Disease(Long ranking, Long percent, Diagnosis diagnosis, DiseaseConst diseaseConst) {
        this.ranking = ranking;
        this.percent = percent;
        this.diagnosis = diagnosis;
        this.diseaseConst = diseaseConst;
    }

}
