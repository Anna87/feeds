package cs.digital.media.feeds.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "image")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    int length;

    String type;

    @Lob
    byte[] body;

    @OneToOne(mappedBy = "image")
    Article article;
}
