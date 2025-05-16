package solutiona.challenge.pickaboo.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import solutiona.challenge.pickaboo.domain.entity.PdfUrl;

@Repository
public interface PdfUrlJpaRepository extends JpaRepository<PdfUrl, Long> {
}
