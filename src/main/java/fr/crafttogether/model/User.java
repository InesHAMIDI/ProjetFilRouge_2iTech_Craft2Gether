package fr.crafttogether.model;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructorpublic class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer num;
	@NonNull
	String nom;
}
