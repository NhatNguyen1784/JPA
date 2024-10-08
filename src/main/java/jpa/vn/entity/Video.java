package jpa.vn.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Videos")
@NamedQuery(name="Video.findAll", query="SELECT v FROM Video v")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Video implements java.io.Serializable{
    public static final long serialVersionUID = 1L;

    @Id
    @Column(name="VideoId")
    private String videoId;

    @Column(name="Active")
    private int active;

    @Column(name = "Description", columnDefinition = "NVARCHAR(5000) NULL")
    private String description;

    @Column(name="Poster", columnDefinition = "NVARCHAR(255) NULL")
    private String poster;

    @Column(name="Title", columnDefinition = "NVARCHAR(255) NULL")
    private String title;

    @Column(name="Views")
    private int views;

    @ManyToOne
    @JoinColumn(name = "CategoryId")
    private Category category;


}
