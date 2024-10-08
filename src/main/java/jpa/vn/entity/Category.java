package jpa.vn.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category  implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryId")
    private int categoryid;

    @Column(name = "CategoryName", columnDefinition = "NVARCHAR(50) NOT NULL")
    @NotEmpty(message = "Khong duoc bo trong")
    private String categoryname;

    @Column(name = "Images", columnDefinition = "NVARCHAR(500) NULL")
    private String image;

    private int status;

    @OneToMany(mappedBy = "category")
    private List<Video> Videos;

    public Video addVideo(Video video) {
        getVideos().add(video);
        video.setCategory(this);
        return video;
    }

    public Video removeVideo(Video video) {
        getVideos().remove(video);
        video.setCategory(null);
        video.setCategory(null);
        return video;
    }
}
