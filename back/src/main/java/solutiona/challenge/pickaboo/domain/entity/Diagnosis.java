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
import java.time.LocalDate;
import java.time.LocalDateTime;
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
@Table(name = "diagnosis")
public class Diagnosis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "part")
    private String part;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "description")
    private String description;

    @Column(name = "ko_custom_description")
    private String koCustomDescription;

    @Column(name = "en_custom_description")
    private String enCustomDescription;


    @Column(name = "create_at")
    private LocalDate createAt;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "diagnosis", cascade = CascadeType.MERGE)
    private List<Disease> diseases;

    @OneToMany(mappedBy = "diagnosis", cascade = CascadeType.MERGE)
    private List<PdfUrl> pdfUrls;

    @Builder
    public Diagnosis(String part, String imageUrl, String description, String koCustomDescription, String enCustomDescription, User user) {
        this.part = part;
        this.imageUrl = imageUrl;
        this.description = description;
        this.koCustomDescription = koCustomDescription;
        this.enCustomDescription = enCustomDescription;
        this.user = user;
        this.createAt = LocalDate.now();
    }

}
