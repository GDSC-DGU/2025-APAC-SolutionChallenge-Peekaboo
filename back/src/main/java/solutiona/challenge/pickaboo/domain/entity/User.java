package solutiona.challenge.pickaboo.domain.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;
import solutiona.challenge.pickaboo.domain.type.EProvider;
import solutiona.challenge.pickaboo.domain.type.ERole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "serial_id", nullable = false, unique = true)
    private String serialId;

    @Column(name = "nickname", length = 20)
    private String nickname;

    @Column(name =  "loginId")
    private String loginId;

    @Column(name = "user_role", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private ERole role;

    @Column(name = "login_provider", nullable = false)
    @Enumerated(EnumType.STRING)
    private EProvider provider;

    @Column(name = "profile_image_url", length = 2048)
    private String profileImageUrl;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "fcm_token")
    private String fcmToken;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "profile")
    private String profile;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "location")
    private String location;

    @Column(name = "language")
    private String language;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "gender")
    private String  gender;


    @Column(name = "skin_type")
    private String skinType;

    @Column(name = "blood_type")
    private String bloodType;


    //--------------------------------------------------

    @OneToMany(mappedBy = "user", cascade = CascadeType.MERGE)
    private List<Allergy> allergies;

    @OneToMany(mappedBy = "user", cascade = CascadeType.MERGE)
    private List<OnboardingDisease> onboardingDiseases;

    @OneToMany(mappedBy = "user", cascade = CascadeType.MERGE)
    private List<Diagnosis> diagnoses;

    //--------------------------------------------------

    @Builder
    public User(String serialId, String nickname, ERole role, EProvider provider, String email, String password, String profile, String fcmToken) {
        this.serialId = serialId;
        this.nickname = nickname;
        this.role = role;
        this.provider = provider;
        this.fcmToken = fcmToken;
        this.profileImageUrl = null;
        this.refreshToken = null;
        this.email = email;
        this.password = password;
        this.profile = profile;
        this.createdAt = LocalDateTime.now();
    }

    public void updateUser(String location, String language, LocalDate birth, String gender, String skinType, String bloodType) {
        this.location = location;
        this.language = language;
        this.birth = birth;
        this.gender = gender;
        this.skinType = skinType;
        this.bloodType = bloodType;
    }

    public void updateLanguage(String language) {
        this.language = language;
    }

}

