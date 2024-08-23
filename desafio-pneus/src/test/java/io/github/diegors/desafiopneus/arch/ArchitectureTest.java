package io.github.diegors.desafiopneus.arch;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import jakarta.persistence.Entity;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

class ArchitectureTest {

    @Test
    void testControllersClassesIfPatternAreCorrect() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("io.github.diegors.desafiopneus.adapter.controller");
        ArchRule rule = classes().that().areAnnotatedWith(RestController.class)
                .and().haveSimpleNameNotStartingWith("Generic")
                .should().beAnnotatedWith(RequestMapping.class)
                .andShould().bePublic()
                .andShould().haveSimpleNameEndingWith("Controller");

        rule.check(importedClasses);
    }

    @Test
    void testServiceClassesIfPatternAreCorrect() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("io.github.diegors.desafiopneus.application.service");
        ArchRule rule = classes().that().areAnnotatedWith(Service.class)
                .and().haveSimpleNameNotStartingWith("Generic")
                .should().bePublic()
                .andShould().haveSimpleNameEndingWith("Service");

        rule.check(importedClasses);
    }

    @Test
    void testModelClassesIfPatternAreCorrect() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("io.github.diegors.desafiopneus.domain.model");
        ArchRule rule = classes().that().areAnnotatedWith(Entity.class)
                .should().bePublic();

        rule.check(importedClasses);
    }

    @Test
    void testRepositoriesClassesIfPatternAreCorrect() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("io.github.diegors.desafiopneus.adapter.repository");
        ArchRule rule = classes()
                .should().beAnnotatedWith(Repository.class)
                .andShould().bePublic()
                .andShould().haveSimpleNameEndingWith("Repository");

        rule.check(importedClasses);
    }
}
