package cs.digital.media.feeds.model;

import lombok.Builder;
import lombok.Value;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "article")
@Value
@Builder(toBuilder = true)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String title;

    @Column(columnDefinition = "TEXT")
    String description;

    Date publicationDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    Image image;
}
