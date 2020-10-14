package cs.digital.media.feeds.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ARTICLE", uniqueConstraints = @UniqueConstraint(columnNames = {"title", "publicationDate"}))
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String title;

    @Column(columnDefinition = "TEXT")
    String description;

    Date publicationDate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    Image image;

}
