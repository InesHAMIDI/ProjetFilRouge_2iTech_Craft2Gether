package fr.crafttogether;
import fr.crafttogether.repositories.BlocRepository;
import fr.crafttogether.repositories.ListeRepository;
import fr.crafttogether.repositories.RecetteRepository;
import fr.crafttogether.repositories.UserRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("unit-tests")
public class UnitTestsBase {

    @MockBean
    protected UserRepository userRepository;

    @MockBean
    protected BlocRepository blocRepository;

    @MockBean
    protected ListeRepository listeRepository;

    @MockBean
    protected RecetteRepository recetteRepository;
}
