package festival.server.univ.univ.repository;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
public class Festival {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long festivalId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private UnivEntity univ;

    @Column(length = 100)
    private String siteName; // 인스타
    @Column(length = 200)
    private String url;
    private String content;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime urlDate;

    @CreationTimestamp
    private Timestamp editDate;

}
