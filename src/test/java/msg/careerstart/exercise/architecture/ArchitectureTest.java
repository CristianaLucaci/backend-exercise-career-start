package msg.careerstart.exercise.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;


public class ArchitectureTest {

    private static final String CONTROLLER_PACKAGE = "msg.careerstart.exercise.controller";
    private static final String SERVICE_PACKAGE = "msg.careerstart.exercise.service";
    private static final String REPOSITORY_PACKAGE = "msg.careerstart.exercise.repository";
    private static final String DOMAIN_PACKAGE = "msg.careerstart.exercise.domain";
    private static final String MAPPER_PACKAGE = "msg.careerstart.exercise.mapper";
    private static final String DTO_PACKAGE = "msg.careerstart.exercise.dto";


    @Test
    public void verifyControllerClasses() {
        JavaClasses classes = new ClassFileImporter().importPackages(CONTROLLER_PACKAGE);

        ArchRule rule = classes().that().resideInAPackage(CONTROLLER_PACKAGE)
                .should().haveSimpleNameEndingWith("Controller")
                .andShould().beAnnotatedWith(RestController.class);

        rule.check(classes);
    }

    @Test
    public void verifyServiceClasses() {
        JavaClasses classes = new ClassFileImporter().importPackages(SERVICE_PACKAGE);

        ArchRule rule = classes().that().resideInAPackage(SERVICE_PACKAGE)
                .and().areInterfaces()
                .should().haveSimpleNameEndingWith("Service")
                .andShould().notBeAnnotatedWith(Service.class);

        rule.check(classes);
    }

    @Test
    public void verifyServiceImplementationClasses() {
        JavaClasses classes = new ClassFileImporter().importPackages(SERVICE_PACKAGE);

        ArchRule rule = classes().that().resideInAPackage(SERVICE_PACKAGE + ".impl..")
                .should().haveSimpleNameEndingWith("Impl")
                .andShould().notBeInterfaces()
                .andShould().beAnnotatedWith(Service.class);

        rule.check(classes);
    }

    @Test
    public void verifyRepositoryClasses() {
        JavaClasses classes = new ClassFileImporter().importPackages(REPOSITORY_PACKAGE);

        ArchRule rule = classes().that().resideInAPackage(REPOSITORY_PACKAGE)
                .and().haveSimpleNameEndingWith("Repository")
                .should().beInterfaces()
                .andShould().beAnnotatedWith(Repository.class);

        rule.check(classes);
    }

    @Test
    public void verifyDtoClasses(){
        JavaClasses classes = new ClassFileImporter().importPackages(DTO_PACKAGE);

        ArchRule classRule = classes().that().resideInAPackage(DTO_PACKAGE)
                .should().haveSimpleNameEndingWith("Dto");

        classRule.check(classes);

    }

    @Test
    public void verifyMapperClasses() {
        JavaClasses classes = new ClassFileImporter().importPackages(MAPPER_PACKAGE, DOMAIN_PACKAGE, DTO_PACKAGE);

        ArchRule rule = classes().that().resideInAPackage(MAPPER_PACKAGE)
                .and().haveSimpleNameEndingWith("Mapper")
                .and().areInterfaces()
                .and().areAnnotatedWith(Mapper.class)
                .should().dependOnClassesThat().resideInAnyPackage(DOMAIN_PACKAGE, DTO_PACKAGE);

        rule.check(classes);
    }

    @Test
    public void verifyNoUsageOfDomainClassesInController() {
        JavaClasses classes = new ClassFileImporter().importPackages(DOMAIN_PACKAGE, CONTROLLER_PACKAGE);

        ArchRule rule = noClasses().that().resideInAPackage(CONTROLLER_PACKAGE)
                .should().dependOnClassesThat().resideInAPackage(DOMAIN_PACKAGE);

        rule.check(classes);
    }

    @Test
    public void verifyDomainClasses() {
        JavaClasses classes = new ClassFileImporter().importPackages(DOMAIN_PACKAGE);

        ArchRule classRule = classes().that().resideInAPackage(DOMAIN_PACKAGE).and().areNotNestedClasses()
                .should().beAnnotatedWith(Entity.class);

        classRule.check(classes);
    }

    @Test
    public void verifyEncapsulation() {
        JavaClasses classes = new ClassFileImporter().importPackages(DOMAIN_PACKAGE, DTO_PACKAGE);

        ArchRule fieldRule = fields().should().bePrivate();

        fieldRule.check(classes);
    }


}
