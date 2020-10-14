package cs.digital.media.feeds.model;

import lombok.Builder;

import javax.persistence.*;

@Entity
@Table(name = "image")
@Builder
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    int length;

    String type;

    @Lob
    byte[] body;

    @OneToOne(mappedBy = "image")
    Article article;
}
