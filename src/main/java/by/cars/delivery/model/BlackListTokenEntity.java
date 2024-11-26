package by.cars.delivery.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "black_list")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlackListTokenEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        private String token;
}
