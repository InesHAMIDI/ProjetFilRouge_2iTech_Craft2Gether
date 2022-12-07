package fr.Craft2Gether.App.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Bloc {

    @Id
    @EqualsAndHashCode.Exclude
    private Long id;


}
