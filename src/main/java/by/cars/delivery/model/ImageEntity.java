package by.cars.delivery.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "image")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(name = "path")
        private String pathToFile;

        @ManyToMany(mappedBy = "images")
        private List<CarEntity> cars = new ArrayList<>();

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "event_id", referencedColumnName = "id")
        private EventEntity event;
}
